package com.wl.uniformresponseformat.beanLifeCycle.verify;

import com.wl.uniformresponseformat.beanLifeCycle.whole.SubNoteBook;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

/**
 * @author WangLong
 * @Description:
 * @date 2022/4/13 16:15
 */
public class SpringBeanLifeCycleTest {

    public static void main(String[] args) {

        //加载mxl文件
        ApplicationContext applicationContext = new ClassPathXmlApplicationContext("classpath:bean-life-cycle.xml");
        AppleNoteBook noteBook = (AppleNoteBook) applicationContext.getBean("appleNoteBook");
        //此处setbeanname会覆盖掉xml配置中的设置
        noteBook.setBeanName("test of set beanName");
        System.out.println("noteBook.getNoteBookName() = " + noteBook.getNoteBookName());
        ((ClassPathXmlApplicationContext)applicationContext).destroy();

        ApplicationContext applicationContext2 = new ClassPathXmlApplicationContext("classpath:sub-bean-life-cycle.xml");
        SubNoteBook subNoteBook = (SubNoteBook) applicationContext2.getBean("subNoteBook");
        subNoteBook.setBeanName("set sub class name");
        System.out.println("subNoteBookName:"+subNoteBook.getSubNoteBookName());
        ((ClassPathXmlApplicationContext)applicationContext2).registerShutdownHook();
    }

}
