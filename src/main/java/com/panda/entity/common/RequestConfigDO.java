package com.panda.entity.common;

import javax.persistence.*;

@Table(name = "request_config")
public class RequestConfigDO {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    /**
     * 域名
     */
    private String domain;

    /**
     * 是否需要cookie
     */
    @Column(name = "need_cookie")
    private Boolean needCookie;

    /**
     * 是否需要代理
     */
    @Column(name = "need_proxy")
    private Boolean needProxy;

    /**
     * 请求头
     */
    @Column(name = "request_headers")
    private String requestHeaders;

    /**
     * @return id
     */
    public Integer getId() {
        return id;
    }

    /**
     * @param id
     */
    public void setId(Integer id) {
        this.id = id;
    }

    /**
     * 获取域名
     *
     * @return domain - 域名
     */
    public String getDomain() {
        return domain;
    }

    /**
     * 设置域名
     *
     * @param domain 域名
     */
    public void setDomain(String domain) {
        this.domain = domain;
    }

    /**
     * 获取是否需要cookie
     *
     * @return need_cookie - 是否需要cookie
     */
    public Boolean getNeedCookie() {
        return needCookie;
    }

    /**
     * 设置是否需要cookie
     *
     * @param needCookie 是否需要cookie
     */
    public void setNeedCookie(Boolean needCookie) {
        this.needCookie = needCookie;
    }

    /**
     * 获取是否需要代理
     *
     * @return need_proxy - 是否需要代理
     */
    public Boolean getNeedProxy() {
        return needProxy;
    }

    /**
     * 设置是否需要代理
     *
     * @param needProxy 是否需要代理
     */
    public void setNeedProxy(Boolean needProxy) {
        this.needProxy = needProxy;
    }

    /**
     * 获取请求头
     *
     * @return request_headers - 请求头
     */
    public String getRequestHeaders() {
        return requestHeaders;
    }

    /**
     * 设置请求头
     *
     * @param requestHeaders 请求头
     */
    public void setRequestHeaders(String requestHeaders) {
        this.requestHeaders = requestHeaders;
    }
}