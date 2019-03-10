package com.jasper.rpc.serialize.json.jackson;

import java.io.IOException;
import java.util.Date;

import com.fasterxml.jackson.core.JsonParser;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.core.Version;
import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.SerializationFeature;
import com.fasterxml.jackson.databind.module.SimpleModule;
import com.jasper.rpc.serialize.stream.ISerializer;

public class JsonSerializer implements ISerializer {

	private static final ObjectMapper mapper = new ObjectMapper();
	static {
		mapper.configure(JsonParser.Feature.ALLOW_COMMENTS, Boolean.TRUE);//注释
		mapper.configure(JsonParser.Feature.ALLOW_UNQUOTED_FIELD_NAMES, Boolean.TRUE);//非引号字段名
		mapper.configure(JsonParser.Feature.ALLOW_SINGLE_QUOTES, Boolean.TRUE);//单引号
		mapper.configure(JsonParser.Feature.ALLOW_UNQUOTED_CONTROL_CHARS, Boolean.TRUE);
		mapper.configure(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES, Boolean.FALSE);
		mapper.configure(SerializationFeature.FAIL_ON_EMPTY_BEANS, Boolean.FALSE);
		
		//自定义日期序列化/反序列化
		SimpleModule module = new SimpleModule("DateTimeModule", Version.unknownVersion());
		module.addSerializer(Date.class, new FDateJsonSerializer());
		module.addDeserializer(Date.class, new FDateJsonDeserializer());
		mapper.registerModule(module);
		
	}
	
	public static ObjectMapper getObjectMapper() {
		return mapper;
	}
	
	@Override
	public <T> byte[] serialize(T obj) {
		if (obj == null) {//空
			return new byte[0];
		}
		try {
			String json = mapper.writerWithDefaultPrettyPrinter().writeValueAsString(obj);
			return json.getBytes();
		} catch (JsonProcessingException e) {
			throw new RuntimeException(e);
		}
	}

	@Override
	public <T> T deserialize(byte[] data, Class<T> clazz) {
		try {
			return (T)mapper.readValue(data, clazz);
		} catch (IOException e) {
			throw new RuntimeException(e);
		}
	}

}
