import java.nio.file.Path;
import java.nio.file.Paths;

public class Utility {
    private static OS currentOS;
    private static Path execDir;
    private static Path tmpDir;
    private static Path modDir;
    private static Path contentDir;

    enum OS {UNKNOWN, WIN, MACOS, LINUX}

    public static OS getCurrentOS() {
        // TODO: Pull info from file if already configured
        if (currentOS == OS.UNKNOWN) {
            String osProperty = System.getProperty("os.name").toLowerCase();
            if (osProperty.contains("win")) {
                currentOS = OS.WIN;
            } else if (osProperty.contains("mac")) {
                currentOS = OS.MACOS;
            } else if (osProperty.contains("nux") || osProperty.contains("nix")) {
                currentOS = OS.LINUX;
            } else {
                // TODO: proper error handling
                System.err.println("Unknown OS");
                System.exit(-1);
            }
        }
        return currentOS;
    }

    public static Path getExecDir() {
        // TODO: pull info from file if already configured
        // TODO: GOG support
        // TODO: custom Steam install directory support
        // Windows: C:\Program Files (x86)\Steam\steamapps\common\Stardew Valley\
        // Mac:     /$HOME/Library/Application Support/Steam/steamapps/common/Stardew Valley/Contents/MacOS/
        //          Note: Mac splits content and .exe locations. Because why the fuck not?
        // Linux:   ~/.local/share/Steam/steamapps/common/Stardew Valley/

        String home = System.getProperty("user.home");

        if (execDir == null) {
            switch (getCurrentOS()) {
                case WIN:
                    execDir = Paths.get("C:\\Program Files (x86)\\Steam\\steamapps\\common\\Stardew Valley\\");
                    break;
                case MACOS:
                    execDir = Paths.get(home, "/Library/Application Support/Steam/steamapps/common/Stardew Valley/Contents/MacOS/");
                    break;
                case LINUX:
                    execDir = Paths.get(home,"/.local/share/Steam/steamapps/common/Stardew Valley/");
                    break;
                default:
                    // TODO: Proper error handling
                    System.err.println("Unknown OS. How did you get to this error?");
                    System.exit(-1);
            }
        }
        return execDir;
    }

    public static Path getTmpDir() {
        // TODO: This, but properly
        if (tmpDir == null) {
            switch(getCurrentOS()) {
                case WIN:
                    tmpDir = Paths.get("C:\\temp\\");
                    break;
                case MACOS:
                case LINUX:
                    tmpDir = Paths.get("/tmp/");
                    break;
                default:
                    // TODO: Proper error handling
                    System.err.println("Unknown OS. How did you get to this error?");
                    System.exit(-1);
            }
        }
        return tmpDir;
    }

    public static void writeSettingsFile() {

    }
}
