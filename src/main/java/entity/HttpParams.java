package entity;

import org.apache.http.client.config.RequestConfig;

import java.util.Map;

/**
 * http 请求类
 *
 * @author panda
 * @date 2017/10/28
 */
public class HttpParams {

    private String url;

    private String charset;

    private Boolean needCookie;

    private Boolean needProxy;

    private Proxy proxy;

    private RequestConfig requestConfig;

    private Map<String, String> headerMap;

    private Map<String, String> formParams;

    public HttpParams() {
        this.charset = "utf-8";
        this.needCookie = false;
        this.needProxy = false;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public String getCharset() {
        return charset;
    }

    public void setCharset(String charset) {
        this.charset = charset;
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

    public Proxy getProxy() {
        return proxy;
    }

    public void setProxy(Proxy proxy) {
        this.proxy = proxy;
    }

    public RequestConfig getRequestConfig() {
        return requestConfig;
    }

    public void setRequestConfig(RequestConfig requestConfig) {
        this.requestConfig = requestConfig;
    }

    public Map<String, String> getHeaderMap() {
        return headerMap;
    }

    public void setHeaderMap(Map<String, String> headerMap) {
        this.headerMap = headerMap;
    }

    public Map<String, String> getFormParams() {
        return formParams;
    }

    public void setFormParams(Map<String, String> formParams) {
        this.formParams = formParams;
    }

}
