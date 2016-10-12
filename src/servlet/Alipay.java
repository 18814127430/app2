package servlet;

import java.util.*;

import alipay.config.AlipayConfig;
import alipay.sign.RSA;
import alipay.util.*;
import bean.OOrder;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.lang.reflect.Type;
import java.net.URL;
import java.net.URLConnection;
import java.net.URLDecoder;
import java.net.URLEncoder;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.apache.struts2.ServletActionContext;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.reflect.TypeToken;
import com.opensymphony.xwork2.ActionSupport;

import service.OOrderService;
import utils.msg;
import utils.test;
import task.Task_ClearOrder;

public class Alipay extends ActionSupport {
	
	private final Log logger = LogFactory.getLog(getClass());
	private OOrderService oorderService;
	
	// action调用url = "http://localhost:8080/app2/servlet/goods_doHomePage.action"
	public void test1() throws Exception {
		
		HttpServletResponse response = ServletActionContext.getResponse();
		response.setCharacterEncoding("UTF-8");
		response.setContentType("application/data; charset=utf-8");
		PrintWriter out = response.getWriter();
		
		HttpServletRequest request = ServletActionContext.getRequest();
		request.setCharacterEncoding("UTF-8");
		
		String result = "";//访问返回结果
		BufferedReader read = null;//读取访问结果
		String url = "http://localhost:8080/app2/servlet/goods_doHomePage.action";
		String param = null;
		try {
			//创建url
			URL realurl = new URL(url + "?" + param);
			//打开连接
			URLConnection connection = realurl.openConnection();
			// 设置通用的请求属性
			connection.setRequestProperty("accept", "*/*");
			connection.setRequestProperty("connection", "Keep-Alive");
			connection.setRequestProperty("user-agent", "Mozilla/4.0 (compatible; MSIE 6.0; Windows NT 5.1;SV1)");
			//建立连接
			connection.connect();
			// 获取所有响应头字段
			Map<String, List<String>> map = connection.getHeaderFields();
			// 遍历所有的响应头字段，获取到cookies等
			for (String key : map.keySet()) {
				System.out.println(key + "--->" + map.get(key));
			}
			// 定义 BufferedReader输入流来读取URL的响应
			read = new BufferedReader(new InputStreamReader(connection.getInputStream(), "UTF-8"));
			String line;//循环读取
			while ((line = read.readLine()) != null) {
				result += line;
			}
		} catch (IOException e) {
			e.printStackTrace();
		}
		finally {
			if (read != null) {//关闭流
				try {
					read.close();
				} catch (IOException e) {
					e.printStackTrace();
				}
			}
		}
		
		test.a("test1:" + result);
		
		out.write(result);
		out.flush();
		out.close();
		return;
	}
	
	public void return_url() throws IOException {
		HttpServletResponse response = ServletActionContext.getResponse();
		response.setCharacterEncoding("UTF-8");
		response.setContentType("application/data; charset=utf-8");
		PrintWriter out = response.getWriter();
		
		HttpServletRequest request = ServletActionContext.getRequest();
		request.setCharacterEncoding("UTF-8");
		
		// 获取支付宝POST过来反馈信息
		
		response.setContentType("text/html");// 注意加上
		String result = null;
		java.io.BufferedReader reader = null;
		String success = null;
		String sign = null;
		String partner = null;
		String service = null;
		String prestr = "";
		
		// post获取字符串
		try {
			reader = request.getReader();// 获得字符流
			StringBuffer content = new StringBuffer();
			String line;
			while ((line = reader.readLine()) != null) {
				content.append(line + "\r\n");
			}
			result = content.toString();
		} catch (Exception e) {
			e.printStackTrace();
		}
		finally {
			try {
				reader.close();
				reader = null;
			} catch (Exception e) {
				
			}
		}
		
		// 客户端同步参数建议urlencode之后传输到此服务端，防止字符“＋”变成空格。
		result = URLDecoder.decode(result, "utf-8");
		
		// 利用“&”分割
		String[] array = result.split("&");
		
		// 除去数组中的空值和签名参数,且把数组所有元素，按照“参数=参数值”的模式用“&”字符拼接成字符串,不需要排序
		for (int i = 0; i < array.length; i++) {
			String key = array[i].split("=", 2)[0];
			String value = array[i].split("=", 2)[1];
			if (key.equals("sign") || key.equals("sign_type")) {
				if (key.equals("sign")) {
					sign = value.replace("\"", "");
				}
				continue;
			}
			else {
				if (key.equals("success")) {
					success = value.replace("\"", "");
				}
				if (key.equals("partner")) {
					partner = value.replace("\"", "");
				}
				if (key.equals("service")) {
					service = value.replace("\"", "");
				}
				prestr = prestr + key + "=" + value + "&";
			}
		}
		
		prestr = prestr.substring(0, prestr.length() - 1);// 去掉最后一个&符号。
		
		// 调试用，判断取值是否正确。
		// AlipayCore.logResult(success+"\r\n"+sign+"\r\n"+partner+"\r\n"+prestr,"temp");
		
		if (prestr != "") {
			// 注意：在客户端把返回参数请求过来的时候务必要把sign做一次urlencode,保证"+"号字符不会变成空格。
			if (success.equals("true"))// 判断success是否为true.
			{
				// 验证参数是否匹配
				if (partner.equals(AlipayConfig.partner) && service.equals(AlipayConfig.service)) {
					// 调试用，判断取值是否正确。
					// AlipayCore.logResult("prestr:"+prestr+"\r\n"+"sign:"+sign,"dataandsign");
					
					boolean issign = false;
					
					// 获得验签结果
					issign = RSA.verify(prestr, sign, AlipayConfig.alipay_public_key, AlipayConfig.input_charset);
					if (issign) {
						// 此处可做商家业务逻辑，建议商家以异步通知为准。
						// out.clear();
						System.out.println("return success");
						out.write("return success");
						out.flush();
						out.close();
						return;
					}
					else {
						System.out.println("return fail");
						out.write("return fail");
						out.flush();
						out.close();
						return;
					}
				}
				else {
					System.out.println("客户端信息与服务端配置信息有误！");
					out.write("客户端信息与服务端信息配置信息有误！");
					out.flush();
					out.close();
					return;
				}
			}
			else {
				System.out.println("此同步返回无效!");
				out.write("此同步返回无效!");
				out.flush();
				out.close();
				return;
			}
		}
		else {
			System.out.println("无客户端参数!");
			out.write("无客户端参数!");
			out.flush();
			out.close();
			return;
		}
	}
	
	public void signatures_url() throws Exception {
		Gson gson = new GsonBuilder().disableHtmlEscaping().create();
		Map<String, String> map = new HashMap<String, String>();
		final Type maptype = new TypeToken<Map<String, String>>() {
		}.getType();
		
		String status = "";
		String message = "";
		String data = "";
		int page = 0;
		String mapdata = "";
		
		HttpServletResponse response = ServletActionContext.getResponse();
		response.setCharacterEncoding("UTF-8");
		response.setContentType("application/data; charset=utf-8");
		PrintWriter out = response.getWriter();
		
		HttpServletRequest request = ServletActionContext.getRequest();
		request.setCharacterEncoding("UTF-8");
		
		// 获取支付宝POST过来反馈信息
		String json = request.getParameter("data");
		String partner = request.getParameter("partner");
		String service = request.getParameter("service");
		
		test.a(json);
		test.a(partner);
		test.a(service);
		
		AlipayCore.logResult(json, "signatures_json");
		AlipayCore.logResult(partner, "signatures_partner");
		AlipayCore.logResult(service, "signatures_service");
		
		Map<String, String> params = gson.fromJson(json, maptype);
		AlipayCore.logResult(params.toString(), "signatures_params");
		test.a(params.toString());
		
		if (params != null && params.size() > 0) {
			
			// 确认PID和接口名称。
			if (partner.equals(AlipayConfig.partner) && service.equals(AlipayConfig.service)) {
				
				// 将post接收到的数组所有元素，按照“参数=参数值”的模式用“&”字符拼接成字符串。需要排序。
				data = AlipayCore.createLinkString(params);
				
				AlipayCore.logResult(data, "signatures_datashuju_0");
				
				// 将待签名字符串使用私钥签名。
				String rsa_sign = URLEncoder.encode(RSA.sign(data, AlipayConfig.private_key, AlipayConfig.input_charset),
						AlipayConfig.input_charset);
				
				// 把签名得到的sign和签名类型sign_type拼接在待签名字符串后面。
				data = data + "&sign=" + rsa_sign + "&sign_type=" + AlipayConfig.sign_type;
				AlipayCore.logResult(data, "signatures_datashuju_1");
				
				status = msg.status_0;
				message = msg.sign_success;
				
				map.put("status", status);
				//map.put("message", message);
				map.put("data", data);
				mapdata = gson.toJson(map);
				
				out.write(mapdata);
				out.flush();
				out.close();
				return;
			}
			else {
				System.out.println("客户端信息与服务端配置信息有误！");
				AlipayCore.logResult("客户端信息与服务端配置信息有误", "signatures_客户端信息与服务端配置信息有误");
				
				status = msg.status_1;
				
				map.put("status", status);
				map.put("message", "客户端信息与服务端配置信息有误！");
				mapdata = gson.toJson(map);
				
				out.write(mapdata);
				out.flush();
				out.close();
				return;
			}
		}
		else {
			System.out.println("无客户端信息!");
			AlipayCore.logResult("无客户端信息!", "signatures_无客户端信息");
			
			status = msg.status_1;
			
			map.put("status", status);
			map.put("message", "无客户端信息!");
			mapdata = gson.toJson(map);
			
			out.write(mapdata);
			out.flush();
			out.close();
			return;
		}
	}
	
	public void notify_url() throws Exception {
		
		HttpServletResponse response = ServletActionContext.getResponse();
		response.setCharacterEncoding("UTF-8");
		response.setContentType("application/data; charset=utf-8");
		PrintWriter out = response.getWriter();
		
		HttpServletRequest request = ServletActionContext.getRequest();
		request.setCharacterEncoding("UTF-8");
		
		// 获取支付宝POST过来反馈信息
		Map<String, String> params = new HashMap<String, String>();
		Map requestParams = request.getParameterMap();
		logger.info("requestParams:" + requestParams.toString());
		AlipayCore.logResult(requestParams.toString(), "notify_requestParams");
		
		for (Iterator iter = requestParams.keySet().iterator(); iter.hasNext();) {
			String name = (String) iter.next();
			String[] values = (String[]) requestParams.get(name);
			String valueStr = "";
			for (int i = 0; i < values.length; i++) {
				valueStr = (i == values.length - 1) ? valueStr + values[i] : valueStr + values[i] + ",";
			}
			// 乱码解决，这段代码在出现乱码时使用。如果mysign和sign不相等也可以使用这段代码转化
			// valueStr = new String(valueStr.getBytes("ISO-8859-1"), "gbk");
			logger.info("name:" + name.toString() + "----valueStr:" + valueStr);
			params.put(name, valueStr);
		}
		logger.info("params:" + params.toString());
		AlipayCore.logResult(params.toString(), "notify_params");
		
		// 获取支付宝的通知返回参数，可参考技术文档中页面跳转同步通知参数列表(以下仅供参考)//
		// 商户订单号
		String out_trade_no = new String(request.getParameter("out_trade_no").getBytes("ISO-8859-1"), "UTF-8");
		logger.info("out_trade_no:" + out_trade_no.toString());
		AlipayCore.logResult(out_trade_no, "notify_out_trade_no");
		
		// 支付宝交易号
		String trade_no = new String(request.getParameter("trade_no").getBytes("ISO-8859-1"), "UTF-8");
		logger.info("trade_no:" + trade_no.toString());
		AlipayCore.logResult(trade_no, "notify_trade_no");
		
		//交易状态
		String trade_status = new String(request.getParameter("trade_status").getBytes("ISO-8859-1"), "UTF-8");
		logger.info("trade_status:" + trade_status.toString());
		AlipayCore.logResult(trade_status, "notify_trade_status");
		
		//异步通知ID
		String notify_id = request.getParameter("notify_id");
		logger.info("notify_id:" + notify_id.toString());
		AlipayCore.logResult(notify_id, "notify_notify_id");
		
		//交易金额
		String total_fee_ = new String(request.getParameter("total_fee").getBytes("ISO-8859-1"), "UTF-8");
		logger.info("total_fee_:" + total_fee_.toString());
		AlipayCore.logResult(total_fee_, "notify_total_fee_");
		double total_fee = 0;
		if (test.isDouble(total_fee_)) {
			total_fee = Double.parseDouble(total_fee_);
		}
		
		//交易付款时间
		String gmt_payment = new String(request.getParameter("gmt_payment").getBytes("ISO-8859-1"), "UTF-8");
		logger.info("gmt_payment:" + gmt_payment.toString());
		AlipayCore.logResult(gmt_payment, "notify_gmt_payment");
		
		//sign
		String sign = request.getParameter("sign");
		logger.info("sign:" + sign.toString());
		AlipayCore.logResult(sign, "notify_sign");
		//获取支付宝的通知返回参数，可参考技术文档中页面跳转同步通知参数列表(以上仅供参考)//
		
		if (notify_id != "" && notify_id != null) {// //判断接受的post通知中有无notify_id，如果有则是异步通知。
			if (AlipayNotify.verifyResponse(notify_id).equals("true"))// 判断成功之后使用getResponse方法判断是否是支付宝发来的异步通知。
			{
				if (AlipayNotify.getSignVeryfy(params, sign))// 使用支付宝公钥验签
				{
					// ——请根据您的业务逻辑来编写程序（以下代码仅作参考）——
					if (trade_status.equals("TRADE_FINISHED")) {
						
						TRADE_FINISHED(out_trade_no);
						// 判断该笔订单是否在商户网站中已经做过处理
						// 如果没有做过处理，根据订单号（out_trade_no）在商户网站的订单系统中查到该笔订单的详细，并执行商户的业务程序
						// 如果有做过处理，不执行商户的业务程序
						// 注意：
						// 退款日期超过可退款期限后（如三个月可退款），支付宝系统发送该交易状态通知
						// 请务必判断请求时的total_fee、seller_id与通知时获取的total_fee、seller_id为一致的
						
					}
					else if (trade_status.equals("TRADE_SUCCESS")) {
						TRADE_SUCCESS(out_trade_no, trade_no, gmt_payment, total_fee);
						// 判断该笔订单是否在商户网站中已经做过处理
						// 如果没有做过处理，根据订单号（out_trade_no）在商户网站的订单系统中查到该笔订单的详细，并执行商户的业务程序
						// 如果有做过处理，不执行商户的业务程序
						// 注意：
						// 付款完成后，支付宝系统发送该交易状态通知
						// 请务必判断请求时的total_fee、seller_id与通知时获取的total_fee、seller_id为一致的
					}
					else if (trade_status.equals("WAIT_BUYER_PAY")) {
						WAIT_BUYER_PAY(out_trade_no);
						// 判断该笔订单是否在商户网站中已经做过处理
						// 如果没有做过处理，根据订单号（out_trade_no）在商户网站的订单系统中查到该笔订单的详细，并执行商户的业务程序
						// 如果有做过处理，不执行商户的业务程序
						// 注意：
						// 付款完成后，支付宝系统发送该交易状态通知
						// 请务必判断请求时的total_fee、seller_id与通知时获取的total_fee、seller_id为一致的
					}
					// ——请根据您的业务逻辑来编写程序（以上代码仅作参考）——
					System.out.println("success");// 请不要修改或删除
					AlipayCore.logResult("success", "notify_success");
					out.write("success");
					out.flush();
					out.close();
					return;
					
					// 调试打印logAlipayCore.logResult("notify_url success!","notify_url");
				}
				else// 验证签名失败
				{
					System.out.println("sign fail");
					AlipayCore.logResult("sign fail", "notify_signfail");
					out.write("sign fail");
					out.flush();
					out.close();
					return;
				}
			}
			else// 验证是否来自支付宝的通知失败
			{
				System.out.println("response fail");
				AlipayCore.logResult("response fail", "notify_responsefail");
				out.write("response fail");
				out.flush();
				out.close();
				return;
			}
		}
		else {
			System.out.println("no notify message");
			AlipayCore.logResult("no notify message", "notify_nonotifymessage");
			out.write("no notify message");
			out.flush();
			out.close();
			return;
		}
	}
	
	/**
	 * 交易成功
	 * 
	 * @param orderSerial
	 * @throws Exception
	 */
	public void TRADE_FINISHED(String orderSerial) throws Exception {
		
		OOrder db_oorder = oorderService.View_Serial(orderSerial);
		test.a(db_oorder);
		
		if (db_oorder == null) {
			return;
		}
		
		// 支付方式：1=关闭状态 、2=货到付款、3=线上支付
		int statusmethod = 3;
		// 付款状态：1=关闭状态、2=未付款、3=已付款
		int statuspay = 3;
		// 订单状态：1=关闭状态、2=未完成、3=已完成、4=未关闭
		int statusorder = 3;
		// 货物状态：1=关闭状态、2=未发货、3=已发货、4=已收货
		int statussend = 4;
		// 退款状态：1=关闭状态 、2=等待退款、3=退款成功、4=允许退款、 5=拒绝退款
		int statusrefund = 1;
		// 评论状态：1=未评论 、2=已评论
		int statuscomment = 1;
		
		db_oorder.setStatusMethod(statusmethod);
		db_oorder.setStatusPay(statuspay);
		db_oorder.setStatusOrder(statusorder);
		db_oorder.setStatusSend(statussend);
		db_oorder.setStatusRefund(statusrefund);
		
		db_oorder = oorderService.Update(db_oorder);
		test.a(db_oorder);
		
		for (int i = Task_ClearOrder.list.size() - 1; i >= 0; i--) {
			int orderid = (Integer) Task_ClearOrder.list.get(i);
			if (orderid == db_oorder.getOrderId()) {
				Task_ClearOrder.list.remove(i);
			}
		}
		
		return;
	}
	
	/**
	 * 支付成功
	 * 
	 * @param orderSerial
	 * @param paySerial
	 * @param payDate
	 * @param money
	 * @throws Exception
	 */
	public void TRADE_SUCCESS(String orderSerial, String paySerial, String payDate, double money) throws Exception {
		
		OOrder db_oorder = oorderService.View_Serial(orderSerial);
		
		if (db_oorder == null) {
			return;
		}
		
		if (db_oorder.getMoneyTotal() != money) {
			oorderService.Delete(db_oorder);
			/* 申请退款 */
		}
		
		// 支付方式：1=关闭状态 、2=货到付款、3=线上支付
		int statusmethod = 3;
		// 付款状态：1=关闭状态、2=未付款、3=已付款
		int statuspay = 3;
		// 订单状态：1=关闭状态、2=未完成、3=已完成、4=未关闭
		int statusorder = 2;
		// 货物状态：1=关闭状态、2=未发货、3=已发货、4=已收货
		int statussend = 2;
		// 退款状态：1=关闭状态 、2=等待退款、3=退款成功、4=允许退款、 5=拒绝退款
		int statusrefund = 1;
		// 评论状态：1=未评论 、2=已评论
		int statuscomment = 1;
		
		db_oorder.setStatusMethod(statusmethod);
		db_oorder.setStatusPay(statuspay);
		db_oorder.setStatusOrder(statusorder);
		db_oorder.setStatusSend(statussend);
		db_oorder.setStatusRefund(statusrefund);
		
		db_oorder.setPayDate(test.String2Timestamp(payDate));
		db_oorder.setPaySerial(paySerial);
		
		db_oorder = oorderService.Update(db_oorder);
		if (db_oorder == null) {
			return;
		}
		
		for (int i = Task_ClearOrder.list.size() - 1; i >= 0; i--) {
			int orderid = (Integer) Task_ClearOrder.list.get(i);
			if (orderid == db_oorder.getOrderId()) {
				Task_ClearOrder.list.remove(i);
			}
		}
		
		return;
		
	}
	
	/**
	 * 交易创建
	 * 
	 * @param orderSerial
	 * @param paySerial
	 * @param payDate
	 * @param money
	 * @throws Exception
	 */
	public void WAIT_BUYER_PAY(String orderSerial) throws Exception {
		
		OOrder db_oorder = oorderService.View_Serial(orderSerial);
		
		if (db_oorder == null) {
			return;
		}
		
		// 支付方式：1=关闭状态 、2=货到付款、3=线上支付
		int statusmethod = 3;
		// 付款状态：1=关闭状态、2=未付款、3=已付款
		int statuspay = 2;
		// 订单状态：1=关闭状态、2=未完成、3=已完成、4=未关闭
		int statusorder = 2;
		// 货物状态：1=关闭状态、2=未发货、3=已发货、4=已收货
		int statussend = 2;
		// 退款状态：1=关闭状态 、2=等待退款、3=退款成功、4=允许退款、 5=拒绝退款
		int statusrefund = 1;
		// 评论状态：1=未评论 、2=已评论
		int statuscomment = 1;
		
		db_oorder.setStatusMethod(statusmethod);
		db_oorder.setStatusPay(statuspay);
		db_oorder.setStatusOrder(statusorder);
		db_oorder.setStatusSend(statussend);
		db_oorder.setStatusRefund(statusrefund);
		
		db_oorder = oorderService.Update(db_oorder);
		if (db_oorder == null) {
			return;
		}
		
		for (int i = Task_ClearOrder.list.size() - 1; i >= 0; i--) {
			int orderid = (Integer) Task_ClearOrder.list.get(i);
			if (orderid == db_oorder.getOrderId()) {
				Task_ClearOrder.list.remove(i);
			}
		}
		
		Task_ClearOrder.list.add(db_oorder.getOrderId());
		
		return;
	}
	
	public OOrderService getOorderService() {
		return oorderService;
	}
	
	public void setOorderService(OOrderService oorderService) {
		this.oorderService = oorderService;
	}
	
}
