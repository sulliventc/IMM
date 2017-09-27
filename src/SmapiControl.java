import com.google.gson.Gson;
import com.google.gson.stream.JsonReader;
import jdk.internal.util.xml.impl.Input;

import java.io.*;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.nio.file.Path;
import java.nio.file.Paths;

public class SmapiControl {
    // TODO: implement checkSmapiInstall
    private static void checkSmapiInstall() {

    }

    // TODO: implement checkSmapiVersion
    private static void checkSmapiVersion() {

    }

    // TODO: implement downloadSmapi
    // TODO: Deal with github assets. This currently downloads source
    public static void downloadSmapi(){
        String url = getGithubResponse().tarball_url;
        final String filename = "smapi.tar.gz";

        try {
            System.out.println(sendGetAndSave(url, filename));
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    private static SmapiResponse getGithubResponse() {
        final String url = "https://api.github.com/repos/Pathoschild/SMAPI/releases/latest";
        final String filename = "smapiresp.json";
        Path responsePath = null;
        JsonReader jsonReader = null;
        Gson gson;

        try {
            responsePath = sendGetAndSave(url, filename);
        } catch (IOException e) {
            e.printStackTrace();
        }

        gson = new Gson();
        try {
            assert responsePath != null;
            jsonReader = new JsonReader(new FileReader(responsePath.toFile()));
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
        assert jsonReader != null;
        return gson.fromJson(jsonReader, SmapiResponse.class);
    }

    private static Path sendGetAndSave(String url, String filename) throws IOException {
        URL urlObj;
        HttpURLConnection connection;
        Path savePath;
        InputStream in;
        FileOutputStream out;
        int responseCode;
        int read;
        byte[] buffer;

        urlObj = new URL(url);
        connection = (HttpURLConnection) urlObj.openConnection();

        connection.setRequestMethod("GET");
        connection.setRequestProperty("User-Agent", "Iridium Mod Manager");

        responseCode = connection.getResponseCode();
        savePath = Paths.get(System.getProperty("java.io.tmpdir"), filename);
        if (savePath.toFile().exists()) {
            savePath.toFile().delete();
        }

        in = connection.getInputStream();
        out = new FileOutputStream(savePath.toFile());

        read = 0;
        buffer = new byte[32768];
        while ((read = in.read(buffer)) > 0) {
            out.write(buffer, 0, read);
        }

        out.close();
        in.close();
        return savePath;
    }

    private static void installSmapi() {
        // TODO: unzip SMAPI
        // TODO: copy all files; Mono if *nix, Windows if win
        // TODO: rename .exe if *nix
        // TODO: show "edit your launch settings" if windows
    }
}
