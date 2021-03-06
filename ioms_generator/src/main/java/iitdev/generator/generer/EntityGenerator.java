package iitdev.generator.generer;

import iitdev.generator.entity.EntityTable;

/**
 * 实体生成
 *
 *
 */
public class EntityGenerator extends AbstractGenerator{
	/**
	 *需要手动修改的变量 --------------------------------
	 */
	private String moduleName = "portal";//模块
	
	public void execute() throws Exception{
		//EntityTable ss = new EntityTable("tc_pick_notice_place_dtl", "notice_detail_id");
		//EntityTable sub1 = new EntityTable("tc_pick_notice_dtl", "notice_id",ss);
		EntityTable mainTable = new EntityTable("RO_ACT_CODE");
		
		//generatorPO(new EntityTable[]{mainTable},true);
		generatorVO(new EntityTable[]{mainTable},true);
		
		//generatorPO(new EntityTable[]{sub1,ss},false);
		//generatorVO(new EntityTable[]{sub1,ss},true);
	
	}
	
	
	/**                                                    **/
	/**-------------------------------------END 一下不用管------------ **/
	 /**                                                    */
	private static final String poTem = "TEMPLATE_NEW/ENTITY_TEMPLATE/po";
	private static final String voTem = "TEMPLATE_NEW/ENTITY_TEMPLATE/vo";
	private static final String subPoTem = "TEMPLATE_NEW/ENTITY_TEMPLATE/subPO";
	private static final String subVoTem = "TEMPLATE_NEW/ENTITY_TEMPLATE/subVO";
	
	public static void main(String[] args) throws Exception{
		EntityGenerator eg = new EntityGenerator();
		eg.execute();
		open();
	}
	public EntityGenerator(){
		gg.put("moduleName",moduleName);
	}
	public EntityGenerator(String moduleName){
		gg.put("moduleName",moduleName);
	}
	
	
	/**
	 * 生成PO
	 * @param sequence  是否添加Hibernatne Id递增
	 * @param tables
	 * @throws Exception
	 */
	public void generatorPO(EntityTable[] tables,boolean sequence) throws Exception{
		for(EntityTable table: tables){
			String mainTableName= table.getTableName();
			if(sequence){
				generator.generateByTable(mainTableName,poTem);
			}else{
				generator.generateByTable(mainTableName,subPoTem);
			}
		}
	}
	
	public void generatorVO(EntityTable[] tables,boolean isTem) throws Exception{
		for(EntityTable table: tables){
			String mainTableName= table.getTableName();
			if(isTem){
				String foreignKey = table.getFkColumn();
				gg.put("foreignKey", foreignKey);
				generator.generateByTable(mainTableName,subVoTem);
			}else{
				generator.generateByTable(mainTableName,voTem);
			}
		}
	}
	
	
	/*public void generator(EntityTable mainTable) throws Exception{
		String mainTableName= mainTable.getTableName();
		
		generator.generateByTable(mainTableName,poTem);
		generator.generateByTable(mainTableName,voTem);	
		
		generatorSubTable(mainTable);
	}*/
	
	
	/*public void generator(EntityTable mainTable) throws Exception{
		String mainTableName= mainTable.getTableName();
		
		generator.generateByTable(mainTableName,poTem);
		generator.generateByTable(mainTableName,voTem);	
		
		generatorSubTable(mainTable);
	}
	
	public void generatorSubTable(EntityTable mainTable) throws Exception{
		if(mainTable.hasSubTable()){
			for(EntityTable sub : mainTable.getSubEntitys()){
				String detailTableName = sub.getTableName();
				String foreignKey = sub.getFkColumn();
				super.gg.put("foreignKey", foreignKey);
				
				generator.generateByTable(detailTableName,subPoTem);
				generator.generateByTable(detailTableName,subVoTem);	
				
				if(sub.hasSubTable()){generatorSubTable(sub);}
			}
		}
	}*/
}
