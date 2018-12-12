package com.panda.util;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * 通用工具类
 *
 * @author panda
 * @date 2017/10/28
 */
public class CommonUtil {

    private static final Logger logger = LoggerFactory.getLogger(CommonUtil.class);

    /**
     * 获取 doc 对象
     *
     * @param body
     * @return
     */
    public static Document getDocument(String body) {

        Document doc = null;

        try {
            doc = Jsoup.parse(body);
        } catch (Exception e) {
            logger.error("加载doc对象失败：", e);
        }

        return doc;
    }

    /**
     * 获取路径前缀
     *
     * @param url
     * @return
     */
    public static String getUrlPrefix(String url) {
        url = match(url, "(https?://.*?)/")[1];
        return url;
    }

    /**
     * 获取绝对路径
     *
     * @param baseUrl
     * @param url
     * @return
     */
    public static String getAbsoluteUrl(String baseUrl, String url) {
        String absoluteUrl = url;
        baseUrl = match(baseUrl, "(https?://.*?)/")[1];
        if (url.startsWith("/")) {
            absoluteUrl = baseUrl + url;
        } else if (url.startsWith(".")) {
            absoluteUrl = baseUrl + url.replaceAll("\\.", "/");
        }
        return absoluteUrl;
    }

    /**
     * 获取域名
     *
     * @param url
     * @return
     */
    public static String getDomain(String url) {
        if (url.startsWith("common://")) {
            url = url.substring(7);
        } else if (url.startsWith("https://")) {
            url = url.substring(8);
        }
        int n = url.indexOf("/");
        if (n != -1) {
            url = url.substring(0, n);
        }
        return url;
    }

    /**
     * 正则匹配
     *
     * @param content
     * @param regex
     * @return
     */
    public static String[] match(String content, String regex) {
        Matcher matcher = Pattern.compile(regex).matcher(content);

        if (matcher.find()) {
            String[] s = new String[matcher.groupCount() + 1];
            for (int i = 0; i <= matcher.groupCount(); i++) {
                s[i] = matcher.group(i);
            }
            return s;
        }

        return null;
    }

    /**
     * 是否匹配
     *
     * @param content
     * @param regex
     * @return
     */
    public static boolean isMatch(String content, String regex) {
        Matcher matcher = Pattern.compile(regex).matcher(content);
        return matcher.find();
    }

}
