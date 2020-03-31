package cn.lhq;

import org.springframework.context.support.ClassPathXmlApplicationContext;

/**
 * Hello world!
 */
public class App {
    public static void main(String[] args) {

        ClassPathXmlApplicationContext context=new ClassPathXmlApplicationContext("dubbo-client.xml");
        IHello iHello= (IHello) context.getBean("helloService");

        System.out.println(iHello.sayHello("lhq"));
    }
}
