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

/* *
 功能：支付宝服务器异步通知页面
 版本：1.0
 日期：2016-06-06
 说明：
 以下代码只是为了方便商户测试而提供的样例代码，商户可以根据自己网站的需要，按照技术文档编写,并非一定要使用该代码。
 该代码仅供学习和研究支付宝接口使用，只是提供一个参考。

 //***********页面功能说明***********
 * 本页面代码示例用于处理客户端使用http(s) post传输到此服务端的移动支付请求参数待签名字符串。
 * 本页面代码示例采用客户端创建订单待签名的请求字符串传输到服务端的这里进行签名操作并返回。
 //********************************
 * */

public class signatures_url extends ActionSupport {
	
	public void signature_url() throws IOException {
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
			params.put(name, valueStr);
		}
		
		if (params != null && params.size() > 0) {
			// partner
			String partner = request.getParameter("partner");
			AlipayCore.logResult(partner, "partner");
			
			// 接口名
			String service = request.getParameter("service");
			
			// 获取支付宝的通知返回参数，可参考技术文档中页面跳转同步通知参数列表(以上仅供参考)//
			if (partner.replace("\"", "").equals(AlipayConfig.partner)
					&& service.replace("\"", "").equals(AlipayConfig.service)) {// 确认PID和接口名称。
			
				// 将post接收到的数组所有元素，按照“参数=参数值”的模式用“&”字符拼接成字符串。需要排序。
				String data = AlipayCore.createLinkString(params);
				
				// 打印待签名字符串。工程目录下的log文件夹中。
				AlipayCore.logResult(data, "datashuju");
				
				// 将待签名字符串使用私钥签名。
				String rsa_sign = URLEncoder.encode(RSA.sign(data, AlipayConfig.private_key, AlipayConfig.input_charset),
						AlipayConfig.input_charset);
				
				// 把签名得到的sign和签名类型sign_type拼接在待签名字符串后面。
				data = data + "&sign=\"" + rsa_sign + "\"&sign_type=\"" + AlipayConfig.sign_type + "\"";
				
				// 返回给客户端,建议在客户端使用私钥对应的公钥做一次验签，保证不是他人传输。
				System.out.println(data);
			}
			else {
				System.out.println("客户端信息与服务端配置信息有误！");
			}
		}
		else {
			System.out.println("无客户端信息!");
		}
		
	}
	
}
