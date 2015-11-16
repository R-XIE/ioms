package com.iitdev.web;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

public class GsonTools {
	/**
	 * 使用实体Bean转换json
	 * 
	 * @param t
	 *            实体类
	 * @return 返回这个类的实体类
	 */
	public static <T> String bean2json(T t) {
		Gson gson = new Gson();
		return gson.toJson(t);
	}

	/**
	 * 使用List转换json
	 * 
	 * @param list
	 *            list集合
	 * @return json 字串
	 */
	public static <T> String list2json(List<T> list) {
		Gson gson = new Gson();
		return gson.toJson(list);
	}

	/**
	 * 返回Map转换json字串
	 * 
	 * @param map
	 *            一个Map数据 类名
	 * @return 返回一个json
	 */
	public static <T> String map2json(Map<String, T> map) {
		Gson gson = new Gson();
		return gson.toJson(map);
	}

	/**
	 * 返回json字串
	 * 
	 * @param set 集合参数
	 * @return 返回json字串
	 */
	public static <T> String set2json(Set<T> set) {
		Gson gson = new Gson();
		return gson.toJson(gson);
	}
	
	/**
	 * 使用json转换成实体Bean
	 * 
	 * @param json json数据字串
	 * @param className 类名，为了和JSON对接
	 * @return 返回这个类的实体类
	 */
	public static <T> T json2Bean(String json, Class<T> className) {
		T t = null;
		try {
			Gson gson = new Gson();
			t = gson.fromJson(json, className);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return t;
	}

	/**
	 * 使用json转换成List
	 * 
	 * @param json json格式字符串
	 * @param className  类名
	 * @return 返回一个list
	 */
	public static <T> List<T> json2List(String json, Class<T> className) {
		List<T> list = new ArrayList<T>();
		try {
			Gson gson = new Gson();
			list = gson.fromJson(json, new TypeToken<List<T>>() {
			}.getType());
		} catch (Exception e) {
			e.printStackTrace();
		}
		return list;
	}
	/**
	 * @author hfox
	 * @param json
	 * @param className:List<T>.class
	 * @return List<T>
	 */
	public static <T> List<T> json2list(String json,Class<T> className) {
		List<T> list = new ArrayList<T>();
		try {
			Gson gson = new Gson();
			list = gson.fromJson(json, new TypeToken<List<T>>(){}.getType());
		} catch (Exception e) {
			e.printStackTrace();
		}
		return list;
	}


	/**
	 * 返回Map<String,T>
	 * 
	 * @param json json数据字符串
	 * @param className 类名
	 * @return 返回一个Map类型
	 */
	public static <T> Map<String, T> json2Map(String json, Class<T> className) {
		Map<String, T> map = new HashMap<String, T>();
		try {
			Gson gson = new Gson();
			map = gson.fromJson(json, new TypeToken<Map<String, T>>() {
			}.getType());
		} catch (Exception e) {
			e.printStackTrace();
		}
		return map;
	}

	/**
	 * 返回Set<T>
	 * 
	 * @param json 一个json格式数据
	 * @param className 匹配的数据类型
	 * @return 返回这个类型的集合Set
	 */
	public static <T> Set<T> json2Set(String json, Class<T> className) {
		Set<T> set = new HashSet<T>();
		try {
			Gson gson = new Gson();
			set = gson.fromJson(json, new TypeToken<Set<T>>() {
			}.getType());
		} catch (Exception e) {
			e.printStackTrace();
		}
		return set;
	}

	/**
	 * 返回List<Map<String,String>>
	 * 
	 * @param json 一个json格式数据
	 * @return 返回格式为List<Map<String,String>>
	 */
	public static List<Map<String, String>> json2maplist(String json) {
		List<Map<String, String>> maplist = new ArrayList<Map<String, String>>();
		try {
			Gson gson = new Gson();
			maplist = gson.fromJson(json,
					new TypeToken<List<Map<String, String>>>() {
					}.getType());
		} catch (Exception e) {
			e.printStackTrace();
		}
		return maplist;
	}
}
