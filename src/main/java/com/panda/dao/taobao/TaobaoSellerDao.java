package com.panda.dao.taobao;

import com.panda.dao.MapperFactory;
import com.panda.dao.taobao.mapper.TaobaoSellerMapper;
import com.panda.entity.taobao.TaobaoSellerDO;
import tk.mybatis.mapper.entity.Example;

import java.util.List;

/**
 * 淘宝店铺评论 dao 接口
 */
public class TaobaoSellerDao {

    private static TaobaoSellerDao dao;

    private TaobaoSellerMapper mapper;

    private TaobaoSellerDao() {
        mapper = MapperFactory.createMapper(TaobaoSellerMapper.class);
    }

    public static TaobaoSellerDao getInstance() {
        if (dao == null) {
            synchronized (TaobaoSellerDao.class) {
                if (dao == null) {
                    dao = new TaobaoSellerDao();
                }
            }
        }
        return dao;
    }

    public List<TaobaoSellerDO> selectByBatchNo(Integer batchNo) {
        Example example = new Example(TaobaoSellerDO.class);
        example.and().andEqualTo("batchNo", batchNo);
        return mapper.selectByExample(example);
    }

    public TaobaoSellerDO selectByFileName(String fileName) {
        Example example = new Example(TaobaoSellerDO.class);
        example.and().andEqualTo("filename", fileName);
        return mapper.selectOneByExample(example);
    }

    public int insertList(List<TaobaoSellerDO> sellerInfoList) {
        if (sellerInfoList == null || sellerInfoList.size() == 0) {
            return 0;
        }
        return mapper.insertList(sellerInfoList);
    }
}