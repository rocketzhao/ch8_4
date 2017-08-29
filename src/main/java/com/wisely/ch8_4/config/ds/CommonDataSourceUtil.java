package com.wisely.ch8_4.config.ds;

import com.alibaba.druid.pool.DruidDataSource;

/**
 * 处理数据源的公共类
 *
 * Created by jun.zhao on 2017/8/29.
 */
public class CommonDataSourceUtil {

    public static void generateDataSource(DruidDataSource dataSource, String driverClass, String url, String user, String password){
        //driverClass, url, user, password
        dataSource.setDriverClassName(driverClass);
        dataSource.setUrl(url);
        dataSource.setUsername(user);
        dataSource.setPassword(password);
    }
}
