package com.panda.entity.common;

import javax.persistence.*;

@Table(name = "proxy")
public class ProxyDO {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    /**
     * 代理IP
     */
    @Column(name = "proxy_ip")
    private String proxyIp;

    /**
     * 代理端口
     */
    @Column(name = "proxy_port")
    private Integer proxyPort;

    /**
     * 代理用户名
     */
    @Column(name = "proxy_username")
    private String proxyUsername;

    /**
     * 代理密码
     */
    @Column(name = "proxy_password")
    private String proxyPassword;

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
     * 获取代理IP
     *
     * @return proxy_ip - 代理IP
     */
    public String getProxyIp() {
        return proxyIp;
    }

    /**
     * 设置代理IP
     *
     * @param proxyIp 代理IP
     */
    public void setProxyIp(String proxyIp) {
        this.proxyIp = proxyIp;
    }

    /**
     * 获取代理端口
     *
     * @return proxy_port - 代理端口
     */
    public Integer getProxyPort() {
        return proxyPort;
    }

    /**
     * 设置代理端口
     *
     * @param proxyPort 代理端口
     */
    public void setProxyPort(Integer proxyPort) {
        this.proxyPort = proxyPort;
    }

    /**
     * 获取代理用户名
     *
     * @return proxy_username - 代理用户名
     */
    public String getProxyUsername() {
        return proxyUsername;
    }

    /**
     * 设置代理用户名
     *
     * @param proxyUsername 代理用户名
     */
    public void setProxyUsername(String proxyUsername) {
        this.proxyUsername = proxyUsername;
    }

    /**
     * 获取代理密码
     *
     * @return proxy_password - 代理密码
     */
    public String getProxyPassword() {
        return proxyPassword;
    }

    /**
     * 设置代理密码
     *
     * @param proxyPassword 代理密码
     */
    public void setProxyPassword(String proxyPassword) {
        this.proxyPassword = proxyPassword;
    }
}