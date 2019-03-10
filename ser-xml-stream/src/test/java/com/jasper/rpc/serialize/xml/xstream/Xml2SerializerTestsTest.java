package com.jasper.rpc.serialize.xml.xstream;

import org.junit.Before;
import org.junit.Test;

import com.jasper.rpc.serialize.beans.Person;
import com.jasper.rpc.serialize.beans.PhoneNumber;
import com.jasper.rpc.serialize.xml.java.Xml2Serializer;

public class Xml2SerializerTestsTest {

	Xml2Serializer xs = null;
	Person p = null;
	
	@Before
	public void before() {
		xs = new Xml2Serializer();
		
		p = new Person();
		p.setFirstname("jasper");
		p.setLastname("郭");
		p.setFax(new PhoneNumber(123, "1234-456"));
		p.setPhone(new PhoneNumber(123, "9999-999"));
	}
	
	@Test
	public void testSerialize() {
		byte[] data = xs.serialize(p);
		System.out.println(new String(data));
	}

	@Test
	public void testDeserialize() {
		byte[] data = xs.serialize(p);
		Person obj = xs.deserialize(data, Person.class);
		System.out.println(obj);
	}

}
