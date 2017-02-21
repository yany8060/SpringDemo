package com.yany.common.filter.context;

import com.alibaba.fastjson.JSON;
import com.yany.common.filter.annotation.YanYUrl;
import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.BeansException;
import org.springframework.beans.factory.BeanDefinitionStoreException;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ApplicationContextAware;
import org.springframework.core.MethodIntrospector;
import org.springframework.core.annotation.AnnotationUtils;
import org.springframework.core.io.Resource;
import org.springframework.core.io.support.PathMatchingResourcePatternResolver;
import org.springframework.core.io.support.ResourcePatternResolver;
import org.springframework.core.type.classreading.CachingMetadataReaderFactory;
import org.springframework.core.type.classreading.MetadataReader;
import org.springframework.core.type.classreading.MetadataReaderFactory;
import org.springframework.stereotype.Controller;
import org.springframework.util.ClassUtils;
import org.springframework.util.ReflectionUtils;
import org.springframework.web.method.HandlerMethod;
import org.springframework.web.servlet.mvc.method.RequestMappingInfo;
import org.springframework.web.servlet.mvc.method.annotation.RequestMappingHandlerMapping;

import java.io.IOException;
import java.lang.reflect.Method;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;
import java.util.Set;

/**
 * Created by yanyong on 2017/2/20.
 */
public class CustomerAnnotaionScan implements ApplicationContextAware {
    private final static Logger logger = LoggerFactory.getLogger(CustomerAnnotaionScan.class);

    private String basePackage;

    private String resourcePattern = "**/*.class";

    private ResourcePatternResolver resourcePatternResolver = new PathMatchingResourcePatternResolver();

    private MetadataReaderFactory metadataReaderFactory =
            new CachingMetadataReaderFactory(this.resourcePatternResolver);

    /**
     * 判断方式是否包含该注解
     */
    private ReflectionUtils.MethodFilter MODEL_ATTRIBUTE_METHODS = new ReflectionUtils.MethodFilter() {
        @Override
        public boolean matches(Method method) {
            return (AnnotationUtils.findAnnotation(method, YanYUrl.class) != null);
        }
    };

    @Override
    public void setApplicationContext(ApplicationContext applicationContext) throws BeansException {
        logger.info("setApplicationContext start");
        RequestMappingHandlerMapping handlerMapping = applicationContext.getBean(RequestMappingHandlerMapping.class);
        Map<Method, Set<String>> mapMethodUrl = mappingParse(handlerMapping);
        try {
            String packageSearchPath = ResourcePatternResolver.CLASSPATH_ALL_URL_PREFIX + ClassUtils.convertClassNameToResourcePath(basePackage) + "/" + this.resourcePattern;
            Resource[] resources = this.resourcePatternResolver.getResources(packageSearchPath);
            for (Resource resource : resources) {
                if (resource.isReadable()) {
                    MetadataReader metadataReader = this.metadataReaderFactory.getMetadataReader(resource);
                    if (metadataReader.getAnnotationMetadata().isAnnotated(Controller.class.getName())) {
                        addWhiteSet(metadataReader, mapMethodUrl);
                    }
                }
            }
        } catch (IOException e) {
            throw new BeanDefinitionStoreException("I/O failure during classpath scanning", e);
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }

        logger.info("white url set:{}", JSON.toJSONString(UrlSet.getInstance().getWhilteUrlSet()));
        logger.info("setApplicationContext end");
    }

    /**
     * Map<RequestMappingInfo, HandlerMethod> 转换
     *
     * @param handlerMapping
     * @return
     */
    private Map<Method, Set<String>> mappingParse(RequestMappingHandlerMapping handlerMapping) {
        Map<Method, Set<String>> mapMethodUrl = new HashMap<>();
        Map<RequestMappingInfo, HandlerMethod> map = handlerMapping.getHandlerMethods();
        Iterator<Map.Entry<RequestMappingInfo, HandlerMethod>> iterable = map.entrySet().iterator();
        while (iterable.hasNext()) {
            Map.Entry<RequestMappingInfo, HandlerMethod> entry = iterable.next();
            RequestMappingInfo requestMappingInfo = entry.getKey();
            Set<String> setUrls = requestMappingInfo.getPatternsCondition().getPatterns();
            HandlerMethod handleMethod = entry.getValue();
            Method controllerMethod = handleMethod.getMethod();
            mapMethodUrl.put(controllerMethod, setUrls);
        }
        return mapMethodUrl;
    }

    /**
     * @param metadataReader
     * @param mapMethodUrl
     * @throws ClassNotFoundException
     */
    private void addWhiteSet(MetadataReader metadataReader, Map<Method, Set<String>> mapMethodUrl) throws ClassNotFoundException {
        String className = metadataReader.getClassMetadata().getClassName();
        logger.info("customer scanner class name:{}", className);
        Class<?> clazz = Class.forName(className);
        Set<Method> attrMethods = MethodIntrospector.selectMethods(clazz, MODEL_ATTRIBUTE_METHODS);
        Iterator<Method> iterable = attrMethods.iterator();
        while (iterable.hasNext()) {
            Method method = iterable.next();

            String tianlianUrlValue = method.getAnnotation(YanYUrl.class).value();
            logger.info("TianlianUrl annotation value:{}", tianlianUrlValue);
            if (StringUtils.isBlank(tianlianUrlValue) || !UrlSet.WHITETAG.equals(tianlianUrlValue)) {
                continue;
            }
            logger.info("white url controller method nanme:{}", method.getName());
            Set<String> whilteUrlSet = mapMethodUrl.get(method);
            if (whilteUrlSet == null || whilteUrlSet.size() == 0) {
                continue;
            }
            logger.info("method {} white url sets:{}", method.getName(), JSON.toJSONString(whilteUrlSet));
            UrlSet.getInstance().getWhilteUrlSet().addAll(whilteUrlSet);
        }
    }


    public void setBasePackage(String basePackage) {
        this.basePackage = basePackage;
    }
}
