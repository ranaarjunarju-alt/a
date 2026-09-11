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
  Set<String> exact = Set.of("com.vega.launcher.init.AssistConfig", "com.vega.launcher.init.config.AssistConfig", "com.vega.core.context.debug.APIHost", "com.vega.audio.tone.TextToAudioRequest", "com.vega.audio.tone.ToneApiService", "com.vega.audio.tone.tts.engine.server.RemoteSAMIToneManager", "com.vega.launcher.init.config.AssistDevelopSetting", "com.vega.core.context.debug.DevelopSetting", "com.vega.launcher.init.config.AppPropertyImpl", "com.vega.audio.tone.tts.engine.streaming.StreamingReadingToneManager", "com.vega.audio.tone.tts.engine.streaming.StreamingSavingToneManager", "com.vega.audio.tone.tts.engine.sami.SamiTextToSpeechEngine", "com.lemon.lv.editor.proxy.IAccount", "com.vega.audio.tone.tts.engine.nonstreaming.clipflow.nodes.SignTextWithRSANode$runAsync$2", "com.vega.audio.tone.tts.data.StreamingToneSpeakerInfo", "com.vega.materialgenerate.ITtsApiService", "com.vega.materialgenerate.TtsApiServiceFactory", "com.vega.core.context.ContextExtKt", "com.vega.core.net.TimeoutInterceptor", "com.vega.audio.tone.tts.TextToSpeechTaskManager", "com.vega.audio.tone.tts.core.TextToSpeechTaskScheduler", "com.vega.audio.tone.tts.core.TextToSpeechTaskType", "com.vega.audio.tone.tts.cache.DefaultTTSCacheManager", "com.vega.core.net.TypedJson", "com.lemon.lv.data.TextToAudioInfo");
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
   Files.write(out.resolve("candidate-classes.txt"), candidates);
   Files.write(out.resolve("routing-candidates.txt"), routingCandidates);
  }
 }
}
