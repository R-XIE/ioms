package com.iitdev.ioms.book.service.impl;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.iitdev.ioms.book.data.bo.BookType;
import com.iitdev.ioms.book.data.vo.BookTypeVO;
import com.iitdev.ioms.book.service.BookBS;
import com.iitdev.ioms.book.service.BookTypeBS;
import com.iitdev.orm.PublicBSImpl;

/**
 *图书类型 SERVICE接口 实现类
 *
 */
@Service("bookTypeBS")
public class BookTypeBSImpl extends PublicBSImpl implements BookTypeBS{
	public  final String SQL_QUERY_VO =  BookTypeVO.QUERY_SQL;
	@Autowired
	private BookBS bookBS;
	public BookTypeVO queryVOById(Long id){
		String sql = SQL_QUERY_VO+
			" where obj.book_type_id  = ? ";
		return super.queryForBean(BookTypeVO.class,sql ,new Object[]{id});
	}
	@Override
	public List<BookTypeVO> queryListAll() {
		// TODO Auto-generated method stub
		return super.queryForBeanList(BookTypeVO.class,SQL_QUERY_VO, new Object[]{});
	}
	
	/**有外键的字段必须填充,而且要一致***/
	public BookType addBookType(BookType entity) throws Exception{
		//1主表验证和此表的外键字段一致
		//添加code编码
		//保存表
		super.saveObject(entity);
		return entity;
	}
	public BookType modifyBookType(BookType entity) throws Exception{
		//1主表验证和此表的外键字段一致
		
		//修改表
		super.updateObject(entity);
		return entity;
	}
	public Boolean delBookType(BookType entity)throws Exception{
		//1表删除
		if(bookBS.validateCountByType(entity.getBookTypeId())!=0){
			return false;
		}else{
			delete(entity);
			return true;
		}
	}
	@Override
    public Map<Long, String> getBookTypeMap() {
        Map<Long, String> map = new HashMap<>();
        List<BookTypeVO> bookTypeList =queryListAll();
        for (BookTypeVO vo : bookTypeList) {
            map.put(vo.getBookTypeId(), vo.getBookTypeName());
        }
        return map;
    }
    
  
    @Override
    public String getBookTypeLable(String bookType) {
        if (bookType != null) {
            String[] indexArr = bookType.split(",");
            StringBuilder sb = new StringBuilder();
            for (String str : indexArr) {
                String value =this.getBookTypeMap().get(new Long(str));
                if (value != null) {
                    sb.append(this.getBookTypeMap().get(new Long(str)));
                    sb.append(",");
                }
            }
            sb.deleteCharAt(sb.lastIndexOf(","));
            return sb.toString();
        } else {
            return "";
        }
    }

}