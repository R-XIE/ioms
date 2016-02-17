package com.iitdev.ioms.welcome.action;

import java.io.BufferedInputStream;
import java.io.BufferedOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.math.BigDecimal;
import java.util.List;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.ModelAndView;

import com.iitdev.encrypt.MD5;
import com.iitdev.globals.PropertiesConstant;
import com.iitdev.ioms.base.action.BaseAction;
import com.iitdev.ioms.member.common.Globals;
import com.iitdev.ioms.member.data.bo.Staff;
import com.iitdev.ioms.member.data.vo.StaffVO;
import com.iitdev.ioms.view.data.po.ModulePO;
import com.iitdev.utils.Util;

/**
 * 
 * staff Action
 * 
 */
@Controller
@RequestMapping("/welcome")
public class MainAction extends BaseAction<Integer> {
	//private Logger log = Logger.getLogger(MainAction.class);
	public MainAction() {
		super();
	}
	/************************************** 页面跳转 ******************************/
	@RequestMapping("/index")
	public ModelAndView left(){
		Long staffId = super.getLoginInfo().getStaffId();
		List<ModulePO> modules = permissionsBS.permissModuleByStaff(staffId);
		return new ModelAndView("index","modules",modules);
	}
	@RequestMapping("/main")
	public ModelAndView main(){
		ModelAndView view = new ModelAndView("main");
		Long staffId=super.getLoginInfo().getStaffId();
		int icountLeave = leaveBS.queryCountLeaveByStaff(staffId);
		int icountSalaryAlter = salaryAlterBS.queryCountSalaryAlterByStaff(staffId);
		int icountBookRecord=bookRecordBS.queryCountByStaff(staffId);
		int icountBook=bookBS.queryCountAll();
		int icountBusiness=businessTravelBS.queryCountAllByStaff(staffId);
		int icountWeekly=weeklyBS.queryCountAll(staffId);
		BigDecimal salaryCount=salaryBS.queryCountStaff(staffId);
		view.addObject("leaveCount", icountLeave);
		view.addObject("salaryAlterCount", icountSalaryAlter);
		view.addObject("bookRecordCount", icountBookRecord);
		view.addObject("bookCount", icountBook);
		view.addObject("businessCount", icountBusiness);
		view.addObject("weeklyCount", icountWeekly);
		view.addObject("salaryCount", salaryCount);
		return view;
	}
	
	@RequestMapping("/myprofile")
	public ModelAndView myprofile(){
		ModelAndView model=new ModelAndView("myprofile");
		StaffVO staffVO =staffBS.queryVOById(super.getLoginInfo().getStaffId());
		model.addObject("staff", staffVO);
		model.addObject("sexMap", Globals.M_MEMBER_SEX);
		model.addObject("degreeMap", Globals.M_MEMBER_DEGREE);
		model.addObject("postMap", com.iitdev.ioms.base.common.CmdUtils.getPostMap());
		model.addObject("positionMap",com.iitdev.ioms.base.common.CmdUtils.getPositionMap());
		return model;
	}
	
	@RequestMapping("/saveprofile")
	public void saveprofile(Staff staff
		//	,@RequestParam(value = "icon", required = false) MultipartFile icon
			){
		StaffVO vo = staffBS.queryVOById(staff.getStaffId());
		if(Util.isNotNull(staff.getStaffPwd())){
			vo.setStaffPwd(MD5.getMd5Password(staff.getStaffPwd()));
		}
		vo.setStaffRealName(staff.getStaffRealName());//设置真实姓名
		vo.setStaffPhone(staff.getStaffPhone());//设置电话
		vo.setStaffEmergency(staff.getStaffEmergency());//紧急联系方式
		vo.setStaffEmail(staff.getStaffEmail());//email
		vo.setStaffAddress(staff.getStaffAddress());//地址
		try {
			staff=staffBS.modifyStaff(vo.cloneBO());
		} catch (Exception e) {
			e.printStackTrace();
			super.printJson("false");
		}
		super.setLoginInfo(new StaffVO(staff).getLoginInfo());
		super.printJson("true");
	}
	@RequestMapping("saveImgProfile")
	public void uploadFile(
			Long recordId,
			@RequestParam(value = "fileupload", required = false) MultipartFile fileupload)
			throws Exception {
		// String fileBeginName = super.request.getSession().getServletContext()
		// .getRealPath("/")
		// + "WEB-INF" + File.separator + "upload" + File.separator;
		String fileBeginName = PropertiesConstant.getUploadDirProfile()
				+ File.separator;
		String fileEndName = fileupload.getOriginalFilename().substring(
				fileupload.getOriginalFilename().lastIndexOf(".") + 1);
		String fileName = MD5.GetMd5(MD5.GetMd5(recordId + "") + recordId);
		if (recordId != null) {
			StaffVO vo = staffBS.queryVOById(recordId);
			if (!fileupload.isEmpty()) {
				File file = new File(fileBeginName, fileName + "."
						+ fileEndName);
				if (!file.exists()) {
					file.mkdirs();
				}
				if (Util.isNotNull(vo.getStaffIcon())) {
					File fileExists = new File(fileBeginName,
							vo.getStaffIcon());
					if (fileExists.exists())
						fileExists.delete();
				}
				fileupload.transferTo(file);
				vo.setStaffIcon(fileName + "." + fileEndName);
				staffBS.modifyStaff(vo.cloneBO());
				super.setLoginInfo(vo.getLoginInfo());
				super.printHtml(fileName + "." + fileEndName);
			}
		}
	}
	
	@RequestMapping("saveImgProfileDownload")
	public void download(String fileName) throws Exception {
		String fileBeginName = PropertiesConstant.getUploadDirProfile()
				+ File.separator;
		File file = new File(fileBeginName + fileName);
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
								+ new String((fileName)
										.getBytes("utf-8"), "ISO8859-1"));
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
}