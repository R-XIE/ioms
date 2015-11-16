package iitdev.generator.entity;

import java.beans.PropertyDescriptor;

import cn.org.rapid_framework.generator.provider.db.table.TableFactory;
import cn.org.rapid_framework.generator.provider.db.table.model.Column;
import cn.org.rapid_framework.generator.provider.db.table.model.Table;
import cn.org.rapid_framework.generator.provider.java.model.JavaClass;
import cn.org.rapid_framework.generator.provider.java.model.JavaProperty;

public class JSPProperty extends JavaProperty {
	private Column column;
	
	public JSPProperty(PropertyDescriptor pd, JavaClass javaClass) throws Exception {
		super(pd, javaClass);
		String tableName;
		boolean isTable=javaClass.getClazz().isAnnotationPresent(javax.persistence.Table.class);//是否有持久化注解
		if(isTable){
			javax.persistence.Table atable =(javax.persistence.Table) javaClass.getClazz().getAnnotation(javax.persistence.Table.class);
			tableName=atable.name();
		}else{
			throw new Exception("@Table识别失败.");
		}
		Table dbTable=TableFactory.getInstance().getTable(tableName);
		this.column=dbTable.getColumnByName(this.getColumnName());
	}
	public Column getColumn() {
		return column;
	}
	public void setColumn(Column column) {
		this.column = column;
	}

}
