import jadx.api.*;
import java.io.File;
import java.nio.file.*;
import java.util.*;
public class ExtractDeepTts {
 public static void main(String[] argv) throws Exception {
  JadxArgs args = new JadxArgs();
  args.setInputFiles(List.of(new File(argv[0])));
  args.setSkipResources(true);
  args.setThreadsCount(2);
  args.setCommentsLevel(CommentsLevel.DEBUG);
  Path out = Paths.get(argv[1]); Files.createDirectories(out);
  Set<String> exact = Set.of("com.vega.edit.base.tone.TextInfo", "com.vega.audio.tone.util.TextToSpeechReportInfo", "com.vega.edit.base.tone.TextToSpeechIntent", "com.vega.edit.base.tone.TextToAudioUtils", "com.vega.audio.tone.tts.engine.server.RemoteSAMIToneUtil", "com.vega.audio.tone.ToneTypeHelper$fetchEffectToneType$2", "com.vega.launcher.init.core.hook.NetworkInitHook", "com.vega.launcher.start.schedule.tasks.NetworkInitTask", "com.vega.core.app.AppContext", "com.vega.core.utils.FlavorLocale", "com.bytedance.crash.dart.DartCrash", "com.bytedance.crash.dart.DartSummary", "com.bytedance.sdk.bridge.js.spec.IFlutterInterceptorListener", "com.vega.audio.tone.manager.TextToAudioService", "com.vega.audio.tone.manager.TextToAudioServiceHelper", "com.lemon.editor.proxy.AccountImpl", "com.vega.audio.tone.ToneTypeHelper", "com.vega.launcher.start.schedule.tasks.NetworkInitTaskImpl", "com.vega.launcher.debug.AssistConfig", "com.vega.core.context.debug.APIHost", "com.vega.audio.tone.manager.TextToAudioRequest", "com.vega.audio.tone.ToneApiService", "com.vega.audio.tone.tts.engine.server.RemoteSAMIToneManager", "com.vega.launcher.init.config.AssistDevelopSetting", "com.vega.core.context.debug.DevelopSetting", "com.vega.launcher.init.config.AppPropertyImpl", "com.vega.audio.tone.tts.engine.streaming.StreamingReadingToneManager", "com.vega.audio.tone.tts.engine.streaming.StreamingSavingToneManager", "com.vega.audio.tone.tts.engine.sami.SamiTextToSpeechEngine", "com.lemon.lv.editor.proxy.IAccount", "com.vega.audio.tone.tts.engine.nonstreaming.clipflow.nodes.SignTextWithRSANode$runAsync$2", "com.vega.audio.tone.tts.data.StreamingToneSpeakerInfo", "com.vega.materialgenerate.ITtsApiService", "com.vega.materialgenerate.TtsApiServiceFactory", "com.vega.core.context.ContextExtKt", "com.vega.core.net.TimeoutInterceptor", "com.vega.audio.tone.tts.TextToSpeechTaskManager", "com.vega.audio.tone.tts.core.TextToSpeechTaskScheduler", "com.vega.audio.tone.tts.core.TextToSpeechTaskType", "com.vega.audio.tone.tts.cache.DefaultTTSCacheManager", "com.vega.core.net.TypedJson", "com.lemon.lv.data.TextToAudioInfo");
  java.util.regex.Pattern relevant = java.util.regex.Pattern.compile("(?i)flutter|dart|HostEnv|HostConfig|HostSettings|DevelopSettings|TTSEngine.*Config|TtsQwen|TtsV3|TtsSign|SignTextWithRSA|StreamingToneMessageService|StreamingToneRequest|StreamingToneReqPayload|RemoteSAMIRequest|ElevenLabs|Tone.*ApiService|Speaker.*Api|Tts.*Api|Speech.*Api|Net.*Interceptor|Cookie.*Interceptor|Sign.*Interceptor|Auth.*Interceptor|Token.*Interceptor");
  List<String> candidates = new ArrayList<>();
  List<String> routingCandidates = new ArrayList<>();
  int selected = 0;
  try (JadxDecompiler jadx = new JadxDecompiler(args)) {
   jadx.load();
   for (JavaClass cls : jadx.getClasses()) {
    String name = cls.getFullName();
    if (relevant.matcher(name).find()) candidates.add(name);
    if (name.matches("(?i).*(Host|AccountImpl|NetWorkInit|NetworkInit|NetworkService|WsChannel|WebSocket|ToneTypeHelper|VoiceList|ToneList|Lynx.*Tts|Tts.*Lynx).*")) routingCandidates.add(name);
    boolean pick = exact.contains(name) || (relevant.matcher(name).find() && !name.contains("$") && (name.startsWith("com.vega.") || name.startsWith("com.lemon.") || name.startsWith("io.flutter.")));
    if (pick && selected++ < 180) {
     System.out.println("Extracting " + name);
     String code = cls.getCode().replaceAll("\"[A-Za-z0-9+/=_-]{160,}\"", "\"<REDACTED_LONG_LITERAL>\"");
     Files.writeString(out.resolve(name + ".java"), code);
    }
   }
   List<String> uses = new ArrayList<>();
   List<String> constructorUses = new ArrayList<>();
   Set<JavaClass> parents = new LinkedHashSet<>();
   for (JavaClass cls : jadx.getClasses()) {
    if (cls.getFullName().equals("com.vega.edit.base.tone.TextToSpeechIntent")) {
     cls.getCode();
     for (JavaMethod method : cls.getMethods()) {
      if (method.isConstructor()) {
       for (JavaNode use : method.getUseIn()) {
        constructorUses.add(method.getFullName() + " <- " + use.getFullName());
        if (!use.getTopParentClass().getFullName().equals(cls.getFullName())) parents.add(use.getTopParentClass());
       }
      }
     }
     for (JavaField field : cls.getFields()) {
      for (JavaNode use : field.getUseIn()) uses.add(field.getFullName() + " <- " + use.getFullName());
     }
    }
   }
   Files.write(out.resolve("intent-constructor-usage.txt"), constructorUses);
   int count = 0;
   long total = 0;
   List<String> callerScan = new ArrayList<>();
   for (JavaClass parent : parents) {
    if (count++ >= 18) break;
    String code = parent.getCode().replaceAll("\"[A-Za-z0-9+/=_-]{160,}\"", "\"<REDACTED_LONG_LITERAL>\"");
    if (code.length() > 700000 || total + code.length() > 5000000) { callerScan.add("Size limit: " + parent.getFullName()); continue; }
    total += code.length();
    Files.writeString(out.resolve(parent.getFullName() + ".java"),code);
    callerScan.add("Extracted: " + parent.getFullName());
   }
   Files.write(out.resolve("intent-callers-scan.txt"), callerScan);
   Files.write(out.resolve("intent-field-usage.txt"), uses);
   Files.write(out.resolve("candidate-classes.txt"), candidates);
   Files.write(out.resolve("routing-candidates.txt"), routingCandidates);
  }
 }
}
