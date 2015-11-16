package iitdev.generator.generer;


import iitdev.generator.entity.EntityClazz;
import iitdev.generator.entity.EntityJSP;

import java.util.HashMap;
import java.util.Map;

import cn.org.rapid_framework.generator.provider.java.model.JavaClass;

/**
 * 生成service类
 *
 */
public class JSPGenerator extends AbstractGenerator{
	/**
	 *需要手动修改的变量  
	 */
	String moduleName = "";
	
	
	
	public void execute() throws Exception{
		
		
	}
	
	/**                                                    **/
	/**-------------------------------------END 一下不用管------------ **/
	public JSPGenerator(){
		gg.put("moduleName",moduleName);
	}
	public JSPGenerator(String moduleName){
		gg.put("moduleName",moduleName);
	}
	
	
	public static void main(String[] args) throws Exception{
		JSPGenerator ge = new JSPGenerator();
		ge.execute();
		open();
	}
	
	private static final String singleMain = "TEMPLATE_NEW/JSP_TEMPLATE/singleTable/main";
	private static final String twoGrid = "TEMPLATE_NEW/JSP_TEMPLATE/twoTable/grid";
	private static final String temMain = "TEMPLATE_NEW/JSP_TEMPLATE/twoTable/temTable/main";
	private static final String temSub = "TEMPLATE_NEW/JSP_TEMPLATE/twoTable/temTable/sub";
	
	private static final String threeMain = temMain;//list跟twoGrid的一模一样复制拷贝而已
	private static final String threeSubMain = "TEMPLATE_NEW/JSP_TEMPLATE/threeTable/subMain";
	//private static final String threeSub = "TEMPLATE_NEW/JSP_TEMPLATE/twoTable/temTable/sub";
	
	public void generatorSingle(EntityClazz entityClazz) throws Exception{
		Class mainClazz = entityClazz.clazz;
		String entityCnName=entityClazz.entityCnName;
		/*JavaClass[] subClazzes = new JavaClass[entityClazz.subClazzes.size()];
		int i=0;
		for(EntityClazz subClass: entityClazz.subClazzes){
			subClazzes[i] = new JavaClass(subClass.clazz);
			subClazzes[i].setFkProperty(subClass.fkProperty);
			i++;
		}
		Map map = new HashMap();
		map.put("subClazzes", subClazzes);*/
		Map map = new HashMap();
		JavaClass clazz=new EntityJSP(mainClazz);
		String clazzName= clazz.getClassName();
		char[] chars=new char[1];
		chars[0]=clazzName.charAt(0);
		String clazzLowerName = null;
		String temp = new String(chars);
		if(chars[0]>='A'  &&  chars[0]<='Z'){
			clazzLowerName=clazzName.replaceFirst(temp, temp.toLowerCase());
		}
		map.put("clazzLowerName",clazzLowerName);
		map.put("clazz",clazz);
		
		map.put("entityCnName", entityCnName);
		generator.generateByMap(map,singleMain);
	}
	
	public void generatorGrid(EntityClazz entityClazz) throws Exception{
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
		generator.generateByMap(map,twoGrid);
	}
	

	public void generateJsp4Two(EntityClazz entityClazz) throws Exception{
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
		generator.generateByMap(map,temMain);
		
		for(EntityClazz subClass: entityClazz.subClazzes){
			map = new HashMap();
			map.put("clazz",new JavaClass(subClass.clazz));
			map.put("mainClazzId", subClass.fkProperty);
			map.put("fatherClazz",new JavaClass(subClass.fatherClazz.clazz));
			map.put("entityCnName", subClass.entityCnName);
			generator.generateByMap(map, temSub);
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
