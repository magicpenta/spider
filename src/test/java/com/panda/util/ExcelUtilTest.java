package com.panda.util;

import com.panda.config.ExportConstants;
import com.panda.dao.taobao.TaobaoCommentDao;
import com.panda.entity.taobao.TaobaoCommentDO;
import com.panda.entity.taobao.TaobaoSellerDO;
import org.junit.Test;

import java.util.List;

/**
 * excel工具类单元测试
 *
 * @author panda
 * @date 2018/11/13
 */
public class ExcelUtilTest {

    @Test
    public void testGetDataList() {
        List<String[]> dataList = ExcelUtil.getDataList("C:\\Users\\37516\\Desktop\\final\\test");
        System.out.println(dataList.size() + dataList.get(1)[1]);
        assert dataList.size() > 0;
    }

    @Test
    public void testExportExcel() {
        TaobaoSellerDO sellerInfo = new TaobaoSellerDO();
        sellerInfo.setFilename("19");
        sellerInfo.setSellerId(62684548L);
        sellerInfo.setGoodCommentsHalfYearAgo(7042);
        sellerInfo.setNormalCommentsHalfYearAgo(0);
        sellerInfo.setBadCommentsHalfYearAgo(1);
        Long sellerId = sellerInfo.getSellerId();
        System.out.println("当前seller: " + sellerId);
        List<TaobaoCommentDO> commentList = TaobaoCommentDao.getInstance().selectBySellerId(sellerId);

        System.out.println("seller[" + sellerId + "]共有评价数: " + commentList.size());
        if (commentList.size() == 0) {
            return;
        }

        String filePath = "C:\\Users\\37516\\Desktop\\final\\test";
        String fileName = sellerInfo.getFilename() + "old" + sellerId
                + "_" + sellerInfo.getGoodCommentsHalfYearAgo()
                + "_" + sellerInfo.getNormalCommentsHalfYearAgo()
                + "_" + sellerInfo.getBadCommentsHalfYearAgo()
                + ".xls";

        ExcelUtil.exportExcel(filePath, fileName, ExportConstants.HISTORY_DATA_FIELDS.split(","), commentList);
    }
}
