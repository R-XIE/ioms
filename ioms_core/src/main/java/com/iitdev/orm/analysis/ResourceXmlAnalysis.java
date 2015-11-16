package com.iitdev.orm.analysis;

import java.io.InputStream;
import java.util.Iterator;

import org.dom4j.Document;
import org.dom4j.DocumentException;
import org.dom4j.Element;
import org.dom4j.io.SAXReader;

import com.iitdev.globals.AnalysisConstant;
import com.iitdev.globals.ResourcesPO;
import com.iitdev.utils.LogPrint;

public class ResourceXmlAnalysis {
	LogPrint log = new LogPrint(ResourceXmlAnalysis.class);

	private InputStream is;
	private Document document = null;

	/**
	 * 读取XML文件
	 * 
	 * @param ormFile
	 */
	private void prep(String ormFile) {
		is = ResourceXmlAnalysis.class.getClassLoader().getResourceAsStream(
				ormFile);
		SAXReader saxReader = new SAXReader();
		try {
			document = saxReader.read(is);
		} catch (DocumentException e) {
			e.printStackTrace();
		}
	}

	public ResourceXmlAnalysis(String ormFile) {
		AnalysisConstant.allRes.clear();
		log.info("Loading resources configure file from:" + ormFile);
		prep(ormFile);
		// 查询基本的参数
		try {
			Element roots = document.getRootElement();
			analysisModules(roots);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	/**
	 *
	 */
	private void analysisModules(Element rootsElement) {

		//Element rootElt = rootsElement.element("iitdevres");
		@SuppressWarnings("unchecked")
		Iterator<Element> iter = rootsElement.elementIterator("resource");
		while (iter.hasNext()) {
			ResourcesPO resource = new ResourcesPO();
			Element moduleEle = iter.next();
			resource.setResourcesId(new Long(moduleEle.attributeValue("id")));
			resource.setResourcesName(moduleEle.attributeValue("name"));
			resource.setResourcesUrl(moduleEle.attributeValue("url"));
			resource.setResourcesCss(moduleEle.attributeValue("css"));
			resource.setResourcesSuper(new Long(moduleEle.attributeValue("super")));
			AnalysisConstant.allRes.put(resource.getResourcesId(), resource);
		}
		
	}

	public ResourceXmlAnalysis() {
		this("resource.xml");
	}
}