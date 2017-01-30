package com.yany.configure.mybatis.multi.aop;

import com.yany.configure.mybatis.multi.AdsDataSourceConfig;
import com.yany.configure.mybatis.multi.RdsDataSourceConfig;
import org.mybatis.spring.SqlSessionFactoryBean;
import org.springframework.boot.autoconfigure.AutoConfigureAfter;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.io.ClassPathResource;
import org.springframework.core.io.support.PathMatchingResourcePatternResolver;
import org.springframework.jdbc.datasource.DataSourceTransactionManager;

import javax.annotation.Resource;
import javax.sql.DataSource;
import java.util.HashMap;
import java.util.Map;

/**
 * Created by yanyong on 2017/1/30.
 */
@Configuration
@AutoConfigureAfter(value = {AdsDataSourceConfig.class, RdsDataSourceConfig.class})
public class AopMyBatisConfig {
    private static String MYBATIS_CONFIG = "mybatis-config.xml";
    private static String MAPPER_PATH = "classpath:/com/yany/mapper/multi/aop/**.xml";

    @Resource(name = "adsDataSource")
    DataSource adsDataSource;

    @Resource(name = "rdsDataSource")
    DataSource rdsDataSource;

    @Bean
    public DynamicDataSource setDataSource() {
        Map<Object, Object> targetDataSources = new HashMap<>();
        targetDataSources.put(DatabaseType.Ads, adsDataSource);
        targetDataSources.put(DatabaseType.Rds, rdsDataSource);

        DynamicDataSource dataSource = new DynamicDataSource();
        dataSource.setTargetDataSources(targetDataSources);
        dataSource.setDefaultTargetDataSource(rdsDataSource);// 默认的datasource设置为rdsDataSource
        return dataSource;
    }


    /**
     * 根据数据源创建SqlSessionFactory
     */
    @Bean(name = "aopSqlSessionFactory")
    public SqlSessionFactoryBean aopSqlSessionFactory(DynamicDataSource ds) throws Exception {
        SqlSessionFactoryBean sqlSessionFactoryBean = new SqlSessionFactoryBean();
        sqlSessionFactoryBean.setDataSource(ds);// 指定数据源(这个必须有，否则报错)
        // 下边两句仅仅用于*.xml文件，如果整个持久层操作不需要使用到xml文件的话（只用注解就可以搞定），则不加
        sqlSessionFactoryBean.setConfigLocation(new ClassPathResource(MYBATIS_CONFIG));
        PathMatchingResourcePatternResolver resolver = new PathMatchingResourcePatternResolver();
        sqlSessionFactoryBean.setMapperLocations(resolver.getResources(MAPPER_PATH));
        return sqlSessionFactoryBean;
    }


    /**
     * TODO 没有验证
     *
     * 配置事务管理器
     */
    @Bean(name = "aopTransactionManager")
    public DataSourceTransactionManager transactionManager(DynamicDataSource dataSource) throws Exception {
        return new DataSourceTransactionManager(dataSource);
    }


}
