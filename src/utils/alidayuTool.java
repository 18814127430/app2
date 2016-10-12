package utils;

import java.io.File;
import java.io.IOException;

import org.apache.log4j.Logger;
import org.apache.struts2.ServletActionContext;

import bean.Sort;

/**
 * ALIDAYU工具类
 * 
 * @author
 * 
 */
public class alidayuTool {
	private static final Logger log = Logger.getLogger(alidayuTool.class);
	
	/**
	 * 发短信给阿里大鱼的模板一
	 * 
	 * @param code
	 * @return
	 */
	public static String getAliMsg_1(String code) {
		return "{name:'某某先生',n:'**电商平台',action:'忘记密码',ms:'" + code + "'}";
	}
	
}
