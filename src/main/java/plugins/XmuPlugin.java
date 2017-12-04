package plugins;

import entity.Task;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import util.CommonUtil;
import util.FileUtils;

import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * xmu 插件
 *
 * @author panda
 * @date 2017/12/01
 */
@Plugin(value = "sm.xmu.edu.cn")
public class XmuPlugin extends AbstractPlugin {

    private static final Logger logger = LoggerFactory.getLogger(XmuPlugin.class);

    public XmuPlugin(Task task) {
        super(task);
    }

    @Override
    public void parseContent(String body) {

        Document doc = CommonUtil.getDocument(body);

        try {
            String title = doc.select("p.h1").first().text();
            String publishTimeStr = doc.select("div.right-content").first().text();
            publishTimeStr = CommonUtil.match(publishTimeStr, "(\\d{4}-\\d{2}-\\d{2} \\d{2}:\\d{2}:\\d{2})")[1];
            Date publishTime = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").parse(publishTimeStr);
            String content = "";
            Elements elements = doc.select("p.MsoNormal");
            for (Element element : elements) {
                content += "\n" + element.text();
            }

            logger.info("title: " + title);
            logger.info("publishTime: " + publishTime);
            logger.info("content: " + content);

            FileUtils.writeFile(title + ".txt", content);
        } catch (Exception e) {
            logger.error(" 解析内容异常：" + task.getUrl(), e);
        }
    }

    @Override
    public boolean isDetailPage(String url) {
        return CommonUtil.isMatch(url, "&a=show&catid=\\d+&id=\\d+");
    }

}
