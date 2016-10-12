package task;

import java.io.File;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.quartz.JobExecutionContext;
import org.quartz.JobExecutionException;
import org.springframework.scheduling.quartz.QuartzJobBean;

import utils.msg;
import utils.test;
import utils.QRCode.QRCodeUtil;

public class Task_Backup extends QuartzJobBean {
	
	private final Log logger = LogFactory.getLog(getClass());
	
	protected void executeInternal(JobExecutionContext context) throws JobExecutionException {
		
		try {
			init.initBackup();
		} catch (Exception e) {
			e.printStackTrace();
		}
		return;
	}
	
	public static void main(String[] args) {
		File desFile = null;
		String sourFolder = "";
		if (msg.root_path.equals("")) {
			sourFolder = "C://Users//10919//Workspaces//MyEclipse Professional 2014//.metadata//.me_tcat7//webapps//app2//attached";
		}
		else {
			sourFolder = msg.root_path + "attached";// 可以修改源文件夹路径
		}
		String desFolder = msg.root_app_attached_manual;// 可以修改目标文件夹路径
		File sourFile = new File(sourFolder);
		
		if (!sourFile.isDirectory() || !sourFile.exists()) {
			test.a("源文件夹不存在");
		}
		else {
			desFile = new File(desFolder);
			desFile.mkdir();
			test.copyFolder(sourFile.listFiles(), desFile);
			test.a("文件夹复制成功！");
		}
	}
	
}
