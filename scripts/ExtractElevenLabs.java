import jadx.api.*;
import java.io.File;
import java.nio.file.*;
import java.util.*;
public class ExtractElevenLabs {
 public static void main(String[] argv) throws Exception {
  JadxArgs args = new JadxArgs();
  args.setInputFiles(List.of(new File(argv[0])));
  args.setSkipResources(true);
  args.setThreadsCount(2);
  Path out = Paths.get(argv[1]); Files.createDirectories(out);
  Set<String> extras = Set.of("ThirdpartyApiServiceFactory", "ThirdPartyPlayer", "BaseTextToSpeechExecutor", "TextToSpeechExecutorFactory", "TextToSpeechTask", "TextToSpeechExecutorType");
  try (JadxDecompiler jadx = new JadxDecompiler(args)) {
   jadx.load();
   for (JavaClass cls : jadx.getClasses()) {
    String name = cls.getFullName();
    if (name.contains("ElevenLabs") || (name.startsWith("com.vega.audio.tone.tts.") && extras.contains(cls.getName()))) {
     System.out.println("Extracting " + name);
     String code = cls.getCode();
     Files.writeString(out.resolve(name + ".java"), code);
    }
   }
  }
 }
}
