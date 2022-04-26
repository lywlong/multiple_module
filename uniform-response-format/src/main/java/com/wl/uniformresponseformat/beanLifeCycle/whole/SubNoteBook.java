package com.wl.uniformresponseformat.beanLifeCycle.whole;

import com.wl.uniformresponseformat.beanLifeCycle.verify.AppleNoteBook;
import org.springframework.beans.factory.BeanClassLoaderAware;
import org.springframework.context.*;
import org.springframework.core.env.Environment;
import org.springframework.core.io.ResourceLoader;
import org.springframework.util.StringValueResolver;

/**
 * @author WangLong
 * @Description: 为了验证Bean完整的生命周期，需要新建一个SubNoteBook继承AppleNoteBook类
 * SubNoteBook类与AppleNoteBook是互补关系
 * @date 2022/4/13 16:31
 */
public class SubNoteBook extends AppleNoteBook implements BeanClassLoaderAware, EnvironmentAware , EmbeddedValueResolverAware
        , ResourceLoaderAware, ApplicationEventPublisherAware, MessageSourceAware {

    private String subNoteBookName;


    public String getSubNoteBookName() {
        return subNoteBookName;
    }

    public void setSubNoteBookName(String subNoteBookName) {
        System.out.println("set方法设置属性值："+subNoteBookName);
        this.subNoteBookName = subNoteBookName;
    }

    //设置bean的类加载器
    @Override
    public void setBeanClassLoader(ClassLoader classLoader) {
        System.out.println("SubNoteBook.<setBeanClassLoader> invoke");
    }

    //应用事件发布器
    @Override
    public void setApplicationEventPublisher(ApplicationEventPublisher applicationEventPublisher) {
        System.out.println("SubNoteBook.<setApplicationEventPublisher> invoke");
    }

    @Override
    public void setEmbeddedValueResolver(StringValueResolver resolver) {
        System.out.println("SubNoteBook.<setEmbeddedValueResolver> 属性解析器invoke");
    }

    //环境设置
    @Override
    public void setEnvironment(Environment environment) {
        System.out.println("SubNoteBook.<setEnvironment> 设置环境invoke");
    }

    @Override
    public void setMessageSource(MessageSource messageSource) {
        System.out.println("SubNoteBook.<setMessageSource> invoke");
    }

    @Override
    public void setResourceLoader(ResourceLoader resourceLoader) {
        System.out.println("SubNoteBook.<setResourceLoader> invoke");
    }
}
