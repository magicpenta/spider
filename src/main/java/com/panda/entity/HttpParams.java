package com.panda.entity;

import com.panda.entity.common.ProxyDO;

import java.util.Map;

/**
 * common 请求类
 *
 * @author panda
 * @date 2017/10/28
 */
public class HttpParams {

    private String url;

    private String charset;

    private ProxyDO proxy;

    private Map<String, String> headerMap;

    private Map<String, String> formParams;

    public static HttpParamsBuilder getBuilder() {
        return new HttpParamsBuilder();
    }

    private HttpParams(String url, String charset, Boolean needCookie, Boolean needProxy, ProxyDO proxy,
                       Map<String, String> headerMap, Map<String, String> formParams) {
        this.url = url;
        this.charset = charset;
        this.proxy = proxy;
        this.headerMap = headerMap;
        this.formParams = formParams;
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

    public ProxyDO getProxy() {
        return proxy;
    }

    public void setProxy(ProxyDO proxy) {
        this.proxy = proxy;
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

    public static class HttpParamsBuilder {

        private String url;

        private String charset = "utf-8";

        private Boolean needCookie = false;

        private Boolean needProxy = false;

        private ProxyDO proxy;

        private Map<String, String> headerMap;

        private Map<String, String> formParams;

        public HttpParams build() {
            return new HttpParams(url, charset, needCookie, needProxy, proxy, headerMap, formParams);
        }

        public HttpParamsBuilder setUrl(String url) {
            this.url = url;
            return this;
        }

        public HttpParamsBuilder setCharset(String charset) {
            this.charset = charset;
            return this;
        }

        public HttpParamsBuilder setNeedCookie(Boolean needCookie) {
            this.needCookie = needCookie;
            return this;
        }

        public HttpParamsBuilder setNeedProxy(Boolean needProxy) {
            this.needProxy = needProxy;
            return this;
        }

        public HttpParamsBuilder setProxy(ProxyDO proxy) {
            this.proxy = proxy;
            return this;
        }

        public HttpParamsBuilder setHeaderMap(Map<String, String> headerMap) {
            this.headerMap = headerMap;
            return this;
        }

        public HttpParamsBuilder setFormParams(Map<String, String> formParams) {
            this.formParams = formParams;
            return this;
        }
    }

}
