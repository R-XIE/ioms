package com.iitdev.ioms.base.service;

//import com.iitdev.ioms.base.data.bo.Position;
import java.util.List;

import com.iitdev.ioms.base.common.CmdUtils;
import com.iitdev.ioms.base.data.bo.Position;
import com.iitdev.ioms.base.data.vo.PositionVO;
import com.iitdev.orm.PublicBS;


/**
 *职位 SERVICE接口 
 */
public interface PositionBS extends PublicBS {
	/**通用查询方法***/
	public PositionVO queryVOById(Long id);	
	/**通用持久化方法(添加和修改)***/
	public Position addPosition(Position entity) throws Exception;//有外键的字段必须填充,而且要一致
	public Position modifyPosition(Position entity) throws Exception;//有外键的字段必须填充,而且要一致
	/**删除的方法**/
	public boolean delPosition(Position entity)throws Exception;
	
	List<PositionVO> queryVOAllList();
	/**
	 * @see CmdUtils#getPositionByStaff(Long)
	 * @param staffId
	 * @return
	 */
	public PositionVO queryPositionByStaff(Long staffId);
}