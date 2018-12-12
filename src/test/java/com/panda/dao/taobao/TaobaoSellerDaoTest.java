package com.panda.dao.taobao;

import com.panda.entity.taobao.TaobaoSellerDO;
import org.junit.Test;

import java.util.ArrayList;
import java.util.List;

/**
 * 淘宝店铺信息单元测试
 *
 * @author panda
 * @date 2018/12/10
 */
public class TaobaoSellerDaoTest {

    @Test
    public void testSelectByBatchNo() {
        List<TaobaoSellerDO> sellerInfoList = TaobaoSellerDao.getInstance().selectByBatchNo(1);
        assert sellerInfoList.size() >= 0;
    }

    @Test
    public void testSelectByFileName() {
        TaobaoSellerDO sellerInfo = TaobaoSellerDao.getInstance().selectByFileName("238");
        assert sellerInfo.getFilename().equals("238");
    }

    @Test
    public void testInsertList() {
        List<TaobaoSellerDO> sellerInfoList = new ArrayList<TaobaoSellerDO>();
        TaobaoSellerDO sellerInfo = new TaobaoSellerDO();
        sellerInfo.setSellerId(111L);
        sellerInfo.setSellerName("panda");
        sellerInfo.setIsTianmao(1);
        sellerInfoList.add(sellerInfo);
        int result = TaobaoSellerDao.getInstance().insertList(sellerInfoList);
        assert result >= 0;
    }
}
