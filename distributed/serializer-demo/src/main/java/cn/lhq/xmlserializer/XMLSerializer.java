package cn.lhq.xmlserializer;

import cn.lhq.ISerializer;
import com.thoughtworks.xstream.XStream;
import com.thoughtworks.xstream.io.xml.DomDriver;

public class XMLSerializer implements ISerializer {

    XStream xStream=new XStream(new DomDriver());

    @Override
    public <T> byte[] serialize(T obj) {
        return xStream.toXML(obj).getBytes();
    }

    @Override
    public <T> T deserialize(byte[] data, Class<T> clazz) {
        return (T) xStream.fromXML(new String(data));
    }

    @Override
    public <T> void serializeFile(T obj) {

    }

    @Override
    public <T> T deserializeFile() {
        return null;
    }
}
