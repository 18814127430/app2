package utils;

/**
 * @author 10919
 * 
 */
public class msg {
	
	public static String project_name = "app2";//项目名称
	
	public static String root_path = "";
	//	public static String root_url = "http://192.168.191.1";//项目url根路径
	public static String root_url = "http://202.116.161.86:8081";//项目url根路径
	
	public static final String it_b_pay = "3h";// 订单过期时间
	public static final long it_b_pay_second = 3 * 365 * 24 * 60 * 60;// 订单过期时间->秒
	public static final double goodsmoneyleast = 80.0;//包邮金额
	public static final double goodsmoneydeliver = 2.0;//配送费
	public static final int homepage_size = 24;//移动端首页商品的个数
	
	public static final int RECORD_SIZE = 10;// 每一页的记录数目
	public static final int PAGE_SIZE = 10;// 每一组的页码数目
	
	public static String apk_name = "easyfresh.apk";//apk名称
	public static String root_app_attached_auto = "D://alj//attached";//attached图片自动备份目录
	public static String root_app_attached_manual = "D://alj//attached_Manual";//attached图片手动备份目录
	public static String root_app_alipay = "D://alj//alipay/";//支付宝备份目录
	
	public static final String defaultImg_admin = "/app2/uploads/defaultImg.png";// 管理员默认照片
	public static final String defaultImg_customer = "/app2/uploads/defaultImg.png";
	public static final String defaultImg_goods = "/app2/uploads/defaultImg.png";
	public static final String defaultImg_page = "/app2/uploads/defaultImg.png";
	
	public static final int sort_parent = 1;//一级分类的classlevel
	public static final int sort_child = 2;//二级分类的classlevel
	
	//英文逗号
	//===============================轮播图=========================
	public static final String defaultImgPage = "750,320";
	//===============================分类=========================
	public static final String defaultImgSort = "750,320";
	//===============================商品=========================
	public static final String defaultImg1 = "750,320";//轮播图
	public static final String defaultImg2 = "120,120";//新品上市及热销榜单：pic_home_first_grid
	public static final String defaultImg3 = "470,590";//分类左中图：pic_home_second_left
	public static final String defaultImg4 = "590,300";//分类右上图：pic_home_second_right_top
	public static final String defaultImg5 = "590,300";//分类右下图：pic_home_second_bottom_top
	public static final String defaultImg6 = "750,750";//详情页：pic_gooddetail
	public static final String defaultImg7 = "390,390";//搜索二级页面：pic_sec_good
	public static final String defaultImg8 = "200,200";//购物车(收藏)
	public static final String defaultImg9_16 = "1125,680";//购物车(收藏)
	
	public static final String admin_success = "success";
	public static final String admin_fail = "fail";
	public static final String admin_authority = "your authority is limited";
	public static final String admin_longdistancelogin = "long distance login, please change your password";
	public static final String admin_accountexist = "account exist";
	public static final String admin_accountnull = "no account found";
	public static final String admin_passwordwrong = "wrong password";
	public static final String admin_phoneexist = "phone exist";
	public static final String admin_phonenull = "no phone found";
	public static final String admin_nameexist = "name exist";
	public static final String admin_namenull = "no name found";
	public static final String admin_idnull = "no id found";
	
	public static final String caddress_success = "success";
	public static final String caddress_fail = "fail";
	public static final String caddress_jsonnull = "null json";
	public static final String caddress_customerwrong = "two different customer, wrong customer";
	public static final String caddress_addressnull = "null address";
	public static final String caddress_illegalinput = "illegal input";
	
	public static final String cart_success = "success";
	public static final String cart_fail = "fail";
	public static final String cart_delete = "not all deleted";
	public static final String cart_jsonnull = "null json";
	public static final String cart_customerwrong = "two different customer, wrong customer";
	public static final String cart_cartnull = "null cart";
	public static final String cart_illegalinput = "illegal input";
	
	public static final String collect_success = "success";
	public static final String collect_fail = "fail";
	public static final String collect_delete = "not all deleted";
	public static final String collect_jsonnull = "null json";
	public static final String collect_customerwrong = "two different customer, wrong customer";
	public static final String collect_collectnull = "null collect";
	public static final String collect_collectexist = "collect exist";
	public static final String collect_illegalinput = "illegal input";
	
	public static final String comment_success = "success";
	public static final String comment_fail = "fail";
	public static final String comment_jsonnull = "null json";
	public static final String comment_customerwrong = "two different customer, wrong customer";
	public static final String comment_commentnull = "null comment";
	public static final String comment_commentexist = "comment exist";
	public static final String comment_beyongcount = "beyond count";
	public static final String comment_illegalinput = "illegal input";
	
	public static final String company_success = "success";
	public static final String company_fail = "fail";
	public static final String company_companynull = "null company";
	public static final String company_companyexist = "company exist";
	
	public static final String customer_success = "success";
	public static final String customer_fail = "fail";
	public static final String customer_jsonnull = "null json";
	public static final String customer_customernull = "null customer";
	public static final String customer_customerexist = "customer exist";
	public static final String customer_codewaiting = "waiting for quatified code";
	public static final String customer_codewrong = "illegal code";
	public static final String customer_passwordwrong = "illegal password";
	public static final String customer_tokenwrong = "illegal token";
	public static final String customer_illegalinput = "illegal input";
	
	public static final String gbatch_success = "success";
	public static final String gbatch_fail = "fail";
	public static final String gbatch_gbatchnull = "null gbatch";
	public static final String gbatch_gbatchexist = "gbatch exist";
	public static final String gbatch_illegalinput = "illegal input";
	public static final String gbatch_gbatchwrong = "two different gbatch, wrong gbatch";
	
	public static final String goods_success = "success";
	public static final String goods_fail = "fail";
	public static final String goods_goodsnull = "null goods";
	public static final String goods_goodsexist = "goods exist";
	public static final String goods_illegalinput = "illegal input";
	public static final String goods_goodswrong = "two different goods, wrong goods";
	
	public static final String help_success = "success";
	public static final String help_fail = "fail";
	public static final String help_helpnull = "null help";
	public static final String help_helpexist = "help exist";
	public static final String help_illegalinput = "illegal input";
	
	public static final String push_success = "success";
	public static final String push_fail = "fail";
	public static final String push_pushnull = "null push";
	public static final String push_pushexist = "push exist";
	public static final String push_illegalinput = "illegal input";
	
	public static final String logs_success = "success";
	public static final String logs_fail = "fail";
	public static final String logs_logsnull = "null logs";
	public static final String logs_logsexist = "logs exist";
	public static final String logs_illegalinput = "illegal input";
	
	public static final String order_success = "success";
	public static final String order_fail = "fail";
	public static final String order_customerwrong = "two different customer, wrong customer";
	public static final String order_jsonnull = "null json";
	public static final String order_ordernull = "null order";
	public static final String order_orderexist = "order exist";
	public static final String order_illegalinput = "illegal input";
	
	public static final String order_statuspay0 = "null pay status";
	public static final String order_statuspay1 = "didn't pay yet";
	public static final String order_statuspay2 = "payed";
	public static final String order_statuspay3 = "order finished";
	public static final String order_statuspay4 = "order closed";
	public static final String order_statusrefund0 = "null refund status";
	public static final String order_statusrefund1 = "wait for comfiring refund";
	public static final String order_statusrefund2 = "refund for money";
	public static final String order_statusrefund3 = "refund for money and goods";
	public static final String order_statusrefund4 = "refund refushed";
	public static final String order_statussend0 = "null send status";
	public static final String order_statussend1 = "didn't send yet";
	public static final String order_statussend2 = "sent";
	public static final String order_statussend3 = "received";
	public static final String order_statusmethod0 = "null pay method";
	public static final String order_statusmethod1 = "pay offline";
	public static final String order_statusmethod2 = "pay online";
	
	public static final String sign_success = "success";
	public static final String sign_fail = "fail";
	
	public static final String oinfo_success = "success";
	public static final String oinfo_fail = "fail";
	public static final String oinfo_customerwrong = "two different customer, wrong customer";
	public static final String oinfo_jsonnull = "null json";
	public static final String oinfo_oinfonull = "null oinfo";
	public static final String oinfo_oinfoexist = "oinfo exist";
	public static final String oinfo_illegalinput = "illegal input";
	
	public static final String page_success = "success";
	public static final String page_fail = "fail";
	public static final String page_pagenull = "null page";
	public static final String page_pageexist = "page exist";
	public static final String page_illegalinput = "illegal input";
	
	public static final String sort_success = "success";
	public static final String sort_fail = "fail";
	public static final String sort_sortnull = "null sort";
	public static final String sort_sortexist = "sort exist";
	public static final String sort_illegalinput = "illegal input";
	
	public static final String status_0 = "100";// 成功
	public static final String status_1 = "101";// 失败
	public static final String status_2 = "102";// 未付款状态
	public static final String status_3 = "103";
	public static final String status_4 = "104";
	public static final String status_5 = "105";
	
	// 支付方式：1=关闭状态 、2=货到付款、3=线上支付
	//	int statusmethod = 3;
	
	// 付款状态：1=关闭状态、2=未付款、3=已付款
	//	int statuspay = 3;
	
	// 订单状态：1=关闭状态、2=未完成、3=已完成、4=未关闭
	//	int statusorder = 3;
	
	// 货物状态：1=关闭状态、2=未发货、3=已发货、4=已收货
	//	int statussend = 4;
	
	// 退款状态：1=关闭状态 、2=等待退款、3=退款成功、4=允许退款、 5=拒绝退款
	//	int statusrefund = 1;
	
	// 评论状态：1=未评论 、2=已评论
	//	int statuscomment = 1;
	
}
