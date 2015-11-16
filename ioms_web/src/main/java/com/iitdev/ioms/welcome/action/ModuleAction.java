package com.iitdev.ioms.welcome.action;

import java.io.BufferedInputStream;
import java.io.BufferedOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;
import javax.servlet.http.HttpSession;

import net.sf.ehcache.Cache;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import com.iitdev.globals.PropertiesConstant;
import com.iitdev.ioms.base.action.BaseAction;
import com.iitdev.ioms.book.common.Globals;
import com.iitdev.ioms.book.data.vo.BookRecordVO;
import com.iitdev.ioms.book.data.vo.BookVO;
import com.iitdev.ioms.business.data.vo.BusinessTravelVO;
import com.iitdev.ioms.member.action.LeaveAction;
import com.iitdev.ioms.member.action.SalaryAlterAction;
import com.iitdev.ioms.member.common.CmdUtils;
import com.iitdev.ioms.member.data.po.LeaveQueryForm;
import com.iitdev.ioms.member.data.vo.LeaveVO;
import com.iitdev.ioms.member.data.vo.SalaryAlterVO;
import com.iitdev.ioms.member.data.vo.SalaryVO;
import com.iitdev.ioms.operation.data.vo.PwdVO;
import com.iitdev.ioms.weekly.common.Globals.Mweekly;
import com.iitdev.ioms.weekly.data.bo.Weekly;
import com.iitdev.ioms.weekly.data.po.WeeklyQueryForm;
import com.iitdev.ioms.weekly.data.vo.WeeklyVO;
import com.iitdev.page.Result;

/**
 * 
 * staff Action
 * 
 */
@Controller
@RequestMapping("/welcome")
public class ModuleAction extends BaseAction<Integer> {
	@Resource(name="ehCache")
	private Cache cache;
	
	public ModuleAction() {
		super();
	}

	/************************************** 页面跳转 ******************************/

	@RequestMapping("leaveActionListAjax")
	public ModelAndView listAjax(@ModelAttribute("leave") LeaveQueryForm leave) {
		ModelAndView view = new ModelAndView("member/leaveListAjax");
		Long staffId = super.getLoginInfo().getStaffId();
		Map<String, String> queryMap = leave.buildQueryMap();
		queryMap.put("staffId", staffId+"");
		Result<LeaveVO> page = leaveBS.queryResultByPage(leave.getP(),
				queryMap, leave.getS());
		view.addObject("query", leave);
		view.addObject("page", page);
		Map<Long, String> positionMap = com.iitdev.ioms.base.common.CmdUtils
				.getPositionMap();
		Map<Long, String> branchMap = com.iitdev.ioms.base.common.CmdUtils
				.getBranchMap();
		Map<Long, String> staffMap = com.iitdev.ioms.member.common.CmdUtils
				.getStaffMap();
		Map<Long, String> leaveMap = com.iitdev.ioms.member.common.Globals.M_LAEAVE_TYPE;
		view.addObject("pageMap",
				com.iitdev.ioms.base.common.Globals.M_PAGE_MAP);
		view.addObject("branchMap", branchMap);
		view.addObject("positionMap", positionMap);
		view.addObject("staffMap", staffMap);
		view.addObject("leaveMap", leaveMap);
		view.addObject("type", "s");
		return view;
	}

	@RequestMapping("leaveActionView")
	public ModelAndView leaveView(Long recordId) {
		ModelAndView view = new ModelAndView("member/leaveInput");
		Long staffId = super.getLoginInfo().getStaffId();
		LeaveVO leaveVO = leaveBS.queryLeaveByStaff(staffId, recordId);
		view.addObject("type", "SV");
		view.addObject("entity", leaveVO);
		view = LeaveAction.setGlobals(view);
		return view;
	}

	@RequestMapping("salaryAlterActionList")
	public ModelAndView salaryAlterList() {
		ModelAndView view = new ModelAndView("member/salaryAlterList");
		Long staffId = super.getLoginInfo().getStaffId();
		List<SalaryAlterVO> salaryAlterList = salaryAlterBS
				.queryAlterByStaff(staffId);
		view.addObject("type", "s");
		view.addObject("list", salaryAlterList);
		view = SalaryAlterAction.setGlobals(view);
		return view;
	}

	@RequestMapping("salaryAlterActionView")
	public ModelAndView salaryAlterView(Long recordId) {
		ModelAndView view = new ModelAndView("member/salaryAlterInput");
		Long staffId = super.getLoginInfo().getStaffId();
		SalaryAlterVO salaryAlterVO = salaryAlterBS.queryAlterByStaff(staffId,
				recordId);
		view.addObject("type", "SV");
		view.addObject("entity", salaryAlterVO);
		view = SalaryAlterAction.setGlobals(view);
		return view;
	}

	@RequestMapping("bookRecordList")
	public ModelAndView bookRecordList() {
		ModelAndView view = new ModelAndView("welcome/bookRecordList");
		Long staffId = super.getLoginInfo().getStaffId();
		List<BookRecordVO> list = bookRecordBS.queryBookRecordByStaff(staffId);
		Map<Long, String> bookRecordMap = Globals.M_BOOK_RECORD_STATE;
		view.addObject("list", list);
		view.addObject("bookRecordMap", bookRecordMap);
		return view;
	}

	@RequestMapping("bookList")
	public ModelAndView bookList() {
		ModelAndView view = new ModelAndView("welcome/bookList");
		List<BookVO> list = bookBS.queryVOListAllExist();
		Map<Long, String> bookMap = Globals.M_BOOK_STATE;
		view.addObject("list", list);
		view.addObject("bookMap", bookMap);
		return view;
	}

	@RequestMapping("bookListDownload")
	public void download() throws Exception {
		String fileBeginName = super.request.getSession().getServletContext()
				.getRealPath("/")
				+ "WEB-INF" + File.separator + "download" + File.separator;
		File file = new File(fileBeginName + "bookmanager.doc");
		if (file.exists()) {
			response.setContentType("text/html;charset=utf-8");
			request.setCharacterEncoding("UTF-8");
			java.io.BufferedInputStream bis = null;
			java.io.BufferedOutputStream bos = null;
			try {
				long fileLength = file.length();
				response.setContentType("application/x-msdownload;");
				response.setHeader(
						"Content-disposition",
						"attachment; filename="
								+ new String("图书馆管理制度.doc".getBytes("utf-8"),
										"ISO8859-1"));
				response.setHeader("Content-Length", String.valueOf(fileLength));
				bis = new BufferedInputStream(new FileInputStream(file));
				bos = new BufferedOutputStream(response.getOutputStream());
				byte[] buff = new byte[2048];
				int bytesRead;
				while (-1 != (bytesRead = bis.read(buff, 0, buff.length))) {
					bos.write(buff, 0, bytesRead);
				}
			} catch (Exception e) {
				e.printStackTrace();
			} finally {
				if (bis != null)
					bis.close();
				if (bos != null)
					bos.close();
			}
		}

	}

	@RequestMapping("businessTravelList")
	public ModelAndView businessTravelList() {
		ModelAndView view = new ModelAndView("business/businessTravelList");
		Long staffId = super.getLoginInfo().getStaffId();
		List<BusinessTravelVO> list = businessTravelBS
				.queryVOListStaffAll(staffId);
		view.addObject("s", "sss");
		view.addObject("list", list);
		view.addObject("url", request.getContextPath()
				+ "/welcome/businessTravelList.htm");
		return view;
	}

	@RequestMapping("businessTravelView")
	public ModelAndView businessTravelView(Long recordId) {
		ModelAndView view = new ModelAndView("business/businessTravelInput");
		Long staffId = super.getLoginInfo().getStaffId();
		BusinessTravelVO entity = businessTravelBS.queryVOByStaff(staffId,
				recordId);
		view.addObject("s", "ssss");
		view.addObject("entity", entity);
		view.addObject("type", "VIEW");
		view.addObject("staffMap", CmdUtils.getStaffMap());
		return view;
	}


	@RequestMapping("weeklyListAjax")
	public ModelAndView weeklyListAjax(@ModelAttribute("weekly") WeeklyQueryForm weekly) {
		ModelAndView view = new ModelAndView("weekly/weeklyListAjax");
		view.addObject("s", "sss");
		view.addObject("url", request.getContextPath()
				+ "/welcome/weeklyListAjax.htm");
		Map<String,String> queryMap=weekly.buildQueryMap();
		queryMap.put("staffId", super.getLoginInfo().getStaffId().toString());
		Result<WeeklyVO>  page=weeklyBS.queryResultByPage(weekly.getP(), queryMap, weekly.getS());
		view.addObject("query", weekly);
		view.addObject("page", page);
		view.addObject("pageMap", com.iitdev.ioms.base.common.Globals.M_PAGE_MAP);
		view.addObject("weeklyMap", com.iitdev.ioms.weekly.common.Globals.M_WEEKLY_STATE);
		return view;
	}


	@RequestMapping("weeklyView")
	public ModelAndView weeklyView(Long recordId) {
		ModelAndView view = new ModelAndView("weekly/weeklyInput");
		Long staffId = super.getLoginInfo().getStaffId();
		WeeklyVO entity = weeklyBS.queryBeanStaff(recordId, staffId);
		view.addObject("s", "ssss");
		view.addObject("entity", entity);
		view.addObject("type", "VIEW");
		view.addObject("weeklyMap",
				com.iitdev.ioms.weekly.common.Globals.M_WEEKLY_STATE);
		// view.addObject("staffMap", CmdUtils.getStaffMap());
		return view;
	}

	@RequestMapping("weeklyInput")
	public ModelAndView input(Long recordId) throws Exception {
		ModelAndView view = new ModelAndView("weekly/weeklyInput");
		String type = "";
		if (recordId != null) {
			WeeklyVO vo = weeklyBS.queryVOById(recordId);
			view.addObject("entity", vo);
			type = "EDIT";
		} else {
			type = "ADD";
		}
		view.addObject("s", "ssss");
		view.addObject("type", type);
		return view;
	}

	@RequestMapping("weeklySave")
	public void save(WeeklyVO weeklyVO) throws Exception {
		weeklyVO.setWeeklyStaff(super.getLoginInfo().getStaffId());
		Weekly temp;
		if (weeklyVO.getWeeklyId() == null) {
			temp = weeklyBS.addWeekly(weeklyVO.cloneBO(), Mweekly.UNSUBMITED);
		} else {
			WeeklyVO vo = weeklyBS.queryVOById(weeklyVO.getWeeklyId());
			weeklyVO.setWeeklyState(vo.getWeeklyState());
			temp = weeklyBS.modifyWeekly(weeklyVO.cloneBO(),
					vo.getWeeklyStaff());
		}
		if (temp != null)
			super.printJson(true + "");
		else
			super.printJson(false + "");
	}

	@RequestMapping("weeklyDel")
	public void del(Long recordId) throws Exception {
		WeeklyVO vo = weeklyBS.queryVOById(recordId);
		boolean res = weeklyBS.delWeekly(vo.cloneBO());
		super.printJson(res + "");
	}

	@RequestMapping("weeklyState")
	public void state(Long recordId) throws Exception {
		Weekly temp;
		WeeklyVO vo = weeklyBS.queryVOById(recordId);
		vo.setWeeklyState(com.iitdev.ioms.weekly.common.Globals.L_WEEKLY_STATE_SUBMIT);
		temp = weeklyBS.modifyWeekly(vo.cloneBO(), vo.getWeeklyStaff());
		if (temp != null)
			super.printJson(true + "");
		else
			super.printJson(false + "");
	}

	@RequestMapping("salaryList")
	public ModelAndView salaryList() {
		ModelAndView view = new ModelAndView("member/salaryList");
		Long staffId = super.getLoginInfo().getStaffId();
		List<SalaryVO> list = salaryBS.queryVOListStaff(staffId);
		view.addObject("s", "sss");
		view.addObject("list", list);
		view.addObject("url", request.getContextPath()
				+ "/welcome/salaryList.htm");
		return view;
	}

	@RequestMapping("salaryView")
	public ModelAndView salaryView(Long recordId) {
		ModelAndView view = new ModelAndView("member/salaryInput");
		Long staffId = super.getLoginInfo().getStaffId();
		SalaryVO entity = salaryBS.queryBeanStaff(recordId, staffId);
		view.addObject("s", "ssss");
		view.addObject("entity", entity);
		view.addObject("type", "VIEW");
		// view.addObject("staffMap", CmdUtils.getStaffMap());
		return view;
	}
	
	@RequestMapping("pwdList")
	public ModelAndView pwdList() {
		ModelAndView view = new ModelAndView("welcome/pwdList");
		List<PwdVO> list =pwdBS.queryVOListByStaff(super.getLoginInfo().getStaffId());
		view.addObject("list", list);
		view.addObject("pwdMap", com.iitdev.ioms.operation.common.Globals.M_PWD_LEVEL);
		return view;
	}
	
	@RequestMapping("cacheClean")
	public void cacheClean() {
		//删除缓存
		cache.removeAll();
		//删除缓存资源
		HttpSession session = request.getSession();
		session.removeAttribute(PropertiesConstant.getSessionResKey());
		//重新保存用户session
		Long staffId = super.getLoginInfo().getStaffId();
		super.setLoginInfo(staffBS.queryVOById(staffId).getLoginInfo());
	}
}