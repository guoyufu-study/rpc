package com.jasper.rpc.serialize.json.jackson;

import java.io.IOException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

import org.apache.commons.lang3.StringUtils;

import com.fasterxml.jackson.core.JsonParser;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.DeserializationContext;
import com.fasterxml.jackson.databind.JsonDeserializer;

public class FDateJsonDeserializer extends JsonDeserializer<Date> {

	@Override
	public Date deserialize(JsonParser jp, DeserializationContext ctxt) throws IOException, JsonProcessingException {
		String date = jp.getText();
		if (StringUtils.isEmpty(date)) {//空
			return null;
		}
		
		if (StringUtils.isNumeric(date)) {//数字
			return new Date(Long.valueOf(date));
		}
		
		//yyyy-MM-dd HH:mm:ss 格式
		try {
			SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
			return format.parse(date);
		} catch (ParseException e) {
			throw new IOException(e);
		}
	}

}
