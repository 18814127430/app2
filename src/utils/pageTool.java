package utils;

import java.io.File;
import java.io.IOException;
import java.util.Random;

import net.coobird.thumbnailator.Thumbnails;

import org.apache.log4j.Logger;
import org.apache.struts2.ServletActionContext;

import bean.Page;

/**
 * Page工具类
 * 
 * @author
 * 
 */
public class pageTool {
	private static final Logger log = Logger.getLogger(pageTool.class);
	
	/**
	 * 按照像素要求更新page的图片文件
	 * 
	 * @param goods
	 * @return
	 * @throws IOException
	 */
	public static Page SetPageImgSize(Page newpage) throws IOException {
		System.out.println("===========================按照像素要求更新page的图片文件 begin==============================");
		
		String savePath = ServletActionContext.getServletContext().getRealPath("/") + "attached/";
		int count = 0;
		Page temppage = newpage;
		
		if (newpage.getPageImg() != null && !newpage.getPageImg().equals("") && newpage.getPageImg().indexOf("model") < 0) {
			
			String img = newpage.getPageImg();
			String imgpath = savePath + img.substring(img.lastIndexOf("image"));
			File file = new File(imgpath);
			
			String[] str = msg.defaultImgPage.split(",");
			int width = Integer.parseInt(str[0]);
			int height = Integer.parseInt(str[1]);
			
			if (file.exists()) {
				
				String newimg = img.replace(file.getName(), test.NameByTime() + "_" + new Random().nextInt(1000) + "_轮播图."
						+ test.GetFileExtension(file));
				String newimgpath = savePath + newimg.substring(newimg.lastIndexOf("image"));
				
				test.a(count + "----->zooms:" + ThumbnailatorTest.getImgWidth(imgpath) + "*"
						+ ThumbnailatorTest.getImgHeight(imgpath));
				test.a(count + "----->path:" + img);
				
				Thumbnails.of(imgpath).size(width, height).toFile(newimgpath);
				
				test.a(count + "----->zooms:" + ThumbnailatorTest.getImgWidth(newimgpath) + "*"
						+ ThumbnailatorTest.getImgHeight(newimgpath));
				test.a(count + "----->path:" + newimg);
				
				if (ThumbnailatorTest.getImgWidth(newimgpath) < width) {
					ThumbnailatorTest.buBai(newimgpath, newimgpath, width, height);
				}
				
				temppage.setPageImg(newimg);
				test.DeleteFile(imgpath);
			}
		}
		System.out.println("===========================按照像素要求更新page的图片文件 end==============================");
		return temppage;
	}
	
	/**
	 * 删除添加失败、更新失败page的文件
	 * 
	 * @param page
	 */
	public static void DeleteImg_New(Page page) {
		
		String savePath = ServletActionContext.getServletContext().getRealPath("/") + "attached/";
		String saveUrl = ServletActionContext.getRequest().getContextPath() + "/attached/";
		System.out.println("===========================删除添加失败、更新失败page的文件 begin==============================");
		
		int count = 0;
		if (!page.getPageImg().equals(page.getGoods().getImg1()) && page.getPageImg() != null
				&& !page.getPageImg().equals("") && page.getPageImg().indexOf("model") < 0) {
			String test_imgpath1 = page.getPageImg().substring(page.getPageImg().lastIndexOf("image"));
			String test_imgpath0 = savePath + test_imgpath1;
			File file = new File(test_imgpath0);
			if (file.exists()) {
				System.out.println(count++ + "--------------->deleted:" + file.getAbsolutePath());
				test.DeleteFile(file.getAbsolutePath());
			}
		}
		System.out.println("===========================删除添加失败、更新失败page的文件 end==============================");
		
	}
	
	/**
	 * 删除更新成功page的旧文件
	 * 
	 * @param old_page
	 * @param new_page
	 */
	public static void DeleteImg_Old(Page old_page, Page new_page) {
		
		String savePath = ServletActionContext.getServletContext().getRealPath("/") + "attached/";
		String saveUrl = ServletActionContext.getRequest().getContextPath() + "/attached/";
		System.out.println("========================删除更新成功page的旧文件 Begin==============================");
		
		int count = 0;
		if (!old_page.getPageImg().equals(old_page.getGoods().getImg1()) && old_page.getPageImg() != null
				&& !old_page.getPageImg().equals("") && !old_page.getPageImg().equals(new_page.getPageImg())
				&& old_page.getPageImg().indexOf("model") < 0) {
			String test_imgpath1 = old_page.getPageImg().substring(old_page.getPageImg().lastIndexOf("image"));
			String test_imgpath0 = savePath + test_imgpath1;
			count++;
			File file = new File(test_imgpath0);
			if (file.exists()) {
				System.out.println(count + "--------------->deleted:" + file.getAbsolutePath());
				test.DeleteFile(file.getAbsolutePath());
			}
		}
		System.out.println("========================删除更新成功page的旧文件 End==============================");
	}
}
