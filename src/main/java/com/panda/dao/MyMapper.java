package com.panda.dao;

import tk.mybatis.mapper.common.Mapper;
import tk.mybatis.mapper.common.special.InsertListMapper;

/**
 * 基础mapper
 *
 * @author panda
 * @date 2018/12/11
 */
public interface MyMapper<T> extends Mapper<T>, InsertListMapper<T> {
}
