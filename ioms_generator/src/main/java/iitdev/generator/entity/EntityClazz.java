package iitdev.generator.entity;

import java.util.ArrayList;
import java.util.List;

public class EntityClazz {
	public Class clazz;
	public EntityClazz fatherClazz;
	public List<EntityClazz> subClazzes = new ArrayList<EntityClazz>();
	public String entityCnName = "";
	public String fkProperty;
	/**
	 * 主表对象
	 * @param clazz 类对象
	 * @param cnName 中文名称
	 * @param subClazzes 子表对象
	 */
	public EntityClazz(Class clazz,String cnName,EntityClazz... subClazzes) {
		this.clazz = clazz;
		this.entityCnName = cnName;
		if(subClazzes!=null&&subClazzes.length>0){
			for(EntityClazz sub:subClazzes){
				sub.fatherClazz = this;
				this.subClazzes.add(sub);
			}
		}
	}
	
	
	/**
	 * 子表对象
	 * @param clazz
	 * @param cnName
	 * @param fkProperty
	 * @param subClazzes
	 */
	public EntityClazz(Class clazz,String cnName,String fkProperty,EntityClazz... subClazzes) {
		this.clazz = clazz;
		this.entityCnName = cnName;
		this.fkProperty = fkProperty;
		if(subClazzes!=null&&subClazzes.length>0){
			for(EntityClazz sub:subClazzes){
				sub.fatherClazz = this;
				this.subClazzes.add(sub);
			}
		}
	}
	

	public boolean hasSub() {
		return subClazzes != null && !subClazzes.isEmpty();
	}
}
