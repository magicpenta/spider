package util;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * 通用工具类
 *
 * @author xiepd
 * @date 2017/10/28
 */
public class CommonUtil {

    private static final Logger logger = LoggerFactory.getLogger(CommonUtil.class);

    public static Document getDocument(String body) {

        Document doc = null;

        try {
            doc = Jsoup.parse(body);
        } catch (Exception e) {
            logger.error("加载doc对象失败：", e);
        }

        return doc;
    }

    public static String[] match(String content, String pattern) {
        Matcher matcher = Pattern.compile(pattern).matcher(content);

        if (matcher.find()) {
            String[] s = new String[matcher.groupCount()];
            for (int i = 0; i < matcher.groupCount(); i ++) {
                s[i] = matcher.group(i);
            }
            return s;
        } else {
            return null;
        }

    }


}
