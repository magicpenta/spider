package entity;

/**
 * Demo class
 *
 * @author xiepd
 * @date 2017/10/28
 */
public class Website {

    private String scheme;

    private String host;

    private String path;

    public Website() {
        scheme = "http";
    }

    public String getScheme() {
        return scheme;
    }

    public void setScheme(String scheme) {
        this.scheme = scheme;
    }

    public String getHost() {
        return host;
    }

    public void setHost(String host) {
        this.host = host;
    }

    public String getPath() {
        return path;
    }

    public void setPath(String path) {
        this.path = path;
    }
}
