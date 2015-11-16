package iitdev.generator.main;

import iitdev.generator.entity.EntityTable;
import iitdev.generator.generer.EntityGenerator;



/**
 * 请使用具体的单表，多表生成器来生成实体
 * 实体代码生成器
 * 
 * 
 */
@Deprecated
public class EntityCodeGenerator extends BaseAbstractCodeGenerator {

	private static String moduleName = "";//模块
	
	public static void main(String[] args)throws Exception{
		/**
		 * 注意：如果VO继承临时表需要指定外键字段
		 * 一张表一张表生成，不用设置父子关系
		 */
		EntityTable table = new EntityTable("PRD_DEV_TROUBLE");	//参数为 表名：tc_pick_after,子表对象：sub
		EntityTable table2 = new EntityTable("PRD_DEV_TROUBLE_DTL");	//参数为 表名：tc_pick_after,子表对象：sub
		
		//EntityTable sub = new EntityTable("tc_end_shipment_out", "result_group_id");//参数为 表名：tc_pick_after,外键:before_id（如果不是继承临时表实体，不需要指定）
		
		
		//生成表对应的PO，VO代码
		gt(table,true,false);//（entityTable对象,是否生成sequence，是否继承临时表）
		gt(table2,true,true);
		//生成表对应的PO，VO代码
		//gt(sub,true,true);
		
		open();
	}
	
	///////////////////////////////////////////////////////////
	///////////////////////////////////////////////////////////
	
	public EntityCodeGenerator(){}
	
	/**
	 * 
	 * @param table
	 * @param seqFlg  PO是否使用Hibernate自动生成sequece
	 * @param temFlag VO实体是否继承临时表
	 * @throws Exception
	 */
	public static void gt(EntityTable table,boolean seqFlg,boolean temFlag) throws Exception{
		EntityGenerator eg = new EntityGenerator(moduleName);
		eg.generatorPO(new EntityTable[]{table}, seqFlg);
		eg.generatorVO(new EntityTable[]{table}, temFlag);
	}
	
}
