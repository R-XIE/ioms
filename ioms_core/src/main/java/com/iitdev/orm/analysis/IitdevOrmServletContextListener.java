package com.iitdev.orm.analysis;

import java.util.Map;
import java.util.Map.Entry;

import javax.servlet.ServletContextEvent;
import javax.servlet.ServletContextListener;

import org.slf4j.LoggerFactory;

import com.iitdev.globals.AnalysisConstant;
import com.iitdev.globals.ResourcesPO;
import com.iitdev.orm.xml.Table;

public class IitdevOrmServletContextListener implements ServletContextListener {

	@Override
	public void contextDestroyed(ServletContextEvent event) {
		
	}

	@Override
	public void contextInitialized(ServletContextEvent event) {
		String ormFileName=event.getServletContext().getInitParameter("ormConfigLocation");
		String resFileName=event.getServletContext().getInitParameter("resConfigLocation");
		new OrmXmlAnalysis(ormFileName);
		Map<Class<?>, Table> map = AnalysisConstant.beanMap;
		org.slf4j.Logger log = LoggerFactory.getLogger(IitdevOrmServletContextListener.class);
		log.info("已经加载的类有:");
		for (Entry<Class<?>, Table> cls : map.entrySet()) {
			log.info(cls.getKey().getName());
		}
		new ResourceXmlAnalysis(resFileName);
		Map<Long,ResourcesPO> reses=AnalysisConstant.allRes;
		log.info("已经加载的资源有:");
		for (Entry<Long, ResourcesPO> res :reses.entrySet()) {
			log.info("key:"+res.getKey()+",value:"+res.getValue().getResourcesName());
		}
		
//		ServletContext application =event.getServletContext();
//		application.setAttribute("url", PropertiesConstant.getBiuLocation());
//		application.setAttribute("biu", PropertiesConstant.getBiuProject());
	}

}
