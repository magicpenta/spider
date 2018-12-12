package com.panda.plugins;

import com.google.gson.*;
import com.panda.dao.taobao.TaobaoAppendDao;
import com.panda.dao.taobao.TaobaoCommentDao;
import com.panda.entity.common.TaskDO;
import com.panda.entity.taobao.TaobaoAppendDO;
import com.panda.entity.taobao.TaobaoCommentDO;
import com.panda.main.TaskRunner;
import org.apache.commons.lang3.StringUtils;
import org.apache.commons.lang3.time.DateUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import com.panda.util.CommonUtil;
import com.panda.util.ParserUtil;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

/**
 * 淘宝店铺评论插件
 *
 * @author panda
 * @date 2018/1/4
 */
@Plugin(value = "rate.taobao.com/member_rate.htm")
public class TaobaoCommentPlugin extends AbstractPlugin {

    private static final Logger logger = LoggerFactory.getLogger(TaobaoCommentPlugin.class);

    public TaobaoCommentPlugin(TaskDO task) {
        super(task);
    }

    @Override
    public void parseContent(String body) throws Exception {
        List<TaobaoCommentDO> commentList = new ArrayList<TaobaoCommentDO>();
        List<TaobaoAppendDO> appendList = new ArrayList<TaobaoAppendDO>();

        Date minCommentTime = DateUtils.parseDate("2013-10-01 00:00:00", "yyyy-MM-dd HH:mm:ss");
        Long sellerId = task.getTaskId();
        String sellerName = task.getTaskName();
        boolean isStop = false;

        body = CommonUtil.match(body, "(\\{[\\s\\S]+\\})")[0];
        JsonObject jsonObject = new JsonParser().parse(body).getAsJsonObject();
        JsonArray jsonArray = jsonObject.getAsJsonArray("rateListDetail");

        logger.info("当前页共有评论记录数: " + jsonArray.size());
        for (int i = 0; i < jsonArray.size(); i++) {
            JsonObject commentObject = jsonArray.get(i).getAsJsonObject();
            Long commentId = commentObject.get("rateId").getAsLong();
            Integer commentLevel = commentObject.get("rate").getAsInt();

            Date commentTime = DateUtils.parseDate(commentObject.get("date").getAsString(),
                    "yyyy年MM月dd日 HH:mm");
            if (commentTime.before(minCommentTime)) {
                isStop = true;
                break;
            }

            String content = commentObject.get("content").getAsString();
            content = ParserUtil.parseEmoji(content);
            Integer ifLevelDefault;
            Integer ifContentEmpty;
            if (content.contains("默认好评!") || content.equals("默认好评")) {
                ifLevelDefault = 1;
            } else {
                ifLevelDefault = 0;
            }
            if (content.contains("用户未填写评论内容")) {
                ifContentEmpty = 1;
            } else {
                ifContentEmpty = 0;
            }

            JsonArray photoArray = commentObject.get("photos").getAsJsonArray();
            String photoPath = null;
            Integer photoNum = 0;
            if (photoArray != null && photoArray.size() > 0) {
                photoNum = photoArray.size();
                List<String> photoPathList = new ArrayList<String>();
                for (int j = 0; j < photoArray.size(); j++) {
                    photoPathList.add("common:" + photoArray.get(j).getAsJsonObject().get("url").getAsString());
                }
                photoPath = photoPathList.toString();
            }

            Integer hasReply = 0;
            String reply = null;
            JsonElement replyElement = commentObject.get("reply");
            if (!(replyElement instanceof JsonNull)) {
                hasReply = 1;
                reply = replyElement.getAsJsonObject().get("content").getAsString();
            }

            Integer validScore = commentObject.get("validscore").getAsInt() == -4 ? 1 : 0;

            // 买家信息
            String userName = commentObject.getAsJsonObject("user").get("nick").getAsString();
            String rank = commentObject.getAsJsonObject("user").get("rank").getAsString();
            int vipLevel = commentObject.getAsJsonObject("user").get("vipLevel").getAsInt();

            // 追评
            Integer hasAppend = 0;
            JsonArray appendArray = commentObject.get("appendList").getAsJsonArray();
            if (appendArray != null && appendArray.size() > 0) {
                hasAppend = 1;
                appendList.addAll(this.resolveAppendList(appendArray, sellerId, commentId, commentTime));
            }

            // 商品信息
            String goodsName = commentObject.getAsJsonObject("auction").get("title").getAsString();
            String goodsPrice = commentObject.getAsJsonObject("auction").get("auctionPrice").getAsString();
            String sku = commentObject.getAsJsonObject("auction").get("sku").getAsString();
            String goodsColor = null;
            String goodsCombo = null;
            String goodsOtherSku = "";
            if (StringUtils.isNotEmpty(sku)) {
                String[] goodsAllInfo = sku.split("&nbsp;&nbsp");
                for (String goodsInfo : goodsAllInfo) {
                    if (goodsInfo.contains("颜色分类")) {
                        try {
                            goodsColor = CommonUtil.match(goodsInfo, "颜色分类:(.*)")[1];
                        } catch (Exception e) {
                        }
                    } else if (goodsInfo.contains("套餐")) {
                        try {
                            goodsCombo = CommonUtil.match(goodsInfo, "套餐.*?:(.*)")[1];
                        } catch (Exception e) {
                        }
                    } else {
                        goodsOtherSku += goodsInfo + ";";
                    }
                }
                if (goodsOtherSku.endsWith(";")) {
                    goodsOtherSku = goodsOtherSku.substring(0, goodsOtherSku.lastIndexOf(";"));
                }
            }
            if (StringUtils.isEmpty(goodsOtherSku)) {
                goodsOtherSku = null;
            }
            logger.info("------------------------index " + (i + 1));
            logger.info("sku: {}", sku);
            logger.info("goodsColor: {}", goodsColor);
            logger.info("goodsCombo: {}", goodsCombo);
            logger.info("goodsOtherSku: {}", goodsOtherSku);

            TaobaoCommentDO comment = new TaobaoCommentDO();
            comment.setSellerId(sellerId);
            comment.setSellerName(sellerName);
            comment.setCommentId(commentId);
            comment.setCommentLevel(commentLevel);
            comment.setCommentTime(commentTime);
            comment.setIfLevelDefault(ifLevelDefault);
            comment.setIfContentEmpty(ifContentEmpty);
            comment.setContent(content);
            comment.setPhotoNum(photoNum);
            comment.setPhotoPath(photoPath);
            comment.setHasReply(hasReply);
            comment.setReply(reply);
            comment.setAppendNum(hasAppend);
            comment.setValidScore(validScore);
            comment.setUsername(userName);
            comment.setRank(rank);
            comment.setVipLevel(vipLevel);
            comment.setGoodsName(goodsName);
            comment.setGoodsPrice(goodsPrice);
            comment.setGoodsColor(goodsColor);
            comment.setGoodsCombo(goodsCombo);
            comment.setGoodsOtherSku(goodsOtherSku);
            commentList.add(comment);
        }

        if (commentList.size() == 0) {
            TaskRunner.setStop(true);
            return;
        }

        int commentResult = TaobaoCommentDao.getInstance().insertList(commentList);
        logger.info("本次新增评论记录数: " + commentResult);
        int appendResult = TaobaoAppendDao.getInstance().insertList(appendList);
        logger.info("本次新增追评记录数: " + appendResult);

        int currentPageNum = jsonObject.get("currentPageNum").getAsInt();
        int maxPage = jsonObject.get("maxPage").getAsInt();

        if (currentPageNum == maxPage || isStop) {
            logger.info("插件检测任务执行完毕, 准备退出进程...");
            TaskRunner.setStop(true);
            return;
        }

        if (currentPageNum < maxPage) {
            String nextPageUrl = task.getUrl().replaceAll("page=\\d+", "page=" + (currentPageNum + 1));
            logger.info("nextPageUrl: " + nextPageUrl);
            boolean result = addUrl(nextPageUrl);
            if (!result) {
                logger.info("插件检测到重复链接, 准备退出进程...");
                TaskRunner.setStop(true);
            }
        }
    }

    @Override
    public boolean isDetailPage(String url) {
        return true;
    }

    private List<TaobaoAppendDO> resolveAppendList(JsonArray appendArray, Long sellerId,
                                                       Long commentId, Date commentTime) {
        List<TaobaoAppendDO> appendList = new ArrayList<TaobaoAppendDO>();
        for (int i = 0; i < appendArray.size(); i++) {
            JsonObject appendObject = appendArray.get(i).getAsJsonObject();
            Long appendId = appendObject.get("appendId").getAsLong();
            String content = appendObject.get("content").getAsString();
            Integer dayAfterConfirm = appendObject.get("dayAfterConfirm").getAsInt();
            Date appendTime = DateUtils.addDays(commentTime, dayAfterConfirm);

            JsonArray photoArray = appendObject.get("photos").getAsJsonArray();
            String photoPath = null;
            Integer photoNum = 0;
            if (photoArray != null && photoArray.size() > 0) {
                photoNum = photoArray.size();
                List<String> photoPathList = new ArrayList<String>();
                for (int j = 0; j < photoArray.size(); j++) {
                    photoPathList.add("common:" + photoArray.get(j).getAsJsonObject().get("url").getAsString());
                }
                photoPath = photoPathList.toString();
            }

            Integer hasReply = 0;
            String reply = null;
            JsonElement replyElement = appendObject.get("reply");
            if (!(replyElement instanceof JsonNull)) {
                hasReply = 1;
                reply = replyElement.getAsJsonObject().get("content").getAsString();
            }

            TaobaoAppendDO append = new TaobaoAppendDO();
            append.setSellerId(sellerId);
            append.setCommentId(commentId);
            append.setAppendId(appendId);
            append.setContent(content);
            append.setDayAfterConfirm(dayAfterConfirm);
            append.setAppendTime(appendTime);
            append.setPhotoNum(photoNum);
            append.setPhotoPath(photoPath);
            append.setHasReply(hasReply);
            append.setReply(reply);
            appendList.add(append);
        }
        return appendList;
    }
}
