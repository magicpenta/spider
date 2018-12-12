package com.panda.dao.taobao;

import com.panda.dao.MapperFactory;
import com.panda.dao.taobao.mapper.TaobaoCommentMapper;
import com.panda.entity.taobao.TaobaoCommentDO;
import tk.mybatis.mapper.entity.Example;

import java.util.List;

/**
 * 淘宝店铺评论 dao 接口
 */
public class TaobaoCommentDao {

    private static TaobaoCommentDao dao;

    private TaobaoCommentMapper mapper;

    private TaobaoCommentDao() {
        mapper = MapperFactory.createMapper(TaobaoCommentMapper.class);
    }

    public static TaobaoCommentDao getInstance() {
        if (dao == null) {
            synchronized (TaobaoCommentDao.class) {
                if (dao == null) {
                    dao = new TaobaoCommentDao();
                }
            }
        }
        return dao;
    }

    public List<TaobaoCommentDO> selectBySellerId(Long sellerId) {
        Example example = new Example(TaobaoCommentDO.class);
        example.and().andEqualTo("sellerId", sellerId);
        return mapper.selectByExample(example);
    }

    public int insertList(List<TaobaoCommentDO> commentList) {
        if (commentList == null || commentList.size() == 0) {
            return 0;
        }
        return mapper.insertList(commentList);
    }
}