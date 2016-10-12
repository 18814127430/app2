package alipay.config;

import utils.msg;

/* *
 *类名：AlipayConfig
 *功能：基础配置类
 *详细：设置帐户有关信息及返回路径
 *版本：1.0
 *日期：2016-06-06
 *说明：
 *以下代码只是为了方便商户测试而提供的样例代码，商户可以根据自己网站的需要，按照技术文档编写,并非一定要使用该代码。
 *该代码仅供学习和研究支付宝接口使用，只是提供一个参考。
 */

public class AlipayConfig {
	
	// ↓↓↓↓↓↓↓↓↓↓请在这里配置您的基本信息↓↓↓↓↓↓↓↓↓↓↓↓↓↓↓
	
	// 合作身份者ID，签约账号，以2088开头由16位纯数字组成的字符串，查看地址：https://openhome.alipay.com/platform/keyManage.htm?keyType=partner
	public static String partner = "2088811979854602";
	
	// 商户的私钥,需要PKCS8格式，RSA公私钥生成：https://doc.open.alipay.com/doc2/detail.htm?spm=a219a.7629140.0.0.nBDxfy&treeId=58&articleId=103242&docType=1
	public static String private_key = "MIICeAIBADANBgkqhkiG9w0BAQEFAASCAmIwggJeAgEAAoGBANrwykAcIgSjZNXzd02BAxjux5zh6SsYwdeT5TOMckzQ3pmRcECEFWLmrmiafOZROiMKXB3+QqXQbdLkQbUPlKm9/NjI7rOwAi/uUvBPrCgT4emjnbyAP4sGy1ON+2MPAWCF5Zk4Df/BCa4cvNTlkH0yWtb4ehqFVLbwz+SGIz45AgMBAAECgYAiEZIHQ9lvS1WnjUklQEwRFybsnKLB2Aph11u1F8e5eE8LKnyDLYp+Z1MAZ/nDhSQ8y6Hl8OjCEJrmVCp9WD1Tms2+viD8w39B+U09nbPpHS7IubX5m1f6lptfkTeY1uIJ3tliIprtv5zp/HiUxpjduQBxaqd5vTZIvJvLr4yqYQJBAPNFKX8t131ScpQT8iDijmfwUItxeHHMqYlWoxMQ/91e5cXI0/9XcnrCRIO9yPeuwbQ4Lr6e+CYphaAX2sl2zVUCQQDmZbW3tgsg3ONYKDoBKYX2pxhA7CuF2U134kjXHVhWXQaaEzThOkiHbfsPRGZe539//SSO+5uNNPvsi3KiNM1VAkEAh52713l/0w5+b4x7A9gpGUqALGxyf8ZfkP+qY3urmAZKQ6uEe6mNWWApf+PyQBm9RLG5ZW0L24J0St5hisRfDQJBANT8IUh7GT5/9AqC5Vw9roGdZmZd7L4Ha/3vSbbSgd8UkA3Ow4uR/Yp0uSob6SbCirMeqLUlE18uI9U/8/DPLyECQQDc0fIlb9MjcGAg5uR21B9ERph3TJotZzmMykJZKoF74oxYv2engZnXhBGSL10KNqt2XLfeYg5IWU+zFEfHvg5y";
	
	// 支付宝的公钥，查看地址：https://openhome.alipay.com/platform/keyManage.htm?keyType=partner
	public static String alipay_public_key = "MIGfMA0GCSqGSIb3DQEBAQUAA4GNADCBiQKBgQDDI6d306Q8fIfCOaTXyiUeJHkrIvYISRcc73s3vF1ZT7XN8RNPwJxo8pWaJMmvyTn9N4HQ632qJBVHf8sxHi/fEsraprwCtzvzQETrNRwVxLO5jVmRGi60j8Ue1efIlzPXV9je9mkjzOmdssymZkh2QhUrCmZYI/FCEa3/cNMW0QIDAQAB";
	
	// 签名方式
	public static String sign_type = "RSA";
	
	// 调试用，创建TXT日志文件夹路径，见AlipayCore.java类中的logResult(String sWord)打印方法。
	public static String log_path = msg.root_app_alipay;
	
	// 字符编码格式 目前支持 gbk 或 utf-8
	public static String input_charset = "utf-8";
	
	// 接收通知的接口名
	public static String service = "mobile.securitypay.pay";
	
	// ↑↑↑↑↑↑↑↑↑↑请在这里配置您的基本信息↑↑↑↑↑↑↑↑↑↑↑↑↑↑↑
}
