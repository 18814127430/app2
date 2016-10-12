package utils;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStreamWriter;

import org.apache.log4j.Logger;
import org.apache.struts2.ServletActionContext;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;

import bean.Goods;
import bean.Page;
import bean.Sort;

/**
 * Goods工具类
 * 
 * @author
 * 
 */
public class goodsTool {
	private static final Logger log = Logger.getLogger(goodsTool.class);
	
	public static Goods formatPath(Goods goods) {
		
		if (goods.getImg1() != null && goods.getImg1().indexOf("\\") >= 0) {
			test.a(goods.getImg1());
			goods.setImg1(goods.getImg1().replaceAll("\\\\", "/"));
		}
		if (goods.getImg2() != null && goods.getImg2().indexOf("\\") >= 0) {
			test.a(goods.getImg2());
			goods.setImg2(goods.getImg2().replaceAll("\\\\", "/"));
		}
		if (goods.getImg3() != null && goods.getImg3().indexOf("\\") >= 0) {
			test.a(goods.getImg3());
			goods.setImg3(goods.getImg3().replaceAll("\\\\", "/"));
		}
		if (goods.getImg4() != null && goods.getImg4().indexOf("\\") >= 0) {
			test.a(goods.getImg4());
			goods.setImg4(goods.getImg4().replaceAll("\\\\", "/"));
		}
		if (goods.getImg5() != null && goods.getImg5().indexOf("\\") >= 0) {
			test.a(goods.getImg5());
			goods.setImg5(goods.getImg5().replaceAll("\\\\", "/"));
		}
		if (goods.getImg6() != null && goods.getImg6().indexOf("\\") >= 0) {
			test.a(goods.getImg6());
			goods.setImg6(goods.getImg6().replaceAll("\\\\", "/"));
		}
		if (goods.getImg7() != null && goods.getImg7().indexOf("\\") >= 0) {
			test.a(goods.getImg7());
			goods.setImg7(goods.getImg7().replaceAll("\\\\", "/"));
		}
		if (goods.getImg8() != null && goods.getImg8().indexOf("\\") >= 0) {
			test.a(goods.getImg8());
			goods.setImg8(goods.getImg8().replaceAll("\\\\", "/"));
		}
		if (goods.getImg9() != null && goods.getImg9().indexOf("\\") >= 0) {
			test.a(goods.getImg9());
			goods.setImg9(goods.getImg9().replaceAll("\\\\", "/"));
		}
		if (goods.getImg10() != null && goods.getImg10().indexOf("\\") >= 0) {
			test.a(goods.getImg10());
			goods.setImg10(goods.getImg10().replaceAll("\\\\", "/"));
		}
		if (goods.getImg11() != null && goods.getImg11().indexOf("\\") >= 0) {
			test.a(goods.getImg11());
			goods.setImg11(goods.getImg11().replaceAll("\\\\", "/"));
		}
		if (goods.getImg12() != null && goods.getImg12().indexOf("\\") >= 0) {
			test.a(goods.getImg12());
			goods.setImg12(goods.getImg12().replaceAll("\\\\", "/"));
		}
		if (goods.getImg13() != null && goods.getImg13().indexOf("\\") >= 0) {
			test.a(goods.getImg13());
			goods.setImg13(goods.getImg13().replaceAll("\\\\", "/"));
		}
		if (goods.getImg14() != null && goods.getImg14().indexOf("\\") >= 0) {
			test.a(goods.getImg14());
			goods.setImg14(goods.getImg14().replaceAll("\\\\", "/"));
		}
		if (goods.getImg15() != null && goods.getImg15().indexOf("\\") >= 0) {
			test.a(goods.getImg15());
			goods.setImg15(goods.getImg15().replaceAll("\\\\", "/"));
		}
		if (goods.getImg16() != null && goods.getImg16().indexOf("\\") >= 0) {
			test.a(goods.getImg16());
			goods.setImg16(goods.getImg16().replaceAll("\\\\", "/"));
		}
		if (goods.getGoodsHtmlUrl().indexOf("\\") >= 0) {
			test.a(goods.getGoodsHtmlUrl());
			goods.setGoodsHtmlUrl(goods.getGoodsHtmlUrl().replaceAll("\\\\", "/"));
		}
		
		return goods;
	}
	
	/**
	 * 删除添加失败、更新失败goods的文件
	 * 
	 * @param goods
	 */
	public static void DeleteImgHtml_All(Goods goods) {
		
		String savePath = ServletActionContext.getServletContext().getRealPath("/") + "attached/";
		test.a("===========================删除添加失败、更新失败goods的文件 begin==============================");
		
		int count = 0;
		if (goods.getGoodsHtmlUrl() != null && !goods.getGoodsHtmlUrl().equals("") && goods.getImg1().indexOf("model") < 0) {
			String test_imgpath1 = goods.getGoodsHtmlUrl().substring(goods.getGoodsHtmlUrl().lastIndexOf("attached") + 9);
			String test_imgpath0 = savePath + test_imgpath1;
			File file = new File(test_imgpath0);
			if (file.exists() && !file.getName().equals("model.html")) {
				test.a(count++ + "------>deleted:" + file.getAbsolutePath());
				test.DeleteFile(file.getAbsolutePath());
			}
		}
		if (goods.getImg1() != null && !goods.getImg1().equals("") && goods.getImg1().indexOf("model") < 0) {
			String test_imgpath1 = goods.getImg1().substring(goods.getImg1().lastIndexOf("image"));
			String test_imgpath0 = savePath + test_imgpath1;
			File file = new File(test_imgpath0);
			if (file.exists()) {
				test.a(count++ + "------>deleted:" + file.getAbsolutePath());
				test.DeleteFile(file.getAbsolutePath());
			}
		}
		if (goods.getImg2() != null && !goods.getImg2().equals("") && goods.getImg2().indexOf("model") < 0) {
			String test_imgpath2 = goods.getImg2().substring(goods.getImg2().lastIndexOf("image"));
			String test_imgpath0 = savePath + test_imgpath2;
			File file = new File(test_imgpath0);
			if (file.exists()) {
				test.a(count++ + "------>deleted:" + file.getAbsolutePath());
				test.DeleteFile(file.getAbsolutePath());
			}
		}
		if (goods.getImg3() != null && !goods.getImg3().equals("") && goods.getImg3().indexOf("model") < 0) {
			String test_imgpath3 = goods.getImg3().substring(goods.getImg3().lastIndexOf("image"));
			String test_imgpath0 = savePath + test_imgpath3;
			File file = new File(test_imgpath0);
			if (file.exists()) {
				test.a(count++ + "------>deleted:" + file.getAbsolutePath());
				test.DeleteFile(file.getAbsolutePath());
			}
		}
		if (goods.getImg4() != null && !goods.getImg4().equals("") && goods.getImg4().indexOf("model") < 0) {
			String test_imgpath4 = goods.getImg4().substring(goods.getImg4().lastIndexOf("image"));
			String test_imgpath0 = savePath + test_imgpath4;
			File file = new File(test_imgpath0);
			if (file.exists()) {
				test.a(count++ + "------>deleted:" + file.getAbsolutePath());
				test.DeleteFile(file.getAbsolutePath());
			}
		}
		if (goods.getImg5() != null && !goods.getImg5().equals("") && goods.getImg5().indexOf("model") < 0) {
			String test_imgpath5 = goods.getImg5().substring(goods.getImg5().lastIndexOf("image"));
			String test_imgpath0 = savePath + test_imgpath5;
			File file = new File(test_imgpath0);
			if (file.exists()) {
				test.a(count++ + "------>deleted:" + file.getAbsolutePath());
				test.DeleteFile(file.getAbsolutePath());
			}
		}
		if (goods.getImg6() != null && !goods.getImg6().equals("") && goods.getImg8().indexOf("model") < 0) {
			String test_imgpath6 = goods.getImg6().substring(goods.getImg6().lastIndexOf("image"));
			String test_imgpath0 = savePath + test_imgpath6;
			File file = new File(test_imgpath0);
			if (file.exists()) {
				test.a(count++ + "------>deleted:" + file.getAbsolutePath());
				test.DeleteFile(file.getAbsolutePath());
			}
		}
		if (goods.getImg7() != null && !goods.getImg7().equals("") && goods.getImg7().indexOf("model") < 0) {
			String test_imgpath7 = goods.getImg7().substring(goods.getImg7().lastIndexOf("image"));
			String test_imgpath0 = savePath + test_imgpath7;
			File file = new File(test_imgpath0);
			if (file.exists()) {
				test.a(count++ + "------>deleted:" + file.getAbsolutePath());
				test.DeleteFile(file.getAbsolutePath());
			}
		}
		if (goods.getImg8() != null && !goods.getImg8().equals("") && goods.getImg8().indexOf("model") < 0) {
			String test_imgpath8 = goods.getImg8().substring(goods.getImg8().lastIndexOf("image"));
			String test_imgpath0 = savePath + test_imgpath8;
			File file = new File(test_imgpath0);
			if (file.exists()) {
				test.a(count++ + "------>deleted:" + file.getAbsolutePath());
				test.DeleteFile(file.getAbsolutePath());
			}
		}
		if (goods.getImg9() != null && !goods.getImg9().equals("") && goods.getImg9().indexOf("model") < 0) {
			String test_imgpath9 = goods.getImg9().substring(goods.getImg9().lastIndexOf("image"));
			String test_imgpath0 = savePath + test_imgpath9;
			File file = new File(test_imgpath0);
			if (file.exists()) {
				test.a(count++ + "------>deleted:" + file.getAbsolutePath());
				test.DeleteFile(file.getAbsolutePath());
			}
		}
		if (goods.getImg10() != null && !goods.getImg10().equals("") && goods.getImg10().indexOf("model") < 0) {
			String test_imgpath10 = goods.getImg10().substring(goods.getImg10().lastIndexOf("image"));
			String test_imgpath0 = savePath + test_imgpath10;
			File file = new File(test_imgpath0);
			if (file.exists()) {
				test.a(count++ + "------>deleted:" + file.getAbsolutePath());
				test.DeleteFile(file.getAbsolutePath());
			}
		}
		if (goods.getImg11() != null && !goods.getImg11().equals("") && goods.getImg11().indexOf("model") < 0) {
			String test_imgpath11 = goods.getImg11().substring(goods.getImg11().lastIndexOf("image"));
			String test_imgpath0 = savePath + test_imgpath11;
			File file = new File(test_imgpath0);
			if (file.exists()) {
				test.a(count++ + "------>deleted:" + file.getAbsolutePath());
				test.DeleteFile(file.getAbsolutePath());
			}
		}
		if (goods.getImg12() != null && !goods.getImg12().equals("") && goods.getImg12().indexOf("model") < 0) {
			String test_imgpath12 = goods.getImg12().substring(goods.getImg12().lastIndexOf("image"));
			String test_imgpath0 = savePath + test_imgpath12;
			File file = new File(test_imgpath0);
			if (file.exists()) {
				test.a(count++ + "------>deleted:" + file.getAbsolutePath());
				test.DeleteFile(file.getAbsolutePath());
			}
		}
		if (goods.getImg13() != null && !goods.getImg13().equals("") && goods.getImg13().indexOf("model") < 0) {
			String test_imgpath1 = goods.getImg13().substring(goods.getImg13().lastIndexOf("image"));
			String test_imgpath0 = savePath + test_imgpath1;
			File file = new File(test_imgpath0);
			if (file.exists()) {
				test.a(count++ + "------>deleted:" + file.getAbsolutePath());
				test.DeleteFile(file.getAbsolutePath());
			}
		}
		if (goods.getImg14() != null && !goods.getImg14().equals("") && goods.getImg14().indexOf("model") < 0) {
			String test_imgpath1 = goods.getImg14().substring(goods.getImg14().lastIndexOf("image"));
			String test_imgpath0 = savePath + test_imgpath1;
			File file = new File(test_imgpath0);
			if (file.exists()) {
				test.a(count++ + "------>deleted:" + file.getAbsolutePath());
				test.DeleteFile(file.getAbsolutePath());
			}
		}
		if (goods.getImg15() != null && !goods.getImg15().equals("") && goods.getImg15().indexOf("model") < 0) {
			String test_imgpath1 = goods.getImg15().substring(goods.getImg15().lastIndexOf("image"));
			String test_imgpath0 = savePath + test_imgpath1;
			File file = new File(test_imgpath0);
			if (file.exists()) {
				test.a(count++ + "------>deleted:" + file.getAbsolutePath());
				test.DeleteFile(file.getAbsolutePath());
			}
		}
		if (goods.getImg16() != null && !goods.getImg16().equals("") && goods.getImg16().indexOf("model") < 0) {
			String test_imgpath1 = goods.getImg16().substring(goods.getImg16().lastIndexOf("image"));
			String test_imgpath0 = savePath + test_imgpath1;
			File file = new File(test_imgpath0);
			if (file.exists()) {
				test.a(count++ + "------>deleted:" + file.getAbsolutePath());
				test.DeleteFile(file.getAbsolutePath());
			}
		}
		test.a("===========================删除添加失败、更新失败goods的文件 end==============================");
	}
	
	/**
	 * 删除更新成功goods的旧文件
	 * 
	 * @param old_goods
	 * @param new_goods
	 */
	public static void DeleteImgHtml_Some(Goods old_goods, Goods new_goods) {
		
		String savePath = ServletActionContext.getServletContext().getRealPath("/") + "attached/";
		test.a("========================删除更新成功goods的旧文件 Begin==============================");
		
		int count = 0;
		if (old_goods.getGoodsHtmlUrl() != null && !old_goods.getGoodsHtmlUrl().equals("")
				&& !old_goods.getGoodsHtmlUrl().equals(new_goods.getGoodsHtmlUrl())) {
			String test_imgpath1 = old_goods.getGoodsHtmlUrl().substring(
					old_goods.getGoodsHtmlUrl().lastIndexOf("attached") + 9);
			String test_imgpath0 = savePath + test_imgpath1;
			count++;
			File file = new File(test_imgpath0);
			if (file.exists() && !file.getName().equals("model.html")) {
				test.a(count + "------>deleted:" + file.getAbsolutePath());
				test.DeleteFile(file.getAbsolutePath());
			}
		}
		if (old_goods.getImg1() != null && !old_goods.getImg1().equals("")
				&& !old_goods.getImg1().equals(new_goods.getImg1()) && old_goods.getImg1().indexOf("model") < 0) {
			String test_imgpath1 = old_goods.getImg1().substring(old_goods.getImg1().lastIndexOf("image"));
			String test_imgpath0 = savePath + test_imgpath1;
			count++;
			File file = new File(test_imgpath0);
			if (file.exists()) {
				test.a(count + "------>deleted:" + file.getAbsolutePath());
				test.DeleteFile(file.getAbsolutePath());
			}
		}
		if (old_goods.getImg2() != null && !old_goods.getImg2().equals("")
				&& !old_goods.getImg2().equals(new_goods.getImg2()) && old_goods.getImg2().indexOf("model") < 0) {
			String test_imgpath2 = old_goods.getImg2().substring(old_goods.getImg2().lastIndexOf("image"));
			String test_imgpath0 = savePath + test_imgpath2;
			count++;
			File file = new File(test_imgpath0);
			if (file.exists()) {
				test.a(count + "------>deleted:" + file.getAbsolutePath());
				test.DeleteFile(file.getAbsolutePath());
			}
		}
		if (old_goods.getImg3() != null && !old_goods.getImg3().equals("")
				&& !old_goods.getImg3().equals(new_goods.getImg3()) && old_goods.getImg3().indexOf("model") < 0) {
			String test_imgpath3 = old_goods.getImg3().substring(old_goods.getImg3().lastIndexOf("image"));
			String test_imgpath0 = savePath + test_imgpath3;
			count++;
			File file = new File(test_imgpath0);
			if (file.exists()) {
				test.a(count + "------>deleted:" + file.getAbsolutePath());
				test.DeleteFile(file.getAbsolutePath());
			}
		}
		if (old_goods.getImg4() != null && !old_goods.getImg4().equals("")
				&& !old_goods.getImg4().equals(new_goods.getImg4()) && old_goods.getImg4().indexOf("model") < 0) {
			String test_imgpath4 = old_goods.getImg4().substring(old_goods.getImg4().lastIndexOf("image"));
			String test_imgpath0 = savePath + test_imgpath4;
			count++;
			File file = new File(test_imgpath0);
			if (file.exists()) {
				test.a(count + "------>deleted:" + file.getAbsolutePath());
				test.DeleteFile(file.getAbsolutePath());
			}
		}
		if (old_goods.getImg5() != null && !old_goods.getImg5().equals("")
				&& !old_goods.getImg5().equals(new_goods.getImg5()) && old_goods.getImg5().indexOf("model") < 0) {
			String test_imgpath5 = old_goods.getImg5().substring(old_goods.getImg5().lastIndexOf("image"));
			String test_imgpath0 = savePath + test_imgpath5;
			count++;
			File file = new File(test_imgpath0);
			if (file.exists()) {
				test.a(count + "------>deleted:" + file.getAbsolutePath());
				test.DeleteFile(file.getAbsolutePath());
			}
		}
		if (old_goods.getImg6() != null && !old_goods.getImg6().equals("")
				&& !old_goods.getImg6().equals(new_goods.getImg6()) && old_goods.getImg6().indexOf("model") < 0) {
			String test_imgpath6 = old_goods.getImg6().substring(old_goods.getImg6().lastIndexOf("image"));
			String test_imgpath0 = savePath + test_imgpath6;
			count++;
			File file = new File(test_imgpath0);
			if (file.exists()) {
				test.a(count + "------>deleted:" + file.getAbsolutePath());
				test.DeleteFile(file.getAbsolutePath());
			}
		}
		if (old_goods.getImg7() != null && !old_goods.getImg7().equals("")
				&& !old_goods.getImg7().equals(new_goods.getImg7()) && old_goods.getImg7().indexOf("model") < 0) {
			String test_imgpath7 = old_goods.getImg7().substring(old_goods.getImg7().lastIndexOf("image"));
			String test_imgpath0 = savePath + test_imgpath7;
			count++;
			File file = new File(test_imgpath0);
			if (file.exists()) {
				test.a(count + "------>deleted:" + file.getAbsolutePath());
				test.DeleteFile(file.getAbsolutePath());
			}
		}
		if (old_goods.getImg8() != null && !old_goods.getImg8().equals("")
				&& !old_goods.getImg8().equals(new_goods.getImg8()) && old_goods.getImg8().indexOf("model") < 0) {
			String test_imgpath8 = old_goods.getImg8().substring(old_goods.getImg8().lastIndexOf("image"));
			String test_imgpath0 = savePath + test_imgpath8;
			count++;
			File file = new File(test_imgpath0);
			if (file.exists()) {
				test.a(count + "------>deleted:" + file.getAbsolutePath());
				test.DeleteFile(file.getAbsolutePath());
			}
		}
		if (old_goods.getImg9() != null && !old_goods.getImg9().equals("")
				&& !old_goods.getImg9().equals(new_goods.getImg9()) && old_goods.getImg9().indexOf("model") < 0) {
			String test_imgpath9 = old_goods.getImg9().substring(old_goods.getImg9().lastIndexOf("image"));
			String test_imgpath0 = savePath + test_imgpath9;
			count++;
			File file = new File(test_imgpath0);
			if (file.exists()) {
				test.a(count + "------>deleted:" + file.getAbsolutePath());
				test.DeleteFile(file.getAbsolutePath());
			}
		}
		if (old_goods.getImg10() != null && !old_goods.getImg10().equals("")
				&& !old_goods.getImg10().equals(new_goods.getImg10()) && old_goods.getImg10().indexOf("model") < 0) {
			String test_imgpath10 = old_goods.getImg10().substring(old_goods.getImg10().lastIndexOf("image"));
			String test_imgpath0 = savePath + test_imgpath10;
			count++;
			File file = new File(test_imgpath0);
			if (file.exists()) {
				test.a(count + "------>deleted:" + file.getAbsolutePath());
				test.DeleteFile(file.getAbsolutePath());
			}
		}
		if (old_goods.getImg11() != null && !old_goods.getImg11().equals("")
				&& !old_goods.getImg11().equals(new_goods.getImg11()) && old_goods.getImg11().indexOf("model") < 0) {
			String test_imgpath11 = old_goods.getImg11().substring(old_goods.getImg11().lastIndexOf("image"));
			String test_imgpath0 = savePath + test_imgpath11;
			count++;
			File file = new File(test_imgpath0);
			if (file.exists()) {
				test.a(count + "------>deleted:" + file.getAbsolutePath());
				test.DeleteFile(file.getAbsolutePath());
			}
		}
		if (old_goods.getImg12() != null && !old_goods.getImg12().equals("")
				&& !old_goods.getImg12().equals(new_goods.getImg12()) && old_goods.getImg12().indexOf("model") < 0) {
			String test_imgpath12 = old_goods.getImg12().substring(old_goods.getImg12().lastIndexOf("image"));
			String test_imgpath0 = savePath + test_imgpath12;
			count++;
			File file = new File(test_imgpath0);
			if (file.exists()) {
				test.a(count + "------>deleted:" + file.getAbsolutePath());
				test.DeleteFile(file.getAbsolutePath());
			}
		}
		if (old_goods.getImg13() != null && !old_goods.getImg13().equals("")
				&& !old_goods.getImg13().equals(new_goods.getImg13()) && old_goods.getImg13().indexOf("model") < 0) {
			String test_imgpath1 = old_goods.getImg13().substring(old_goods.getImg13().lastIndexOf("image"));
			String test_imgpath0 = savePath + test_imgpath1;
			count++;
			File file = new File(test_imgpath0);
			if (file.exists()) {
				test.a(count + "------>deleted:" + file.getAbsolutePath());
				test.DeleteFile(file.getAbsolutePath());
			}
		}
		if (old_goods.getImg14() != null && !old_goods.getImg14().equals("")
				&& !old_goods.getImg14().equals(new_goods.getImg14()) && old_goods.getImg14().indexOf("model") < 0) {
			String test_imgpath1 = old_goods.getImg14().substring(old_goods.getImg14().lastIndexOf("image"));
			String test_imgpath0 = savePath + test_imgpath1;
			count++;
			File file = new File(test_imgpath0);
			if (file.exists()) {
				test.a(count + "------>deleted:" + file.getAbsolutePath());
				test.DeleteFile(file.getAbsolutePath());
			}
		}
		if (old_goods.getImg15() != null && !old_goods.getImg15().equals("")
				&& !old_goods.getImg15().equals(new_goods.getImg15()) && old_goods.getImg15().indexOf("model") < 0) {
			String test_imgpath1 = old_goods.getImg15().substring(old_goods.getImg15().lastIndexOf("image"));
			String test_imgpath0 = savePath + test_imgpath1;
			count++;
			File file = new File(test_imgpath0);
			if (file.exists()) {
				test.a(count + "------>deleted:" + file.getAbsolutePath());
				test.DeleteFile(file.getAbsolutePath());
			}
		}
		if (old_goods.getImg16() != null && !old_goods.getImg16().equals("")
				&& !old_goods.getImg16().equals(new_goods.getImg16()) && old_goods.getImg16().indexOf("model") < 0) {
			String test_imgpath1 = old_goods.getImg16().substring(old_goods.getImg16().lastIndexOf("image"));
			String test_imgpath0 = savePath + test_imgpath1;
			count++;
			File file = new File(test_imgpath0);
			if (file.exists()) {
				test.a(count + "------>deleted:" + file.getAbsolutePath());
				test.DeleteFile(file.getAbsolutePath());
			}
		}
		test.a("========================删除更新成功goods的旧文件 End==============================");
	}
	
	/**
	 * 自动生成html网页
	 * 
	 * @param goods
	 * @return
	 * @throws IOException
	 */
	public static String createHtml(Goods goods) throws IOException {
		
		System.out
				.println("==================================create new html begin=========================================");
		String path = ServletActionContext.getServletContext().getRealPath("/attached");
		String savePath = ServletActionContext.getServletContext().getRealPath("/") + "attached/";
		String saveUrl = ServletActionContext.getRequest().getContextPath() + "/attached/";
		String test_imgpath1 = goods.getImg1().substring(goods.getImg1().lastIndexOf("image"));
		String test_imgpath0 = savePath + test_imgpath1;
		// path:C:\Users\10919\Workspaces\MyEclipse Professional 2014\.metadata\.me_tcat7\webapps\app2\attached
		// savePath:C:\Users\10919\Workspaces\MyEclipse Professional 2014\.metadata\.me_tcat7\webapps\app2\attached/
		// saveUrl:/app2/attached/
		// test_imgpath1:image/20160730/20160730155256_183.jpg
		// test_imgpath0:C:\Users\10919\Workspaces\MyEclipse Professional 2014\.metadata\.me_tcat7\webapps\app2\attached/image/20160730/20160730155256_183.jpg
		// test_imgpath0:C:\Users\10919\Workspaces\MyEclipse Professional 2014\.metadata\.me_tcat7\webapps\app2\attached\image\20160730\20160730155256_183.jpg
		
		String new_html_name = test.NameByTime() + "_" + goods.getGoodsName() + "_.html";
		String new_html_path = savePath + new_html_name;
		File new_html_file = new File(new_html_path);
		if (!new_html_file.exists()) {
			new_html_file.createNewFile();
		}
		
		String model_name = "model.html";
		String model_path = savePath + model_name;
		File model_html_file = new File(model_path);
		
		test.CopyFile(model_html_file, new_html_file);
		
		Document doc = Jsoup.parse(new_html_file, "UTF-8");
		
		// ID=img img list
		int count = 0;
		if (goods.getImg9() != null && !goods.getImg9().equals("")) {
			String html_imgpath = goods.getImg9().substring(goods.getImg9().lastIndexOf("image"));
			Element ele = doc.getElementById("img1");
			ele.attr("src", html_imgpath);
			test.a(count++ + "------------>ele:" + ele);
		}
		if (goods.getImg10() != null && !goods.getImg10().equals("")) {
			String html_imgpath = goods.getImg10().substring(goods.getImg10().lastIndexOf("image"));
			Element ele = doc.getElementById("img2");
			ele.attr("src", html_imgpath);
			test.a(count++ + "------------>ele:" + ele);
		}
		if (goods.getImg11() != null && !goods.getImg11().equals("")) {
			String html_imgpath = goods.getImg11().substring(goods.getImg11().lastIndexOf("image"));
			Element ele = doc.getElementById("img3");
			ele.attr("src", html_imgpath);
			test.a(count++ + "------------>ele:" + ele);
		}
		if (goods.getImg12() != null && !goods.getImg12().equals("")) {
			String html_imgpath = goods.getImg12().substring(goods.getImg12().lastIndexOf("image"));
			Element ele = doc.getElementById("img4");
			ele.attr("src", html_imgpath);
			test.a(count++ + "------------>ele:" + ele);
		}
		if (goods.getImg13() != null && !goods.getImg13().equals("")) {
			String html_imgpath = goods.getImg13().substring(goods.getImg13().lastIndexOf("image"));
			Element ele = doc.getElementById("img5");
			ele.attr("src", html_imgpath);
			test.a(count++ + "------------>ele:" + ele);
		}
		else {
			Element ele = doc.getElementById("img5");
			ele.remove();
		}
		if (goods.getImg14() != null && !goods.getImg14().equals("")) {
			String html_imgpath = goods.getImg14().substring(goods.getImg14().lastIndexOf("image"));
			Element ele = doc.getElementById("img6");
			ele.attr("src", html_imgpath);
			test.a(count++ + "------------>ele:" + ele);
		}
		else {
			Element ele = doc.getElementById("img6");
			ele.remove();
		}
		if (goods.getImg15() != null && !goods.getImg15().equals("")) {
			String html_imgpath = goods.getImg15().substring(goods.getImg15().lastIndexOf("image"));
			Element ele = doc.getElementById("img7");
			ele.attr("src", html_imgpath);
			test.a(count++ + "------------>ele:" + ele);
		}
		else {
			Element ele = doc.getElementById("img7");
			ele.remove();
		}
		if (goods.getImg16() != null && !goods.getImg16().equals("")) {
			String html_imgpath = goods.getImg16().substring(goods.getImg16().lastIndexOf("image"));
			Element ele = doc.getElementById("img8");
			ele.attr("src", html_imgpath);
			test.a(count++ + "------------>ele:" + ele);
		}
		else {
			Element ele = doc.getElementById("img8");
			ele.remove();
		}
		
		if (goods.getGoodsDescription() != null && !goods.getGoodsDescription().equals("")) {
			Element ele = doc.getElementById("create2");
			ele.text(goods.getGoodsDescription());
			test.a(count++ + "------------>ele:" + ele);
		}
		
		FileOutputStream fos = new FileOutputStream(new_html_file, false);// 这将创建一个输出文件流写入到具有指定名称的文件并且覆盖(false)
		OutputStreamWriter osw = new OutputStreamWriter(fos, "utf8");
		osw.write(doc.html());
		osw.close();
		fos.close();
		
		String htmlname = new_html_file.getName();
		test.a("new html:" + new_html_file.getAbsolutePath());
		test.a("return name:" + htmlname);
		System.out
				.println("==================================create new html end=========================================");
		return htmlname;
	}
}
