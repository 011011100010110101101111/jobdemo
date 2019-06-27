package org.gitors.jobdemo.utils;

import org.springframework.beans.BeansException;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ApplicationContextAware;
import org.springframework.stereotype.Component;

/**
 * @author : liuwenlong
 * @desc : SpringContextUtil 工具类
 * @date : 2019-06-26 19:46
 */
@Component
public class SpringContextUtil implements ApplicationContextAware {

    private static   ApplicationContext applicationContext;

    @Override
    public void setApplicationContext(ApplicationContext applicationContext) throws BeansException {
        SpringContextUtil.applicationContext = applicationContext;
    }

    /**
     * 获取bean
     * @param name bean name
     * @return
     */
    public static Object getBean(String name) {
        return SpringContextUtil.applicationContext.getBean(name);
    }
}
