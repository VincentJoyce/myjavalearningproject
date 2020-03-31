package cn.lhq.jsonserializer;

import cn.lhq.ISerializer;
import com.alibaba.fastjson.JSON;

public class FastjsonSerializer implements ISerializer {
    @Override
    public <T> byte[] serialize(T obj) {
        return JSON.toJSONString(obj).getBytes();
    }

    @Override
    public <T> T deserialize(byte[] data, Class<T> clazz) {
        return JSON.parseObject(new String(data), clazz);
    }

    @Override
    public <T> void serializeFile(T obj) {

    }

    @Override
    public <T> T deserializeFile() {
        return null;
    }
}
