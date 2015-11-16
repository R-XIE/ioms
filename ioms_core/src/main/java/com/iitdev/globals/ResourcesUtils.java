package com.iitdev.globals;

import java.util.LinkedList;
import java.util.List;
import java.util.Map.Entry;

public class ResourcesUtils {
	
	/**
	 * 获取所有的模块
	 * @return
	 */
	public static List<ResourcesPO> getAllResources(){
		return getResourcesByModule(-1l);
	}
	
	
	/**
	 * 根据模块获取资源
	 * @param moduleId
	 * @return
	 */
	public static List<ResourcesPO> getResourcesByModule(Long moduleId){
		List<ResourcesPO> resources=new LinkedList<ResourcesPO>();
		for (Entry<Long, ResourcesPO> res : AnalysisConstant.allRes.entrySet()) {
			ResourcesPO resPO=res.getValue();
			if(resPO.getResourcesSuper().equals(moduleId)){
				resources.add(resPO);
			}
		}
		return resources;
	}
}
