package com.lemonnt.ms.common.util;

import java.io.*;
import java.io.IOException;
import javax.xml.bind.*;
import org.apache.log4j.Logger;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.lemonnt.ms.common.exception.UtilException;

public class SerializateUtil {
    private static Logger logger = Logger.getLogger(SerializateUtil.class);
    
	public static String toXml(Object t) throws UtilException{
		JAXBContext jaxbContext;
		try {
			jaxbContext = JAXBContext.newInstance(t.getClass());
			Marshaller jaxbMarshaller = jaxbContext.createMarshaller();
			ByteArrayOutputStream out = new ByteArrayOutputStream();
			jaxbMarshaller.marshal(t, out);
			return out.toString();
		} catch (Exception e) {
			logger.error("Failed to switch object 2 xml []",e);
			throw new UtilException("Failed to switch object 2 xml []");
		}
	}

	@SuppressWarnings("unchecked")
	public static <T extends Object> T fromXml(Class<T> c, String xml) throws UtilException {
		try {
			JAXBContext jaxbContext = JAXBContext.newInstance(c);
			Object t = jaxbContext.createUnmarshaller().unmarshal(
					new ByteArrayInputStream(xml.toString().getBytes("UTF-8")));
			return (T) t;

		} catch (Exception e) {
		    logger.error("Failed to switch xml 2 Object []",e);
            throw new UtilException("Failed to switch xml 2 Object []");
		}
	}

	public static String toJson(Object t) throws UtilException {
		ObjectMapper objectMapper = new ObjectMapper();
		try {
			return objectMapper.writeValueAsString(t);
		} catch (JsonProcessingException e) {
		    logger.error("Failed to switch object 2 json []",e);
            throw new UtilException("Failed to switch object 2 json []");
		}
	}

	public static <T> T fromJson(String json, TypeReference<T> tr) throws UtilException {
		ObjectMapper objectMapper = new ObjectMapper();
		try {
			return objectMapper.readValue(json, tr);
		} catch (IOException e) {
		    logger.error("Failed to switch json 2 Object []",e);
            throw new UtilException("Failed to switch json 2 Object []");
		}
	}

	public static <T extends Object> T fromJson(Class<T> c, String json) throws UtilException {
		ObjectMapper objectMapper = new ObjectMapper();
		try {
			return objectMapper.readValue(json, c);
		} catch (IOException e) {
		    logger.error("Failed to switch json 2 Object []",e);
            throw new UtilException("Failed to switch json 2 Object []");
		}
	}
}
