import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;

public class SmapiControl {

    // TODO: implement checkSmapiInstall
    private static void checkSmapiInstall() {

    }

    // TODO: implement checkSmapiVersion
    private static void checkSmapiVersion() {

    }

    // TODO: implement downloadSmapi
    public static void downloadSmapi() throws Exception {
        // download with githup releases API
        // to System.getProperty("java.io.tmpdir")
        // possibly return absolute path
        String url = "https://api.github.com/repos/Pathoschild/SMAPI/releases/latest";

        URL obj = new URL(url);
        HttpURLConnection connection = (HttpURLConnection) obj.openConnection();

        connection.setRequestMethod("GET");
        connection.setRequestProperty("User-Agent", "Iridium Mod Manager");

        int responseCode = connection.getResponseCode();
        System.out.println("\nSending 'GET' request.");
        System.out.println("Response code: " + responseCode);

        BufferedReader in = new BufferedReader(new InputStreamReader(connection.getInputStream()));
        String inputLine;
        StringBuffer response = new StringBuffer();

        while ((inputLine = in.readLine()) != null) {
            response.append(inputLine);
        }
        in.close();

        System.out.println(response.toString());
    }

    private static void getGithubResponse() {
        // TODO: send request currently in downloadSmapi
        // TODO: Save response file to temp directory
        // TODO: deserialize with gson to object that holds tarball url and release number; don't really care about the rest.
        // TODO: return that object
    }

    public static void installSmapi() {
        // TODO: unzip SMAPI
        // TODO: copy all files; Mono if *nix, Windows if win
        // TODO: rename .exe if *nix
        // TODO: show "edit your launch settings" if windows
    }
}
