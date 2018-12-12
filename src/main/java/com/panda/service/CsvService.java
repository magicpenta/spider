package com.panda.service;

import com.panda.dao.taobao.TaobaoCommentDao;
import com.panda.dao.taobao.TaobaoSellerDao;
import com.panda.entity.taobao.TaobaoCommentDO;
import com.panda.entity.taobao.TaobaoSellerDO;
import com.panda.filter.CsvFileFilter;
import org.apache.commons.lang3.StringUtils;
import org.apache.commons.lang3.time.DateUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import com.panda.util.CommonUtil;
import com.panda.util.CsvUtil;

import java.io.File;
import java.text.ParseException;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

/**
 * csv服务
 *
 * @author panda
 * @date 2018/11/13
 */
public class CsvService {

    private static final Logger logger = LoggerFactory.getLogger(CsvService.class);

    public static void main(String[] args) {
        File directory = new File("C:\\Users\\37516\\Desktop\\final\\Data_ixus");
        if (directory.isDirectory()) {
            File[] fileList = directory.listFiles(new CsvFileFilter());
            logger.info("读取文件数: " + fileList.length);
            for (File file : fileList) {
                List<String[]> dataList = CsvUtil.readCsv(file.getAbsolutePath());
                if (dataList != null && dataList.size() > 0) {
                    logger.info("文件[{}]共有记录数: {}", file.getName(), dataList.size());
                    List<TaobaoCommentDO> commentList = new ArrayList<TaobaoCommentDO>();
                    String fileName = file.getName();
                    fileName = CommonUtil.match(fileName, "(\\d+)old")[1];
                    TaobaoSellerDO sellerInfo = TaobaoSellerDao.getInstance().selectByFileName(fileName);
                    if (sellerInfo == null) {
                        logger.error("找不到filename为[{}]的卖家, 检查店铺是否存在...", fileName);
                        continue;
                    }
                    Long sellerId = sellerInfo.getSellerId();
                    String sellerName = sellerInfo.getSellerName();
                    for (int i = 0; i < dataList.size(); i++) {
                        String timePrefix = dataList.get(i)[0];
                        String timeSuffix = dataList.get(i)[1];
                        timeSuffix = StringUtils.leftPad(timeSuffix, 6, "0");
                        Date commentTime = null;
                        try {
                            commentTime = DateUtils.parseDate(timePrefix + timeSuffix, "yyyyMMddHHmmss");
                        } catch (ParseException e) {
                            logger.error("解析时间异常: ", e);
                        }
                        Long commentId = sellerId + commentTime.getTime() + i;
                        Integer commentLevel = Integer.valueOf(dataList.get(i)[2]);
                        String goodsPrice = dataList.get(i)[3];
                        String goodsName = dataList.get(i)[4];
                        String content = dataList.get(i)[5];
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
                        String reply = null;
                        if (content.contains("[回复]")) {
                            content = CommonUtil.match(content, "(.*?)\\[回复\\]")[1];
                            reply = CommonUtil.match(content, "(\\[回复\\].*)")[1];
                        }
                        Integer hasReply = 0;
                        if (StringUtils.isNotEmpty(reply)) {
                            hasReply = 1;
                        }
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
                        comment.setGoodsName(goodsName);
                        comment.setGoodsPrice(goodsPrice);
                        commentList.add(comment);
                    }
                    int result = TaobaoCommentDao.getInstance().insertList(commentList);
                    logger.info("文件[{}]新增评论数: {}", file.getName(), result);
                }
//                try {
//                    Thread.sleep(1000);
//                } catch (InterruptedException e) {
//                    e.printStackTrace();
//                }
            }
        }
    }
}
