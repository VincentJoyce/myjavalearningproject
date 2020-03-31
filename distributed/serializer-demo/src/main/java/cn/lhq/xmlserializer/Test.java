package cn.lhq.xmlserializer;

import cn.lhq.ISerializer;
import cn.lhq.User;

public class Test {

    public static void main(String[] args) {

        ISerializer iSerializer = new XMLSerializer();
        User user = new User();
        user.setName("Lhq");
        user.setAge(27);
        user.setHobby("天天");
        user.setSex("男");

        byte[] bytes = iSerializer.serialize(user);
        System.out.println(new String(bytes));

        User user1 = iSerializer.deserialize(bytes, User.class);
        System.out.println(user1);
    }
}
