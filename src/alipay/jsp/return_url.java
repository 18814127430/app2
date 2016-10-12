package alipay.jsp;

import java.util.*;

import alipay.util.*;
import alipay.config.*;
import alipay.sign.RSA;

import java.io.IOException;
import java.io.PrintWriter;
import java.net.*;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.struts2.ServletActionContext;

import com.opensymphony.xwork2.ActionSupport;

/* *
 功能：支付宝移动支付服务端同步验签页面
 版本：1.0
 日期：2016-06-06
 说明：
 以下代码只是为了方便商户测试而提供的样例代码，商户可以根据自己网站的需要，按照技术文档编写,并非一定要使用该代码。
 该代码仅供学习和研究支付宝接口使用，只是提供一个参考。

 //***********页面功能说明***********
 * 本页面代码示例用于处理客户端使用http(s) post传输到此服务端的移动支付同步返回中的result待验签字段.
 * 注意：只要把同步返回中的result结果传输过来做验签.
 //********************************
 * */

public class return_url extends ActionSupport {

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
		} finally {
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
			} else {
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
					} else {
						System.out.println("return fail");
					}
				} else {
					System.out.println("客户端信息与服务端配置信息有误！");
				}
			} else {
				System.out.println("此同步返回无效!");
			}
		} else {
			System.out.println("无客户端参数!");
		}
	}
}
