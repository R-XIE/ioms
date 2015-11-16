package com.iitdev.ioms.member.action;

import java.io.BufferedInputStream;
import java.io.BufferedOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.util.Date;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.ModelAndView;

import com.iitdev.action.BaseAction;
import com.iitdev.encrypt.MD5;
import com.iitdev.globals.PropertiesConstant;
import com.iitdev.ioms.base.common.CmdUtils;
import com.iitdev.ioms.member.common.Globals;
import com.iitdev.ioms.member.data.bo.Interview;
import com.iitdev.ioms.member.data.po.InterviewQueryForm;
import com.iitdev.ioms.member.data.vo.InterviewVO;
import com.iitdev.ioms.member.service.InterviewBS;
import com.iitdev.ioms.permiss.data.bo.Role;
import com.iitdev.page.Result;
import com.iitdev.utils.Util;

/**
 * 
 * 面试 Action
 * 
 */
@Controller
@RequestMapping("/member")
public class InterviewAction extends BaseAction<Role> {
	@Autowired
	private InterviewBS interviewBS;

	public InterviewAction() {
		super();
	}

	/************************************** 页面跳转 ******************************/

	@RequestMapping("interviewActionListAjax")
	public ModelAndView listAjax(@ModelAttribute("interview") InterviewQueryForm interview) {
		ModelAndView view = new ModelAndView("member/interviewListAjax");
		Map<String,String> queryMap=interview.buildQueryMap();
		Result<InterviewVO>  page=interviewBS.queryResultByPage(interview.getP(), queryMap, interview.getS());
		view.addObject("query", interview);
		view.addObject("page", page);
		view = setGlobals(view);
		return view;
	}


	@RequestMapping("interviewActionDel")
	public void del(Long recordId) throws Exception {
		boolean flag;
		InterviewVO vo = interviewBS.queryVOById(recordId);
		String fileBeginName = PropertiesConstant.getUploadDirInterview()
				+ File.separator;
		if (recordId != null) {
			if (vo.getInterviewCv() != null) {
				File file = new File(fileBeginName, vo.getInterviewCv());
				if (file.exists())
					file.delete();
			}
			flag = interviewBS.delInterview(vo.cloneBO());
		} else {
			flag = false;
		}
		printJson(flag + "");
	}

	@RequestMapping("interviewActionInput")
	public ModelAndView input(Long recordId) throws Exception {
		ModelAndView view = new ModelAndView("member/interviewInput");
		String type = "";
		if (recordId != null) {
			InterviewVO vo = interviewBS.queryVOById(recordId);
			view.addObject("entity", vo);
			type = "EDIT";
		} else {
			type = "ADD";
		}
		view.addObject("type", type);
		view = setGlobals(view);
		return view;
	}

	@RequestMapping("interviewActionView")
	public ModelAndView view(Long recordId) throws Exception {
		ModelAndView view = new ModelAndView("member/interviewInput");
		String type = "";
		if (recordId != null) {
			InterviewVO vo = interviewBS.queryVOById(recordId);
			view.addObject("entity", vo);
			type = "VIEW";
		}
		view.addObject("type", type);
		view = setGlobals(view);
		return view;
	}

	@RequestMapping("interviewActionUploadView")
	public ModelAndView uploadView(Long recordId) throws Exception {
		ModelAndView view = new ModelAndView("member/interviewUploadView");
		if (recordId != null) {
			InterviewVO vo = interviewBS.queryVOById(recordId);
			view.addObject("entity", vo);
		}
		view = setGlobals(view);
		return view;
	}

	@RequestMapping("interviewActionUploadFile")
	public void uploadFile(
			Long recordId,
			@RequestParam(value = "fileupload", required = false) MultipartFile fileupload)
			throws Exception {
		// String fileBeginName = super.request.getSession().getServletContext()
		// .getRealPath("/")
		// + "WEB-INF" + File.separator + "upload" + File.separator;
		String fileBeginName = PropertiesConstant.getUploadDirInterview()
				+ File.separator;
		String fileEndName = fileupload.getOriginalFilename().substring(
				fileupload.getOriginalFilename().lastIndexOf(".") + 1);
		String fileName = MD5.GetMd5(MD5.GetMd5(recordId + "") + recordId);
		if (recordId != null) {
			InterviewVO vo = interviewBS.queryVOById(recordId);
			if (!fileupload.isEmpty()) {
				File file = new File(fileBeginName, fileName + "."
						+ fileEndName);
				if (!file.exists()) {
					file.mkdirs();
				}
				if (Util.isNotNull(vo.getInterviewCv())) {
					File fileExists = new File(fileBeginName,
							vo.getInterviewCv());
					if (fileExists.exists())
						fileExists.delete();
				}
				fileupload.transferTo(file);
				vo.setInterviewCv(fileName + "." + fileEndName);
				interviewBS.modifyInterview(vo.cloneBO());
				super.printHtml(fileName + "." + fileEndName);
			}
		}
	}

	@RequestMapping("interviewActionSave")
	public void save(InterviewVO interview) throws Exception {
		Interview temp;
		if (interview.getInterviewId() == null) {
			interview.setCreateTime(new Date());
			temp = interviewBS.addInterview(interview.cloneBO());
		} else {
			InterviewVO vo = interviewBS
					.queryVOById(interview.getInterviewId());
			interview.setCreateTime(vo.getCreateTime());
			interview.setInterviewCv(vo.getInterviewCv());
			temp = interviewBS.modifyInterview(interview.cloneBO());
		}
		if (temp != null)
			super.printJson(true + "");
		else
			super.printJson(false + "");
	}

	@RequestMapping("interviewActionDelFile")
	public void del(Long recordId, String fileName) throws Exception {
		// String fileBeginName = super.request.getSession().getServletContext()
		// .getRealPath("/")
		// + "WEB-INF" + File.separator + "upload" + File.separator;
		String fileBeginName = PropertiesConstant.getUploadDirInterview()
				+ File.separator;
		if (recordId != null) {
			InterviewVO vo = interviewBS.queryVOById(recordId);
			File file = new File(fileBeginName, fileName);
			if (file.exists())
				file.delete();
			vo.setInterviewCv(null);
			interviewBS.modifyInterview(vo.cloneBO());
			super.printJson(true + "");
		} else {
			super.printJson(false + "");
		}
	}

	@RequestMapping("interviewActionDownload")
	public void download(String fileName, String out) throws Exception {
		out = new String(out.getBytes("iso8859-1"), "UTF-8");
		String fileBeginName = PropertiesConstant.getUploadDirInterview()
				+ File.separator;
		String fileEndName = fileName.substring(fileName.lastIndexOf(".") + 1);
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
								+ new String((out + "." + fileEndName)
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

	private ModelAndView setGlobals(ModelAndView model) {
		Map<Long, String> positionMap = CmdUtils.getPositionMap();
		Map<Long, String> sexMap = Globals.M_MEMBER_SEX;
		Map<Long, String> currentMap = Globals.M_INTERVIEW_CURRENT;
		Map<Long, String> resultMap = Globals.M_INTERVIEW_RESULT;
		model.addObject("positionMap", positionMap);
		model.addObject("sexMap", sexMap);
		model.addObject("currentMap", currentMap);
		model.addObject("resultMap", resultMap);
		model.addObject("pageMap",com.iitdev.ioms.base.common.Globals.M_PAGE_MAP);
		return model;
	}

}