package iitdev.generator.generer;


import iitdev.generator.entity.EntityClazz;

import java.util.HashMap;
import java.util.Map;

import cn.org.rapid_framework.generator.provider.java.model.JavaClass;




/**
 * 生成service类
 *
 */
public class ServiceGenerator extends AbstractGenerator{
	/**
	 *需要手动修改的变量 
	 */
	//模块名
	private String moduleName;
	
	public  ServiceGenerator(String moduleName){
		this.moduleName = moduleName;
		gg.put("moduleName",this.moduleName);
	}
	
	public void generatorCacheBS(EntityClazz entityClazz) throws Exception{
		Class mainClazz = entityClazz.clazz;
		String entityCnName=entityClazz.entityCnName;
		
		//1 单表
		if(!entityClazz.hasSub()){
			gg.put("entityCnName", entityCnName);
			generator.generateByClass(mainClazz,sigleTem);
			return;
		}
		
		//2 多表
		/**
		 * 生成主表的bs
		 */
		JavaClass[] subClazzes = new JavaClass[entityClazz.subClazzes.size()];
		int i=0;
		for(EntityClazz subClass: entityClazz.subClazzes){
			subClazzes[i] = new JavaClass(subClass.clazz);
			subClazzes[i].setFkProperty(subClass.fkProperty);
			i++;
		}
		
		Map map = new HashMap();
		map.put("subClazzes", subClazzes);
		map.put("clazz",new JavaClass(mainClazz));
		map.put("entityCnName", entityCnName);
		generator.generateByMap(map,cacheMainTem);
		
		for(EntityClazz subClass: entityClazz.subClazzes){
			generatorSubCacheBS(subClass);
		}	
	}
	//生成子表service
	private void generatorSubCacheBS(EntityClazz entityClazz) throws Exception{
		Class mainClazz = entityClazz.clazz;
		String entityCnName=entityClazz.entityCnName;
		Map map = new HashMap();
		map = new HashMap();
		map.put("clazz",new JavaClass(entityClazz.clazz));
		map.put("entityCnName", entityClazz.entityCnName);
		
		map.put("mainClazzId", entityClazz.fkProperty);
		map.put("fatherClazz",new JavaClass(entityClazz.fatherClazz.clazz));
		
		JavaClass[] sces = new JavaClass[entityClazz.subClazzes.size()];
		int j=0;
		for(EntityClazz sc: entityClazz.subClazzes){
			sces[j] = new JavaClass(sc.clazz);
			j++;
		}
		map.put("subClazzes", sces);
		generator.generateByMap(map,cacheSubTem);
		if(sces.length>0){
			for(EntityClazz subClass: entityClazz.subClazzes){
				generatorSubCacheBS(subClass);
			}		
		}
	}
	
	
	public void generatorGrid(EntityClazz entityClazz) throws Exception{
		if(!entityClazz.hasSub()){
			generatorCacheBS(entityClazz);
		}
		
		Class mainClazz = entityClazz.clazz;
		String entityCnName=entityClazz.entityCnName;
		
		//1 单表
		//TODO
		
		//2 多表
		JavaClass[] subClazzes = new JavaClass[entityClazz.subClazzes.size()];
		int i=0;
		for(EntityClazz subClass: entityClazz.subClazzes){
			subClazzes[i] = new JavaClass(subClass.clazz);
			subClazzes[i].setFkProperty(subClass.fkProperty);
			i++;
		}
		
		Map map = new HashMap();
		map.put("subClazzes", subClazzes);
		map.put("clazz",new JavaClass(mainClazz));
		map.put("entityCnName", entityCnName);
		generator.generateByMap(map,gridMainTem);
		
		for(EntityClazz subClass: entityClazz.subClazzes){
			generatorSubGrid(subClass);
		}
	}
	public void generatorSubGrid(EntityClazz subClass) throws Exception{
		Map map = new HashMap();
		map.put("clazz",new JavaClass(subClass.clazz));
		map.put("mainClazzId", subClass.fkProperty);
		map.put("fatherClazz",new JavaClass(subClass.fatherClazz.clazz));
		map.put("entityCnName", subClass.entityCnName);
		generator.generateByMap(map,gridSubTem);
	}
	
	/**
	 * 生成3
	 * @param entityClazz
	 * @throws Exception
	 */
	public void generatorThree(EntityClazz entityClazz) throws Exception{
		Class mainClazz = entityClazz.clazz;
		String entityCnName=entityClazz.entityCnName;
		
		
		JavaClass[] subClazzes = new JavaClass[entityClazz.subClazzes.size()];
		int i=0;
		for(EntityClazz subClass: entityClazz.subClazzes){
			subClazzes[i] = new JavaClass(subClass.clazz);
			subClazzes[i].setFkProperty(subClass.fkProperty);
			i++;
		}
		
		Map map = new HashMap();
		map.put("subClazzes", subClazzes);
		map.put("clazz",new JavaClass(mainClazz));
		map.put("entityCnName", entityCnName);
		generator.generateByMap(map,threeMain);
		
		for(EntityClazz subMain: entityClazz.subClazzes){
			if(!subMain.hasSub()){
				generatorSubGrid(subMain);
				continue;
			}
			map = new HashMap();
			map.put("clazz",new JavaClass(subMain.clazz));
			map.put("mainClazzId", subMain.fkProperty);
			map.put("fatherClazz",new JavaClass(entityClazz.clazz));
			map.put("entityCnName", subMain.entityCnName);
			
			subClazzes  = new JavaClass[subMain.subClazzes.size()];
			i=0;
			for(EntityClazz subClass: subMain.subClazzes){
				subClazzes[i] = new JavaClass(subClass.clazz);
				subClazzes[i].setFkProperty(subClass.fkProperty);
				i++;
			}
			map.put("subClazzes", subClazzes);
			
			generator.generateByMap(map,threeSubMain);
			if(subMain.hasSub()){
				for(EntityClazz subClass: subMain.subClazzes){
					map = new HashMap();
					map.put("clazz",new JavaClass(subClass.clazz));
					map.put("mainClazzId", subClass.fkProperty);
					map.put("fatherClazz",new JavaClass(subClass.fatherClazz.clazz));
					map.put("entityCnName", subClass.entityCnName);
					generator.generateByMap(map,threeSub);
				}
			}
		}
	}
	
	
	private static final String sigleTem = "TEMPLATE_NEW/SERVICE_TEMPLATE/single";
	private static final String gridMainTem= "TEMPLATE_NEW/SERVICE_TEMPLATE/twoTable/grid/main";
	private static final String gridSubTem= "TEMPLATE_NEW/SERVICE_TEMPLATE/twoTable/grid/sub";
	private static final String cacheMainTem= "TEMPLATE_NEW/SERVICE_TEMPLATE/twoTable/temTable/main";
	private static final String cacheSubTem= "TEMPLATE_NEW/SERVICE_TEMPLATE/twoTable/temTable/sub";
	private static final String threeMain= cacheMainTem;
	private static final String threeSubMain= "TEMPLATE_NEW/SERVICE_TEMPLATE/threeTable/submain";
	private static final String threeSub= "TEMPLATE_NEW/SERVICE_TEMPLATE/threeTable/sub";
}