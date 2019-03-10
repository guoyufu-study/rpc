package com.jasper.rpc.serialize.xml.java;

import java.beans.XMLDecoder;
import java.beans.XMLEncoder;
import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;

import com.jasper.rpc.serialize.stream.ISerializer;

public class Xml2Serializer implements ISerializer {

	public <T> byte[] serialize(T obj) {
		ByteArrayOutputStream out = new ByteArrayOutputStream();
		XMLEncoder xe = new XMLEncoder(out, "UTF-8", Boolean.TRUE, 0);
		xe.writeObject(obj);
		xe.close();
		return out.toByteArray();
	}

	@SuppressWarnings("unchecked")
	public <T> T deserialize(byte[] data, Class<T> clazz) {
		XMLDecoder xd = new XMLDecoder(new ByteArrayInputStream(data));
		Object obj = xd.readObject();
		xd.close();
		return (T)obj;
	}

}
