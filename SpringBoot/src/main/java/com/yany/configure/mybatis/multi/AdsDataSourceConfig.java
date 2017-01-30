package com.yany.configure.mybatis.multi;

import com.alibaba.druid.pool.DruidDataSource;
import org.mybatis.spring.SqlSessionFactoryBean;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.io.ClassPathResource;
import org.springframework.core.io.support.PathMatchingResourcePatternResolver;
import org.springframework.jdbc.datasource.DataSourceTransactionManager;

import javax.sql.DataSource;

/**
 * Created by yanyong on 2017/1/29.
 */
@Configuration
public class AdsDataSourceConfig {
    private static String MYBATIS_CONFIG = "mybatis-config.xml";
    private static String MAPPER_PATH = "classpath:/com/yany/mapper/multi/ads/**.xml";


    @Bean(name = "adsDataSource")
    public DataSource adsDataSource() {
        DruidDataSource dataSource = new DruidDataSource();
        dataSource.setDriverClassName("com.mysql.jdbc.Driver");
        dataSource.setUrl(dbUrl);
        dataSource.setUsername(dbUserName);
        dataSource.setPassword(dbPassWord);
        dataSource.setDriverClassName(dbDriverClassName);
        //TODO 省略配置
        return dataSource;
    }

    /**
     * 分包
     *
     * @param adsDataSource
     * @return
     * @throws Exception
     */
    @Bean(name = "adsSqlSessionFactory")
    public SqlSessionFactoryBean rdsSqlSessionFactory(@Qualifier("adsDataSource") DataSource adsDataSource) throws Exception {
        final SqlSessionFactoryBean sessionFactory = new SqlSessionFactoryBean();
        sessionFactory.setConfigLocation(new ClassPathResource(MYBATIS_CONFIG));
        sessionFactory.setDataSource(adsDataSource);
        PathMatchingResourcePatternResolver pathMatchingResourcePatternResolver = new PathMatchingResourcePatternResolver();
        sessionFactory.setMapperLocations(pathMatchingResourcePatternResolver.getResources(MAPPER_PATH));
        return sessionFactory;
    }


    // TODO 没有验证
    @Bean(name = "adsTransactionManager")
    public DataSourceTransactionManager adsTransactionManager(@Qualifier("adsDataSource") DataSource adsDataSource) {
        return new DataSourceTransactionManager(adsDataSource);
    }

    @Value("${AdsDatasource.url}")
    private String dbUrl;

    @Value("${AdsDatasource.username}")
    private String dbUserName;
    @Value("${AdsDatasource.password}")
    private String dbPassWord;
    @Value("${AdsDatasource.driver-class-name}")
    private String dbDriverClassName;

    @Value("${BaseDatasourceConfig.type}")
    private String dbType;

    @Value("${BaseDatasourceConfig.initialSize}")
    private String dbInitialSize;

    @Value("${BaseDatasourceConfig.minIdle}")
    private String dbMinIdle;

    @Value("${BaseDatasourceConfig.maxActive}")
    private String dbMaxActive;

    @Value("${BaseDatasourceConfig.maxWait}")
    private String dbMaxWait;

    @Value("${BaseDatasourceConfig.timeBetweenEvictionRunsMillis}")
    private String dbTimeBetweenEvictionRunsMillis;

    @Value("${BaseDatasourceConfig.minEvictableIdleTimeMillis}")
    private String dbMinEvictableIdleTimeMillis;

    @Value("${BaseDatasourceConfig.validationQuery}")
    private String dbValidationQuery;

    @Value("${BaseDatasourceConfig.testWhileIdle}")
    private String dbTestWhileIdle;

    @Value("${BaseDatasourceConfig.testOnBorrow}")
    private String dbTestOnBorrow;

    @Value("${BaseDatasourceConfig.testOnReturn}")
    private String dbTestOnReturn;

    @Value("${BaseDatasourceConfig.poolPreparedStatements}")
    private String dbPoolPreparedStatements;

    @Value("${BaseDatasourceConfig.maxPoolPreparedStatementPerConnectionSize}")
    private String dbMaxPoolPreparedStatementPerConnectionSize;

    @Value("${BaseDatasourceConfig.filters}")
    private String dbFilters;

    @Value("${BaseDatasourceConfig.connectionProperties}")
    private String dbConnectionProperties;

    @Value("${BaseDatasourceConfig.useGlobalDataSourceStat}")
    private String dbUseGlobalDataSourceStat;

}
