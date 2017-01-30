package com.yany.configure.mybatis;

import com.yany.configure.mybatis.multi.annotation.AdsRepository;
import com.yany.configure.mybatis.multi.annotation.RdsRepository;
import org.mybatis.spring.mapper.MapperScannerConfigurer;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * 多数据源有三种方式:
 * 1:分包: 不同数据源的在不同的目录下;事务的回滚需要创建根据数据源创建
 * 2:AOP: aop注解切面需要在Service层进行数据源切换;事务可以将多个数据源放在一个事务中;
 * (但是流程中需要操作多个库是需要创建多个服务并进行相互调用)
 * 3:注解: 。。。。 待了解(通过扫描基本包中的类,根据注解进行注册。。。);
 * <p>
 * <p>
 * Created by yanyong on 2017/1/29.
 */
@Configuration
public class MapperScannerConfig {

    /**
     * 单数据源配置
     *
     * @return
     */
    @Bean
    public MapperScannerConfigurer createSingleMapperScannerConfigurer() {
        System.out.println("singleDataSource");
        MapperScannerConfigurer mapperScannerConfigurer = new MapperScannerConfigurer();
        mapperScannerConfigurer.setBasePackage("com.yany.dao.single");
        mapperScannerConfigurer.setSqlSessionFactoryBeanName("singleSqlSessionFactory");
        return mapperScannerConfigurer;
    }

    /**
     * 以分包的形式进行多数据源配置
     *
     * @return
     */
    @Bean
    public MapperScannerConfigurer createAdsMapperScannerConfigurer() {
        System.out.println("adsDataSource");
        MapperScannerConfigurer mapperScannerConfigurer = new MapperScannerConfigurer();
        mapperScannerConfigurer.setBasePackage("com.yany.dao.multi.ads");
        mapperScannerConfigurer.setSqlSessionFactoryBeanName("adsSqlSessionFactory");
        return mapperScannerConfigurer;
    }

    /**
     * 以分包的形式进行多数据源配置
     *
     * @return
     */
    @Bean
    public MapperScannerConfigurer createRdsMapperScannerConfigurer() {
        System.out.println("rdsDataSource");
        MapperScannerConfigurer mapperScannerConfigurer = new MapperScannerConfigurer();
        mapperScannerConfigurer.setBasePackage("com.yany.dao.multi.rds");
        mapperScannerConfigurer.setSqlSessionFactoryBeanName("rdsSqlSessionFactory");
        return mapperScannerConfigurer;
    }

    /**
     * 以AOP切面的方式 进行多数据源配置
     *
     * @return
     */
    @Bean
    public MapperScannerConfigurer createAopMapperScannerConfigurer() {
        System.out.println("aopDataSource");
        MapperScannerConfigurer mapperScannerConfigurer = new MapperScannerConfigurer();
        mapperScannerConfigurer.setBasePackage("com.yany.dao.multi.aop");
        mapperScannerConfigurer.setSqlSessionFactoryBeanName("aopSqlSessionFactory");
        return mapperScannerConfigurer;
    }


    /**
     * 以注解的方式 进行多数据源配置
     *
     * @return
     */
    @Bean
    public MapperScannerConfigurer createAnnotatationAdsMapperScannerConfigurer() {
        MapperScannerConfigurer mapperScannerConfigurer = new MapperScannerConfigurer();
        mapperScannerConfigurer.setBasePackage("com.yany.dao.multi.annotation");
        mapperScannerConfigurer.setSqlSessionFactoryBeanName("annotationAdsSqlSessionFactory");
        mapperScannerConfigurer.setAnnotationClass(AdsRepository.class);
        return mapperScannerConfigurer;
    }

    /**
     * 以注解的方式 进行多数据源配置
     *
     * @return
     */
    @Bean
    public MapperScannerConfigurer createAnnotatationRdsMapperScannerConfigurer() {
        MapperScannerConfigurer mapperScannerConfigurer = new MapperScannerConfigurer();
        mapperScannerConfigurer.setBasePackage("com.yany.dao.multi.annotation");
        mapperScannerConfigurer.setSqlSessionFactoryBeanName("annotationRdsSqlSessionFactory");
        mapperScannerConfigurer.setAnnotationClass(RdsRepository.class);
        return mapperScannerConfigurer;
    }

}
