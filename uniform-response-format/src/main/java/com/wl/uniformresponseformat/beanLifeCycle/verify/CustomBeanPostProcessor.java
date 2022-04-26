package com.wl.uniformresponseformat.beanLifeCycle.verify;

import org.springframework.beans.BeansException;
import org.springframework.beans.factory.config.BeanPostProcessor;

/**
 * @author WangLong
 * @Description:
 * @date 2022/4/13 16:03
 */
public class CustomBeanPostProcessor implements BeanPostProcessor {

    // 容器加载的时候会加载一些其他的bean，会调用初始化前和初始化后方法
    // 这次只关注book(bean)的生命周期
    @Override
    public Object postProcessBeforeInitialization(Object bean, String beanName) throws BeansException {
        if (bean instanceof AppleNoteBook) {
            System.out.println("CustomBeanPostProcessor.postProcess<Before>Initialization exe");
        }
        return bean;
    }

    @Override
    public Object postProcessAfterInitialization(Object bean, String beanName) throws BeansException {
        if (bean instanceof AppleNoteBook) {
            System.out.println("CustomBeanPostProcessor.postProcess<After>Initialization exe");
        }
        return bean;
    }
}
