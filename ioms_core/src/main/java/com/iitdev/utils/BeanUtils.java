package com.iitdev.utils;

import java.beans.BeanInfo;
import java.beans.Introspector;
import java.beans.PropertyDescriptor;
import java.lang.reflect.Method;
import java.util.HashMap;
import java.util.Map;

import com.iitdev.data.QueryFormBean;

public class BeanUtils {
	public static Map<String, String> getQueryMap(QueryFormBean obj) {
		if (obj.getP() == null)
			obj.setP(1);
		if (obj.getS() == null)
			obj.setS(5);
		Map<String, String> queryMap = transBean2Map(obj);
		return queryMap;
	}

	private static Map<String, String> transBean2Map(QueryFormBean obj) {
		if (obj == null) {
			return null;
		}
		Map<String, String> map = new HashMap<String, String>();
		try {
			BeanInfo beanInfo = Introspector.getBeanInfo(obj.getClass());
			PropertyDescriptor[] propertyDescriptors = beanInfo
					.getPropertyDescriptors();
			for (PropertyDescriptor property : propertyDescriptors) {
				String key = property.getName();
				// 过滤class属性
				if (!key.equals("class")) {
					/**
					 * 时间段末尾+BE
					 */
					if (key.endsWith("BE")) {
						Method getter = property.getReadMethod();
						Object value = getter.invoke(obj);
						if(value != null){
							String[] dateArr=value.toString().split(" - ");
							String dateName=key.substring(0, key.length()-2);
							if(dateArr.length==2){
								map.put(dateName+"Begin", dateArr[0]);
								map.put(dateName+"End", dateArr[1]);
							}
						}
					} else {
						// 得到property对应的getter方法
						Method getter = property.getReadMethod();
						Object value = getter.invoke(obj);
						if (value != null)
							map.put(key, value.toString());
					}
				}

			}
		} catch (Exception e) {
			System.out.println("transBean2Map Error " + e);
		}
		return map;
	}
}