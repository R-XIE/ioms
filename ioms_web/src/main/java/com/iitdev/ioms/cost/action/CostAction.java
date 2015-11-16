package com.iitdev.ioms.cost.action;

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
import com.iitdev.ioms.cost.common.CmdUtils;
//import com.ttcity.common.Struts2Action;
//import com.ttcity.exception.IitdevRuntimeException;
import com.iitdev.ioms.cost.data.bo.Cost;
import com.iitdev.ioms.cost.data.po.CostQueryForm;
import com.iitdev.ioms.cost.data.vo.CostVO;
import com.iitdev.ioms.cost.service.CostBS;
import com.iitdev.page.Result;
import com.iitdev.utils.Util;

/**
 *
 * 费用 Action
 *
 */
@Controller
@RequestMapping("/cost")
public class CostAction extends BaseAction<CostVO> {
	public CostAction() {
	}

	@Autowired
	private CostBS costBS;

	/************************************** 页面跳转 ******************************/


	@RequestMapping("costActionListAjax")
	public ModelAndView listAjax(@ModelAttribute("cost") CostQueryForm cost) {
		ModelAndView view = new ModelAndView("cost/costListAjax");
		Map<String,String> queryMap=cost.buildQueryMap();
		Result<CostVO>  page=costBS.queryResultByPage(cost.getP(), queryMap, cost.getS());
		view.addObject("query", cost);
		view.addObject("page", page);
		view = setGlobals(view, "costActionListAjax");
		return view;
	}


	@RequestMapping("costActionDel")
	public void del(Long recordId) throws Exception {
		boolean flag;
		CostVO vo = costBS.queryVOById(recordId);
		String fileBeginName = PropertiesConstant.getUploadDirCost()
				+ File.separator;
		if (recordId != null) {
			if (vo.getCostDetail() != null) {
				File file = new File(fileBeginName, vo.getCostDetail());
				if (file.exists())
					file.delete();
			}
			flag = costBS.delCost(vo.cloneBO());
		} else {
			flag = false;
		}
		super.printJson(flag + "");
	}

	@RequestMapping("costActionInput")
	public ModelAndView input(Long recordId) throws Exception {
		ModelAndView view = new ModelAndView("cost/costInput");
		String type = "";
		if (recordId != null) {
			CostVO vo = costBS.queryVOById(recordId);
			view.addObject("entity", vo);
			type = "EDIT";
		} else {
			type = "ADD";
		}
		view = setGlobals(view, "costActionInput");
		view.addObject("type", type);
		return view;
	}

	@RequestMapping("costActionView")
	public ModelAndView view(Long recordId) throws Exception {
		ModelAndView view = new ModelAndView("cost/costInput");
		String type = "";
		if (recordId != null) {
			CostVO vo = costBS.queryVOById(recordId);
			view.addObject("entity", vo);
			type = "VIEW";
		}
		view = setGlobals(view, "costActionView");
		view.addObject("type", type);
		return view;
	}

	/**
	 * 上传文件界面
	 * @param recordId
	 * @return
	 * @throws Exception
	 */
	@RequestMapping("costActionUploadView")
	public ModelAndView uploadView(Long recordId) throws Exception {
		ModelAndView view = new ModelAndView("cost/costInputUploadView");
		if (recordId != null) {
			CostVO vo = costBS.queryVOById(recordId);
			view.addObject("entity", vo);
		}
		view = setGlobals(view, "costActionUploadView");
		return view;
	}

	/**
	 * 上传文件操作
	 * @param recordId
	 * @param fileupload
	 * @throws Exception
	 */
	@RequestMapping("costActionUploadFile")
	public void uploadFile(
			Long recordId,
			@RequestParam(value = "fileupload", required = false) MultipartFile fileupload)
			throws Exception {
		// String fileBeginName = super.request.getSession().getServletContext()
		// .getRealPath("/")
		// + "WEB-INF" + File.separator + "upload" + File.separator;
		String fileBeginName = PropertiesConstant.getUploadDirCost()
				+ File.separator;
		String fileEndName = fileupload.getOriginalFilename().substring(
				fileupload.getOriginalFilename().lastIndexOf(".") + 1);
		String fileName = MD5.GetMd5(MD5.GetMd5(recordId + "") + recordId);
		if (recordId != null) {
			CostVO vo = costBS.queryVOById(recordId);
			if (!fileupload.isEmpty()) {
				File file = new File(fileBeginName, fileName + "."
						+ fileEndName);
				if (!file.exists()) {
					file.mkdirs();
				}
				if (Util.isNotNull(vo.getCostDetail())) {
					File fileExists = new File(fileBeginName,
							vo.getCostDetail());
					if (fileExists.exists())
						fileExists.delete();
				}
				fileupload.transferTo(file);
				vo.setCostDetail(fileName + "." + fileEndName);
				costBS.modifyCost(vo.cloneBO());
				super.printHtml(fileName + "." + fileEndName);
			}
		}
	}

	@RequestMapping("costActionDelFile")
	public void del(Long recordId, String fileName) throws Exception {
		// String fileBeginName = super.request.getSession().getServletContext()
		// .getRealPath("/")
		// + "WEB-INF" + File.separator + "upload" + File.separator;
		String fileBeginName = PropertiesConstant.getUploadDirCost()
				+ File.separator;
		if (recordId != null) {
			CostVO vo = costBS.queryVOById(recordId);
			File file = new File(fileBeginName, fileName);
			if (file.exists())
				file.delete();
			vo.setCostDetail(null);
			costBS.modifyCost(vo.cloneBO());
			super.printJson(true + "");
		} else {
			super.printJson(false + "");
		}

	}

	/**
	 * 下载文件
	 * @param fileName
	 * @param out
	 * @throws Exception
	 */
	@RequestMapping("costActionDownload")
	public void download(String fileName, String out) throws Exception {
		out = new String(out.getBytes("iso8859-1"), "UTF-8");
		String fileBeginName = PropertiesConstant.getUploadDirCost()
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

	@RequestMapping("costActionSave")
	public void save(CostVO costVO) throws Exception {
		Cost temp;
		if (costVO.getCostId() == null) {
			costVO.setCostCreateTime(new Date());
			temp = costBS.addCost(costVO.cloneBO());
		} else {
			CostVO vo = costBS.queryVOById(costVO.getCostId());
			costVO.setCostCreateTime(vo.getCostCreateTime());
			costVO.setCostDetail(vo.getCostDetail());
			temp = costBS.modifyCost(costVO.cloneBO());
		}
		if (temp != null)
			super.printJson(true + "");
		else
			super.printJson(false + "");
	}

	public ModelAndView setGlobals(ModelAndView model, String url) {
		model.addObject("url", request.getContextPath() + "/cost/" + url
				+ ".htm");
		model.addObject("costTypeMap", CmdUtils.getCostTypeMap());
		model.addObject("pageMap", com.iitdev.ioms.base.common.Globals.M_PAGE_MAP);
		model.addObject("costTypeList", CmdUtils.getCostTypeList());
		return model;
	}
}