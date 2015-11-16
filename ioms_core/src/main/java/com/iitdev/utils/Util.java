package com.iitdev.utils;

import java.text.SimpleDateFormat;
import java.util.Arrays;
import java.util.Calendar;
import java.util.Date;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;

/**
 * 常用函数封装
 * 
 * @author Jerry
 * 
 */
public class Util {
	/**
	 * function:判断字符串是否为空<br/>
	 * remark:判断字符串是否为空
	 * 
	 * @param expression
	 *            字符串
	 * @return 是/否字符串
	 **/
	public static boolean isNull(String expression) {
		if (expression == null || expression.length() == 0)
			return true;
		return false;
	}

	/**
	 * function:判断字符串是否不为空<br/>
	 * remark:判断字符串是否不为空
	 * 
	 * @param expression
	 *            字符串
	 * @return 是/否字符串
	 **/
	public static boolean isNotNull(String expression) {
		return !isNull(expression);
	}

	/**
	 * function:判断字符串是否为浮点型字符串<br/>
	 * remark:判断字符串是否为空
	 * 
	 * @param expression
	 *            字符串
	 * @return 是/否浮点型
	 **/
	public static boolean isFloat(String expression) {
		if (isNull(expression))
			return false;

		try {
			Float.valueOf(expression);
			return true;
		} catch (NumberFormatException e) {
			return false;
		}
	}

	/**
	 * function:判断字符串是否为整型字符串<br/>
	 * remark:
	 * 
	 * @param expression
	 *            字符串
	 * @return 是/否整型
	 **/
	public static boolean isInteger(String expression) {
		if (isNull(expression))
			return false;

		try {
			Integer.valueOf(expression);
			return true;
		} catch (NumberFormatException e) {
			return false;
		}
	}

	/**
	 * function:字符串右边去空格<br/>
	 * remark:
	 * 
	 * @param expression
	 *            字符串
	 * @return 右去空格后的字符串
	 **/

	public static String rTrim(String expression) {
		int i = 0;
		if (expression == null || expression.length() == 0)
			return "";
		for (i = expression.length(); i >= 0; i--) {
			if (expression.charAt(i - 1) != ' ')
				break;
		}
		if (i == 0)
			return "";
		else
			return expression.substring(0, i);
	}

	/**
	 * function:字符串左边去空格<br/>
	 * remark:
	 * 
	 * @param expression
	 *            字符串
	 * @return 去左空格后的字符串
	 **/
	public static String lTrim(String expression) {
		int i = 0;
		if (expression == null || expression.length() == 0)
			return "";
		for (i = 0; i < expression.length(); i++) {
			if (expression.charAt(i) != ' ')
				break;
		}
		return expression.substring(i);
	}

	/**
	 * function:判断字符串是否为数字型字符串<br/>
	 * remark:
	 * 
	 * @param expression
	 *            字符串
	 * @return 是否数字型
	 **/
	public static boolean isNumeric(String expression) {
		if (isFloat(expression) || isInteger(expression))
			return true;
		return false;
	}

	/**
	 * function：得到按指定格式的系统当前时间<br/>
	 * remark:
	 * 
	 * @param dateFormat
	 *            日期格式
	 * @return 格式化的日期字符串
	 **/
	public static String getSysDate(String dateFormat) {
		if (dateFormat == null || "".equals(dateFormat)) {
			dateFormat = "yyyy-MM-dd HH:mm:ss";
		}
		Calendar date = Calendar.getInstance();
		SimpleDateFormat sdf = new SimpleDateFormat(dateFormat);
		String dateStr = sdf.format(date.getTime());
		return dateStr;
	}

	/**
	 * function：得到按指定格式的系统当前时间<br/>
	 * remark:
	 * 
	 * @param dateFormat
	 *            日期格式
	 * @return 格式化的日期字符串
	 **/
	public static Date getCurrentDate(String dateFormat) {
		if (dateFormat == null || "".equals(dateFormat)) {
			dateFormat = "yyyy-MM-dd HH:mm:ss";
		}
		SimpleDateFormat sdf = new SimpleDateFormat(dateFormat);
		Date dDate = null;
		try {
			dDate = sdf.parse(getSysDate(dateFormat));
		} catch (Exception e) {
			e.printStackTrace();
		}
		return dDate;
	}

	/**
	 * function:得到按指定格式的系统当前时间<br/>
	 * remark:
	 * 
	 * @return 格式化的日期字符串
	 **/
	public static String getTimeNumber() {
		Calendar CD = Calendar.getInstance();
		int iYY = CD.get(Calendar.YEAR);
		int iMM = CD.get(Calendar.MONTH) + 1;
		int iDD = CD.get(Calendar.DATE);
		int iHH = CD.get(Calendar.HOUR);
		int iNN = CD.get(Calendar.MINUTE);
		int iSS = CD.get(Calendar.SECOND);
		int iMI = CD.get(Calendar.MILLISECOND);

		String strTemp = format(iYY, "%02d") + format(iMM, "%02d")
				+ format(iDD, "%02d") + "-" + format(iHH, "%02d")
				+ format(iNN, "%02d") + format(iSS, "%02d") + "-"
				+ format(iMI, "%03d");
		return strTemp;
	}

	/**
	 * function:得到上周周一的日期<br/>
	 * remark:
	 * 
	 * @return 上周周一日期
	 **/
	public static String getLastMonday() {
		return minusDay(getSysDate("yyyy-MM-dd"), 7 + getWeek() - 1);
	}

	/**
	 * function:得到上周周日的日期<br/>
	 * remark:
	 * 
	 * @return 上周周日日期
	 **/
	public static String getLastSunday() {
		return minusDay(getSysDate("yyyy-MM-dd"), getWeek());
	}

	/**
	 * 将日期字符转换为指定格式日期字符.缺省格式为yyyy-MM-dd<br/>
	 * 
	 * @param dateStr
	 *            日期
	 * @param dateFormat
	 *            日期格式
	 * @return 指定格式的日期
	 **/
	public static String getDateByFormat(String dateStr, String dateFormat) {
		if (dateFormat == null || "".equals(dateFormat)) {
			dateFormat = "yyyy-MM-dd HH:mm:ss";
		}
		String str = "";
		try {
			if (dateStr != null && !"".equals(dateStr)) {
				dateStr = dateStr.replaceAll("年", "-");
				dateStr = dateStr.replaceAll("月", "-");
				dateStr = dateStr.replaceAll("日", "");
				dateStr = dateStr.replaceAll("/", "-");
				java.sql.Date dt = java.sql.Date.valueOf(dateStr);
				SimpleDateFormat sdf = new SimpleDateFormat(dateFormat);
				str = sdf.format(dt);
			} else {
				str = "";
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return str;
	}

	/**
	 * function:日期加天<br/>
	 * remark:
	 * 
	 * @param date
	 *            日期
	 * @param daynum
	 *            天数
	 * @return 日期
	 **/
	public static String plusDay(String date, long daynum) {
		if (date == null || "".equals(date))
			return "";
		java.sql.Date dt = java.sql.Date.valueOf(date);
		long dl = dt.getTime();
		dl = dl + 24 * 60 * 60 * 1000 * daynum;
		java.sql.Date dt2 = new java.sql.Date(dl);
		return dt2.toString();
	}

	/**
	 * function:日期减天<br/>
	 * remark:
	 * 
	 * @param date
	 *            日期
	 * @param daynum
	 *            天数
	 * @return 日期
	 **/
	public static String minusDay(String date, long daynum) {
		if (date == null || "".equals(date))
			return "";
		java.sql.Date dt = java.sql.Date.valueOf(date);
		long dl = dt.getTime();
		dl = dl - 24 * 60 * 60 * 1000 * daynum;
		java.sql.Date dt2 = new java.sql.Date(dl);
		return dt2.toString();
	}

	/**
	 * function:计算日期之间的天数<br/>
	 * remark:
	 * 
	 * @param date1
	 *            被减日期
	 * @param date2
	 *            减的日期
	 * @return 天数
	 **/
	public static long betweenDays(String date1, String date2) {
		if (date1 == null || "".equals(date1) || date2 == null
				|| "".equals(date2))
			return 0;
		java.sql.Date dt1 = java.sql.Date.valueOf(date1);
		java.sql.Date dt2 = java.sql.Date.valueOf(date2);
		long dl = dt1.getTime() - dt2.getTime();
		long daynum = dl / (24 * 60 * 60 * 1000);
		return daynum;
	}

	/**
	 * function:计算六位日期之间的天数<br/>
	 * remark:
	 * 
	 * @param date1
	 *            被减日期
	 * @param date2
	 *            减的日期
	 * @return 天数
	 **/
	public static int betweenDaysSix(String date1, String date2) {
		if (date1 == null || "".equals(date1) || date2 == null
				|| "".equals(date2))
			return 0;
		try {
			date1 = date1.replaceAll("-", "");
			date2 = date2.replaceAll("-", "");
			int d1 = Integer.parseInt(date1);
			int d2 = Integer.parseInt(date2);
			return d2 - d1;
		} catch (Exception e) {
			return 0;
		}

	}

	/**
	 * function:计算六位日期之间的月数<br/>
	 * remark:
	 * 
	 * @param date1
	 * @param date2
	 * @return 月数
	 **/
	public static int betweenMonths(String date1, String date2) {
		int months = 0;
		try {
			int date1_year = (new Integer(date1.substring(0, 4))).intValue();
			int date2_year = (new Integer(date2.substring(0, 4))).intValue();
			int date1_month = (new Integer(date1.substring(5, 7))).intValue();
			int date2_month = (new Integer(date2.substring(5, 7))).intValue();

			if (date1_month > date2_month)
				months = (date1_year - date2_year) * 12
						+ ((new Integer(date1_month - date2_month)).intValue());
			else
				months = (date1_year - date2_year - 1)
						* 12
						+ ((new Integer(date1_month + 12 - date2_month))
								.intValue());
		} catch (Exception e) {

		}
		return months;

	}

	/**
	 * function:得到中文格式的日期<br/>
	 * remark: 如一九七七年八月二十日
	 * 
	 * @return 中文格式日期
	 **/

	public static String getSysChnDate() {
		Calendar date = Calendar.getInstance();
		String year = String.valueOf(date.get(Calendar.YEAR));
		String month = String.valueOf(date.get(Calendar.MONTH) + 1);
		String day = String.valueOf(date.get(Calendar.DAY_OF_MONTH));
		year = year.replaceAll("0", "○");
		year = year.replaceAll("1", "一");
		year = year.replaceAll("2", "二");
		year = year.replaceAll("3", "三");
		year = year.replaceAll("4", "四");
		year = year.replaceAll("5", "五");
		year = year.replaceAll("6", "六");
		year = year.replaceAll("7", "七");
		year = year.replaceAll("8", "八");
		year = year.replaceAll("9", "九");
		if (month.length() < 2) {
			month = month.replaceAll("1", "一");
			month = month.replaceAll("2", "二");
			month = month.replaceAll("3", "三");
			month = month.replaceAll("4", "四");
			month = month.replaceAll("5", "五");
			month = month.replaceAll("6", "六");
			month = month.replaceAll("7", "七");
			month = month.replaceAll("8", "八");
			month = month.replaceAll("9", "九");
		} else {
			month = month.replaceAll("10", "十");
			month = month.replaceAll("11", "十一");
			month = month.replaceAll("12", "十二");
		}
		if (day.length() < 2) {
			day = day.replaceAll("1", "一");
			day = day.replaceAll("2", "二");
			day = day.replaceAll("3", "三");
			day = day.replaceAll("4", "四");
			day = day.replaceAll("5", "五");
			day = day.replaceAll("6", "六");
			day = day.replaceAll("7", "七");
			day = day.replaceAll("8", "八");
			day = day.replaceAll("9", "九");
		} else {
			day = day.replaceAll("10", "十");
			day = day.replaceAll("11", "十一");
			day = day.replaceAll("12", "十二");
			day = day.replaceAll("13", "十三");
			day = day.replaceAll("14", "十四");
			day = day.replaceAll("15", "十五");
			day = day.replaceAll("16", "十六");
			day = day.replaceAll("17", "十七");
			day = day.replaceAll("18", "十八");
			day = day.replaceAll("19", "十九");
			day = day.replaceAll("20", "二十");
			day = day.replaceAll("21", "二十一");
			day = day.replaceAll("22", "二十二");
			day = day.replaceAll("23", "二十三");
			day = day.replaceAll("24", "二十四");
			day = day.replaceAll("25", "二十五");
			day = day.replaceAll("26", "二十六");
			day = day.replaceAll("27", "二十七");
			day = day.replaceAll("28", "二十八");
			day = day.replaceAll("29", "二十九");
			day = day.replaceAll("30", "三十");
			day = day.replaceAll("31", "三十一");

		}
		String sdate = year + "年" + month + "月" + day + "日";
		return sdate;
	}

	/**
	 * function:ISO字符串转成GBK字符串<br/>
	 * remark: ISO字符串
	 * 
	 * @param str
	 * @return GBK字符串
	 **/
	public static String ex_ch(String str) {
		if (str == null) {
			str = "";
		} else {
			try {
				// str = new String(str.getBytes("iso-8859-1"),"gb2312");
				str = new String(str.getBytes("iso-8859-1"), "gbk");
			} catch (Exception ex) {
				ex.printStackTrace();
			}
		}
		return str.toString();
	}

	/**
	 * function:得到当日星期几<br/>
	 * remark:
	 * 
	 * @return 星期几
	 **/

	public static int getWeek() {
		Calendar cal = Calendar.getInstance();

		int iWeek = cal.get(Calendar.DAY_OF_WEEK) - 1;

		if (iWeek == 0)
			return 7;
		else
			return iWeek;
	}

	/**
	 * function:格式化字符串<br/>
	 * remark: 和C语言的printf功能一样，参数也一样<br/>
	 * 如 %10s, %-10s, %010s,
	 * 
	 * @return 格式化后的字符串
	 **/
	public static String format(String expression, String format) {
		String strFormat;
		String strDest;
		int nRequireLen;
		int nSrcLen;

		int i = format.indexOf('%', 0);

		nSrcLen = expression.length();
		if (i == -1)
			return expression;
		else { // if 1
			strFormat = format.substring(i + 1, format.length() - 1);
			if (strFormat.charAt(0) == '-') // Left align, if 1.1
			{
				try {
					nRequireLen = Integer.parseInt(strFormat.substring(1));
				} catch (Exception e) {
					return expression;
				}

				if (nSrcLen >= nRequireLen) // if 1.11
					return expression;
				else {
					char[] cFill = new char[nRequireLen - nSrcLen];
					Arrays.fill(cFill, 0, nRequireLen - nSrcLen, ' ');
					strDest = expression
							+ new String(cFill, 0, nRequireLen - nSrcLen);
				} // if 1.11

			} // if 1.1
			else // Right align
			{
				try {
					nRequireLen = Integer.parseInt(strFormat.substring(0));
				} catch (Exception e) {
					return expression;
				}

				if (nSrcLen >= nRequireLen) // if 1.11
					return expression;
				else {
					char[] cFill = new char[nRequireLen - nSrcLen];
					if (strFormat.charAt(0) == '0')
						Arrays.fill(cFill, 0, nRequireLen - nSrcLen, '0');
					else
						Arrays.fill(cFill, 0, nRequireLen - nSrcLen, ' ');

					strDest = new String(cFill, 0, nRequireLen - nSrcLen)
							+ expression;
				} // if 1.11
			}// if 1.1
		} // if 1
		return strDest;

	}

	/**
	 * function:格式化整型<br/>
	 * remark:和C语言的printf功能一样，参数也一样
	 * 
	 * @return 格式化后的字符串
	 */
	public static String format(int expression, String format) {
		return format(String.valueOf(expression), format);
	}

	/**
	 * function:格式化长整型<br/>
	 * remark:和C语言的printf功能一样，参数也一样
	 * 
	 * @return 格式化后的字符串
	 **/
	public static String format(long expression, String format) {

		return format(String.valueOf(expression), format);
	}

	/**
	 * function:格式化长整型<br/>
	 * remark:和C语言的printf功能一样，参数也一样
	 * 
	 * @return 格式化后的字符串
	 **/
	public static String format(short expression, String format) {
		return format(String.valueOf(expression), format);
	}

	/**
	 * function:格式化浮点型数据<br/>
	 * remark:和C语言的printf功能一样，参数也一样
	 * 
	 * @return 格式化后的字符串
	 **/
	public static String format(float expression, String format) {
		int nDot = format.indexOf('.', 0);
		int nRequireLen;

		int i = format.indexOf('%', 0);

		String strFormat = format.substring(i, nDot) + "f";

		String strSrc = String.valueOf(expression);

		if (nDot != -1) // if 1
		{
			try {
				nRequireLen = Integer.parseInt(format.substring(nDot + 1,
						format.length() - 1));
				int nSrcDot = String.valueOf(expression).indexOf('.', 0);
				int nSrcDotLen = strSrc.substring(nSrcDot + 1).length();

				if (nSrcDotLen < nRequireLen) // if 1.1
				{
					char[] cFill = new char[nRequireLen - nSrcDotLen];
					Arrays.fill(cFill, 0, nRequireLen - nSrcDotLen, '0');
					strSrc = strSrc
							+ new String(cFill, 0, nRequireLen - nSrcDotLen);
				} // if 1.1

			} catch (Exception e) {
				return strSrc;

			}
		} // if 1
		return format(String.valueOf(strSrc), strFormat);
	}

	/**
	 * function:将Map中的数据转成特点的格式字符串<br/>
	 * remark:字符串格式：field1:value1;field2:value2
	 * 
	 * @return 格式化后的字符串
	 **/
	public static String toParamStr(Map<?, ?> paramMap) {
		String strParam = "";
		if ((paramMap == null) || (paramMap.size() <= 0))
			return null;

		for (Iterator<?> iter = paramMap.keySet().iterator(); iter.hasNext();) {
			Object okey = iter.next();
			Object oVal = paramMap.get(okey);
			strParam = strParam + okey + "&#58;" + oVal + "&#59;";
		}
		return strParam;
	}

	/**
	 * function:将特定格式的字符串入到MAP<br/>
	 * remark:字符串格式：field1:value1;field2:value2
	 * 
	 * @return Map形式的数据
	 **/
	public static Map<String, String> toParamMap(String paramStr) {
		Map<String, String> hmParam = new HashMap<String, String>();
		String strTemp = paramStr;

		strTemp = strTemp.replaceAll("%3A", ":");
		strTemp = strTemp.replaceAll("%3a", ":");
		strTemp = strTemp.replaceAll("%3B", ";");
		strTemp = strTemp.replaceAll("%3b", ";");

		String[] arrParam = strTemp.split(";");

		for (int i = 0; i < arrParam.length; i++) {
			String arrVal[] = arrParam[i].split(":");
			if (arrVal.length <= 1)
				continue;
			hmParam.put(arrVal[0], arrVal[1]);
		}
		return hmParam;
	}

	/**
	 * function:取得当月第 一天<br/>
	 * remark:
	 * 
	 * @return 格式化后的字符串
	 */
	public static String getFirstMonthDate(String format) {
		Calendar curCal = Calendar.getInstance();
		curCal.set(Calendar.DAY_OF_MONTH, 1);

		SimpleDateFormat sdf = new SimpleDateFormat(format);
		return sdf.format(curCal.getTime());

	}

	/**
	 * function:取得当月最后一天<br/>
	 * remark:
	 * 
	 * @return 格式化后的字符串
	 */
	public static String getLastMonthDate(String format) {

		Calendar curCal = Calendar.getInstance();
		curCal.set(Calendar.DATE, 1);
		curCal.roll(Calendar.DATE, -1);

		SimpleDateFormat sdf = new SimpleDateFormat(format);
		return sdf.format(curCal.getTime());
	}

	/**
	 * 分区号，操作流水号的后两位(Long类型的最后两位)
	 * 
	 * @param serialNBR
	 *            传入的Long类型
	 * @return 返回后两位
	 */
	public static Long getPartitionByOperSerialNbr(Long serialNBR) {
		long num = serialNBR.longValue();
		return num % 100;
	}

	/**
	 * @param date
	 *            要转换的时间
	 * @param formatStr
	 *            转换格式
	 * @return 返回转换格式后的字符串
	 */
	public static String format(Date date, String formatStr) {
		if (date == null) {
			return "";
		}
		if (Util.isNull(formatStr)) {
			formatStr = "yyyy-MM-dd HH:mm";
		}
		SimpleDateFormat sf = new SimpleDateFormat(formatStr);
		return sf.format(date);
	}

	/**
	 * functions：默认返回yyyy-MM-dd格式的日期
	 * 
	 * @param date
	 *            日期
	 * @return 返回String类型的时间
	 */
	public static String formatDate(Date date) {
		return format(date, "yyyy-MM-dd");
	}

	/**
	 * functions：默认返回yyyy-MM-dd HH：mm格式的日期
	 * 
	 * @param date
	 *            日期时间
	 * @return 返回String类型的时间
	 */
	public static String formatDateTime(Date date) {
		return format(date, "yyyy-MM-dd HH:mm");
	}

	/**
	 * 返回当天0:00:00.000
	 * 
	 * @return 返回当天开始时间的Date类型
	 */
	public static Date getCurrentDate() {
		Date now = new Date();
		return getDateBegin(now);
	}

	public static Date getDateBegin(Date date) {
		if (date == null)
			return null;
		Calendar c = Calendar.getInstance();
		c.setTime(date);
		c.set(Calendar.HOUR_OF_DAY, 0);
		c.set(Calendar.MINUTE, 0);
		c.set(Calendar.SECOND, 0);
		c.set(Calendar.MILLISECOND, 0);
		return c.getTime();
	}

	/**
	 * 返回当天23:59:59.000
	 * 
	 * @return 返回结束时间
	 */
	public static Date getDateEnd(Date date) {
		if (date == null)
			return null;
		Calendar c = Calendar.getInstance();
		c.setTime(date);
		c.set(Calendar.HOUR_OF_DAY, 23);
		c.set(Calendar.MINUTE, 59);
		c.set(Calendar.SECOND, 59);
		c.set(Calendar.MILLISECOND, 000);
		return c.getTime();
	}

	/**
	 * 将数据数组转为String
	 * 
	 * @param arr
	 *            为一个string数组
	 * @return 获取数组的字符串,类似于对象的toString方法
	 */
	public static String arrayToString(String[] arr) {
		if (arr.length <= 0) {
			return "";
		}
		String strTemp = "";
		for (int i = 0; i < arr.length; i++) {
			strTemp = strTemp + "," + "'" + arr[i] + "'";
		}
		return strTemp.substring(1);
	}

	public static boolean containElement(List<String> arr, String val) {
		boolean bFlag = false;
		for (String t : arr) {
			if (isNotNull(t) && t.equals(val)) {
				bFlag = true;
				break;
			}
		}
		return bFlag;
	}
	
	public static <T> boolean containElement(List<T> arr, T val) {
		boolean bFlag = false;
		for (T t : arr) {
			if (t!=null && t.equals(val)) {
				bFlag = true;
				break;
			}
		}
		return bFlag;
	}
	
	/**
	 * function:数组是否包含某个值
	 * 
	 * @param arr
	 *            (数组)
	 * @param val
	 *            (某个值)
	 * @return 返回true/false 是/否 包含某个值
	 */

	public static boolean containElement(String[] arr, String val) {
		boolean bFlag = false;
		for (int i = 0; i < arr.length; i++) {
			if (arr[i].equals(val)) {
				bFlag = true;
				break;
			}
		}
		return bFlag;
	}

	/**
	 * function:返回某位长度的最大值数字 remark: 999
	 * 
	 * @param len
	 *            （位数）
	 * @return Long类型
	 */

	public static Long getMaxSerial(int len) {
		String strVal = "9";

		for (int i = 1; i < len; i++) {
			strVal = strVal + "9";
		}

		return Long.valueOf(strVal);
	}

	/**
	 * 判断param的日期是否在begin 和end 之间 包含
	 * 
	 * @param begin
	 *            日期起始位置
	 * @param end
	 *            日期结束位置
	 * @param param
	 *            是要判断的日期
	 * @return true/false 是否位于 日期之间
	 */
	public static boolean IsDateBetween(Date begin, Date end, Date param) {
		Calendar c = Calendar.getInstance();
		Calendar b = Calendar.getInstance();
		Calendar e = Calendar.getInstance();

		c.setTime(param == null ? new Date() : param);
		b.setTime(begin == null ? new Date() : begin);
		e.setTime(end == null ? new Date() : end);

		if (c.after(b) && c.before(e)) {
			return true;
		}

		return false;
	}

	/**
	 * function: 从一个object转换成string类型,为空返回空串
	 * 
	 * @param obj
	 *            要转换的obj对象
	 * @return 返回这个对象的toString方法，若为空则返回空串
	 */
	public static String fromObj2Str(Object obj) {
		if (obj != null) {
			return obj.toString();
		}
		return "";
	}

	/**
	 * function: 从一个object转换成string类型,为空返回NULL
	 * 
	 * @param obj
	 *            要转换的obj对象
	 * @return 返回这个对象的toString方法，若为空则返回null
	 */
	public static String fromObj2StrNull(Object obj) {
		if (obj != null) {
			return obj.toString();
		}
		return null;
	}

	/**
	 * 取得一个月的最后一天
	 * */
	public static Date getLastDayOfMonth(Date oneDate) {
		if (oneDate == null) {
			return oneDate;
		}
		Calendar c = Calendar.getInstance();
		c.setTime(oneDate);
		int lastEndDate = c.getActualMaximum(Calendar.DAY_OF_MONTH);
		c.set(Calendar.DATE, lastEndDate);
		Date lastDate = c.getTime();
		return lastDate;
	}

	/**
	 * 此方法把汉字转换为ASCII
	 * 
	 * @param gbkStr
	 *            中文字串
	 * @return 返回ASCII的字符串
	 */
	public static String gbkToAscii(String gbkStr) {
		char[] chars = gbkStr.toCharArray();
		StringBuffer sb = new StringBuffer();
		for (int i = 0; i < chars.length; i++) {
			sb.append("%" + (int) chars[i]);
		}
		return sb.toString();
	}

	/**
	 * 此方法把ASCII转换为汉字
	 * 
	 * @param asciiStr
	 *            ascii字符串
	 * @return 转换后的汉字字符串
	 */
	public static String asciiToGbk(String asciiStr) {
		String[] chars = asciiStr.split("%");
		StringBuffer sb = new StringBuffer();
		for (int i = 1; i < chars.length; i++) {
			sb.append((char) Integer.parseInt(chars[i]));
		}
		return sb.toString();
	}

	/**
	 * 从目的字符串中切除关键字
	 * @param str
	 * @param key
	 * @return 切除关键字之后的字符串
	 */
	public static String killString(String str, String key) {
		int index = str.indexOf(key);
		return str.substring(index + key.length(), str.length());
	}
	
	/**
	 *	返回一个Map，该Map 过略掉 map里面值是null 或 ""空字符串值 
	 * @param map
	 * @return
	 */
	public static <K,V>  Map<K,V> clearBlankValue(Map<K,V> map){
		Map<K,V> resultMap = new HashMap<K,V>();
		if(map!=null&&!map.isEmpty()){
			for(Entry<K,V> entry : map.entrySet()){
				if(entry.getValue()==null||"".equals(entry.getValue().toString().trim())){
					continue;
				}
				resultMap.put(entry.getKey(), entry.getValue());
			}
		}
		return resultMap;
	}
	
	public static String getSubLastStr(String str, int num) {
		  String result = "";
		  int i = 0;
		  while(i < num) {
		   int lastFirst = str.lastIndexOf('/');
		   result = str.substring(lastFirst) + result;
		   str = str.substring(0, lastFirst);
		   i++;
		  }
		  return result.substring(1);
		 } 
	public static String getToField(String str){
		String s = str.substring(3);
		String temp=new String(s.toCharArray()[0]+"");
		s=s.replaceFirst(temp,temp.toLowerCase());
		return s;
	}
}