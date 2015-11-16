package iitdev.generator.main;

import iitdev.generator.entity.EntityClazz;
import iitdev.generator.entity.EntityTable;
import iitdev.generator.generer.ActionGenerator;
import iitdev.generator.generer.EntityGenerator;
import iitdev.generator.generer.JSPGenerator;
import iitdev.generator.generer.ServiceGenerator;

import com.iitdev.ioms.operation.data.bo.Backup;

/**
 * 单表 代码生成器
 *1)修改模块moduleName
 *2）
 *a)生成表的实体对象
 * 注释main方法里里的gg.generatorClazzAndJSP(),启用gg.generatorTable()
 * 修改generatorTable 里的变量，参考方法说明
 *b)生成 service,action,jsp(必须先执行生成实体，并已经拷贝到依赖的开发工程里)
 * 注释main方法里里的gg.generatorTable(),启用gg.generatorClazzAndJSP()
 * 修改 generatorClazzAndJSP 里的变量，参考方法说明
 * 
 * Modify By:<br/>
 * Modify Date:2012-7-20<br/>
 * Remark:修改说明<br/>
 */
public class SingleTableGenerator extends BaseAbstractCodeGenerator{
	
	/**
	 *需要手动修改的变量 
	 */
	public static String moduleName = "operation";
	
	public static void main(String[] args)throws Exception{
		SingleTableGenerator gg = new SingleTableGenerator();
//		gg.generatorTable();
		gg.generatorClazzAndJSP();
		
		open();
	}
	
	/**
	 * 使用说明
	 * 1)定义表Entity
	 * 例如 ：EntityTable mainTable = new EntityTable("BM_SYS_PARAM（表名称）");
	 * 
	 * 2)调用gt(主表EntityTable)生成文件
	 */
	public void generatorTable() throws Exception {
		EntityTable mainTable = new EntityTable("o_backup");	
		gt(mainTable);
	}
	/**
	 * 使用说明
	 * 1)定义主表Entity（只有一个）
	 * 例如 ：
	 * EntityClazz mainClazz = new EntityClazz(BmCodeType.class, "代码类型");//参数为：类class，中文名称
	 * 
	 * 2)调用gt(表EntityClazz)生成文件
	 */
	public void generatorClazzAndJSP()throws Exception{
		EntityClazz mainClazz = new EntityClazz(Backup.class,"备份记录");
		gc(mainClazz);
	}
	
	
//////////////////////////下面内容请勿修改/////////////////////////////////
//////////////////////////下面内容请勿修改/////////////////////////////////
//////////////////////////下面内容请勿修改/////////////////////////////////
	private void gt(EntityTable table) throws Exception{
		EntityGenerator eg = new EntityGenerator(moduleName);
		eg.generatorPO(new EntityTable[]{table}, true);
		eg.generatorVO(new EntityTable[]{table}, false);
	}
	
	private void gc(EntityClazz clazz)throws Exception{
		ServiceGenerator sg = new ServiceGenerator(moduleName);
		sg.generatorCacheBS(clazz);
		ActionGenerator ag = new ActionGenerator(moduleName);
		ag.generatorGrid(clazz);
		JSPGenerator jg = new JSPGenerator(moduleName);
		jg.generatorSingle(clazz);
	}
}