package com.iitdev.orm.annotation;

import java.beans.PropertyDescriptor;
import java.lang.reflect.Field;
import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.persistence.GeneratedValue;

import com.iitdev.exception.IitdevRuntimeException;
import com.iitdev.orm.analysis.ClassUtils;
import com.iitdev.orm.xml.Column;
import com.iitdev.orm.xml.Id;
import com.iitdev.orm.xml.Table;

/**
 * 暂支持MYSQL版本
 * Target: type : 类 接口 enum的声明
 * 		   field 属性
 * 		   method 方法
 * 		   param 参数
 * Retention(RetentionPolicy.RUNTIME)  类反射
 * 
 * @author jerry
 *
 */
public class AnnotationTableInfoAnalysis {
	
	/**
	 * 通过xml解析到的类名集合在反射成类集合
	 * @param clazzs
	 * @return
	 */
	private static List<Class<?>> readClass(List<String> packages){
		List<Class<?>> clas= new ArrayList<Class<?>>();
		for (String _package : packages) {
			try {
				clas.addAll(ClassUtils.getClasses(_package));
			} catch (Exception e) {
				//log.error("clazz:"+clazz+",未找到", e);
			}
		}
		return clas;
	}
	
	/**
	 * 通过class来封装Table信息
	 * @param <ATable>
	 * @param clazz
	 * @return
	 */
	private static  Table readTable(Class<?> clazz){
		Table table =new Table();
		Id id = null;
		List<Column> columns = null;
		try {
			//Object bean = clazz.getConstructor(new Class[]{}).newInstance(new Object[]{});
			Field[] fields= clazz.getDeclaredFields();
			boolean isTable=clazz.isAnnotationPresent(javax.persistence.Table.class);//是否有持久化注解
			if(isTable){
				javax.persistence.Table atable =clazz.getAnnotation(javax.persistence.Table.class);
				table.setClazz(clazz);
				table.setName(atable.name());
				for (Field field : fields) {
					if(field.equals(clazz.getDeclaredField("serialVersionUID")))continue;
					//field.setAccessible(true);//开启支私有变量的访问权限
					PropertyDescriptor pd = new PropertyDescriptor(field.getName(),clazz);
					Method getMethod = pd.getReadMethod();//获得get方法
					if(getMethod.isAnnotationPresent(javax.persistence.Id.class) && getMethod.isAnnotationPresent(javax.persistence.Column.class)&&getMethod.isAnnotationPresent(GeneratedValue.class)){
						//id
						id=new Id();
						javax.persistence.Column acolumn=getMethod.getAnnotation(javax.persistence.Column.class);
						GeneratedValue generatedValue=getMethod.getAnnotation(GeneratedValue.class);
						id.setColumn(getMethod.getName());
						id.setName(acolumn.name());
						id.setType(generatedValue.strategy());
					}else if(!getMethod.isAnnotationPresent(javax.persistence.Id.class) && getMethod.isAnnotationPresent(javax.persistence.Column.class)){
						//column
						if(null==columns){
							columns=new ArrayList<Column>();
						}
						Column column=new Column();
						javax.persistence.Column acolumn=getMethod.getAnnotation(javax.persistence.Column.class);
						column.setColumn(getMethod.getName());
						column.setLength(acolumn.length());
						column.setName(acolumn.name());
						column.setNullable(acolumn.nullable());
						column.setUnique(acolumn.unique());
						columns.add(column);
					}
				}
				table.setId(id);
				table.setColumns(columns);
			}else{
				throw new IitdevRuntimeException("传入对象不是持久化对象");
			}
			
		} catch (Exception e) {
			e.printStackTrace();
		}
		return table;
	}
	
	public static Map<Class<?>, Table> readTables(List<String> packages){
		Map<Class<?>, Table> tableMap = new HashMap<Class<?>, Table>();
		List<Class<?>> classes = readClass(packages);
		for (Class<?> clazz : classes) {
			tableMap.put(clazz, readTable(clazz));
		}
		return tableMap;
	}

}
