package cn.lhq;

import cn.lhq.javaserializer.SuperClass;

import java.io.Serializable;

//SuperClass没有实现Serializable，SuperClass里面的内容不会参与序列化和反序列化
public class User extends SuperClass implements Serializable {

    //自动生成serialVersionUID：File | Settings | Editor | Inspections 开启包含VersionUID的选项
    private static final long serialVersionUID = -6938969723145487074L;
    private String name;
    private int age;
    //transient修饰的不会参与序列化和反序列化
    private transient String hobby;
    //静态变量不会参与序列化与反序列化
    public static int num;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getAge() {
        return age;
    }

    public void setAge(int age) {
        this.age = age;
    }

    public String getHobby() {
        return hobby;
    }

    public void setHobby(String hobby) {
        this.hobby = hobby;
    }

    @Override
    public String toString() {
        return "User{" +
                "name='" + name + '\'' +
                ", age=" + age +
                ", num=" + num +
                '}';
    }
}
