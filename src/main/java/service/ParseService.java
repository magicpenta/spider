package service;

import org.jsoup.nodes.Document;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import util.CommonUtil;

/**
 * 解析服务
 *
 * @author panda
 * @date 2017/10/28
 */
public class ParseService {

    private static final Logger logger = LoggerFactory.getLogger(ParseService.class);

    public void parse(String body) {
        logger.info("开始解析页面");

        Document doc = CommonUtil.getDocument(body);

        try {
            String title = doc.getElementsByClass("h-title").text();
            String dateStr = doc.getElementsByClass("h-time").text();
            String content = doc.getElementById("p-detail").getElementsByTag("P").text();
            logger.info("新闻标题：" + title);
            logger.info("发布时间：" + dateStr);
            logger.info("新闻内容：" + content);
        } catch (Exception e) {
            logger.error("解析页面异常：", e);
        }
    }

}
