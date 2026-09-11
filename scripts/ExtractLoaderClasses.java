import jadx.api.*;
import java.io.File;
import java.nio.file.*;
import java.util.*;
public class ExtractLoaderClasses {
 public static void main(String[] argv) throws Exception {
  JadxArgs args = new JadxArgs();
  args.setInputFiles(List.of(new File(argv[0])));
  args.setSkipResources(true); args.setThreadsCount(2);
  args.setCommentsLevel(CommentsLevel.DEBUG);
  Path out=Paths.get(argv[2]); Files.createDirectories(out);
  Set<String> names=new HashSet<>(Files.readAllLines(Paths.get(argv[1])));
  names.add("com.vega.launcher.init.config.AppPropertyImpl");
  names.add("com.lm.components.network.extra.NetworkRuntimeConfig");
  int count=0; long size=0; List<String> log=new ArrayList<>();
  try(JadxDecompiler jadx=new JadxDecompiler(args)) {
   jadx.load();
   for(JavaClass c:jadx.getClasses()) {
    if(!names.contains(c.getFullName()))continue;
    if(count++>=70){log.add("class cap: "+c.getFullName());continue;}
    String code=c.getCode().replaceAll("\"[A-Za-z0-9+/=_-]{160,}\"", "\"<REDACTED_LONG_LITERAL>\"");
    if(code.length()>800000 || size+code.length()>6000000){log.add("size cap: "+c.getFullName());continue;}
    size+=code.length();Files.writeString(out.resolve(c.getFullName()+".java"),code);log.add("extracted: "+c.getFullName());
   }
  }
  Files.write(out.resolve("extraction-scope.txt"),log);
 }
}
