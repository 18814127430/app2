package task;

import java.io.File;

import javax.servlet.ServletContextEvent;
import javax.servlet.ServletContextListener;

import org.apache.commons.logging.LogFactory;
import org.apache.commons.logging.Log;

import utils.msg;
import utils.test;
import utils.QRCode.QRCodeUtil;

public class init implements ServletContextListener {
	private final Log logger = LogFactory.getLog(getClass());
	
	// 系统初始化执行方法
	public void contextDestroyed(ServletContextEvent e) {
		logger.info("系统停止...");
	}
	
	public void contextInitialized(ServletContextEvent e) {
		logger.info("系统初始化开始...");
		test.a("系统初始化开始...");
		
		// 获取项目根目录
		String root_path = e.getServletContext().getRealPath("/");
		logger.info("项目根目录:" + root_path);
		msg.root_path = root_path;
		test.a(root_path);
		
		try {
			initBackup();
			initQRCode();
		} catch (Exception e1) {
			e1.printStackTrace();
		}
		
		logger.info("系统初始化结束...");
		test.a("系统初始化结束...");
	}
	
	public static void initBackup() throws Exception {
		
		File desFile = null;
		String sourFolder = msg.root_path + "attached";// 可以修改源文件夹路径
		test.a(test.GetCurrentTime() + "源文件夹路径:" + sourFolder);
		String desFolder = msg.root_app_attached_auto; // 可以修改目标文件夹路径
		File sourFile = new File(sourFolder);
		
		if (!sourFile.isDirectory() || !sourFile.exists()) {
			test.a("源文件夹不存在");
		}
		else {
			desFile = new File(desFolder);
			desFile.mkdir();
			test.copyFolder(sourFile.listFiles(), desFile);
			test.a("文件夹复制成功！");
			
			test.log2alipay("文件夹复制成功！", "文件夹复制成功");
		}
		return;
	}
	
	/**
	 * 生成默认二维码
	 * 
	 * @throws Exception
	 */
	public static void initQRCode() throws Exception {
		
		String root_path_attached = msg.root_path;
		
		test.a(test.GetCurrentTime() + msg.root_path);
		test.a(test.GetCurrentTime() + root_path_attached);
		
		String path_logo = root_path_attached + "attached\\model_html_image\\model1.jpg";
		
		String path_login = root_path_attached + "attached\\image\\QRCode\\login.png";
		String path_login_nologo = root_path_attached + "attached\\image\\QRCode\\login_nologo.png";
		
		String path_appdoewnload = root_path_attached + "attached\\image\\QRCode\\appdownload.png";
		String path_appdoewnload_nologo = root_path_attached + "attached\\image\\QRCode\\appdownload_nologo.png";
		
		String root_url_attached = msg.root_url;
		
		String text_login = root_url_attached;
		String text_appdownload = root_url_attached + "/app/" + msg.apk_name;
		
		QRCodeUtil.encode(text_login, path_logo, path_login, true);//有logo
		QRCodeUtil.encode(text_appdownload, path_logo, path_appdoewnload, true);
		
		QRCodeUtil.encode(text_login, path_login_nologo, true);//没有logo
		QRCodeUtil.encode(text_appdownload, path_appdoewnload_nologo, true);
		
		test.log2alipay(path_login + "---------" + text_login, "initQRCode_login");
		test.log2alipay(text_appdownload + "-------" + path_appdoewnload, "initQRCode_app");
		test.a(path_login + "---------" + text_login + "---------initQRCode_login");
		test.a(text_appdownload + "-------" + path_appdoewnload + "--------initQRCode_app");
		
		return;
	}
	
}
