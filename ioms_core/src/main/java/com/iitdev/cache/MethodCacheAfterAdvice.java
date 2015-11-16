package com.iitdev.cache;

import java.lang.reflect.Method;   
import java.util.List;   
  
import net.sf.ehcache.Cache;   
  
import org.apache.commons.logging.Log;   
import org.apache.commons.logging.LogFactory;   
import org.springframework.aop.AfterReturningAdvice;   
import org.springframework.beans.factory.InitializingBean;   
import org.springframework.util.Assert;   
  
public class MethodCacheAfterAdvice implements AfterReturningAdvice, InitializingBean   
{   
    private static final Log logger = LogFactory.getLog(MethodCacheAfterAdvice.class);   
  
    private Cache cache;   
  
    public void setCache(Cache cache) {   
        this.cache = cache;   
    }   
  
    public MethodCacheAfterAdvice() {   
        super();   
    }   
  
    public void afterReturning(Object arg0, Method arg1, Object[] arg2, Object arg3) throws Throwable {   
        String className = arg3.getClass().getName();   
        List<?> list = cache.getKeys();  
        //获取到该类中所有的缓存方法 清除之
        for(int i = 0;i<list.size();i++){   
            String cacheKey = String.valueOf(list.get(i));   
            if(cacheKey.startsWith(className)){   
                cache.remove(cacheKey);   
                logger.info("删除缓存 " + cacheKey);   
            }   
        }   
    }   
  
    public void afterPropertiesSet() throws Exception {   
        Assert.notNull(cache, "需要一个缓存. 请使用setCache(Cache) 创建它.");   
    }   
  
}  