import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import com.google.gson.stream.JsonReader;

import java.io.*;
import java.lang.reflect.Type;
import java.net.HttpURLConnection;
import java.net.URL;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;

public class SmapiControl {
    // TODO: implement checkSmapiInstall
    private static void checkSmapiInstall() {

    }

    // TODO: implement checkSmapiVersion
    private static void checkSmapiVersion() {

    }

    // TODO: implement downloadSmapi
    // TODO: Deal with github assets. This currently downloads source
    private static Path downloadSmapi() {
        SerializedTypes.SmapiReleaseResponse response = getReleaseResponse();
        String assetsUrl = response.assets_url;
        String tag = response.tag_name;
        String zipUrl = null;
        Path assetsPath = null;
        JsonReader jsonReader = null;
        Path result = null;
        final String assetsFilename = "immAssets.json";
        final String zipFilename = "immSmapi.zip";

        try {
            assetsPath = sendGetAndSave(assetsUrl, assetsFilename);
        } catch (IOException e) {
            e.printStackTrace();
        }

        try {
            assert assetsPath != null;
            jsonReader = new JsonReader(new FileReader(assetsPath.toFile()));
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }

        assert jsonReader != null;
        Type listType = new TypeToken<ArrayList<SerializedTypes.SmapiAssetResponse>>() {
        }.getType();
        List<SerializedTypes.SmapiAssetResponse> assetList = new Gson().fromJson(jsonReader, listType);
        for (SerializedTypes.SmapiAssetResponse element : assetList) {
            if (element.name.equals("SMAPI-" + tag + ".zip")) {
                zipUrl = element.browser_download_url;
            }
        }

        if (zipUrl == null || zipUrl.isEmpty()) {
            // TODO: Proper error handling
            System.err.println("Couldn't find SMAPI download package.");
            System.exit(-1);
        } else {
            try {
                result = sendGetAndSave(zipUrl, zipFilename);
            } catch (IOException e) {
                e.printStackTrace();
            }
        }

        return result;

    }

    private static SerializedTypes.SmapiReleaseResponse getReleaseResponse() {
        final String url = "https://api.github.com/repos/Pathoschild/SMAPI/releases/latest";
        final String filename = "immSmapiResp.json";
        Path responsePath = null;
        JsonReader jsonReader = null;

        try {
            responsePath = sendGetAndSave(url, filename);
        } catch (IOException e) {
            e.printStackTrace();
        }

        try {
            assert responsePath != null;
            jsonReader = new JsonReader(new FileReader(responsePath.toFile()));
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
        assert jsonReader != null;
        return new Gson().fromJson(jsonReader, SerializedTypes.SmapiReleaseResponse.class);
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
        if (responseCode != 200) {
            // TODO: Handle bad response
            System.err.println("Bad server response. Response: " + responseCode + " from address " + url);
            System.exit(-1);
        }
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
