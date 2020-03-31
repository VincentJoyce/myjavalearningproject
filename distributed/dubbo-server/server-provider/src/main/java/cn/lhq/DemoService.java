package cn.lhq;

public class DemoService implements IDemoService {
    @Override
    public String protocolDemo(String msg) {
        return "I'm Protocol Demo: "+msg;
    }
}
