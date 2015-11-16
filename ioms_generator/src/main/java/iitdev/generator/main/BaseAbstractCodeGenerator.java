package iitdev.generator.main;

import java.util.Properties;

import cn.org.rapid_framework.generator.GeneratorProperties;

public abstract class BaseAbstractCodeGenerator {
	//打开
	protected static void open() throws Exception{
		Properties prop = System.getProperties();
		String os = prop.getProperty("os.name");
		//System.out.println(os);
		if(os.startsWith("win") || os.startsWith("Win")){
			Runtime.getRuntime().exec("cmd.exe /c start "
					+ GeneratorProperties.getRequiredProperty("outRoot"));
		}else if(os.startsWith("linux") || os.startsWith("Linux")){
			Runtime.getRuntime().exec("nautilus "
					+ GeneratorProperties.getRequiredProperty("outRoot"));
		}else{
			throw new RuntimeException("當前的系統爲:"+os+",請在BaseAbstractCodeGenerator類中添加打開文件夾支持!");
		}
	}
}
