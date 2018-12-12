package com.panda.dao.taobao;

import com.panda.entity.taobao.TaobaoAppendDO;
import com.panda.entity.taobao.TaobaoCommentDO;
import org.junit.Test;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

/**
 * 淘宝店铺追评 dao 接口单元测试
 *
 * @author panda
 * @date 2018/11/10
 */
public class TaobaoAppendDaoTest {

    @Test
    public void testSelectBySellerId() {
        List<TaobaoAppendDO> appendList = TaobaoAppendDao.getInstance().selectBySellerId(62684548L);
        assert appendList.size() >= 0;
    }

    @Test
    public void testInsertList() {
        List<TaobaoAppendDO> appendList = new ArrayList<TaobaoAppendDO>();
        TaobaoAppendDO append = new TaobaoAppendDO();
        append.setSellerId(123l);
        append.setCommentId(111l);
        append.setAppendId(777L);
        append.setContent("很赞！");
        append.setDayAfterConfirm(10);
        append.setAppendTime(new Date());
        appendList.add(append);
        int result = TaobaoAppendDao.getInstance().insertList(appendList);
        assert result > 0;
    }

}
