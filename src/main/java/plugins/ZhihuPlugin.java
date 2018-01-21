package plugins;

import entity.Task;
import org.bson.Document;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import util.MongoUtil;

/**
 * spider
 *
 * @author panda
 * @date 2018/1/4
 */
@Plugin(value = "www.zhihu.com")
public class ZhihuPlugin extends AbstractPlugin {

    private static final Logger logger = LoggerFactory.getLogger(ZhihuPlugin.class);

    public ZhihuPlugin(Task task) {
        super(task);
    }

    @Override
    public void parseContent(String body) {
        logger.info("开始解析数据...");
        try {
            Document document = Document.parse("[");
            MongoUtil.insertOne(document);
        } catch (Exception e) {
            logger.error("保存数据异常:", e);
        }
        logger.info("解析任务完成...");
    }

    @Override
    public boolean isDetailPage(String url) {
        return true;
    }
}
