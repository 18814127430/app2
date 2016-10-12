package alipay.jsp;

import java.util.*;
import alipay.util.*;
import alipay.config.*;
import alipay.sign.RSA;
import java.io.IOException;
import java.io.PrintWriter;
import java.net.URLEncoder;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import org.apache.struts2.ServletActionContext;
import com.opensymphony.xwork2.ActionSupport;
import servlet.OOrderServlet;

/* *
 功能：支付宝服务器异步通知页面
 版本：1.0
 日期：2016-06-06
 说明：
 以下代码只是为了方便商户测试而提供的样例代码，商户可以根据自己网站的需要，按照技术文档编写,并非一定要使用该代码。
 该代码仅供学习和研究支付宝接口使用，只是提供一个参考。

 //***********页面功能说明***********
 创建该页面文件时，请留心该页面文件中无任何HTML代码及空格。
 该页面不能在本机电脑测试，请到服务器上做测试。请确保外部可以访问该页面。
 该页面调试工具请使用写文本函数logResult，该函数在com.alipay.util文件夹的AlipayNotify.java类文件中
 如果没有收到该页面返回的 success 信息，支付宝会在24小时内按一定的时间策略重发通知
 //********************************
 * */

public class notify_url extends ActionSupport {
	
	private OOrderServlet oorderservlet;
	
	public void notify_url() throws IOException {
		
		HttpServletResponse response = ServletActionContext.getResponse();
		response.setCharacterEncoding("UTF-8");
		response.setContentType("application/data; charset=utf-8");
		PrintWriter out = response.getWriter();
		
		HttpServletRequest request = ServletActionContext.getRequest();
		request.setCharacterEncoding("UTF-8");
		
		// 获取支付宝POST过来反馈信息
		Map<String, String> params = new HashMap<String, String>();
		Map requestParams = request.getParameterMap();
		for (Iterator iter = requestParams.keySet().iterator(); iter.hasNext();) {
			String name = (String) iter.next();
			String[] values = (String[]) requestParams.get(name);
			String valueStr = "";
			for (int i = 0; i < values.length; i++) {
				valueStr = (i == values.length - 1) ? valueStr + values[i] : valueStr + values[i] + ",";
			}
			// 乱码解决，这段代码在出现乱码时使用。如果mysign和sign不相等也可以使用这段代码转化
			// valueStr = new String(valueStr.getBytes("ISO-8859-1"), "gbk");
			params.put(name, valueStr);
		}
		
		// 获取支付宝的通知返回参数，可参考技术文档中页面跳转同步通知参数列表(以下仅供参考)//
		// 商户订单号
		String out_trade_no = new String(request.getParameter("out_trade_no").getBytes("ISO-8859-1"), "UTF-8");
		
		// 支付宝交易号
		String trade_no = new String(request.getParameter("trade_no").getBytes("ISO-8859-1"), "UTF-8");
		
		// 交易状态
		String trade_status = new String(request.getParameter("trade_status").getBytes("ISO-8859-1"), "UTF-8");
		
		// 异步通知ID
		String notify_id = request.getParameter("notify_id");
		
		// sign
		String sign = request.getParameter("sign");
		// 获取支付宝的通知返回参数，可参考技术文档中页面跳转同步通知参数列表(以上仅供参考)//
		
		if (notify_id != "" && notify_id != null) {// //判断接受的post通知中有无notify_id，如果有则是异步通知。
			if (AlipayNotify.verifyResponse(notify_id).equals("true"))// 判断成功之后使用getResponse方法判断是否是支付宝发来的异步通知。
			{
				if (AlipayNotify.getSignVeryfy(params, sign))// 使用支付宝公钥验签
				{
					// ——请根据您的业务逻辑来编写程序（以下代码仅作参考）——
					if (trade_status.equals("TRADE_FINISHED")) {
						
						// 判断该笔订单是否在商户网站中已经做过处理
						// 如果没有做过处理，根据订单号（out_trade_no）在商户网站的订单系统中查到该笔订单的详细，并执行商户的业务程序
						// 如果有做过处理，不执行商户的业务程序
						// 注意：
						// 退款日期超过可退款期限后（如三个月可退款），支付宝系统发送该交易状态通知
						// 请务必判断请求时的total_fee、seller_id与通知时获取的total_fee、seller_id为一致的
						
					}
					else if (trade_status.equals("TRADE_SUCCESS")) {
						// 判断该笔订单是否在商户网站中已经做过处理
						// 如果没有做过处理，根据订单号（out_trade_no）在商户网站的订单系统中查到该笔订单的详细，并执行商户的业务程序
						// 如果有做过处理，不执行商户的业务程序
						// 注意：
						// 付款完成后，支付宝系统发送该交易状态通知
						// 请务必判断请求时的total_fee、seller_id与通知时获取的total_fee、seller_id为一致的
					}
					// ——请根据您的业务逻辑来编写程序（以上代码仅作参考）——
					System.out.println("success");// 请不要修改或删除
					
					// 调试打印log
					// AlipayCore.logResult("notify_url success!","notify_url");
				}
				else// 验证签名失败
				{
					System.out.println("sign fail");
				}
			}
			else// 验证是否来自支付宝的通知失败
			{
				System.out.println("response fail");
			}
		}
		else {
			System.out.println("no notify message");
		}
		
	}
	
	public OOrderServlet getOorderservlet() {
		return oorderservlet;
	}
	
	public void setOorderservlet(OOrderServlet oorderservlet) {
		this.oorderservlet = oorderservlet;
	}
	
}
