package utils;

import java.awt.Color;
import java.awt.Graphics2D;
import java.awt.image.BufferedImage;
import java.io.BufferedInputStream;
import java.io.BufferedOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.sql.Timestamp;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import alipay.util.AlipayCore;

public class test {
	
	/**
	 * 输出
	 * 
	 * @param object
	 */
	public static void a(Object object) {
		System.out.println("------>" + object);
	}
	
	/**
	 * 利用alipay的日志功能存储
	 * 
	 * @param sWord
	 * @param filename
	 */
	public static void log2alipay(String sWord, String filename) {
		AlipayCore.logResult(sWord, filename);
	}
	
	/**
	 * 判断是否超过保质期
	 * 
	 * @param s1
	 * @return
	 */
	public static boolean betweenDate(String s1) {
		String[] keepdate = s1.split("至");// 用逗号切割
		for (int i = 0; i < keepdate.length; i++) {
			System.out.println(keepdate[i]);
		}
		if (compareDate(keepdate[1]) == -1) {
			System.out.println("当前时间超过保质期时间，失效！");
			return false;
		}
		else {
			System.out.println("当前时间未超过保质期时间，有效！");
			return true;
		}
	}
	
	/**
	 * 判断日期,小于当前日期等于-1
	 * 
	 * @param DATE
	 * @return
	 */
	public static int compareDate(String DATE) {
		DateFormat df = new SimpleDateFormat("yyyy-MM-dd hh:mm");
		try {
			Date givendate = df.parse(DATE);
			Date systemdate = df.parse(GetCurrentTime());
			if (givendate.getTime() > systemdate.getTime()) {
				System.out.println("systemdate<givendate");
				return 1;
			}
			else if (givendate.getTime() < systemdate.getTime()) {
				System.out.println("givendate<systemdate");
				return -1;
			}
			else {
				return 0;
			}
		} catch (Exception exception) {
			exception.printStackTrace();
		}
		return 0;
	}
	
	/**
	 * 判断是否是手机号码
	 * 
	 * @param phone
	 * @return
	 */
	public static boolean isPhoneNO(String phone) {
		Pattern p = Pattern.compile("^((13[0-9])|(15[^4,\\D])|(18[0,5-9]))\\d{8}$");
		Matcher m = p.matcher(phone);
		System.out.println(m.matches() + "---" + phone);
		return m.matches();
	}
	
	/**
	 * 判断一个字符串是不是一个合法的日期格式
	 * 
	 * @param str
	 * @return
	 */
	public static boolean isValidDate(String str) {
		boolean convertSuccess = true;
		SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		try {
			// 设置lenient为false.
			// 否则SimpleDateFormat会比较宽松地验证日期，比如2007/02/29会被接受，并转换成2007/03/01
			format.setLenient(false);
			format.parse(str);
		} catch (ParseException e) {
			// e.printStackTrace();
			convertSuccess = false;
		}
		return convertSuccess;
	}
	
	/**
	 * Date转String
	 * 
	 * @param date
	 * @return
	 */
	public static String Date2String(Date date) {
		String dateStr = "";
		DateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		try {
			dateStr = sdf.format(date);
			System.out.println(dateStr);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return dateStr;
	}
	
	/**
	 * String转Date
	 * 
	 * @param strDate
	 * @return
	 * @throws Exception
	 */
	public static Date String2Date(String strDate) throws Exception {
		Date date = new Date();
		// 注意format的格式要与日期String的格式相匹配
		DateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		try {
			date = sdf.parse(strDate);
			System.out.println(date.toString());
		} catch (Exception e) {
			e.printStackTrace();
		}
		return date;
	}
	
	/**
	 * Date转Timestamp
	 * 
	 * @param date
	 * @return
	 */
	public static Timestamp Date2Timestamp(Date date) {
		String dateStr = Date2String(date);
		Timestamp ts = String2Timestamp(dateStr);
		return ts;
	}
	
	/**
	 * Timestamp转Date
	 * 
	 * @param ts
	 * @return
	 * @throws Exception
	 */
	public static Date Timestamp2Date(Timestamp ts) throws Exception {
		Date date = new Date();
		try {
			date = ts;
			System.out.println(date);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return date;
	}
	
	/**
	 * String转Timestamp
	 * 
	 * @param s
	 * @return
	 */
	public static Timestamp String2Timestamp(String s) {
		Timestamp ts = new Timestamp(System.currentTimeMillis());
		try {
			ts = Timestamp.valueOf(s);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return ts;
	}
	
	/**
	 * Timestamp转String
	 * 
	 * @param ts
	 * @return
	 */
	public static String Timestamp2String(Timestamp ts) {
		String s = "";
		DateFormat format = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		try {
			s = format.format(ts);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return s;
	}
	
	/**
	 * 获得当前系统时间
	 * 
	 * @return "yyyy-MM-dd HH:mm:ss"
	 */
	public static String GetCurrentTime() {
		Date date = new Date();
		DateFormat format = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		String time = format.format(date);
		return time;
	}
	
	/**
	 * 以当前时间命名 yyyyMMddHHmmss
	 * 
	 * @return
	 */
	public static String NameByTime() {
		Date d = new Date();
		SimpleDateFormat sdf = new SimpleDateFormat("yyyyMMddHHmmss");//其中yyyy-MM-dd是你要表示的格式 
		String str = sdf.format(d);
		return str;
	}
	
	public static void copyFolder(File[] fl, File file) {
		if (!file.exists()) // 如果文件夹不存在
			file.mkdir(); // 建立新的文件夹
		for (int i = 0; i < fl.length; i++) {
			if (fl[i].isFile()) { // 如果是文件类型就复制文件
				try {
					FileInputStream fis = new FileInputStream(fl[i]);
					FileOutputStream out = new FileOutputStream(new File(file.getPath() + File.separator + fl[i].getName()));
					
					int count = fis.available();
					byte[] data = new byte[count];
					if ((fis.read(data)) != -1) {
						out.write(data); // 复制文件内容
					}
					out.close(); // 关闭输出流
					fis.close(); // 关闭输入流
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
			if (fl[i].isDirectory()) { // 如果是文件夹类型
				File des = new File(file.getPath() + File.separator + fl[i].getName());
				des.mkdir(); // 在目标文件夹中创建相同的文件夹
				copyFolder(fl[i].listFiles(), des); // 递归调用方法本身
			}
		}
	}
	
	/**
	 * 创建文件
	 * 
	 * @param destFileName
	 * @return
	 */
	public static boolean CreateFile(String destFileName) {
		File file = new File(destFileName);
		if (file.exists()) {
			System.out.println("创建单个文件" + destFileName + "失败，目标文件已存在！");
			return false;
		}
		if (destFileName.endsWith(File.separator)) {
			System.out.println("创建单个文件" + destFileName + "失败，目标不能是目录！");
			return false;
		}
		if (!file.getParentFile().exists()) {
			System.out.println("目标文件所在路径不存在，准备创建。。。");
			if (!file.getParentFile().mkdirs()) {
				System.out.println("创建目录文件所在的目录失败！");
				return false;
			}
		}
		// 创建目标文件
		try {
			if (file.createNewFile()) {
				System.out.println("创建单个文件" + destFileName + "成功！");
				return true;
			}
			else {
				System.out.println("创建单个文件" + destFileName + "失败！");
				return false;
			}
		} catch (IOException e) {
			e.printStackTrace();
			System.out.println("创建单个文件" + destFileName + "失败！");
			return false;
		}
	}
	
	/**
	 * 复制文件
	 * 
	 * @param sourceFile
	 * @param targetFile
	 * @throws IOException
	 */
	public static void CopyFile(File sourceFile, File targetFile) throws IOException {
		BufferedInputStream inBuff = null;
		BufferedOutputStream outBuff = null;
		try {
			inBuff = new BufferedInputStream(new FileInputStream(sourceFile));// 新建文件输入流并对它进行缓冲
			outBuff = new BufferedOutputStream(new FileOutputStream(targetFile));// 新建文件输出流并对它进行缓冲
			byte[] b = new byte[1024 * 1024 * 5];// 缓冲数组
			int len;
			while ((len = inBuff.read(b)) != -1) {
				outBuff.write(b, 0, len);
			}
			outBuff.flush();// 刷新此缓冲的输出流
		}
		finally {
			if (inBuff != null)// 关闭流
				inBuff.close();
			if (outBuff != null)// 关闭流
				outBuff.close();
		}
		System.out.println("--------->:" + sourceFile.getAbsolutePath() + " copyed");
	}
	
	/**
	 * 删除文件
	 * 
	 * @param psth
	 * @return
	 */
	public static boolean DeleteFile(String psth) {
		Boolean flag = false;
		File file = new File(psth);
		// 路径为文件且不为空则进行删除
		if (file.isFile() && file.exists()) {
			file.delete();
			flag = true;
		}
		return flag;
	}
	
	/**
	 * 获得文件的后缀名
	 * 
	 * @param s
	 * @return
	 */
	public static String GetFileExtension(File file) {
		String fileName = file.getName();
		String prefix = fileName.substring(fileName.lastIndexOf(".") + 1);
		return prefix;
	}
	
	/**
	 * 判断是否为整数
	 * 
	 * @param str
	 *            传入的字符串
	 * @return 是整数返回true,否则返回false
	 */
	public static boolean isInteger(String str) {
		Pattern pattern = Pattern.compile("^[-\\+]?[\\d]*$");
		return pattern.matcher(str).matches();
	}
	
	/**
	 * 判断是否为浮点数，包括double和float
	 * 
	 * @param str
	 *            传入的字符串
	 * 
	 * @return 是浮点数返回true,否则返回false
	 */
	public static boolean isDouble(String str) {
		Pattern pattern = Pattern.compile("^[-\\+]?[.\\d]*$");
		return pattern.matcher(str).matches();
	}
	
}
