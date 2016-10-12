package task;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.quartz.JobExecutionContext;
import org.quartz.JobExecutionException;
import org.springframework.scheduling.quartz.QuartzJobBean;

import service.OOrderService;
import utils.msg;
import utils.test;
import bean.OOrder;

public class Task_ClearOrder extends QuartzJobBean {
	
	private final Log logger = LogFactory.getLog(getClass());
	
	private OOrderService oorderService;
	public static int a1 = 1;
	public static int a2 = 4;
	public static int a3 = 9;
	public static int a4 = 16;
	public static int a5 = 25;
	public static List list = new ArrayList();
	
	protected void executeInternal(JobExecutionContext context) throws JobExecutionException {
		
		test.a("清理超时订单开始. . . ");
		test.a(list.size());
		
		for (int i = list.size() - 1; i >= 0; i--) {
			int orderid = (Integer) list.get(i);
			OOrder db_oorder = null;
			
			try {
				db_oorder = oorderService.View_Id(orderid);
			} catch (Exception e) {
				e.printStackTrace();
			}
			
			//订单为空，则移出清空表
			if (db_oorder == null) {
				list.remove(i);
				continue;
			}
			
			test.a("ID:" + db_oorder.getOrderId() + "-----STATUSPAY:" + db_oorder.getStatusPay());
			
			//订单为已付款，则移出清空表
			if (db_oorder.getStatusPay() == 3) {
				list.remove(i);
				continue;
			}
			
			DateFormat df = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
			Date d1 = null;
			Date d2 = null;
			try {
				d1 = df.parse(test.GetCurrentTime());
				d2 = df.parse(test.Timestamp2String(db_oorder.getOrderDate()));
			} catch (ParseException e) {
				e.printStackTrace();
			}
			
			// Difference in milliseconds
			long diff = d1.getTime() - d2.getTime();
			// Difference in seconds
			long diffSec = diff / 1000;
			
			test.a("Difference in seconds " + diffSec);
			
			//订单超时并且未付款并且是线上支付，则删除
			if (diffSec >= msg.it_b_pay_second && db_oorder.getStatusPay() == 2 && db_oorder.getStatusMethod() == 3) {
				try {
					oorderService.Delete(db_oorder);
					logger.info("自动删除订单（ID）：" + db_oorder.getOrderId());
					
				} catch (Exception e) {
					e.printStackTrace();
				}
				list.remove(i);
			}
		}
		
		test.a(list.size());
		test.a("清理超时订单结束. . .");
		
		return;
	}
	
	public OOrderService getOorderService() {
		return oorderService;
	}
	
	public void setOorderService(OOrderService oorderService) {
		this.oorderService = oorderService;
	}
	
	public static List getList() {
		return list;
	}
	
	public static void setList(List list) {
		Task_ClearOrder.list = list;
	}
	
}
