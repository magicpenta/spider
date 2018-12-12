package com.panda.dao;

import org.apache.ibatis.binding.MapperProxyFactory;
import org.apache.ibatis.session.SqlSession;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import tk.mybatis.mapper.common.Mapper;
import tk.mybatis.mapper.entity.Config;
import tk.mybatis.mapper.mapperhelper.MapperHelper;

/**
 * mapperFactory
 *
 * @author panda
 * @date 2018/12/10
 */
public class MapperFactory {

    /**
     * 创建一个Mapper的代理实现类
     */
    @SuppressWarnings("unchecked")
    public static <T> T createMapper(Class<? extends Mapper> clazz) {
        SqlSession sqlSession = MySqlSessionFactory.getSqlSession();
        // 创建一个MapperHelper
        MapperHelper mapperHelper = new MapperHelper();
        // 特殊配置
        Config config = new Config();
        // 主键自增回写方法,默认值MYSQL,详细说明请看文档
        config.setIDENTITY("MYSQL");
        // 支持getter和setter方法上的注解
        config.setEnableMethodAnnotation(true);
        // 设置 insert 和 update 中，是否判断字符串类型!=''
        config.setNotEmpty(true);
        // 校验Example中的类型和最终调用时Mapper的泛型是否一致
        config.setCheckExampleEntityClass(true);
        // 启用简单类型
        config.setUseSimpleType(true);
        // 枚举按简单类型处理
        config.setEnumAsSimpleType(true);
        // 自动处理关键字 - mysql
        config.setWrapKeyword("`{0}`");
        // 设置配置
        mapperHelper.setConfig(config);
        // 配置 mapperHelper 后，执行下面的操作
        mapperHelper.processConfiguration(MySqlSessionFactory.getConfiguration());
        MapperProxyFactory factory = new MapperProxyFactory(clazz);
        Mapper mapper = (Mapper) factory.newInstance(sqlSession);
        return (T) mapper;
    }

}
