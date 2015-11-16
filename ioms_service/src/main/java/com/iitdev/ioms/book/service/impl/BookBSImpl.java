package com.iitdev.ioms.book.service.impl;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.iitdev.ioms.book.data.bo.Book;
import com.iitdev.ioms.book.data.vo.BookVO;
import com.iitdev.ioms.book.service.BookBS;
import com.iitdev.ioms.book.service.BookRecordBS;
import com.iitdev.ioms.book.service.BookTypeBS;
import com.iitdev.orm.PublicBSImpl;

/**
 *图书 SERVICE接口 实现类
 *
 */
@Service("bookBS")
public class BookBSImpl extends PublicBSImpl implements BookBS{
	public  final String SQL_QUERY_VO =  BookVO.QUERY_SQL;
	@Autowired
	private BookTypeBS bookTypeBS;
    @Autowired
	private BookRecordBS bookRecordBS;
    
	public BookVO queryVOById(Long id){
		String sql = SQL_QUERY_VO+
			" where obj.book_id  = ? ";
		BookVO vo= super.queryForBean(BookVO.class,sql ,new Object[]{id});
		return this.getBookVO(vo);
	}
	@Override
	public List<BookVO> queryListAll() {
		String sql = SQL_QUERY_VO+
				" where obj.book_state  = 0 ";
		List<BookVO> bookList= super.queryForBeanList(BookVO.class, sql, new Object[]{});
	       return getList(bookList);
	}
	public List<BookVO> queryVOListAll(){
		 List<BookVO> bookList= super.queryForBeanList(BookVO.class, SQL_QUERY_VO, new Object[]{});
	     return getList(bookList);
	}
	public List<BookVO>  queryVOListAllExist(){
		List<BookVO> bookList= super.queryForBeanList(BookVO.class, SQL_QUERY_VO + " where obj.book_state!=-2", new Object[]{});
        return getList(bookList);
	}
	
	/**有外键的字段必须填充,而且要一致***/
	public Book addBook(Book entity) throws Exception{
		//1主表验证和此表的外键字段一致
		//添加code编码
		//保存表
		super.saveObject(entity);
		return entity;
	}
	public Book modifyBook(Book entity) throws Exception{
		//1主表验证和此表的外键字段一致
		
		//修改表
		super.updateObject(entity);
		return entity;
	}
	public Boolean delBook(Book entity)throws Exception{
		//1表删除
		if(entity.getBookState()!=-1){
			//删除记录表
			String sql="delete from bo_book_record where book_id = "+entity.getBookId();
			super.executeSql(sql);
			delete(entity);
			return true;
		}else{
			return false;
		}
		
	}
	@Override
	public int queryCountAll() {
		String sql="select count(1) from bo_book ";
		return super.queryForInt(sql, new Object[]{});
	}
	@Override
	public int validateCountByType(Long typeId) {
		String sql="select count(1) from bo_book  where book_type like '%"+typeId+"%'";
		return super.queryForInt(sql, new Object[]{});
	}
	
	 /**
     * 更改返回的list,为list添加属性
     * @param list
     * @return 
     */
    private List<BookVO> getList(List<BookVO> list){
        for (int i = 0; i < list.size(); i++) {
            BookVO bookVO = list.get(i);
            String lable=bookTypeBS.getBookTypeLable(bookVO.getBookType());
            bookVO.setBookTypeLable(lable);
            if (bookVO.getBookState().equals(new Long(-1))) {
            	bookVO.setBorrowStaffName(bookRecordBS.getBorrowStaff(bookVO.getBookId()));
    		} else {
    			bookVO.setBorrowStaffName("无");
    		}
            list.set(i, bookVO);
        }
        return list;
    }
    
    /**
     * 添加属性
     * @param vo
     * @return
     */
    private BookVO getBookVO(BookVO vo){
    	String lable=bookTypeBS.getBookTypeLable(vo.getBookType());
    	vo.setBookTypeLable(lable);
        if (vo.getBookState().equals(new Long(-1))) {
        	vo.setBorrowStaffName(bookRecordBS.getBorrowStaff(vo.getBookId()));
		} else {
			vo.setBorrowStaffName("无");
		}
        return vo;
    }
}