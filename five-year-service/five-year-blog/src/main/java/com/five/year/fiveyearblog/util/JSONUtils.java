package com.five.year.fiveyearblog.util;

import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

import com.alibaba.fastjson.JSON;
import com.fasterxml.jackson.core.JsonParser;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.databind.JavaType;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.apache.commons.lang3.StringUtils;

/**
 * JSON转换工具类。
 *
 * @author wangruiv
 * @date 2017-06-29 10:07:51
 */
public final class JSONUtils {
	/**
	 * 日期字符串的格式
	 */
	public static final String DATE_FORMAT = "yyyy-MM-dd HH:mm:ss";

	private static ObjectMapper objectMapper = new ObjectMapper();

	static {
		// 设置日期字符串的格式
		objectMapper.setDateFormat(new SimpleDateFormat(DATE_FORMAT));

		// 反序列化时，允许字段名没有双引号
		objectMapper.configure(JsonParser.Feature.ALLOW_UNQUOTED_FIELD_NAMES, true);

		// 反序列化时，忽略未知的属性
		objectMapper.disable(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES);
	}

	private JSONUtils() {
	}

	/**
	 * 对象转JSON字符串
	 *
	 * @param obj 对象
	 * @return JSON字符串
	 */
	public static String toJSONString(Object obj) {
		try {
			return objectMapper.writeValueAsString(obj);
		}
		catch (JsonProcessingException e) {
			throw new RuntimeException("JSON序列化时出现错误！", e);
		}
	}

	/**
	 * 对象转JSON字符串。
	 *
	 * @param obj        对象。
	 * @param dateFormat 日期格式。
	 * @return JSON字符串。
	 */
	public static String toJSONString(Object obj, String dateFormat) {
		if (StringUtils.isBlank(dateFormat)) {
			dateFormat = DATE_FORMAT;
		}

		objectMapper.setDateFormat(new SimpleDateFormat(dateFormat));
		try {
			return objectMapper.writeValueAsString(obj);
		}
		catch (JsonProcessingException e) {
			throw new RuntimeException("JSON序列化时出现错误！", e);
		}
	}

	/**
	 * JSON字符串转对象。
	 *
	 * @param json  JSON字符串。
	 * @param clazz 对象类型。
	 * @param <T>   类型参数。
	 * @return 指定类型的对象。
	 */
	public static <T> T jsonToObject(String json, Class<T> clazz) {
		if (StringUtils.isBlank(json)) {
			throw new IllegalArgumentException("参数json不能为空！");
		}
		if (clazz == null) {
			throw new IllegalArgumentException("参数clazz不能为空！");
		}

		try {
			return objectMapper.readValue(json.getBytes("UTF-8"), clazz);
		}
		catch (IOException e) {
			throw new RuntimeException("JSON字符串转对象时出现错误！", e);
		}
	}

	/**
	 * JSON字符串转对象数组。
	 *
	 * @param json  JSON字符串。
	 * @param clazz 数组元素的对象类型。
	 * @param <T>   类型参数。
	 * @return 指定类型的对象数组。
	 */
	public static <T> List<T> jsonToList(String json, Class<T> clazz) {
		if (StringUtils.isBlank(json)) {
			throw new IllegalArgumentException("参数json不能为空！");
		}
		if (clazz == null) {
			throw new IllegalArgumentException("参数clazz不能为空！");
		}

		try {
			JavaType javaType = objectMapper.getTypeFactory().constructParametricType(List.class, clazz);
			return objectMapper.readValue(json.getBytes("UTF-8"), javaType);
		}
		catch (IOException e) {
			throw new RuntimeException("JSON字符串转数组时出现错误！", e);
		}
	}

	/**
	 * JSON字符串转MAP。
	 *
	 * @param json JSON字符串。
	 * @return MAP对象。
	 */
	public static Map jsonToMap(String json) {
		if (StringUtils.isBlank(json)) {
			throw new IllegalArgumentException("参数json不能为空！");
		}

		try {
			return objectMapper.readValue(json.getBytes("UTF-8"), Map.class);
		}
		catch (IOException e) {
			throw new RuntimeException("JSON字符串转对象时出现错误！", e);
		}
	}


	/**
	 * 拷贝对象属性，并返回指定类型的新对象。
	 *
	 * @param src   源对象。
	 * @param clazz 目标对象类型。
	 * @param <T>   目标对象类型参数。
	 * @return 与原对象属性值相同的新对象。
	 */
	public static <T> T copyProperties(Object src, Class<T> clazz) {
		return jsonToObject(toJSONString(src), clazz);
	}

	/**
	 * 拷贝对象属性，并返回同类型的新对象。
	 *
	 * @param src 源对象。
	 * @param <T> 目标对象类型参数。
	 * @return 与原对象属性值相同的新对象。
	 */
	public static <T> T copyProperties(T src) {
		return (T) copyProperties(src, src.getClass());
	}

    public static  <T>  Map<String,List<T>> jsonToEntityMap(String value, Class<T> activityProductDealerDtoClass) {
		Map<String,List<T>> result=new HashMap<>();
		net.sf.json.JSONObject json=net.sf.json.JSONObject.fromObject(value);
		Iterator keys=json.keys();
		while(keys.hasNext()) {
			String jsonKey = (String) keys.next();
			String jsonValue = json.get(jsonKey).toString();
			if (jsonValue.startsWith("[") && jsonValue.endsWith("]")) {
				List<T> activityProductDealerDtoList=	JSON.parseArray(jsonValue, activityProductDealerDtoClass);
				result.put(jsonKey, activityProductDealerDtoList);
			}
		}
    return result;
    }
}
