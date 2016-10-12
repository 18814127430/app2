package task;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.quartz.JobExecutionContext;
import org.quartz.JobExecutionException;
import org.springframework.scheduling.quartz.QuartzJobBean;

import java.io.IOException;
import java.net.URL;
import java.net.URLConnection;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import service.GoodsService;
import utils.msg;

import com.google.gson.Gson;

import androidbeans.a_Goods_2;
import bean.Goods;
import utils.test;

public class Task_SetHomePage extends QuartzJobBean {
	
	private final Log logger = LogFactory.getLog(getClass());
	
	private GoodsService goodsService;
	public static String map_data;
	
	protected void executeInternal(JobExecutionContext context) throws JobExecutionException {
		
		test.a("设置移动端主页开始. . . ");
		
		Gson gson = new Gson();
		Map<String, Object> map = new HashMap<String, Object>();
		String status = "";
		String message = "";
		String data = "";
		String mapdata = "";
		
		List list = new ArrayList();
		List list1 = new ArrayList();
		List list2 = new ArrayList();
		List list3 = new ArrayList();
		List list4 = new ArrayList();
		List list5 = new ArrayList();
		List list6 = new ArrayList();
		
		try {
			// 新品6个
			list1 = goodsService.Find_Rand(0, "", 0, 6);
			// 热销6个
			list2 = goodsService.Find_Rand(0, "", 0, 6);
			// 便利生活3个
			list3 = goodsService.Find_Sort_Parent_Rand(1, "", 0, 3);
			// 农科专供3个
			list4 = goodsService.Find_Sort_Parent_Rand(2, "", 0, 3);
			// 精品生鲜3个
			list5 = goodsService.Find_Sort_Parent_Rand(3, "", 0, 3);
			// 粮食副油3个
			list6 = goodsService.Find_Sort_Parent_Rand(4, "", 0, 3);
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		int count = 0;
		for (int i = 0; i < Math.min(6, list1.size()); i++) {
			Goods goods = (Goods) list1.get(i);
			
			a_Goods_2 a_goods_2 = new a_Goods_2();
			a_goods_2.setGoodPrice(goods.getMoneyNew());
			a_goods_2.setGoodsHtmlUrl(goods.getGoodsHtmlUrl());
			a_goods_2.setGoodsId(goods.getGoodsId());
			a_goods_2.setGoodsName(goods.getGoodsName());
			a_goods_2.setImg1(goods.getImg2());//新品上市及热销榜单：pic_home_first_grid 120*120  {0-5 6-11}
			
			list.add(a_goods_2);
			logger.info(count++ + "自动更新移动端首页数据：" + a_goods_2.getGoodsId());
		}
		for (int i = 0; i < Math.min(6, list2.size()); i++) {
			Goods goods = (Goods) list2.get(i);
			
			a_Goods_2 a_goods_2 = new a_Goods_2();
			a_goods_2.setGoodPrice(goods.getMoneyNew());
			a_goods_2.setGoodsHtmlUrl(goods.getGoodsHtmlUrl());
			a_goods_2.setGoodsId(goods.getGoodsId());
			a_goods_2.setGoodsName(goods.getGoodsName());
			a_goods_2.setImg1(goods.getImg2());//新品上市及热销榜单：pic_home_first_grid 120*120  {0-5 6-11}
			
			list.add(a_goods_2);
			logger.info(count++ + "自动更新移动端首页数据：" + a_goods_2.getGoodsId());
		}
		for (int i = 0; i < Math.min(3, list3.size()); i++) {
			Goods goods = (Goods) list3.get(i);
			
			a_Goods_2 a_goods_2 = new a_Goods_2();
			a_goods_2.setGoodPrice(goods.getMoneyNew());
			a_goods_2.setGoodsHtmlUrl(goods.getGoodsHtmlUrl());
			a_goods_2.setGoodsId(goods.getGoodsId());
			a_goods_2.setGoodsName(goods.getGoodsName());
			
			if (i == 0) {
				a_goods_2.setImg1(goods.getImg3());//分类左中图：pic_home_second_left 470*590 {12,15,18,21}
			}
			else if (i == 1) {
				a_goods_2.setImg1(goods.getImg4());//分类右上图：pic_home_second_right_top 590*300 {13,16,19,22}
			}
			else {
				a_goods_2.setImg1(goods.getImg5());//分类右下图：pic_home_second_bottom_top 590*300  {14,17,20,23}
			}
			
			list.add(a_goods_2);
			logger.info(count++ + "自动更新移动端首页数据：" + a_goods_2.getGoodsId());
		}
		for (int i = 0; i < Math.min(3, list4.size()); i++) {
			Goods goods = (Goods) list4.get(i);
			
			a_Goods_2 a_goods_2 = new a_Goods_2();
			a_goods_2.setGoodPrice(goods.getMoneyNew());
			a_goods_2.setGoodsHtmlUrl(goods.getGoodsHtmlUrl());
			a_goods_2.setGoodsId(goods.getGoodsId());
			a_goods_2.setGoodsName(goods.getGoodsName());
			
			if (i == 0) {
				a_goods_2.setImg1(goods.getImg3());//分类左中图：pic_home_second_left 470*590 {12,15,18,21}
			}
			else if (i == 1) {
				a_goods_2.setImg1(goods.getImg4());//分类右上图：pic_home_second_right_top 590*300 {13,16,19,22}
			}
			else {
				a_goods_2.setImg1(goods.getImg5());//分类右下图：pic_home_second_bottom_top 590*300  {14,17,20,23}
			}
			
			list.add(a_goods_2);
			logger.info(count++ + "自动更新移动端首页数据：" + a_goods_2.getGoodsId());
		}
		for (int i = 0; i < Math.min(3, list5.size()); i++) {
			Goods goods = (Goods) list5.get(i);
			
			a_Goods_2 a_goods_2 = new a_Goods_2();
			a_goods_2.setGoodPrice(goods.getMoneyNew());
			a_goods_2.setGoodsHtmlUrl(goods.getGoodsHtmlUrl());
			a_goods_2.setGoodsId(goods.getGoodsId());
			a_goods_2.setGoodsName(goods.getGoodsName());
			
			if (i == 0) {
				a_goods_2.setImg1(goods.getImg3());//分类左中图：pic_home_second_left 470*590 {12,15,18,21}
			}
			else if (i == 1) {
				a_goods_2.setImg1(goods.getImg4());//分类右上图：pic_home_second_right_top 590*300 {13,16,19,22}
			}
			else {
				a_goods_2.setImg1(goods.getImg5());//分类右下图：pic_home_second_bottom_top 590*300  {14,17,20,23}
			}
			
			list.add(a_goods_2);
			logger.info(count++ + "自动更新移动端首页数据：" + a_goods_2.getGoodsId());
		}
		for (int i = 0; i < Math.min(3, list6.size()); i++) {
			Goods goods = (Goods) list6.get(i);
			
			a_Goods_2 a_goods_2 = new a_Goods_2();
			a_goods_2.setGoodPrice(goods.getMoneyNew());
			a_goods_2.setGoodsHtmlUrl(goods.getGoodsHtmlUrl());
			a_goods_2.setGoodsId(goods.getGoodsId());
			a_goods_2.setGoodsName(goods.getGoodsName());
			
			if (i == 0) {
				a_goods_2.setImg1(goods.getImg3());//分类左中图：pic_home_second_left 470*590 {12,15,18,21}
			}
			else if (i == 1) {
				a_goods_2.setImg1(goods.getImg4());//分类右上图：pic_home_second_right_top 590*300 {13,16,19,22}
			}
			else {
				a_goods_2.setImg1(goods.getImg5());//分类右下图：pic_home_second_bottom_top 590*300  {14,17,20,23}
			}
			
			list.add(a_goods_2);
			logger.info(count++ + "自动更新移动端首页数据：" + a_goods_2.getGoodsId());
		}
		
		for (int i = list.size(); i < msg.homepage_size; i++) {
			a_Goods_2 a_goods_2 = (a_Goods_2) list.get(0);
			list.add(a_goods_2);
			logger.info(list.size() - 1 + "自动补移动端首页数据:" + a_goods_2.getGoodsId());
		}
		
		message = msg.goods_success;
		status = msg.status_0;
		map.put("status", status);
		map.put("message", message);
		
		data = gson.toJson(list);
		map.put("data", data);
		
		mapdata = gson.toJson(map);
		
		Task_SetHomePage.setMap_data(mapdata);
		
		test.a(list.size());
		test.a("设置移动端主页结束. . .");
		
		return;
	}
	
	/**
	 * 初始连接
	 * 
	 */
	public static void initConnect() {
		String url = msg.root_url + "/welcome.action";
		String param = null;
		try {
			URL realurl = new URL(url + "?" + param);
			URLConnection connection = realurl.openConnection();
			connection.setRequestProperty("accept", "*/*");
			connection.setRequestProperty("connection", "Keep-Alive");
			connection.setRequestProperty("user-agent", "Mozilla/4.0 (compatible; MSIE 6.0; Windows NT 5.1;SV1)");
			connection.connect();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	
	public GoodsService getGoodsService() {
		return goodsService;
	}
	
	public void setGoodsService(GoodsService goodsService) {
		this.goodsService = goodsService;
	}
	
	public static String getMap_data() {
		return map_data;
	}
	
	public static void setMap_data(String map_data) {
		Task_SetHomePage.map_data = map_data;
	}
	
}
