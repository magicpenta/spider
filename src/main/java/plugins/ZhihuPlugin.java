package plugins;

import entity.Task;
import main.TaskRunner;
import org.apache.commons.lang3.StringUtils;
import org.bson.BsonArray;
import org.bson.BsonDocument;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Element;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import util.CommonUtil;
import util.MongoUtil;

import java.util.ArrayList;
import java.util.List;

/**
 * 知乎插件
 *
 * @author panda
 * @date 2018/1/4
 */
@Plugin(value = "www.zhihu.com")
public class ZhihuPlugin extends AbstractPlugin {

    private static final Logger logger = LoggerFactory.getLogger(ZhihuPlugin.class);

    private static final String TASK_URL_MASK = "#is_task_url=1";

    public ZhihuPlugin(Task task) {
        super(task);
    }

    @Override
    public void parseContent(String body) {
        logger.info("开始解析数据...");
        try {
            if (StringUtils.isEmpty(body)) {
                return;
            }

            BsonDocument document;
            String nextPageUrl;

            if (isTaskUrl(task.getUrl())) {
                Element element = Jsoup.parse(body).getElementById("data");
                body = element.attr("data-state");
                body = body.replaceAll("&quot;", "\"");
                document = BsonDocument.parse(body);
                String userKey = document.getDocument("entities")
                        .getDocument("users")
                        .getFirstKey();
                nextPageUrl = task.getUrl().replaceAll("/people.*",
                        "/people/" + userKey + "/activities");
            } else if (isFirstPage(task.getUrl())) {
                String userId = CommonUtil.match(task.getUrl(), "/people/(.*?)/")[1];
                Element element = Jsoup.parse(body).getElementById("data");
                body = element.attr("data-state");
                body = body.replaceAll("&quot;", "\"");
                document = BsonDocument.parse(body);
                nextPageUrl = document.getDocument("people")
                        .getDocument("activitiesByUser")
                        .getDocument(userId)
                        .getString("next")
                        .getValue();
            } else {
                document = BsonDocument.parse(body);
                try {
                    nextPageUrl = document.getDocument("paging")
                            .getString("next")
                            .getValue();
                } catch (Exception e) {
                    nextPageUrl = null;
                    TaskRunner.setStop(true);
                    logger.error("不存在下一页, 停止翻页!");
                }

                BsonArray bsonArray = document.getArray("data");

                logger.info("共找到[{}]条记录", bsonArray.size());

                List<BsonDocument> documents = new ArrayList<BsonDocument>();
                for (int i = 0; i < bsonArray.size(); i++) {
                    documents.add(bsonArray.get(i).asDocument());
                }
                MongoUtil.insertList(documents);
            }

            if (StringUtils.isNotEmpty(nextPageUrl)) {
                addUrl(nextPageUrl);
            }
        } catch (Exception e) {
            logger.error("解析异常:", e);
        }
        logger.info("解析任务完成...");
    }

    @Override
    public boolean isDetailPage(String url) {
        return true;
    }

    private boolean isTaskUrl(String url) {
        if (StringUtils.isEmpty(url)) {
            return false;
        }
        return url.contains(TASK_URL_MASK);
    }

    private boolean isFirstPage(String url) {
        if (StringUtils.isEmpty(url)) {
            return false;
        }
        return !url.contains("api") && !url.contains(TASK_URL_MASK);
    }

}
