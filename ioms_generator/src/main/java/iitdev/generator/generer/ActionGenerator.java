package iitdev.generator.generer;


import iitdev.generator.entity.EntityClazz;

import java.util.HashMap;
import java.util.Map;

import cn.org.rapid_framework.generator.provider.java.model.JavaClass;

/**
 * 生成service类
 *
 */
public class ActionGenerator extends AbstractGenerator{
	/**
	 *需要手动修改的变量 
	 */
	String moduleName = "";
	
	
	public void execute()throws Exception{
		
	}
	
	/******************************************************/
	
	public ActionGenerator(){
		gg.put("moduleName",moduleName);
	}
	public ActionGenerator(String moduleName){
		gg.put("moduleName",moduleName);
	}
	
	public static void main(String[] args) throws Exception{
		ActionGenerator ge = new ActionGenerator();
		ge.execute();
		open();
	}
	
	
	private static final String sigleTem = "TEMPLATE_NEW/ACTION_TEMPLATE/single";
	private static final String twoGrid = "TEMPLATE_NEW/ACTION_TEMPLATE/twoTable/grid/main";
	private static final String twoTemMain = "TEMPLATE_NEW/ACTION_TEMPLATE/twoTable/temTable/main";
	private static final String twoTemSub = "TEMPLATE_NEW/ACTION_TEMPLATE/twoTable/temTable/sub";
	private static final String threeMain = twoTemMain;
	private static final String threeSubMain = "TEMPLATE_NEW/ACTION_TEMPLATE/threeTable/sub";
	
	
	public void generatorGrid(EntityClazz entityClazz) throws Exception{
		Class mainClazz = entityClazz.clazz;
		String entityCnName=entityClazz.entityCnName;
		
		//1 单表
		if(!entityClazz.hasSub()){
			gg.put("entityCnName", entityCnName);
			generator.generateByClass(mainClazz,sigleTem);
			return;
		}
		
		//2grid 
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
		
		generator.generateByMap(map, twoGrid);
	}
	
	public void generateTem4Two(EntityClazz entityClazz) throws Exception{
		Class mainClazz = entityClazz.clazz;
		String entityCnName=entityClazz.entityCnName;
		Map map = new HashMap();
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
		map.put("subClazzes", subClazzes);
		map.put("clazz",new JavaClass(mainClazz));
		map.put("entityCnName", entityCnName);
		/**
		 * 生成主表的Action
		 */
		generator.generateByMap(map,twoTemMain);
		for(EntityClazz subClass: entityClazz.subClazzes){
			map = new HashMap();
			map.put("clazz",new JavaClass(subClass.clazz));
			map.put("mainClazzId", subClass.fkProperty);
			map.put("fatherClazz",new JavaClass(subClass.fatherClazz.clazz));
			map.put("entityCnName", subClass.entityCnName);
			generator.generateByMap(map,twoTemSub);
		}
	}
	
	public void generateThree(EntityClazz entityClazz) throws Exception{
		Class mainClazz = entityClazz.clazz;
		String entityCnName=entityClazz.entityCnName;
		Map map = new HashMap();
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
		map.put("subClazzes", subClazzes);
		map.put("clazz",new JavaClass(mainClazz));
		map.put("entityCnName", entityCnName);
		/**
		 * 生成主表的Action
		 */
		generator.generateByMap(map,threeMain);
		for(EntityClazz subMain: entityClazz.subClazzes){
			map = new HashMap();
			map.put("clazz",new JavaClass(subMain.clazz));
			map.put("mainClazzId", subMain.fkProperty);
			map.put("fatherClazz",new JavaClass(subMain.fatherClazz.clazz));
			map.put("entityCnName", subMain.entityCnName);
			
			subClazzes = new JavaClass[subMain.subClazzes.size()];
			i=0;
			for(EntityClazz subClass: subMain.subClazzes){
				subClazzes[i] = new JavaClass(subClass.clazz);
				subClazzes[i].setFkProperty(subClass.fkProperty);
				i++;
			}
			map.put("subClazzes", subClazzes);
			
			generator.generateByMap(map,threeSubMain);
		}
	}
}