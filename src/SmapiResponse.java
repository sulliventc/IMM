public class SmapiResponse {
    public String tag_name;
    public String tarball_url;

    @Override
    public String toString() {
        return "tag_name: " + tag_name + " tarball_url: " + tarball_url;
    }
}
