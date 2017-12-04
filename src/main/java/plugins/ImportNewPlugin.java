package plugins;

import entity.Task;
import org.jsoup.nodes.Document;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import util.CommonUtil;
import util.FileUtils;

import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * importnew 插件
 *
 * @author panda
 * @date 2017/12/01
 */
@Plugin(value = "www.importnew.com")
public class ImportNewPlugin extends AbstractPlugin {

    private static final Logger logger = LoggerFactory.getLogger(ImportNewPlugin.class);

    public ImportNewPlugin(Task task) {
        super(task);
    }

    @Override
    public void parseContent(String body) {

        Document doc = CommonUtil.getDocument(body);

        try {
            String title = doc.select("h1").first().text();
            String publishTimeStr = doc.select("div.entry-meta").first().text();
            publishTimeStr = CommonUtil.match(publishTimeStr, "(\\d{4}/\\d{2}/\\d{2})")[1];
            Date publishTime = new SimpleDateFormat("yyyy/MM/dd").parse(publishTimeStr);
            String content = doc.select("div.entry").first().text();

            logger.info("title: " + title);
            logger.info("publishTime: " + publishTime);
            logger.info("content: " + content);

            FileUtils.writeFile(title + ".txt", content);
        } catch (Exception e) {
            logger.error("解析内容异常：", e);
        }
    }

    @Override
    public boolean isDetailPage(String url) {
        return CommonUtil.isMatch(url, "/\\d+\\.html");
    }

}
