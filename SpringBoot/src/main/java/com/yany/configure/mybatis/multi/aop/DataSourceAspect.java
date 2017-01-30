package com.yany.configure.mybatis.multi.aop;

import com.yany.service.IAdsAopService;
import com.yany.service.impl.AdsAopServiceImpl;
import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.aspectj.lang.annotation.Pointcut;
import org.springframework.stereotype.Component;

/**
 * TODO 这边需要灵活定义规则: 简单点可以使用分包; 或者就是在JoinPoint 这边做些文章 ......
 * <p>
 * Created by yanyong on 2017/1/30.
 */
@Aspect
@Component
public class DataSourceAspect {
    /**
     * 使用空方法定义切点表达式
     */
    @Pointcut("execution(* com.yany.service.**.*(..))")
    public void declareJointPointExpression() {
    }

    @Before("declareJointPointExpression()")
    public void setDataSourceKey(JoinPoint point) {
        if (point.getTarget() instanceof IAdsAopService ||
                point.getTarget() instanceof AdsAopServiceImpl) {
            //根据连接点所属的类实例，动态切换数据源
            System.out.println("IAdsAopService Aspect");
            DatabaseContextHolder.setDatabaseType(DatabaseType.Ads);
        } else {//连接点所属的类实例是（当然，这一步也可以不写，因为defaultTargertDataSource就是该类所用的rdsDataSource）
            System.out.println("IRdsAopService Aspect");
            DatabaseContextHolder.setDatabaseType(DatabaseType.Rds);
        }

    }
}
