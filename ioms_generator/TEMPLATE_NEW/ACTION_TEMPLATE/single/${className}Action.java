<#assign className = clazz.className>   
<#assign classNameLower = className?uncap_first>
<#assign classNameFull = "${basepackage}.${moduleName}.data.bo.${className}"> 
<#assign VONameFull = "${basepackage}.${moduleName}.data.vo.${className}VO"> 
package ${basepackage}.${moduleName}.action;


import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.commons.lang.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;

import com.iitdev.page.Result;
import org.springframework.web.servlet.ModelAndView;

import com.iitdev.action.BaseAction;
//import com.ttcity.common.Struts2Action;
//import com.ttcity.exception.IitdevRuntimeException;

import com.iitdev.ioms.book.common.CmdUtils;
import com.iitdev.ioms.book.common.Globals;
import com.iitdev.page.Result;

import ${basepackage}.${moduleName}.data.bo.${className};
import ${basepackage}.${moduleName}.data.po.${className}QueryForm;
import ${basepackage}.${moduleName}.data.vo.${className}VO;
import ${basepackage}.${moduleName}.service.${className}BS;
import ${basepackage}.${moduleName}.common.Globals;
/**
*
*${entityCnName} Action 
*
*/
@Controller
@RequestMapping("/${moduleName}")
public class ${className}Action extends BaseAction<${className}VO>{
	public  ${className}Action(){}
	@Autowired
	private ${className}BS ${classNameLower}BS;
	/**************************************页面跳转******************************/
	//TO 查看界面
	
	@RequestMapping("${classNameLower}ActionList")
	public ModelAndView list() {
		ModelAndView view = new ModelAndView("${moduleName}/${classNameLower}List");
		List<${className}VO> ${classNameLower}List= ${classNameLower}BS.queryVOListAll();
		view.addObject("list", ${classNameLower}List);
		view=setGlobals(view,"${classNameLower}ActionList");
		return view;
	}
	
	@RequestMapping("${classNameLower}ActionListAjax")
	public ModelAndView listAjax(@ModelAttribute("${classNameLower}") ${className}QueryForm ${classNameLower}) {
		ModelAndView view = new ModelAndView("${moduleName}/${classNameLower}ListAjax");
		
		Map<String,String> queryMap=${classNameLower}.buildQueryMap();
		Result<${className}VO>  page=${classNameLower}BS.queryResultByPage(${classNameLower}.getP(), queryMap, ${classNameLower}.getS());
		view.addObject("query", ${classNameLower});
		view.addObject("page", page);
		view=setGlobals(view,"${classNameLower}ActionListAjax");
		return view;
	}
	
	@RequestMapping("${classNameLower}ActionDel")
	public void del(Long recordId) throws Exception {
		${className}VO vo = ${classNameLower}BS.queryVOById(recordId);
		boolean res = ${classNameLower}BS.del${className}(vo.cloneBO());
		super.printJson(res+"");
	}
	
	
	@RequestMapping("${classNameLower}ActionInput")
	public ModelAndView input(Long recordId) throws Exception {
		ModelAndView view = new ModelAndView("${moduleName}/${classNameLower}Input");
		String type="";
		if(recordId!=null){
			${className}VO vo = ${classNameLower}BS.queryVOById(recordId);
			view.addObject("entity", vo);
			type="EDIT";
		}else{
			type="ADD";
		}
		view=setGlobals(view,"${classNameLower}ActionInput");
		view.addObject("type", type);
		return view;
	}
	@RequestMapping("${classNameLower}ActionView")
	public ModelAndView view(Long recordId) throws Exception {
		ModelAndView view = new ModelAndView("${moduleName}/${classNameLower}Input");
		String type="";
		if(recordId!=null){
			${className}VO vo= ${classNameLower}BS.queryVOById(recordId);
			view.addObject("entity", vo);
			type="VIEW";
		}
		view=setGlobals(view,"${classNameLower}ActionView");
		view.addObject("type", type);
		return view;
	}
	
	@RequestMapping("${classNameLower}ActionSave")
	public void save(${className}VO ${classNameLower}VO) throws Exception {
		${className} temp;
		if (${classNameLower}VO.get${className}Id() == null) {
			temp = ${classNameLower}BS.add${className}(${classNameLower}VO.cloneBO());
		}else{
			${className}VO vo = ${classNameLower}BS.queryVOById(${classNameLower}VO.get${className}Id());
			temp =${classNameLower}BS.modify${className}(${classNameLower}VO.cloneBO());
		}
		if (temp != null)
			super.printJson(true + "");
		else
			super.printJson(false + "");
	}
	
	public  ModelAndView setGlobals(ModelAndView model,String url) {
		model.addObject("url", request.getContextPath()+"/${moduleName}/"+url+".htm");
		model.addObject("pageMap",com.iitdev.ioms.base.common.Globals.M_PAGE_MAP);
		return model;
	}
}