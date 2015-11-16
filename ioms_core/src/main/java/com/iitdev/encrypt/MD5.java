package com.iitdev.encrypt;

import java.security.MessageDigest;

import com.iitdev.globals.PropertiesConstant;

/**
 * MD5加密工具类
 * 
 * @author Jerry
 * 
 */
public class MD5 {
	/**
	 * function: GetMd5 加密函数 remark:
	 * 
	 * @param s
	 *            传入一个String类型
	 * @return 返回一个加密后的
	 * @throws Exception
	 */
	public static final String GetMd5(String s) throws Exception {
		char hexDigits[] = { '0', '1', '2', '3', '4', '5', '6', '7', '8', '9',
				'a', 'b', 'c', 'd', 'e', 'f' };
		char str[];
		byte strTemp[] = s.getBytes();
		MessageDigest mdTemp = MessageDigest.getInstance("MD5");
		mdTemp.update(strTemp);
		byte md[] = mdTemp.digest();
		int j = md.length;
		str = new char[j * 2];
		int k = 0;
		for (int i = 0; i < j; i++) {
			byte byte0 = md[i];
			str[k++] = hexDigits[byte0 >>> 4 & 0xf];
			str[k++] = hexDigits[byte0 & 0xf];
		}

		return new String(str);

	}

	/**
	 * 密码加密
	 * 
	 * @param pwd
	 *            输入明文
	 * @return 返回密文
	 */
	public static final String getMd5Password(String pwd) {

		try {
			return MD5.GetMd5(MD5.GetMd5(pwd) + PropertiesConstant.getMD5Key());
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return null;
	}
	
	/**
	 * @param pwd
	 *            输入明文
	 * @param md5pwd
	 *            输入密文
	 * @return 密码是否匹配
	 */
	public static final boolean validate(String pwd, String md5pwd) {
		if (getMd5Password(pwd).equals(md5pwd)) {
			return true;
		}
		return false;
	}
	
}
