package cn.lhq;

public interface ISerializer {

    <T> byte[] serialize(T obj);

    <T> T deserialize(byte[] data, Class<T> clazz);

    //序列化到文件
    <T> void serializeFile(T obj);
    //从文件读取再反序列化
    <T> T deserializeFile();
}
