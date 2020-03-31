package cn.lhq;

public class HelloImpl implements IHello {
    @Override
    public String sayHello(String msg) {
        return "Hello:" + msg;
    }
}
