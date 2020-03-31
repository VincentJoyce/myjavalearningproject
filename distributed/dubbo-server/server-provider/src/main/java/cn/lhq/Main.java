package cn.lhq;

//使用dubbo内置的spring容器发布dubbo服务
public class Main {

    public static void main(String[] args) {
        //Main会调用相关容器去启动服务，默认情况下会使用Spring容器去启动dubbo服务
        //路径需要改成META-INF.spring/dubbo-server.xml
        com.alibaba.dubbo.container.Main.main(new String[]{"spring","log4j","jetty"});
    }
}
