package com.panda.dao.taobao;

import com.panda.entity.taobao.TaobaoCommentDO;
import org.junit.Test;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

/**
 * 淘宝店铺评论 dao 接口单元测试
 *
 * @author panda
 * @date 2018/11/10
 */
public class TaobaoCommentDaoTest {

    @Test
    public void testSelectBySellerId() {
        List<TaobaoCommentDO> commentList = TaobaoCommentDao.getInstance().selectBySellerId(62684548L);
        assert commentList.size() >= 0;
    }

    @Test
    public void testInsertList() {
        List<TaobaoCommentDO> commentEntityList = new ArrayList<TaobaoCommentDO>();
        TaobaoCommentDO comment = new TaobaoCommentDO();
        comment.setSellerId(123l);
        comment.setSellerName("情趣用品店");
        comment.setCommentId(111l);
        comment.setCommentLevel(1);
        comment.setCommentTime(new Date());
        comment.setContent("很赞！");
        comment.setValidScore(1);
        comment.setUsername("panda");
        comment.setRank("150");
        comment.setGoodsName("避孕套");
        comment.setGoodsPrice("60");
        commentEntityList.add(comment);
        int result = TaobaoCommentDao.getInstance().insertList(commentEntityList);
        assert result > 0;
    }

}
