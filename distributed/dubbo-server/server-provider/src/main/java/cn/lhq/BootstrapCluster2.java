package cn.lhq;

import org.springframework.context.support.ClassPathXmlApplicationContext;

import java.io.IOException;

//自定义发布dubbo服务
public class BootstrapCluster2 {

    public static void main(String[] args) throws IOException {
        ClassPathXmlApplicationContext context = new ClassPathXmlApplicationContext("META-INF.spring/dubbo-cluster2.xml");
        context.start();
        System.in.read();//阻塞当前线程
    }
}
