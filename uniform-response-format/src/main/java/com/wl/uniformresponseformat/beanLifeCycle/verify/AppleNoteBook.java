package com.wl.uniformresponseformat.beanLifeCycle.verify;

import org.springframework.beans.BeansException;
import org.springframework.beans.factory.*;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ApplicationContextAware;

import javax.annotation.PostConstruct;
import javax.annotation.PreDestroy;

/**
 * @author WangLong
 * @Description: Bean的生命周期验证
 * @date 2022/4/13 15:46
 */
public class AppleNoteBook implements BeanNameAware, BeanFactoryAware, ApplicationContextAware, InitializingBean, DisposableBean {

    private String noteBookName;

    //类的初始化
    public AppleNoteBook() {
        System.out.println("Class AppleNoteBoot initializing...");
    }

    //
    @Override
    public void setBeanFactory(BeanFactory beanFactory) throws BeansException {
        System.out.println("AppleNoteBoot.setBeanFactory invoke:"+beanFactory);
    }

    //设置Bean的名字
    @Override
    public void setBeanName(String s) {
        System.out.println(" AppleNoteBook.setBeanName invoke :"+s);
    }

    @Override
    public void destroy() throws Exception {
        System.out.println(" AppleNoteBook.destroy invoke ");
    }

    @Override
    public void afterPropertiesSet() throws Exception {
        System.out.println(" AppleNoteBoot. afterPropertiesSet invoke");
    }

    @Override
    public void setApplicationContext(ApplicationContext applicationContext) throws BeansException {
        System.out.println("AppleNoteBook.setApplicationContext invoke:"+applicationContext);
    }

    public String getNoteBookName() {
        return noteBookName;
    }

    public void setNoteBookName(String noteBookName) {
        System.out.println("noteBookName = " + noteBookName);
        this.noteBookName = noteBookName;
        System.out.println("setBeanName:notebook name has set");
    }

    public void customPostConstruct(){
        System.out.println("AppleNoteBook.customPostConstruct invoke");
    }

    //自定义初始化方法
    @PostConstruct
    public void springPostConstruct(){
        System.out.println("@PostConstruct");
    }

    public void customPreDestroy(){
        System.out.println("AppleNoteBook.customPreDestroy invoke");
        System.out.println("-------------------destroy----------------");
    }

    //自定义销毁方法
    @PreDestroy
    public void springPreDestroy(){
        System.out.println("@PreDestroy");
    }

    @Override
    protected void finalize() throws Throwable {
        System.out.println("-------inside finalize-------");
    }
}
