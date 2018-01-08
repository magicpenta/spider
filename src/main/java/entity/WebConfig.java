package entity;

/**
 * 网站配置实体类
 *
 * @author panda
 * @date 2018/1/5
 */
public class WebConfig {

    private Integer id;

    private String domain;

    private Boolean needCookie;

    private Boolean needProxy;

    private String requestHeaders;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getDomain() {
        return domain;
    }

    public void setDomain(String domain) {
        this.domain = domain;
    }

    public Boolean getNeedCookie() {
        return needCookie;
    }

    public void setNeedCookie(Boolean needCookie) {
        this.needCookie = needCookie;
    }

    public Boolean getNeedProxy() {
        return needProxy;
    }

    public void setNeedProxy(Boolean needProxy) {
        this.needProxy = needProxy;
    }

    public String getRequestHeaders() {
        return requestHeaders;
    }

    public void setRequestHeaders(String requestHeaders) {
        this.requestHeaders = requestHeaders;
    }
}
