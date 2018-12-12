package com.panda.service;

import com.panda.config.ExportConstants;
import com.panda.dao.taobao.TaobaoAppendDao;
import com.panda.dao.taobao.TaobaoCommentDao;
import com.panda.dao.taobao.TaobaoSellerDao;
import com.panda.entity.taobao.TaobaoAppendDO;
import com.panda.entity.taobao.TaobaoCommentDO;
import com.panda.entity.taobao.TaobaoSellerDO;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import com.panda.util.ExcelUtil;

import java.util.List;

/**
 * excel服务
 *
 * @author panda
 * @date 2018/11/11
 */
public class ExcelService {

    private static final Logger logger = LoggerFactory.getLogger(ExcelService.class);

    public static void main(String[] args) {
        String exportFilePath = ExportConstants.EXPORT_FILE_PATH;
        String[] recentDataFields = ExportConstants.RECENT_DATA_FIELDS.split(",");
        String[] historyDataFields = ExportConstants.HISTORY_DATA_FIELDS.split(",");
        String[] appendFields = ExportConstants.APPEND_FIELDS.split(",");
        Integer exportDataType = ExportConstants.EXPORT_DATA_TYPE;
        if (exportDataType == 0) {
            export(exportFilePath, exportDataType, historyDataFields, appendFields);
        } else {
            export(exportFilePath, exportDataType, recentDataFields, appendFields);
        }
    }

    public static void export(String filePath, Integer type, String[] fields, String[] appendFields) {
        List<TaobaoSellerDO> sellerInfoList = TaobaoSellerDao.getInstance().selectByBatchNo(2);
        logger.info("共计seller数: " + sellerInfoList.size());
        for (TaobaoSellerDO sellerInfo : sellerInfoList) {
            Long sellerId = sellerInfo.getSellerId();
            logger.info("当前seller: " + sellerId);
            List<TaobaoCommentDO> commentList = TaobaoCommentDao.getInstance().selectBySellerId(sellerId);

            logger.info("seller[{}]共有评价数: {}", sellerId, commentList.size());
            if (commentList.size() == 0) {
                continue;
            }

            String fileType = type == 0 ? "old" : "new";
            Integer isTianmao = sellerInfo.getIsTianmao();
            String fileName;
            if (isTianmao == 0) {
                fileName = sellerInfo.getFilename() + fileType + sellerId
                        + "_" + sellerInfo.getGoodCommentsHalfYearAgo()
                        + "_" + sellerInfo.getNormalCommentsHalfYearAgo()
                        + "_" + sellerInfo.getBadCommentsHalfYearAgo()
                        + ".xls";
            } else {
                fileName = sellerInfo.getFilename() + fileType + sellerId + ".xls";
            }

            ExcelUtil.exportExcel(filePath, fileName, fields, commentList);

            if (type == 1) {
                List<TaobaoAppendDO> appendList = TaobaoAppendDao.getInstance().selectBySellerId(sellerId);
                if (appendList != null && appendList.size() > 0) {
                    fileName = fileName.replace(".xls", "_append.xls");
                    ExcelUtil.exportExcel(filePath, fileName, appendFields, appendList);
                }
            }
        }
    }

}
