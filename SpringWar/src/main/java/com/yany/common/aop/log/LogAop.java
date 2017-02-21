package com.yany.common.aop.log;

import com.yany.common.aop.WebContextUtil;
import org.apache.commons.lang3.builder.ToStringBuilder;
import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.Signature;
import org.aspectj.lang.reflect.MethodSignature;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * Created by yanyong on 2017/2/21.
 */
public class LogAop {
    private static final Logger logger = LoggerFactory.getLogger(LogAop.class);


    /**
     * 手动控制调用核心业务逻辑，以及调用前和调用后的处理,
     * <p>
     * 注意：当核心业务抛异常后，立即退出，转向After Advice 执行完毕After Advice，再转到Throwing Advice
     *
     * @return
     * @throws Throwable
     */
    public Object doAround(ProceedingJoinPoint joinPoint) throws Throwable {
        HttpServletRequest request = WebContextUtil.getRequest();
        HttpServletResponse response = WebContextUtil.getResponse();

        boolean flage = true;
        Object[] args = joinPoint.getArgs();

        Object returnObj = null;

        Signature joinSignature = joinPoint.getSignature();
        if (joinSignature != null && joinSignature instanceof MethodSignature) {
            MethodSignature methodSignature = (MethodSignature) joinSignature;
            Log log = methodSignature.getMethod().getAnnotation(Log.class);
            if (log != null && log.name().equals("controller")) {
                if (log.beforeLog()) {
                    logger.info("调用接口: {}[{}] {}", new Object[]{request.getRequestURI(),
                            methodSignature.getName(), ToStringBuilder.reflectionToString(args)});
                }
            }

        }
        returnObj = joinPoint.proceed(args);
        return returnObj;


    }

    /**
     * 核心业务逻辑调用正常退出后，不管是否有返回值，正常退出后，均执行此Advice
     *
     * @param joinPoint
     */
    public void doReturn(JoinPoint joinPoint) {
    }

    /**
     * 核心业务逻辑调用异常退出后，执行此Advice，处理错误信息
     *
     * @param joinPoint
     * @param ex
     */
    public void doThrowing(JoinPoint joinPoint, Throwable ex) {
        logger.error("处理通知时发现异常", ex.getMessage(), ex);
    }


}