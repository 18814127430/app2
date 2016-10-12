package utils;

import com.taobao.api.ApiException;
import com.taobao.api.DefaultTaobaoClient;
import com.taobao.api.TaobaoClient;
import com.taobao.api.request.AlibabaAliqinFcSmsNumSendRequest;
import com.taobao.api.response.AlibabaAliqinFcSmsNumSendResponse;

public class SendMsg2AliServer {

	// private String extend = "123456";//可能要用于标识用户而传入的ID
	private final String appkey = "23369899";// 加入大鱼的key
	private final String secret = "065b502f3614383ea4555b08b17d8a0f";// 大鱼的证书序列号
	private final String url = "https://eco.taobao.com/router/rest";// 大鱼服务器的url
	private String smsFreeSignName = "我的项目";// 我已经注册的项目签名
	private String smsTemplateCode = "SMS_10170425";// 已注册的一个短信模板
	private String msgModelJson = "{name:'某某先生',n:'**电商平台',action:'忘记密码',ms:'1234'}";

	public SendMsg2AliServer() {

	}

	/**
	 * 用于进行可能存在的签名，模板更换
	 * @param freeSignName短信签名
	 * @param smsTemplateCode短信模板标识码
	 */
	public void setMsgInfo(String freeSignName) {
		this.smsFreeSignName = freeSignName;
	}

	public void setSmsTemplateCode(String smsTemplateCode) {
		this.smsTemplateCode = smsTemplateCode;
	}

	/**
	 * @param phoneNumber请求验证码的用户手机号码
	 * @throws ApiException
	 */

	public void GetPhoneNum2SendMsg(String MsgModel,String phone) throws ApiException {
		TaobaoClient client = new DefaultTaobaoClient(url, appkey, secret);
		AlibabaAliqinFcSmsNumSendRequest req = new AlibabaAliqinFcSmsNumSendRequest();
		req.setExtend("123456");// 用户标识
		req.setSmsType("normal");// 必须填入normal
		req.setSmsFreeSignName(smsFreeSignName);
		req.setSmsParamString(MsgModel);
		req.setSmsTemplateCode(smsTemplateCode);
		req.setRecNum(phone);
		AlibabaAliqinFcSmsNumSendResponse rsp = client.execute(req);
		System.out.println(rsp.getBody());
	}

}