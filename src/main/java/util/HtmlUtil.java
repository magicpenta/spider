package util;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * html 工具类
 *
 * @author panda
 * @date 2017/10/28
 */
public class HtmlUtil {

    private static final Logger logger = LoggerFactory.getLogger(HtmlUtil.class);

    public static Document getDocument(String body) {

        Document doc = null;

        try {
            doc = Jsoup.parse(body);
        } catch (Exception e) {
            logger.error("加载doc对象失败：", e);
        }

        return doc;
    }

}
