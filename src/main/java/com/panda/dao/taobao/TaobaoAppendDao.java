package com.panda.dao.taobao;

import com.panda.dao.MapperFactory;
import com.panda.dao.taobao.mapper.TaobaoAppendMapper;
import com.panda.entity.taobao.TaobaoAppendDO;
import tk.mybatis.mapper.entity.Example;

import java.util.List;

/**
 * 淘宝店铺追评 dao 接口
 *
 * @author panda
 * @date 2018/11/24
 */
public class TaobaoAppendDao {

    private static TaobaoAppendDao dao;

    private TaobaoAppendMapper mapper;

    private TaobaoAppendDao() {
        mapper = MapperFactory.createMapper(TaobaoAppendMapper.class);
    }

    public static TaobaoAppendDao getInstance() {
        if (dao == null) {
            synchronized (TaobaoAppendDao.class) {
                if (dao == null) {
                    dao = new TaobaoAppendDao();
                }
            }
        }
        return dao;
    }

    public List<TaobaoAppendDO> selectBySellerId(Long sellerId) {
        Example example = new Example(TaobaoAppendDO.class);
        example.and().andEqualTo("sellerId", sellerId);
        return mapper.selectByExample(example);
    }

    public int insertList(List<TaobaoAppendDO> appendList) {
        if (appendList == null || appendList.size() == 0) {
            return 0;
        }
        return mapper.insertList(appendList);
    }

}
