package cn.lhq.javaserializer;

import java.io.Serializable;

public class SuperClass implements Serializable {

    private String sex;

    public String getSex() {
        return sex;
    }

    public void setSex(String sex) {
        this.sex = sex;
    }
}
