package com.iitdev.orm.xml;

import java.io.File;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.persistence.GenerationType;

import org.dom4j.Document;
import org.dom4j.Element;
import org.dom4j.io.SAXReader;

import com.iitdev.exception.IitdevException;


public class XmlTableInfoAnalysis {
	
	/**
	 * 根据xml的集合获取输入流集合
	 * 
	 * @param fileList
	 * @return
	 */
	private static List<File> readXml(List<String> fileList) {
		List<File> files = new ArrayList<File>();
		for (String file : fileList) {
			files.add(new File(XmlTableInfoAnalysis.class.
					getResource(file).getFile()));
		}
		return files;
	}

	/**
	 * 读写单独的table信息,是封装在一个方法
	 * @param is
	 * @return
	 */
	private static Table readTable(File is) {
		SAXReader sax = new SAXReader();
		Document doc = null;
		Table table = new Table();
		Id id = null;
		List<Column> columns = null;
		try {
			doc = sax.read(is);
			Element root = doc.getRootElement();
			table.setName(root.attributeValue("name"));
			table.setClazz(Class.forName(root.attributeValue("clazz")));
			@SuppressWarnings("unchecked")
			List<Element> elements = root.elements();
			for (Element element : elements) {
				if (element.getName().equals("Id")) {
					id = new Id();
					id.setColumn(element.attributeValue("column"));
					id.setName(element.attributeValue("name"));
					String type = element.attributeValue("type");
					if ("auto".equals(type)) {
						id.setType(GenerationType.AUTO);
					}
					if ("identity".equals(type)) {
						id.setType(GenerationType.IDENTITY);
					}

				} else if (element.getName().equals("Column")) {
					if(null==columns){
						columns=new ArrayList<Column>();
					}
					Column column=new Column();
					column.setName(element.attributeValue("name"));
					column.setColumn(element.attributeValue("column"));					
					column.setUnique("false".equals(element.attribute("unique"))?false:true);
					column.setNullable("false".equals(element.attribute("nullable"))?false:true);
					String length=element.attributeValue("length");
					column.setLength(Integer.valueOf(length));
					columns.add(column);
				} 
			}
			table.setId(id);
			table.setColumns(columns);
		} catch (Exception e) {
			//log.error("获取Table信息有误", e);
			try {
				throw new IitdevException("获取Table信息有误");
			} catch (IitdevException e1) {
				e1.printStackTrace();
			}
		}
		return table;
	}

	/**
	 * 获取所有的Table信息
	 * @param fileList
	 * @return
	 */
	public static Map<Class<?>, Table> readTables(List<String> fileList) {
		Map<Class<?>, Table> tableMap = new HashMap<Class<?>, Table>();
		List<File> ises = readXml(fileList);
		for (File file : ises) {
			Table table = readTable(file);
			tableMap.put(table.getClazz(), table);
		}
		return tableMap;
	}

}