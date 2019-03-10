package com.jasper.rpc.serialize.json.fastjson;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.serializer.SerializerFeature;
import com.jasper.rpc.serialize.stream.ISerializer;

public class Json2Serializer implements ISerializer {

	@Override
	public <T> byte[] serialize(T obj) {
		JSON.DEFFAULT_DATE_FORMAT = "yyyy-MM-dd HH:mm:ss";
		return JSON.toJSONString(obj, SerializerFeature.WriteDateUseDateFormat, SerializerFeature.PrettyFormat).getBytes();
	}

	@Override
	public <T> T deserialize(byte[] data, Class<T> clazz) {
		return JSON.parseObject(new String(data), clazz);
	}

}
