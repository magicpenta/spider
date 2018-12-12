package com.panda.plugins;

import com.panda.dao.taobao.TaobaoSellerDao;
import com.panda.entity.common.TaskDO;
import com.panda.entity.taobao.TaobaoSellerDO;
import com.panda.main.TaskRunner;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.ArrayList;
import java.util.List;

/**
 * 淘宝店铺信息插件
 *
 * @author panda
 * @date 2018/1/4
 */
@Plugin(value = "rate.taobao.com/user-rate-")
public class TaobaoSellerPlugin extends AbstractPlugin {

    private static final Logger logger = LoggerFactory.getLogger(TaobaoSellerPlugin.class);

    public TaobaoSellerPlugin(TaskDO task) {
        super(task);
    }

    @Override
    public void parseContent(String body) throws Exception {
        List<TaobaoSellerDO> sellerInfoList = new ArrayList<TaobaoSellerDO>();

        Document document = Jsoup.parse(body);

        Integer fileName = task.getId();
        Long sellerId = task.getTaskId();
        String sellerName = task.getTaskName();

        Integer isTianmao = 0;
        Element tianmaoElement = document.select("h1#mallLogo").first();
        if (tianmaoElement != null) {
            isTianmao = 1;
        }

        Integer goodCommentsLastHalfYear = null;
        Integer normalCommentsLastHalfYear = null;
        Integer badCommentsLastHalfYear = null;
        Integer goodCommentsHalfYearAgo = null;
        Integer normalCommentsHalfYearAgo = null;
        Integer badCommentsHalfYearAgo = null;

        Elements commentLevelElements = document.select("ul.menu-content > li");
        if (commentLevelElements != null && commentLevelElements.size() > 0) {
            goodCommentsLastHalfYear = Integer.valueOf(commentLevelElements.get(2).select("td.rateok").text());
            normalCommentsLastHalfYear = Integer.valueOf(commentLevelElements.get(2).select("td.ratenormal").text());
            badCommentsLastHalfYear = Integer.valueOf(commentLevelElements.get(2).select("td.ratebad").text());
            goodCommentsHalfYearAgo = Integer.valueOf(commentLevelElements.last().select("td.rateok").text());
            normalCommentsHalfYearAgo = Integer.valueOf(commentLevelElements.last().select("td.ratenormal").text());
            badCommentsHalfYearAgo = Integer.valueOf(commentLevelElements.last().select("td.ratebad").text());
        }

        logger.info("isTianmao: " + isTianmao);
        logger.info("goodCommentsLastHalfYear: " + goodCommentsLastHalfYear);
        logger.info("normalCommentsLastHalfYear: " + normalCommentsLastHalfYear);
        logger.info("badCommentsLastHalfYear: " + badCommentsLastHalfYear);
        logger.info("goodCommentsHalfYearAgo: " + goodCommentsHalfYearAgo);
        logger.info("normalCommentsHalfYearAgo: " + normalCommentsHalfYearAgo);
        logger.info("badCommentsHalfYearAgo: " + badCommentsHalfYearAgo);

        TaobaoSellerDO sellerInfo = new TaobaoSellerDO();
        sellerInfo.setFilename(fileName + "");
        sellerInfo.setSellerId(sellerId);
        sellerInfo.setSellerName(sellerName);
        sellerInfo.setIsTianmao(isTianmao);
        sellerInfo.setGoodCommentsLastHalfYear(goodCommentsLastHalfYear);
        sellerInfo.setNormalCommentsLastHalfYear(normalCommentsLastHalfYear);
        sellerInfo.setBadCommentsLastHalfYear(badCommentsLastHalfYear);
        sellerInfo.setGoodCommentsHalfYearAgo(goodCommentsHalfYearAgo);
        sellerInfo.setNormalCommentsHalfYearAgo(normalCommentsHalfYearAgo);
        sellerInfo.setBadCommentsHalfYearAgo(badCommentsHalfYearAgo);

        sellerInfoList.add(sellerInfo);
        TaobaoSellerDao.getInstance().insertList(sellerInfoList);
        logger.info("商家信息提取完毕...");
        TaskRunner.setStop(true);
    }

    @Override
    public boolean isDetailPage(String url) {
        return true;
    }

}
