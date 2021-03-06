package com.jasper.rpc.serialize.json.fastjson;

import org.junit.Before;
import org.junit.Test;

import com.jasper.rpc.serialize.beans.Person;
import com.jasper.rpc.serialize.beans.PhoneNumber;

public class Json2SerializerTests {

	Json2Serializer xs = null;
	Person p = null;
	
	@Before
	public void before() {
		xs = new Json2Serializer();
		
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
