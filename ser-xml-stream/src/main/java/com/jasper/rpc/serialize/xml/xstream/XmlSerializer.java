package com.jasper.rpc.serialize.xml.xstream;

import com.jasper.rpc.serialize.beans.Person;
import com.jasper.rpc.serialize.beans.PhoneNumber;
import com.jasper.rpc.serialize.stream.ISerializer;
import com.thoughtworks.xstream.XStream;
import com.thoughtworks.xstream.io.xml.DomDriver;

public class XmlSerializer implements ISerializer {

	private static final XStream xStream = new XStream(new DomDriver());
//	private static final XStream xStream = new XStream(new StaxDriver());
	static {
		xStream.alias("person", Person.class);
		xStream.alias("phonenumber", PhoneNumber.class);
	}
	
	public <T> byte[] serialize(T obj) {
		return xStream.toXML(obj).getBytes();
	}

	@SuppressWarnings("unchecked")
	public <T> T deserialize(byte[] data, Class<T> clazz) {
		return (T) xStream.fromXML(new String(data));
	}

}
