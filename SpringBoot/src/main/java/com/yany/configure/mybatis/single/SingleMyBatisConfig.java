package com.yany.configure.mybatis.single;

import com.alibaba.druid.pool.DruidDataSource;
import com.github.pagehelper.PageHelper;
import org.apache.ibatis.plugin.Interceptor;
import org.mybatis.spring.SqlSessionFactoryBean;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.boot.bind.RelaxedPropertyResolver;
import org.springframework.context.EnvironmentAware;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Primary;
import org.springframework.core.env.Environment;
import org.springframework.core.io.ClassPathResource;
import org.springframework.core.io.support.PathMatchingResourcePatternResolver;
import org.springframework.jdbc.datasource.DataSourceTransactionManager;
import com.github.pagehelper.PageInterceptor;

import javax.sql.DataSource;
import java.io.IOException;
import java.sql.SQLException;
import java.util.Properties;


/**
 * Created by yanyong on 2017/1/25.
 */
@Configuration
public class SingleMyBatisConfig implements EnvironmentAware {
    private final static Logger logger = LoggerFactory.getLogger(SingleMyBatisConfig.class);
    private static String MYBATIS_CONFIG = "mybatis-config.xml";
    //mybatis mapper resource 路径
    private static String MAPPER_PATH = "classpath:/com/yany/mapper/single/**.xml";

    private RelaxedPropertyResolver propertyResolver;

    @Override
    public void setEnvironment(Environment environment) {
        this.propertyResolver = new RelaxedPropertyResolver(environment, "spring.datasource.");
    }


    /**
     * @return
     * @Primary 优先方案，被注解的实现，优先被注入
     */
    @Primary
    @Bean(name = "singleDataSource")
    public DataSource singleDataSource() {
        logger.info("datasource url:{}", propertyResolver.getProperty("url"));

        DruidDataSource datasource = new DruidDataSource();
        datasource.setUrl(propertyResolver.getProperty("url"));
        datasource.setDriverClassName(propertyResolver.getProperty("driver-class-name"));
        datasource.setUsername(propertyResolver.getProperty("username"));
        datasource.setPassword(propertyResolver.getProperty("password"));


        datasource.setInitialSize(Integer.valueOf(propertyResolver.getProperty("initialSize")));
        datasource.setMinIdle(Integer.valueOf(propertyResolver.getProperty("minIdle")));
        datasource.setMaxWait(Long.valueOf(propertyResolver.getProperty("maxWait")));
        datasource.setMaxActive(Integer.valueOf(propertyResolver.getProperty("maxActive")));
        datasource.setTimeBetweenEvictionRunsMillis(Long.valueOf(propertyResolver.getProperty("timeBetweenEvictionRunsMillis")));
        datasource.setMinEvictableIdleTimeMillis(Long.valueOf(propertyResolver.getProperty("minEvictableIdleTimeMillis")));
        datasource.setValidationQuery(propertyResolver.getProperty("validationQuery"));
        datasource.setTestWhileIdle(Boolean.parseBoolean(propertyResolver.getProperty("testWhileIdle")));
        datasource.setTestOnBorrow(Boolean.parseBoolean(propertyResolver.getProperty("testOnBorrow")));
        datasource.setTestOnReturn(Boolean.parseBoolean(propertyResolver.getProperty("testOnReturn")));
        datasource.setPoolPreparedStatements(Boolean.parseBoolean(propertyResolver.getProperty("poolPreparedStatements")));
        datasource.setMaxPoolPreparedStatementPerConnectionSize(Integer.valueOf(propertyResolver.getProperty("maxPoolPreparedStatementPerConnectionSize")));

        try {
            datasource.setFilters(propertyResolver.getProperty("filters"));
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return datasource;

    }

    /**
     * 创建sqlSessionFactory实例
     *
     * @return
     */
    @Bean(name = "singleSqlSessionFactory")
    @Primary
    public SqlSessionFactoryBean createSqlSessionFactoryBean(@Qualifier("singleDataSource") DataSource singleDataSource) throws IOException {
        SqlSessionFactoryBean sqlSessionFactoryBean = new SqlSessionFactoryBean();
        //设置mybatis configuration 扫描路径
        sqlSessionFactoryBean.setConfigLocation(new ClassPathResource(MYBATIS_CONFIG));
        sqlSessionFactoryBean.setDataSource(singleDataSource);

        PathMatchingResourcePatternResolver pathMatchingResourcePatternResolver = new PathMatchingResourcePatternResolver();
        sqlSessionFactoryBean.setMapperLocations(pathMatchingResourcePatternResolver.getResources(MAPPER_PATH));

        //TODO
        PageInterceptor pageInterceptor = new PageInterceptor();
        Properties properties = new Properties();
//        properties.setProperty("dialect", "mysql");
        properties.setProperty("reasonable", "false");
        properties.setProperty("pageSizeZero", "true");
        pageInterceptor.setProperties(properties);


        sqlSessionFactoryBean.setPlugins(new Interceptor[]{pageInterceptor});
        return sqlSessionFactoryBean;
    }


    /**
     * 配置事务管理器
     */
    @Bean(name = "singleTransactionManager")
    @Primary
    public DataSourceTransactionManager transactionManager(@Qualifier("singleDataSource") DataSource singleDataSource) throws Exception {
        return new DataSourceTransactionManager(singleDataSource);
    }


}
