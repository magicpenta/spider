package util;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

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
     * 获取域名
     * @param url
     * @return
     */
    public static String getHost(String url) {
        if (url.startsWith("http://")) {
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

}
