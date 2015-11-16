package iitdev.generator.entity;

import java.beans.BeanInfo;
import java.beans.Introspector;
import java.beans.PropertyDescriptor;
import java.util.ArrayList;
import java.util.List;

import cn.org.rapid_framework.generator.provider.java.model.JavaClass;
import cn.org.rapid_framework.generator.provider.java.model.JavaProperty;

public class EntityJSP extends JavaClass {
	
	public EntityJSP(Class clazz) throws Exception {
		super(clazz);
	}

	public JavaProperty[] getProperties() throws Exception {
		List result = new ArrayList();
		String tableName=null;
		BeanInfo beanInfo = Introspector.getBeanInfo(super.getClazz());
		PropertyDescriptor[] pds = beanInfo.getPropertyDescriptors();
		for (PropertyDescriptor pd : pds) {
			JSPProperty jspProperty = new JSPProperty(pd, this);
			result.add(jspProperty);
		}
		return (JavaProperty[]) result.toArray(new JavaProperty[0]);
	}

}
