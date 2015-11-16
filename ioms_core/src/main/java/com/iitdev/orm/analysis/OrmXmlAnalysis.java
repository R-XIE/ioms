package com.iitdev.orm.analysis;

import java.io.InputStream;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import org.dom4j.Attribute;
import org.dom4j.Document;
import org.dom4j.DocumentException;
import org.dom4j.Element;
import org.dom4j.io.SAXReader;

import com.iitdev.exception.IitdevException;
import com.iitdev.globals.AnalysisConstant;
import com.iitdev.orm.annotation.AnnotationTableInfoAnalysis;
import com.iitdev.orm.xml.Table;
import com.iitdev.orm.xml.XmlTableInfoAnalysis;
import com.iitdev.utils.LogPrint;


public class OrmXmlAnalysis {
	LogPrint log = new LogPrint(OrmXmlAnalysis.class);
	public static List<String> packageValues = new ArrayList<String>();
	public static boolean isXml;
	
	private InputStream is;
	private Document document = null;

	/**
	 * 读取XML文件
	 * @param ormFile
	 */
	private void prep(String ormFile) {
		is = OrmXmlAnalysis.class.getClassLoader().getResourceAsStream(ormFile);
		SAXReader saxReader = new SAXReader();
		try {
			document = saxReader.read(is);
		} catch (DocumentException e) {
			e.printStackTrace();
		}
	}

	public OrmXmlAnalysis(String ormFile) {
		AnalysisConstant.beanMap.clear();
		log.info("Loading orm configure file from:"+ormFile);
		prep(ormFile);
		// 查询基本的参数
		try {
			Element roots = document.getRootElement();
			analysisPackage(roots);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}


	/**
	 * 解析package节点信息
	 * @param rootsElement
	 */
	private void analysisPackage(Element rootsElement) {
		Element list = rootsElement.element("orm-mapping").element("list");
		Attribute type = list.attribute("type");
		if (type != null) {
			if (type.getText().trim().equals("xml")) {
				OrmXmlAnalysis.isXml = true;
			} else if (type.getText().trim().equals("annotation")) {
				OrmXmlAnalysis.isXml = false;
			} else {
				try {
					throw new IitdevException(
							"orm-mapping->list[type] type属性必须为annotation或xml");
				} catch (IitdevException e) {
					e.printStackTrace();
				}
			}
			@SuppressWarnings("unchecked")
			List<Element> mappingList = list.elements();
			for (Element element : mappingList) {
				OrmXmlAnalysis.packageValues.add(element.getText().trim());
			}
			if(isXml){				
				AnalysisConstant.beanMap=XmlTableInfoAnalysis.readTables(OrmXmlAnalysis.packageValues);
			}else{
				AnalysisConstant.beanMap=AnnotationTableInfoAnalysis.readTables(OrmXmlAnalysis.packageValues);
			}
		} else {
			try {
				throw new IitdevException("orm-mapping->list[type] 不能为空");
			} catch (IitdevException e) {
				e.printStackTrace();
			}
		}

	}

	public OrmXmlAnalysis() {
		this("iitdevorm.cfg.xml");
	}

	/**
	 * 获取package的list
	 * 
	 * @return
	 */
	public List<String> getPackageValues() {
		return OrmXmlAnalysis.packageValues;
	}

	
	/**
	 * 获取表信息
	 * @return
	 */
	public Map<Class<?>,Table> getTables(){
		return AnalysisConstant.beanMap;
	}
	
	public boolean isXml() {
		return OrmXmlAnalysis.isXml;
	}
	
}
