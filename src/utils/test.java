package utils;

import java.awt.image.BufferedImage;
import java.io.BufferedInputStream;
import java.io.BufferedOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.nio.channels.FileChannel;
import java.sql.Timestamp;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.UUID;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import javax.imageio.ImageIO;

public class test {

	public static boolean isPhoneNO(String phone) {
		Pattern p = Pattern.compile("^((13[0-9])|(15[^4,\\D])|(18[0,5-9]))\\d{8}$");
		Matcher m = p.matcher(phone);
		System.out.println(m.matches() + "---" + phone);
		return m.matches();
	}

	public static Timestamp getTimeStamp(String s) {
		Timestamp ts = new Timestamp(System.currentTimeMillis());
		try {
			ts = Timestamp.valueOf(s);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return ts;
	}

	// 复制文件
	public static void CopyFile(File sourceFile, File targetFile) throws IOException {
		BufferedInputStream inBuff = null;
		BufferedOutputStream outBuff = null;
		try {
			inBuff = new BufferedInputStream(new FileInputStream(sourceFile));// 新建文件输入流并对它进行缓冲
			outBuff = new BufferedOutputStream(new FileOutputStream(targetFile));// 新建文件输出流并对它进行缓冲
			byte[] b = new byte[1024 * 5];// 缓冲数组
			int len;
			while ((len = inBuff.read(b)) != -1) {
				outBuff.write(b, 0, len);
			}
			outBuff.flush();// 刷新此缓冲的输出流
		} finally {
			if (inBuff != null)// 关闭流
				inBuff.close();
			if (outBuff != null)// 关闭流
				outBuff.close();
		}
	}

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

	// 获取绝对路径的文件名
	public static String GetFileName(String s) {
		if (s == null) {
			return null;
		}
		int index = s.lastIndexOf("\\");
		String newName = s.substring(index + 1);
		return newName;
	}

	public static String GetFileExtension(String s) {
		int index = s.lastIndexOf(".");
		if (index < 0) {
			return "";
		}
		return s.substring(index);
	}

	public static String GetCurrentTime() {
		Date date = new Date();
		DateFormat format = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		String time = format.format(date);
		return time;
	}

	public static String NameByTime() {
		Date date = new Date();
		DateFormat format = new SimpleDateFormat("yyyyMMddHHmmss");
		String time = format.format(date);
		return time;
	}

	public static String getMsgModelLogin(String code) {
		return "{name:'某某先生',n:'**电商平台',action:'忘记密码',ms:'" + code + "'}";
	}

	public static String GetImgResolution(String imgpath) {
		File file = new File(imgpath);
		FileChannel fc = null;
		if (file.exists() && file.isFile()) {
			try {
				FileInputStream fs = new FileInputStream(file);
				fc = fs.getChannel();
				System.out.println(fc.size() + "-----fc.size()");
			} catch (FileNotFoundException e) {
				e.printStackTrace();
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
		System.out.println(file.length() + "-----file.length  B");
		System.out.println(file.length() * 1024 + "-----file.length  kb");
		BufferedImage bi = null;
		try {
			bi = ImageIO.read(file);
		} catch (IOException e) {
			e.printStackTrace();
		}

		int width = bi.getWidth();
		int height = bi.getHeight();

		System.out.println("宽：像素-----" + width + "高：像素" + height);

		String str = width + "*" + height;

		return str;
	}
}
