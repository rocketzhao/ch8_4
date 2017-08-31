package com.wisely.ch8_4.config.ds.dynamic;

import com.alibaba.druid.pool.DruidDataSourceFactory;
import org.apache.ibatis.session.SqlSessionFactory;
import org.mybatis.spring.SqlSessionFactoryBean;
import org.mybatis.spring.annotation.MapperScan;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.boot.bind.RelaxedPropertyResolver;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Primary;
import org.springframework.core.env.Environment;
import org.springframework.core.io.support.PathMatchingResourcePatternResolver;
import org.springframework.jdbc.datasource.DataSourceTransactionManager;

import javax.annotation.Resource;
import javax.sql.DataSource;
import java.util.HashMap;
import java.util.Map;
import java.util.Properties;

/**
 * springboot集成mybatis的基本入口
 * 1）创建数据源（如果采用的是默认的tomcat-jdbc数据源，则不需要）
 * 2）根据数据源创建SqlSessionFactory
 * 3）配置事务管理器，除非需要使用事务，否则不用配置
 *
 * Created by jun.zhao on 2017/8/30.
 */
@Configuration // 该注解类似于spring配置文件
@MapperScan(basePackages = "com.wisely.ch8_4.mapper")
public class MyBatisConfig {

    @Resource
    private Environment env;

    //连接池的补充设置，只加载一次
    private Map<String, Object> rpr;

    //通用方法：读取数据库连接的信息
    private Properties generateProperties(String dsName){
        Properties props = new Properties();
        RelaxedPropertyResolver resolver = new RelaxedPropertyResolver(env, dsName);
        props.put("driverClassName", resolver.getProperty("driverClassName"));
        props.put("url", resolver.getProperty("url"));
        props.put("username", resolver.getProperty("username"));
        props.put("password", resolver.getProperty("password"));
        if(rpr == null){
            rpr = new RelaxedPropertyResolver(env, "spring.datasource").getSubProperties(".");
        }
        props.putAll(rpr);
        return props;
    }

    /**
     * 1.创建数据源(数据源的名称：方法名可以取为XXXDataSource(),XXX为数据库名称,该名称也就是数据源的名称)
     */
    @Bean("ds0DataSource")
    public DataSource ds0DataSource() throws Exception {
        Properties props = generateProperties("custom.datasource.ds0.");
        return DruidDataSourceFactory.createDataSource(props);
    }

    @Bean("ds1DataSource")
    public DataSource ds1DataSource() throws Exception {
        Properties props = generateProperties("custom.datasource.ds1.");
        return DruidDataSourceFactory.createDataSource(props);
    }

    @Bean("ds2DataSource")
    public DataSource ds2DataSource() throws Exception {
        Properties props = generateProperties("custom.datasource.ds2.");
        return DruidDataSourceFactory.createDataSource(props);
    }

    @Bean("ds3DataSource")
    public DataSource ds3DataSource() throws Exception {
        Properties props = generateProperties("custom.datasource.ds3.");
        return DruidDataSourceFactory.createDataSource(props);
    }

    /**
     * @Primary 该注解表示在同一个接口有多个实现类可以注入的时候，默认选择哪一个，而不是让@autowire注解报错
     * @Qualifier 根据名称进行注入，通常是在具有相同的多个类型的实例的一个注入（例如有多个DataSource类型的实例）
     */
    @Bean("dsDataSource")
    @Primary
    public DynamicDataSource dsDataSource(@Qualifier("ds0DataSource") DataSource ds0DataSource,
                                            @Qualifier("ds1DataSource") DataSource ds1DataSource,
                                            @Qualifier("ds2DataSource") DataSource ds2DataSource,
                                            @Qualifier("ds3DataSource") DataSource ds3DataSource) {
        Map<Object, Object> targetDataSources = new HashMap<>();
        targetDataSources.put("ds0DataSource", ds0DataSource);
        targetDataSources.put("ds1DataSource", ds1DataSource);
        targetDataSources.put("ds2DataSource", ds2DataSource);
        targetDataSources.put("ds3DataSource", ds3DataSource);

        DynamicDataSourceContextHolder.dataSourceIds.add("ds0DataSource");
        DynamicDataSourceContextHolder.dataSourceIds.add("ds1DataSource");
        DynamicDataSourceContextHolder.dataSourceIds.add("ds2DataSource");
        DynamicDataSourceContextHolder.dataSourceIds.add("ds3DataSource");


        DynamicDataSource dataSource = new DynamicDataSource();
        dataSource.setTargetDataSources(targetDataSources);// 该方法是AbstractRoutingDataSource的方法
//        dataSource.setDefaultTargetDataSource(ds0DataSource);// 默认的datasource设置为ds0DataSource

        return dataSource;
    }

    /**
     * 2.根据数据源创建SqlSessionFactory
     */
    @Bean("dsSqlSessionFactory")
    public SqlSessionFactory dsSqlSessionFactory(@Qualifier("ds0DataSource") DataSource ds0DataSource,
                                                 @Qualifier("ds1DataSource") DataSource ds1DataSource,
                                                 @Qualifier("ds2DataSource") DataSource ds2DataSource,
                                                 @Qualifier("ds3DataSource") DataSource ds3DataSource) throws Exception {

        SqlSessionFactoryBean fb = new SqlSessionFactoryBean();
        fb.setDataSource(this.dsDataSource(ds0DataSource, ds1DataSource, ds2DataSource, ds3DataSource));// 指定数据源(这个必须有，否则报错)
        // 下边两句仅仅用于*.xml文件，如果整个持久层操作不需要使用到xml文件的话（只用注解就可以搞定），则不加
//        fb.setTypeAliasesPackage(env.getProperty("custom.datasource.mybatis.typeAliasesPackage"));// 指定基包
        fb.setMapperLocations(new PathMatchingResourcePatternResolver().getResources(
                env.getProperty("custom.datasource.ds.mapperLocations")));//

        return fb.getObject();
    }

    /**
     * 3.配置事务管理器
     */
    @Bean("dsTransactionManager")
    public DataSourceTransactionManager dsTransactionManager(@Qualifier("dsDataSource") DynamicDataSource dataSource) throws Exception {
        return new DataSourceTransactionManager(dataSource);
    }

}
