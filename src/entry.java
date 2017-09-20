import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;

public class entry {
    public static void main(String args[]) {
        String home = System.getProperty("user.home");
        Path path = Paths.get(home,"/Documents");
        System.out.println(path.toAbsolutePath());
        if (Files.exists(path)) {
            System.out.println("yup");
        }
        if (Files.isDirectory(path)) {
            System.out.println("10-4");
        }

        System.out.println(System.getProperty("java.io.tmpdir"));

        try {
            SmapiControl.downloadSmapi();
        } catch (Exception e) {
            System.err.println(e);
        }
    }
}
