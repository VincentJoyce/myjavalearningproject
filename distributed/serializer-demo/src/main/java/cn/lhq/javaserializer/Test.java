package cn.lhq.javaserializer;

import cn.lhq.ISerializer;
import cn.lhq.User;

public class Test {
    public static void main(String[] args) {
        ISerializer iSerializer = new JavaSerializer();
        User user = new User();
        user.setName("Lhq");
        user.setAge(27);


        byte[] bytes = iSerializer.serialize(user);
        System.out.println(new String(bytes));
        User user1=iSerializer.deserialize(bytes,User.class);
        System.out.println(user1);


        //序列号和反序列化成文件
//        iSerializer.serializeFile(user);
//        User user1 =iSerializer.deserializeFile();
//        System.out.println(user1);
    }
}
