package com.panda.plugins;

import com.panda.entity.common.TaskDO;
import com.panda.main.TaskRunner;
import org.apache.commons.lang3.StringUtils;
import org.bson.BsonArray;
import org.bson.BsonDocument;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Element;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import com.panda.util.MongoUtil;

import java.util.ArrayList;
import java.util.Date;
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

    public ZhihuPlugin(TaskDO task) {
        super(task);
    }

    @Override
    public void parseContent(String body) {
        logger.info("开始解析数据...");
        try {

            BsonDocument document;
            String nextPageUrl;

            if (isFirstPage(task.getUrl())) {
                Element element = Jsoup.parse(body).getElementById("data");
                body = element.attr("data-state");
                body = body.replaceAll("&quot;", "\"");
                document = BsonDocument.parse(body);
                String userKey = document.getDocument("entities")
                        .getDocument("users")
                        .getFirstKey();
                nextPageUrl = "https://www.zhihu.com/com.panda.api/v4/members/" + userKey + "/activities?limit=8";
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
                if (bsonArray != null) {
                    if (bsonArray.size() > 0){
                        logger.info("共找到[{}]条记录", bsonArray.size());
                        List<BsonDocument> documents = new ArrayList<BsonDocument>();
                        for (int i = 0; i < bsonArray.size(); i++) {
                            documents.add(bsonArray.get(i).asDocument());
                        }
                        MongoUtil.insertList(documents);

                        Date lastDate = new Date(
                                Long.valueOf(bsonArray.get(bsonArray.size() - 1).asDocument().getInt32("created_time").getValue() + "000"));
                        // 2016/01/01 00:00:00
                        Date beginDate = new Date(1451577600000L);
                        logger.info("本次记录最早更新时间:{}", lastDate);
                        if (lastDate.before(beginDate)) {
                            logger.info("本次记录最早更新时间 {} 早于 live 创建时间 {} , 停止翻页", lastDate, beginDate);
                            nextPageUrl = null;
                            TaskRunner.setStop(true);
                        }
                    }
                }
            }

            if (StringUtils.isNotEmpty(nextPageUrl)) {
                addUrl(nextPageUrl);
            }
        } catch (Exception e) {
            logger.error("解析异常:", e);
            TaskRunner.setError(true);
        }
        logger.info("解析任务完成...");
    }

    @Override
    public boolean isDetailPage(String url) {
        return true;
    }

    private boolean isFirstPage(String url) {
        if (StringUtils.isEmpty(url)) {
            return false;
        }
        return !url.contains("com/panda/api");
    }

}
