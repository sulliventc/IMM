import java.util.List;

public class SerializedTypes {
    public class SettingsFile {
        public int os;
        public String smapiVersion;
        public String installDir;
    }

    public class SmapiReleaseResponse {
        public String tag_name;
        public String assets_url;
    }

    public class SmapiAssetResponse {
        public String name;
        public String browser_download_url;
    }

    public class Mod {
        public int type; // 0 is traditional, 1 is XNB
        public String root;
        public List<String> filesAdded;
        public List<String> filesRemoved;
    }
}
