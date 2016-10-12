package utils;

import java.io.File;
import java.io.IOException;
import java.util.Random;

import net.coobird.thumbnailator.Thumbnails;

import org.apache.log4j.Logger;
import org.apache.struts2.ServletActionContext;

import bean.Sort;

/**
 * Sort工具类
 * 
 * @author
 * 
 */
public class sortTool {
	private static final Logger log = Logger.getLogger(sortTool.class);
	
	/**
	 * 按照像素要求更新sort的图片文件
	 * 
	 * @param newsort
	 * @return
	 * @throws IOException
	 */
	public static Sort SetSortImgSize(Sort newsort) throws IOException {
		System.out.println("===========================按照像素要求更新sort的图片文件 begin==============================");
		
		String savePath = ServletActionContext.getServletContext().getRealPath("/") + "attached/";
		int count = 0;
		Sort temp_sort = newsort;
		
		if (newsort.getSortClass() == msg.sort_child && newsort.getSortImgPath() != null
				&& !newsort.getSortImgPath().equals("") && newsort.getSortImgPath().indexOf("model") < 0) {
			String img = newsort.getSortImgPath();
			String imgpath = savePath + img.substring(img.lastIndexOf("image"));
			File file = new File(imgpath);
			
			String[] str = msg.defaultImgSort.split(",");
			int width = Integer.parseInt(str[0]);
			int height = Integer.parseInt(str[1]);
			
			if (file.exists()) {
				
				String newimg = img.replace(file.getName(), test.NameByTime() + "_" + new Random().nextInt(10000) + "_"
						+ newsort.getSortName() + "." + test.GetFileExtension(file));
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
				
				temp_sort.setSortImgPath(newimg);
				test.DeleteFile(imgpath);
			}
		}
		System.out.println("===========================按照像素要求更新sort的图片文件 end==============================");
		return temp_sort;
	}
	
	/**
	 * 删除添加失败、更新失败sort的文件
	 * 
	 * @param sort
	 */
	public static void DeleteImg_New(Sort sort) {
		
		String savePath = ServletActionContext.getServletContext().getRealPath("/") + "attached/";
		String saveUrl = ServletActionContext.getRequest().getContextPath() + "/attached/";
		System.out.println("===========================删除添加失败、更新失败sort的文件 begin==============================");
		
		int count = 0;
		if (sort.getSortClass() == msg.sort_child && sort.getSortImgPath() != null && !sort.getSortImgPath().equals("")
				&& sort.getSortImgPath().indexOf("model") < 0) {
			String test_imgpath1 = sort.getSortImgPath().substring(sort.getSortImgPath().lastIndexOf("image"));
			String test_imgpath0 = savePath + test_imgpath1;
			File file = new File(test_imgpath0);
			if (file.exists()) {
				System.out.println(count++ + "--------------->deleted:" + file.getAbsolutePath());
				test.DeleteFile(file.getAbsolutePath());
			}
		}
		System.out.println("===========================删除添加失败、更新失败sort的文件 end==============================");
		
	}
	
	/**
	 * 删除更新成功sort的旧文件
	 * 
	 * @param old_sort
	 * @param new_sort
	 */
	public static void Delete_Img_Old(Sort old_sort, Sort new_sort) {
		
		String savePath = ServletActionContext.getServletContext().getRealPath("/") + "attached/";
		String saveUrl = ServletActionContext.getRequest().getContextPath() + "/attached/";
		System.out.println("========================删除更新成功sort的旧文件 Begin==============================");
		
		int count = 0;
		
		if (old_sort.getSortClass() == msg.sort_child && old_sort.getSortImgPath() != null
				&& !old_sort.getSortImgPath().equals("") && !old_sort.getSortImgPath().equals(new_sort.getSortImgPath())
				&& old_sort.getSortImgPath().indexOf("model") < 0) {
			String test_imgpath1 = old_sort.getSortImgPath().substring(old_sort.getSortImgPath().lastIndexOf("image"));
			String test_imgpath0 = savePath + test_imgpath1;
			count++;
			File file = new File(test_imgpath0);
			if (file.exists()) {
				System.out.println(count + "--------------->deleted:" + file.getAbsolutePath());
				test.DeleteFile(file.getAbsolutePath());
			}
		}
		System.out.println("========================删除更新成功sort的旧文件 End==============================");
	}
	
}
