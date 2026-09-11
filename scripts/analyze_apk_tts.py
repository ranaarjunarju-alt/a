"""Evidence-only APK inventory and TTS bytecode references; never runs APK code."""
import hashlib
import json
import re
import sys
import zipfile
from collections import Counter
from pathlib import Path

EXPECTED = '46bb98a05a6c3be31a4557e08668a249fa759b9d140eedadbed38c3b4738452f'
TTS = re.compile(r'texttospeech|text_to_speech|text-to-speech|(?:^|[/_.;$])tts(?:[/_.;$]|$)|synthesi[sz]|speak|utterance|phonem|vocoder|piper|kokoro|sherpa|espeak|tacotron|fastspeech|silero|bark|coqui|elevenlabs', re.I)
MODEL = re.compile(r'\.(onnx|ort|tflite|pb|pt|pth|bin|safetensors|gguf|ncnn|vocab|model)$|tokenizer|vocab|voices|phonem|lexicon', re.I)


def main():
    from loguru import logger
    logger.remove()
    from androguard.core.apk import APK
    from androguard.core.dex import DEX
    source, output = Path(sys.argv[1]), Path(sys.argv[2])
    output.mkdir(parents=True, exist_ok=True)
    digest = hashlib.file_digest(source.open('rb'), 'sha256').hexdigest()
    if digest != EXPECTED:
        raise SystemExit(f'Hash mismatch: expected {EXPECTED}, got {digest}; stopping')
    if not zipfile.is_zipfile(source):
        raise SystemExit('Not an APK ZIP container')
    apk = APK(str(source))
    rows = []
    with zipfile.ZipFile(source) as z:
        for info in z.infolist():
            rows.append({'path': info.filename, 'bytes': info.file_size, 'compressed_bytes': info.compress_size})
    (output / 'inventory.json').write_text(json.dumps(rows, indent=2))
    manifest = {'package': apk.get_package(), 'version_name': apk.get_androidversion_name(),
                'version_code': apk.get_androidversion_code(), 'min_sdk': apk.get_min_sdk_version(),
                'target_sdk': apk.get_target_sdk_version(), 'permissions': apk.get_permissions(),
                'activities': apk.get_activities(), 'services': apk.get_services(),
                'receivers': apk.get_receivers(), 'providers': apk.get_providers()}
    (output / 'manifest-summary.json').write_text(json.dumps(manifest, indent=2))
    # Preserve manifest as evidence; do not dump arbitrary DEX strings or credentials.
    from lxml import etree
    (output / 'AndroidManifest.xml').write_bytes(etree.tostring(apk.get_android_manifest_xml(), pretty_print=True))
    refs = []
    classes = set()
    hosts = Counter()
    dex_count = 0
    for blob in apk.get_all_dex():
        dex_count += 1
        dex = DEX(blob)
        for text in dex.get_strings():
            # Only public-looking hostname candidates, never paths, queries or userinfo.
            for host in re.findall(r'https?://([A-Za-z0-9.-]+)(?=[:/\s]|$)', text):
                if '.' in host:
                    hosts[host.lower()] += 1
        for cls in dex.get_classes():
            if TTS.search(cls.get_name()):
                classes.add(cls.get_name())
            for method in cls.get_methods():
                if not method.get_code():
                    continue
                caller = f'{cls.get_name()}->{method.get_name()}{method.get_descriptor()}'
                for ins in method.get_instructions():
                    if not ins.get_name().startswith('invoke-'):
                        continue
                    operand = ins.get_output()
                    if TTS.search(operand):
                        refs.append({'dex': dex_count, 'caller': caller, 'instruction': ins.get_name(), 'target': operand})
    (output / 'tts-call-references.json').write_text(json.dumps(refs, indent=2))
    (output / 'tts-class-candidates.json').write_text(json.dumps(sorted(classes), indent=2))
    (output / 'hostname-candidates.json').write_text(json.dumps(hosts, indent=2))
    candidates = [r for r in rows if MODEL.search(r['path']) or TTS.search(r['path'])]
    libs = [r for r in rows if r['path'].endswith('.so')]
    lines = ['# APK TTS static evidence', '', f'- SHA-256 verified: `{digest}`',
             f'- APK size: {source.stat().st_size:,} bytes', f'- Package: `{manifest["package"]}`',
             f'- Version: `{manifest["version_name"]}` ({manifest["version_code"]})',
             f'- SDK min / target: {manifest["min_sdk"]} / {manifest["target_sdk"]}',
             f'- DEX files: {dex_count}', '', '## Permissions',
             *[f'- `{p}`' for p in manifest['permissions']], '', '## Model / voice / TTS file candidates']
    lines += [f'- `{r["path"]}` — {r["bytes"]:,} bytes' for r in candidates[:120]] or ['None matched filename heuristics.']
    lines += ['', '## Native libraries'] + [f'- `{r["path"]}` — {r["bytes"]:,} bytes' for r in libs[:100]]
    lines += ['', '## TTS class candidates'] + [f'- `{c}`' for c in sorted(classes)[:100]]
    lines += ['', f'## TTS-related invocation evidence ({len(refs)} matches; first 100 shown)']
    lines += [f'- `{r["caller"]}` → `{r["target"]}`' for r in refs[:100]]
    lines += ['', '## Limits',
              'Static scan only. A matching SDK, method, hostname, or filename is not proof it is used at runtime.',
              'No APK execution, listening test, latency benchmark, or network traffic capture was performed.',
              'Absence of matches does not exclude obfuscated, native, Flutter, JavaScript, or downloaded TTS code.',
              'Offline capability, actual text transmission, voice quality and supported languages require follow-up evidence.',
              'Full inventory and method references are in the attached workflow artifact. No arbitrary string dump is logged.']
    (output / 'report.md').write_text('\n'.join(lines) + '\n')


if __name__ == '__main__':
    main()
