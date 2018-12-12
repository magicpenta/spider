package com.panda.plugins;

import com.panda.dao.taobao.TaobaoCommentDao;
import com.panda.entity.common.TaskDO;
import com.panda.entity.taobao.TaobaoCommentDO;
import com.panda.main.TaskRunner;
import org.apache.commons.lang3.StringUtils;
import org.apache.commons.lang3.time.DateUtils;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import com.panda.util.CommonUtil;
import com.panda.util.ParserUtil;
import com.panda.util.TaobaoRankUtil;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

/**
 * 淘宝店铺历史评论插件
 *
 * @author panda
 * @date 2018/1/4
 */
@Plugin(value = "ratehis.taobao.com")
public class TaobaoHistoryCommentPlugin extends AbstractPlugin {

    private static final Logger logger = LoggerFactory.getLogger(TaobaoHistoryCommentPlugin.class);

    public TaobaoHistoryCommentPlugin(TaskDO task) {
        super(task);
    }

    @Override
    public void parseContent(String body) throws Exception {
        if (body.contains("<meta charset=\"gbk\">")) {
            logger.info("暂时无法处理请求");
            logger.info(task.getUrl());
            forceAddUrl(task.getUrl());
            return;
        }
        List<TaobaoCommentDO> commentList = new ArrayList<TaobaoCommentDO>();
        Date minCommentTime = DateUtils.parseDate("2013-10-01 00:00:00", "yyyy-MM-dd HH:mm:ss");
        Long sellerId = task.getTaskId();
        String sellerName = task.getTaskName();
        boolean isStop = false;

        Document document = Jsoup.parse(body);
        Elements elements = document.select("div.rate-list > table > tbody > tr");

        logger.info("当前页共有评论记录数: " + elements.size());
        for (int i = 0; i < elements.size(); i++) {
            Element commentElement = elements.get(i);
            // 评价等级
            Integer commentLevel;
            String commentLevelStr = commentElement.select("i").first().className();
            if ("icon-gnb icon-gnb-1".equals(commentLevelStr)) {
                commentLevel = 1;
            } else if ("icon-gnb icon-gnb-0".equals(commentLevelStr)) {
                commentLevel = 0;
            } else if ("icon-gnb icon-gnb--1".equals(commentLevelStr)) {
                commentLevel = -1;
            } else if ("icon-gnb icon-gnb--2".equals(commentLevelStr)) {
                commentLevel = -2;
            } else {
                commentLevel = -3;
            }
            // 评价内容
            String content = commentElement.select("p.comment").text();
            content = ParserUtil.parseEmoji(content);
            if (StringUtils.isEmpty(content)) {
                content = "";
            }
            Integer ifLevelDefault;
            Integer ifContentEmpty;
            if (content.contains("默认好评")) {
                ifLevelDefault = 1;
            } else {
                ifLevelDefault = 0;
            }
            if (content.contains("用户未填写评论内容")) {
                ifContentEmpty = 1;
            } else {
                ifContentEmpty = 0;
            }
            // 卖家回复
            String reply = null;
            if (commentElement.select("p.reply") != null) {
                reply = commentElement.select("p.reply").text();
            }
            Integer hasReply = 0;
            if (StringUtils.isNotEmpty(reply)) {
                hasReply = 1;
            }
            // 评价时间
            String commentTimeStr = CommonUtil.match(commentElement.select("td").get(1)
                            .select("p.text-gray").first().text(), "\\[(.*?)\\]")[1];
            Date commentTime = DateUtils.parseDate(commentTimeStr, "yyyy-MM-dd HH:mm:ss");
            if (commentTime.before(minCommentTime)) {
                isStop = true;
                break;
            }

            Long commentId = sellerId + commentTime.getTime() + i;

            Integer validScore;
            String validScoreStr = commentElement.select("td").first().select("p.text-gray").text();
            validScore = StringUtils.isEmpty(validScoreStr) ? 0 : 1;

            String userName = commentElement.select("td.user-col > a").first().text();
            String rank = "0";
            String rankStr = commentElement.select("td.user-col > img").first().attr("src");
            if (StringUtils.isNotEmpty(rankStr)) {
                rankStr = rankStr.substring(rankStr.lastIndexOf("/") + 1);
                rank = String.valueOf(TaobaoRankUtil.getRank(rankStr));
            }

            String goodsName = commentElement.select("td").get(3).select("p").first().text();
            String goodsPrice = commentElement.select("td").get(3).select("span.text-red").first().text();

            TaobaoCommentDO comment = new TaobaoCommentDO();
            comment.setSellerId(sellerId);
            comment.setSellerName(sellerName);
            comment.setCommentId(commentId);
            comment.setCommentLevel(commentLevel);
            comment.setCommentTime(commentTime);
            comment.setIfLevelDefault(ifLevelDefault);
            comment.setIfContentEmpty(ifContentEmpty);
            comment.setContent(content);
            comment.setHasReply(hasReply);
            comment.setReply(reply);
            comment.setValidScore(validScore);
            comment.setUsername(userName);
            comment.setRank(rank);
            comment.setGoodsName(goodsName);
            comment.setGoodsPrice(goodsPrice);
            commentList.add(comment);
        }

        if (commentList.size() == 0) {
            logger.info(body);
            TaskRunner.setStop(true);
            return;
        }

        int result = TaobaoCommentDao.getInstance().insertList(commentList);
        logger.info("本次新增记录数: " + result);

        String hasNext = document.select("div#J_pagination").attr("data-hasNext");
        int curPage = Integer.valueOf(document.select("div#J_pagination").attr("data-curPage"));

        if ("0".equals(hasNext) || isStop) {
            logger.info("插件检测任务执行完毕, 准备退出进程...");
            TaskRunner.setStop(true);
            return;
        }

        if ("1".equals(hasNext)) {
            String nextPageUrl = task.getUrl().replaceAll("page=\\d+", "page=" + (curPage + 1));
            logger.info("nextPageUrl: " + nextPageUrl);
            addUrl(nextPageUrl);
        }
    }

    @Override
    public boolean isDetailPage(String url) {
        return true;
    }

}
