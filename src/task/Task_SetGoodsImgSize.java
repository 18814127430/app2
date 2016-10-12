package task;

import net.coobird.thumbnailator.Thumbnails;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.quartz.JobExecutionContext;
import org.quartz.JobExecutionException;
import org.springframework.scheduling.quartz.QuartzJobBean;

import com.taobao.api.domain.Task;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Random;

import service.GoodsService;
import utils.ThumbnailatorTest;
import utils.msg;
import bean.Goods;
import utils.test;

public class Task_SetGoodsImgSize extends QuartzJobBean {
	
	private final Log logger = LogFactory.getLog(getClass());
	private GoodsService goodsService;
	public static List list_goods = new ArrayList();
	
	protected void executeInternal(JobExecutionContext context) throws JobExecutionException {
		
		test.a("初始化图片尺寸开始. . .");
		test.a(list_goods.size());
		
		for (int i = list_goods.size() - 1; i >= 0; i--) {
			int goodsid = (Integer) list_goods.get(i);
			Goods db_goods = null;
			try {
				db_goods = goodsService.View(goodsid);
				if (db_goods == null) {
					list_goods.remove(i);
					continue;
				}
				db_goods = SetImgSize_Add(db_goods);
				db_goods = goodsService.Update(db_goods);
				
				list_goods.remove(i);
				
				logger.info("按照像素要求更新goods:" + db_goods.getGoodsId());
				
			} catch (Exception e1) {
				e1.printStackTrace();
			}
		}
		test.a(list_goods.size());
		test.a("初始化图片尺寸结束. . .");
		
		return;
	}
	
	public static Goods SetImgSize_Add(Goods oldgoods) throws IOException {
		test.a("按照像素要求更新goods的图片文件 begin. . . ");
		
		Goods newgoods = oldgoods;
		String savePath = msg.root_path + "attached/";
		int count = 0;
		
		if (oldgoods.getImg1() != null && !oldgoods.getImg1().equals("")) {
			count++;
			String img = oldgoods.getImg1();
			String imgpath = savePath + img.substring(img.lastIndexOf("image"));
			File file = new File(imgpath);
			
			String[] str = msg.defaultImg1.split(",");
			int width = Integer.parseInt(str[0]);
			int height = Integer.parseInt(str[1]);
			
			if (file.exists()) {
				
				String newimg = img.replace(file.getName(), test.NameByTime() + "_" + new Random().nextInt(1000) + "_"
						+ oldgoods.getGoodsName() + "." + test.GetFileExtension(file));
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
				
				newgoods.setImg1(newimg);
				test.DeleteFile(imgpath);
			}
		}
		
		if (oldgoods.getImg2() != null && !oldgoods.getImg2().equals("")) {
			count++;
			String img = oldgoods.getImg2();
			String imgpath = savePath + img.substring(img.lastIndexOf("image"));
			File file = new File(imgpath);
			
			String[] str = msg.defaultImg2.split(",");
			int width = Integer.parseInt(str[0]);
			int height = Integer.parseInt(str[1]);
			
			if (file.exists()) {
				
				String newimg = img.replace(file.getName(), test.NameByTime() + "_" + new Random().nextInt(1000) + "_"
						+ oldgoods.getGoodsName() + "." + test.GetFileExtension(file));
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
				newgoods.setImg2(newimg);
			}
			
			str = msg.defaultImg6.split(",");
			width = Integer.parseInt(str[0]);
			height = Integer.parseInt(str[1]);
			
			if (file.exists()) {
				
				String newimg = img.replace(file.getName(), test.NameByTime() + "_" + new Random().nextInt(1000) + "_"
						+ oldgoods.getGoodsName() + "." + test.GetFileExtension(file));
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
				newgoods.setImg6(newimg);
			}
			
			str = msg.defaultImg7.split(",");
			width = Integer.parseInt(str[0]);
			height = Integer.parseInt(str[1]);
			
			if (file.exists()) {
				
				String newimg = img.replace(file.getName(), test.NameByTime() + "_" + new Random().nextInt(1000) + "_"
						+ oldgoods.getGoodsName() + "." + test.GetFileExtension(file));
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
				newgoods.setImg7(newimg);
			}
			
			str = msg.defaultImg8.split(",");
			width = Integer.parseInt(str[0]);
			height = Integer.parseInt(str[1]);
			
			if (file.exists()) {
				
				String newimg = img.replace(file.getName(), test.NameByTime() + "_" + new Random().nextInt(1000) + "_"
						+ oldgoods.getGoodsName() + "." + test.GetFileExtension(file));
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
				newgoods.setImg8(newimg);
				test.DeleteFile(imgpath);
			}
			
		}
		
		if (oldgoods.getImg3() != null && !oldgoods.getImg3().equals("")) {
			count++;
			String img = oldgoods.getImg3();
			String imgpath = savePath + img.substring(img.lastIndexOf("image"));
			File file = new File(imgpath);
			
			String[] str = msg.defaultImg3.split(",");
			int width = Integer.parseInt(str[0]);
			int height = Integer.parseInt(str[1]);
			
			if (file.exists()) {
				
				String newimg = img.replace(file.getName(), test.NameByTime() + "_" + new Random().nextInt(1000) + "_"
						+ oldgoods.getGoodsName() + "." + test.GetFileExtension(file));
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
				
				newgoods.setImg3(newimg);
				test.DeleteFile(imgpath);
			}
		}
		
		if (oldgoods.getImg4() != null && !oldgoods.getImg4().equals("")) {
			count++;
			String img = oldgoods.getImg4();
			String imgpath = savePath + img.substring(img.lastIndexOf("image"));
			File file = new File(imgpath);
			
			String[] str = msg.defaultImg4.split(",");
			int width = Integer.parseInt(str[0]);
			int height = Integer.parseInt(str[1]);
			
			if (file.exists()) {
				
				String newimg = img.replace(file.getName(), test.NameByTime() + "_" + new Random().nextInt(1000) + "_"
						+ oldgoods.getGoodsName() + "." + test.GetFileExtension(file));
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
				newgoods.setImg4(newimg);
			}
			
			str = msg.defaultImg5.split(",");
			width = Integer.parseInt(str[0]);
			height = Integer.parseInt(str[1]);
			if (file.exists()) {
				String newimg = img.replace(file.getName(), test.NameByTime() + "_" + new Random().nextInt(1000) + "_"
						+ oldgoods.getGoodsName() + "." + test.GetFileExtension(file));
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
				newgoods.setImg5(newimg);
				test.DeleteFile(imgpath);
			}
			
		}
		
		String[] str9_16 = msg.defaultImg9_16.split(",");
		int width9_16 = Integer.parseInt(str9_16[0]);
		int height9_16 = Integer.parseInt(str9_16[1]);
		
		if (oldgoods.getImg9() != null && !oldgoods.getImg9().equals("")) {
			
			count++;
			String img = oldgoods.getImg9();
			String imgpath = savePath + img.substring(img.lastIndexOf("image"));
			File file = new File(imgpath);
			
			if (file.exists()) {
				
				String newimg = img.replace(file.getName(), test.NameByTime() + "_" + new Random().nextInt(1000) + "_"
						+ oldgoods.getGoodsName() + "." + test.GetFileExtension(file));
				String newimgpath = savePath + newimg.substring(newimg.lastIndexOf("image"));
				
				test.a(count + "----->zooms:" + ThumbnailatorTest.getImgWidth(imgpath) + "*"
						+ ThumbnailatorTest.getImgHeight(imgpath));
				test.a(count + "----->path:" + img);
				
				double scaleSize = Math.max((double) width9_16 / (double) ThumbnailatorTest.getImgWidth(imgpath), 1.0);
				test.a(count + "----->scaleSize:" + scaleSize);
				Thumbnails.of(imgpath).scale(scaleSize).toFile(newimgpath);
				
				test.a(count + "----->zooms:" + ThumbnailatorTest.getImgWidth(newimgpath) + "*"
						+ ThumbnailatorTest.getImgHeight(newimgpath));
				test.a(count + "----->path:" + newimg);
				
				if (ThumbnailatorTest.getImgWidth(newimgpath) < width9_16) {
					ThumbnailatorTest.buBai(newimgpath, newimgpath, width9_16, height9_16);
				}
				
				newgoods.setImg9(newimg);
				test.DeleteFile(imgpath);
			}
		}
		
		if (oldgoods.getImg10() != null && !oldgoods.getImg10().equals("")) {
			
			count++;
			String img = oldgoods.getImg10();
			String imgpath = savePath + img.substring(img.lastIndexOf("image"));
			File file = new File(imgpath);
			
			if (file.exists()) {
				String newimg = img.replace(file.getName(), test.NameByTime() + "_" + new Random().nextInt(1000) + "_"
						+ oldgoods.getGoodsName() + "." + test.GetFileExtension(file));
				String newimgpath = savePath + newimg.substring(newimg.lastIndexOf("image"));
				
				test.a(count + "----->zooms:" + ThumbnailatorTest.getImgWidth(imgpath) + "*"
						+ ThumbnailatorTest.getImgHeight(imgpath));
				test.a(count + "----->path:" + img);
				
				double scaleSize = Math.max((double) width9_16 / (double) ThumbnailatorTest.getImgWidth(imgpath), 1.0);
				test.a(count + "----->scaleSize:" + scaleSize);
				Thumbnails.of(imgpath).scale(scaleSize).toFile(newimgpath);
				
				test.a(count + "----->zooms:" + ThumbnailatorTest.getImgWidth(newimgpath) + "*"
						+ ThumbnailatorTest.getImgHeight(newimgpath));
				test.a(count + "----->path:" + newimg);
				
				if (ThumbnailatorTest.getImgWidth(newimgpath) < width9_16) {
					ThumbnailatorTest.buBai(newimgpath, newimgpath, width9_16, height9_16);
				}
				
				newgoods.setImg10(newimg);
				test.DeleteFile(imgpath);
			}
		}
		
		if (oldgoods.getImg11() != null && !oldgoods.getImg11().equals("")) {
			
			count++;
			String img = oldgoods.getImg11();
			String imgpath = savePath + img.substring(img.lastIndexOf("image"));
			File file = new File(imgpath);
			
			if (file.exists()) {
				String newimg = img.replace(file.getName(), test.NameByTime() + "_" + new Random().nextInt(1000) + "_"
						+ oldgoods.getGoodsName() + "." + test.GetFileExtension(file));
				String newimgpath = savePath + newimg.substring(newimg.lastIndexOf("image"));
				
				test.a(count + "----->zooms:" + ThumbnailatorTest.getImgWidth(imgpath) + "*"
						+ ThumbnailatorTest.getImgHeight(imgpath));
				test.a(count + "----->path:" + img);
				
				double scaleSize = Math.max((double) width9_16 / (double) ThumbnailatorTest.getImgWidth(imgpath), 1.0);
				test.a(count + "----->scaleSize:" + scaleSize);
				Thumbnails.of(imgpath).scale(scaleSize).toFile(newimgpath);
				
				test.a(count + "----->zooms:" + ThumbnailatorTest.getImgWidth(newimgpath) + "*"
						+ ThumbnailatorTest.getImgHeight(newimgpath));
				test.a(count + "----->path:" + newimg);
				
				if (ThumbnailatorTest.getImgWidth(newimgpath) < width9_16) {
					ThumbnailatorTest.buBai(newimgpath, newimgpath, width9_16, height9_16);
				}
				
				newgoods.setImg11(newimg);
				test.DeleteFile(imgpath);
			}
		}
		
		if (oldgoods.getImg12() != null && !oldgoods.getImg12().equals("")) {
			
			count++;
			String img = oldgoods.getImg12();
			String imgpath = savePath + img.substring(img.lastIndexOf("image"));
			File file = new File(imgpath);
			
			if (file.exists()) {
				
				String newimg = img.replace(file.getName(), test.NameByTime() + "_" + new Random().nextInt(1000) + "_"
						+ oldgoods.getGoodsName() + "." + test.GetFileExtension(file));
				String newimgpath = savePath + newimg.substring(newimg.lastIndexOf("image"));
				
				test.a(count + "----->zooms:" + ThumbnailatorTest.getImgWidth(imgpath) + "*"
						+ ThumbnailatorTest.getImgHeight(imgpath));
				test.a(count + "----->path:" + img);
				
				double scaleSize = Math.max((double) width9_16 / (double) ThumbnailatorTest.getImgWidth(imgpath), 1.0);
				test.a(count + "----->scaleSize:" + scaleSize);
				Thumbnails.of(imgpath).scale(scaleSize).toFile(newimgpath);
				
				test.a(count + "----->zooms:" + ThumbnailatorTest.getImgWidth(newimgpath) + "*"
						+ ThumbnailatorTest.getImgHeight(newimgpath));
				test.a(count + "----->path:" + newimg);
				
				if (ThumbnailatorTest.getImgWidth(newimgpath) < width9_16) {
					ThumbnailatorTest.buBai(newimgpath, newimgpath, width9_16, height9_16);
				}
				
				newgoods.setImg12(newimg);
				test.DeleteFile(imgpath);
			}
		}
		
		if (oldgoods.getImg13() != null && !oldgoods.getImg13().equals("")) {
			
			count++;
			String img = oldgoods.getImg13();
			String imgpath = savePath + img.substring(img.lastIndexOf("image"));
			File file = new File(imgpath);
			
			if (file.exists()) {
				String newimg = img.replace(file.getName(), test.NameByTime() + "_" + new Random().nextInt(1000) + "_"
						+ oldgoods.getGoodsName() + "." + test.GetFileExtension(file));
				String newimgpath = savePath + newimg.substring(newimg.lastIndexOf("image"));
				
				test.a(count + "----->zooms:" + ThumbnailatorTest.getImgWidth(imgpath) + "*"
						+ ThumbnailatorTest.getImgHeight(imgpath));
				test.a(count + "----->path:" + img);
				
				double scaleSize = Math.max((double) width9_16 / (double) ThumbnailatorTest.getImgWidth(imgpath), 1.0);
				test.a(count + "----->scaleSize:" + scaleSize);
				Thumbnails.of(imgpath).scale(scaleSize).toFile(newimgpath);
				
				test.a(count + "----->zooms:" + ThumbnailatorTest.getImgWidth(newimgpath) + "*"
						+ ThumbnailatorTest.getImgHeight(newimgpath));
				test.a(count + "----->path:" + newimg);
				
				if (ThumbnailatorTest.getImgWidth(newimgpath) < width9_16) {
					ThumbnailatorTest.buBai(newimgpath, newimgpath, width9_16, height9_16);
				}
				
				newgoods.setImg13(newimg);
				test.DeleteFile(imgpath);
			}
		}
		
		if (oldgoods.getImg14() != null && !oldgoods.getImg14().equals("")) {
			
			count++;
			String img = oldgoods.getImg14();
			String imgpath = savePath + img.substring(img.lastIndexOf("image"));
			File file = new File(imgpath);
			
			if (file.exists()) {
				
				String newimg = img.replace(file.getName(), test.NameByTime() + "_" + new Random().nextInt(1000) + "_"
						+ oldgoods.getGoodsName() + "." + test.GetFileExtension(file));
				String newimgpath = savePath + newimg.substring(newimg.lastIndexOf("image"));
				
				test.a(count + "----->zooms:" + ThumbnailatorTest.getImgWidth(imgpath) + "*"
						+ ThumbnailatorTest.getImgHeight(imgpath));
				test.a(count + "----->path:" + img);
				
				double scaleSize = Math.max((double) width9_16 / (double) ThumbnailatorTest.getImgWidth(imgpath), 1.0);
				test.a(count + "----->scaleSize:" + scaleSize);
				Thumbnails.of(imgpath).scale(scaleSize).toFile(newimgpath);
				
				test.a(count + "----->zooms:" + ThumbnailatorTest.getImgWidth(newimgpath) + "*"
						+ ThumbnailatorTest.getImgHeight(newimgpath));
				test.a(count + "----->path:" + newimg);
				
				if (ThumbnailatorTest.getImgWidth(newimgpath) < width9_16) {
					ThumbnailatorTest.buBai(newimgpath, newimgpath, width9_16, height9_16);
				}
				
				newgoods.setImg14(newimg);
				test.DeleteFile(imgpath);
			}
		}
		
		if (oldgoods.getImg15() != null && !oldgoods.getImg15().equals("")) {
			
			count++;
			String img = oldgoods.getImg15();
			String imgpath = savePath + img.substring(img.lastIndexOf("image"));
			File file = new File(imgpath);
			
			if (file.exists()) {
				
				String newimg = img.replace(file.getName(), test.NameByTime() + "_" + new Random().nextInt(1000) + "_"
						+ oldgoods.getGoodsName() + "." + test.GetFileExtension(file));
				String newimgpath = savePath + newimg.substring(newimg.lastIndexOf("image"));
				
				test.a(count + "----->zooms:" + ThumbnailatorTest.getImgWidth(imgpath) + "*"
						+ ThumbnailatorTest.getImgHeight(imgpath));
				test.a(count + "----->path:" + img);
				
				double scaleSize = Math.max((double) width9_16 / (double) ThumbnailatorTest.getImgWidth(imgpath), 1.0);
				test.a(count + "----->scaleSize:" + scaleSize);
				Thumbnails.of(imgpath).scale(scaleSize).toFile(newimgpath);
				
				test.a(count + "----->zooms:" + ThumbnailatorTest.getImgWidth(newimgpath) + "*"
						+ ThumbnailatorTest.getImgHeight(newimgpath));
				test.a(count + "----->path:" + newimg);
				
				if (ThumbnailatorTest.getImgWidth(newimgpath) < width9_16) {
					ThumbnailatorTest.buBai(newimgpath, newimgpath, width9_16, height9_16);
				}
				
				newgoods.setImg15(newimg);
				test.DeleteFile(imgpath);
			}
		}
		
		if (oldgoods.getImg16() != null && !oldgoods.getImg16().equals("")) {
			
			count++;
			String img = oldgoods.getImg16();
			String imgpath = savePath + img.substring(img.lastIndexOf("image"));
			File file = new File(imgpath);
			
			if (file.exists()) {
				
				String newimg = img.replace(file.getName(), test.NameByTime() + "_" + new Random().nextInt(1000) + "_"
						+ oldgoods.getGoodsName() + "." + test.GetFileExtension(file));
				String newimgpath = savePath + newimg.substring(newimg.lastIndexOf("image"));
				
				test.a(count + "----->zooms:" + ThumbnailatorTest.getImgWidth(imgpath) + "*"
						+ ThumbnailatorTest.getImgHeight(imgpath));
				test.a(count + "----->path:" + img);
				
				double scaleSize = Math.max((double) width9_16 / (double) ThumbnailatorTest.getImgWidth(imgpath), 1.0);
				test.a(count + "----->scaleSize:" + scaleSize);
				Thumbnails.of(imgpath).scale(scaleSize).toFile(newimgpath);
				
				test.a(count + "----->zooms:" + ThumbnailatorTest.getImgWidth(newimgpath) + "*"
						+ ThumbnailatorTest.getImgHeight(newimgpath));
				test.a(count + "----->path:" + newimg);
				
				if (ThumbnailatorTest.getImgWidth(newimgpath) < width9_16) {
					ThumbnailatorTest.buBai(newimgpath, newimgpath, width9_16, height9_16);
				}
				
				newgoods.setImg16(newimg);
				test.DeleteFile(imgpath);
			}
		}
		
		test.a("按照像素要求更新goods的图片文件结束. . . ");
		return newgoods;
	}
	
	public static Goods SetImgSize_Update(Goods oldgoods, Goods newgoods) throws IOException {
		test.a("按照像素要求更新goods的图片文件开始. . . ");
		
		Goods tempgoods = newgoods;
		String savePath = msg.root_path + "attached/";
		int count = 0;
		
		if (newgoods.getImg1() != null && !newgoods.getImg1().equals("") && !newgoods.getImg1().equals(oldgoods.getImg1())) {
			count++;
			String img = newgoods.getImg1();
			String imgpath = savePath + img.substring(img.lastIndexOf("image"));
			File file = new File(imgpath);
			
			String[] str = msg.defaultImg1.split(",");
			int width = Integer.parseInt(str[0]);
			int height = Integer.parseInt(str[1]);
			
			if (file.exists()) {
				
				String newimg = img.replace(file.getName(), test.NameByTime() + "_" + new Random().nextInt(1000) + "_"
						+ newgoods.getGoodsName() + "." + test.GetFileExtension(file));
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
				
				tempgoods.setImg1(newimg);
				test.DeleteFile(imgpath);
			}
		}
		
		if (newgoods.getImg2() != null && !newgoods.getImg2().equals("") && !newgoods.getImg2().equals(oldgoods.getImg2())) {
			count++;
			String img = newgoods.getImg2();
			String imgpath = savePath + img.substring(img.lastIndexOf("image"));
			File file = new File(imgpath);
			
			String[] str = msg.defaultImg2.split(",");
			int width = Integer.parseInt(str[0]);
			int height = Integer.parseInt(str[1]);
			
			if (file.exists()) {
				
				String newimg = img.replace(file.getName(), test.NameByTime() + "_" + new Random().nextInt(1000) + "_"
						+ newgoods.getGoodsName() + "." + test.GetFileExtension(file));
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
				tempgoods.setImg2(newimg);
			}
			
			str = msg.defaultImg6.split(",");
			width = Integer.parseInt(str[0]);
			height = Integer.parseInt(str[1]);
			
			if (file.exists()) {
				
				String newimg = img.replace(file.getName(), test.NameByTime() + "_" + new Random().nextInt(1000) + "_"
						+ newgoods.getGoodsName() + "." + test.GetFileExtension(file));
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
				tempgoods.setImg6(newimg);
			}
			
			str = msg.defaultImg7.split(",");
			width = Integer.parseInt(str[0]);
			height = Integer.parseInt(str[1]);
			
			if (file.exists()) {
				
				String newimg = img.replace(file.getName(), test.NameByTime() + "_" + new Random().nextInt(1000) + "_"
						+ newgoods.getGoodsName() + "." + test.GetFileExtension(file));
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
				tempgoods.setImg7(newimg);
			}
			
			str = msg.defaultImg8.split(",");
			width = Integer.parseInt(str[0]);
			height = Integer.parseInt(str[1]);
			
			if (file.exists()) {
				
				String newimg = img.replace(file.getName(), test.NameByTime() + "_" + new Random().nextInt(1000) + "_"
						+ newgoods.getGoodsName() + "." + test.GetFileExtension(file));
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
				tempgoods.setImg8(newimg);
				test.DeleteFile(imgpath);
			}
			
		}
		
		if (newgoods.getImg3() != null && !newgoods.getImg3().equals("") && !newgoods.getImg3().equals(oldgoods.getImg3())) {
			count++;
			String img = newgoods.getImg3();
			String imgpath = savePath + img.substring(img.lastIndexOf("image"));
			File file = new File(imgpath);
			
			String[] str = msg.defaultImg3.split(",");
			int width = Integer.parseInt(str[0]);
			int height = Integer.parseInt(str[1]);
			
			if (file.exists()) {
				
				String newimg = img.replace(file.getName(), test.NameByTime() + "_" + new Random().nextInt(1000) + "_"
						+ newgoods.getGoodsName() + "." + test.GetFileExtension(file));
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
				
				tempgoods.setImg3(newimg);
				test.DeleteFile(imgpath);
			}
		}
		
		if (newgoods.getImg4() != null && !newgoods.getImg4().equals("") && !newgoods.getImg4().equals(oldgoods.getImg4())) {
			count++;
			String img = newgoods.getImg4();
			String imgpath = savePath + img.substring(img.lastIndexOf("image"));
			File file = new File(imgpath);
			
			String[] str = msg.defaultImg4.split(",");
			int width = Integer.parseInt(str[0]);
			int height = Integer.parseInt(str[1]);
			
			if (file.exists()) {
				
				String newimg = img.replace(file.getName(), test.NameByTime() + "_" + new Random().nextInt(1000) + "_"
						+ newgoods.getGoodsName() + "." + test.GetFileExtension(file));
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
				tempgoods.setImg4(newimg);
			}
			
			str = msg.defaultImg5.split(",");
			width = Integer.parseInt(str[0]);
			height = Integer.parseInt(str[1]);
			if (file.exists()) {
				String newimg = img.replace(file.getName(), test.NameByTime() + "_" + new Random().nextInt(1000) + "_"
						+ newgoods.getGoodsName() + "." + test.GetFileExtension(file));
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
				tempgoods.setImg5(newimg);
				test.DeleteFile(imgpath);
			}
			
		}
		
		String[] str9_16 = msg.defaultImg9_16.split(",");
		int width9_16 = Integer.parseInt(str9_16[0]);
		int height9_16 = Integer.parseInt(str9_16[1]);
		
		if (newgoods.getImg9() != null && !newgoods.getImg9().equals("") && !newgoods.getImg9().equals(oldgoods.getImg9())) {
			
			count++;
			String img = newgoods.getImg9();
			String imgpath = savePath + img.substring(img.lastIndexOf("image"));
			File file = new File(imgpath);
			
			if (file.exists()) {
				
				String newimg = img.replace(file.getName(), test.NameByTime() + "_" + new Random().nextInt(1000) + "_"
						+ newgoods.getGoodsName() + "." + test.GetFileExtension(file));
				String newimgpath = savePath + newimg.substring(newimg.lastIndexOf("image"));
				
				test.a(count + "----->zooms:" + ThumbnailatorTest.getImgWidth(imgpath) + "*"
						+ ThumbnailatorTest.getImgHeight(imgpath));
				test.a(count + "----->path:" + img);
				
				double scaleSize = Math.max((double) width9_16 / (double) ThumbnailatorTest.getImgWidth(imgpath), 1.0);
				test.a(count + "----->scaleSize:" + scaleSize);
				Thumbnails.of(imgpath).scale(scaleSize).toFile(newimgpath);
				
				test.a(count + "----->zooms:" + ThumbnailatorTest.getImgWidth(newimgpath) + "*"
						+ ThumbnailatorTest.getImgHeight(newimgpath));
				test.a(count + "----->path:" + newimg);
				
				if (ThumbnailatorTest.getImgWidth(newimgpath) < width9_16) {
					ThumbnailatorTest.buBai(newimgpath, newimgpath, width9_16, height9_16);
				}
				
				tempgoods.setImg9(newimg);
				test.DeleteFile(imgpath);
			}
		}
		
		if (newgoods.getImg10() != null && !newgoods.getImg10().equals("")
				&& !newgoods.getImg10().equals(oldgoods.getImg10())) {
			
			count++;
			String img = newgoods.getImg10();
			String imgpath = savePath + img.substring(img.lastIndexOf("image"));
			File file = new File(imgpath);
			
			if (file.exists()) {
				String newimg = img.replace(file.getName(), test.NameByTime() + "_" + new Random().nextInt(1000) + "_"
						+ newgoods.getGoodsName() + "." + test.GetFileExtension(file));
				String newimgpath = savePath + newimg.substring(newimg.lastIndexOf("image"));
				
				test.a(count + "----->zooms:" + ThumbnailatorTest.getImgWidth(imgpath) + "*"
						+ ThumbnailatorTest.getImgHeight(imgpath));
				test.a(count + "----->path:" + img);
				
				double scaleSize = Math.max((double) width9_16 / (double) ThumbnailatorTest.getImgWidth(imgpath), 1.0);
				test.a(count + "----->scaleSize:" + scaleSize);
				Thumbnails.of(imgpath).scale(scaleSize).toFile(newimgpath);
				
				test.a(count + "----->zooms:" + ThumbnailatorTest.getImgWidth(newimgpath) + "*"
						+ ThumbnailatorTest.getImgHeight(newimgpath));
				test.a(count + "----->path:" + newimg);
				
				if (ThumbnailatorTest.getImgWidth(newimgpath) < width9_16) {
					ThumbnailatorTest.buBai(newimgpath, newimgpath, width9_16, height9_16);
				}
				
				tempgoods.setImg10(newimg);
				test.DeleteFile(imgpath);
			}
		}
		if (newgoods.getImg11() != null && !newgoods.getImg11().equals("")
				&& !newgoods.getImg11().equals(oldgoods.getImg11())) {
			
			count++;
			String img = newgoods.getImg11();
			String imgpath = savePath + img.substring(img.lastIndexOf("image"));
			File file = new File(imgpath);
			
			if (file.exists()) {
				String newimg = img.replace(file.getName(), test.NameByTime() + "_" + new Random().nextInt(1000) + "_"
						+ newgoods.getGoodsName() + "." + test.GetFileExtension(file));
				String newimgpath = savePath + newimg.substring(newimg.lastIndexOf("image"));
				
				test.a(count + "----->zooms:" + ThumbnailatorTest.getImgWidth(imgpath) + "*"
						+ ThumbnailatorTest.getImgHeight(imgpath));
				test.a(count + "----->path:" + img);
				
				double scaleSize = Math.max((double) width9_16 / (double) ThumbnailatorTest.getImgWidth(imgpath), 1.0);
				test.a(count + "----->scaleSize:" + scaleSize);
				Thumbnails.of(imgpath).scale(scaleSize).toFile(newimgpath);
				
				test.a(count + "----->zooms:" + ThumbnailatorTest.getImgWidth(newimgpath) + "*"
						+ ThumbnailatorTest.getImgHeight(newimgpath));
				test.a(count + "----->path:" + newimg);
				
				if (ThumbnailatorTest.getImgWidth(newimgpath) < width9_16) {
					ThumbnailatorTest.buBai(newimgpath, newimgpath, width9_16, height9_16);
				}
				
				tempgoods.setImg11(newimg);
				test.DeleteFile(imgpath);
			}
		}
		if (newgoods.getImg12() != null && !newgoods.getImg12().equals("")
				&& !newgoods.getImg12().equals(oldgoods.getImg12())) {
			
			count++;
			String img = newgoods.getImg12();
			String imgpath = savePath + img.substring(img.lastIndexOf("image"));
			File file = new File(imgpath);
			
			if (file.exists()) {
				
				String newimg = img.replace(file.getName(), test.NameByTime() + "_" + new Random().nextInt(1000) + "_"
						+ newgoods.getGoodsName() + "." + test.GetFileExtension(file));
				String newimgpath = savePath + newimg.substring(newimg.lastIndexOf("image"));
				
				test.a(count + "----->zooms:" + ThumbnailatorTest.getImgWidth(imgpath) + "*"
						+ ThumbnailatorTest.getImgHeight(imgpath));
				test.a(count + "----->path:" + img);
				
				double scaleSize = Math.max((double) width9_16 / (double) ThumbnailatorTest.getImgWidth(imgpath), 1.0);
				test.a(count + "----->scaleSize:" + scaleSize);
				Thumbnails.of(imgpath).scale(scaleSize).toFile(newimgpath);
				
				test.a(count + "----->zooms:" + ThumbnailatorTest.getImgWidth(newimgpath) + "*"
						+ ThumbnailatorTest.getImgHeight(newimgpath));
				test.a(count + "----->path:" + newimg);
				
				if (ThumbnailatorTest.getImgWidth(newimgpath) < width9_16) {
					ThumbnailatorTest.buBai(newimgpath, newimgpath, width9_16, height9_16);
				}
				
				tempgoods.setImg12(newimg);
				test.DeleteFile(imgpath);
			}
		}
		if (newgoods.getImg13() != null && !newgoods.getImg13().equals("")
				&& !newgoods.getImg13().equals(oldgoods.getImg13())) {
			
			count++;
			String img = newgoods.getImg13();
			String imgpath = savePath + img.substring(img.lastIndexOf("image"));
			File file = new File(imgpath);
			
			if (file.exists()) {
				String newimg = img.replace(file.getName(), test.NameByTime() + "_" + new Random().nextInt(1000) + "_"
						+ newgoods.getGoodsName() + "." + test.GetFileExtension(file));
				String newimgpath = savePath + newimg.substring(newimg.lastIndexOf("image"));
				
				test.a(count + "----->zooms:" + ThumbnailatorTest.getImgWidth(imgpath) + "*"
						+ ThumbnailatorTest.getImgHeight(imgpath));
				test.a(count + "----->path:" + img);
				
				double scaleSize = Math.max((double) width9_16 / (double) ThumbnailatorTest.getImgWidth(imgpath), 1.0);
				test.a(count + "----->scaleSize:" + scaleSize);
				Thumbnails.of(imgpath).scale(scaleSize).toFile(newimgpath);
				
				test.a(count + "----->zooms:" + ThumbnailatorTest.getImgWidth(newimgpath) + "*"
						+ ThumbnailatorTest.getImgHeight(newimgpath));
				test.a(count + "----->path:" + newimg);
				
				if (ThumbnailatorTest.getImgWidth(newimgpath) < width9_16) {
					ThumbnailatorTest.buBai(newimgpath, newimgpath, width9_16, height9_16);
				}
				
				tempgoods.setImg13(newimg);
				test.DeleteFile(imgpath);
			}
		}
		if (newgoods.getImg14() != null && !newgoods.getImg14().equals("")
				&& !newgoods.getImg14().equals(oldgoods.getImg14())) {
			
			count++;
			String img = newgoods.getImg14();
			String imgpath = savePath + img.substring(img.lastIndexOf("image"));
			File file = new File(imgpath);
			
			if (file.exists()) {
				
				String newimg = img.replace(file.getName(), test.NameByTime() + "_" + new Random().nextInt(1000) + "_"
						+ newgoods.getGoodsName() + "." + test.GetFileExtension(file));
				String newimgpath = savePath + newimg.substring(newimg.lastIndexOf("image"));
				
				test.a(count + "----->zooms:" + ThumbnailatorTest.getImgWidth(imgpath) + "*"
						+ ThumbnailatorTest.getImgHeight(imgpath));
				test.a(count + "----->path:" + img);
				
				double scaleSize = Math.max((double) width9_16 / (double) ThumbnailatorTest.getImgWidth(imgpath), 1.0);
				test.a(count + "----->scaleSize:" + scaleSize);
				Thumbnails.of(imgpath).scale(scaleSize).toFile(newimgpath);
				
				test.a(count + "----->zooms:" + ThumbnailatorTest.getImgWidth(newimgpath) + "*"
						+ ThumbnailatorTest.getImgHeight(newimgpath));
				test.a(count + "----->path:" + newimg);
				
				if (ThumbnailatorTest.getImgWidth(newimgpath) < width9_16) {
					ThumbnailatorTest.buBai(newimgpath, newimgpath, width9_16, height9_16);
				}
				
				tempgoods.setImg14(newimg);
				test.DeleteFile(imgpath);
			}
		}
		if (newgoods.getImg15() != null && !newgoods.getImg15().equals("")
				&& !newgoods.getImg15().equals(oldgoods.getImg15())) {
			
			count++;
			String img = newgoods.getImg15();
			String imgpath = savePath + img.substring(img.lastIndexOf("image"));
			File file = new File(imgpath);
			
			if (file.exists()) {
				
				String newimg = img.replace(file.getName(), test.NameByTime() + "_" + new Random().nextInt(1000) + "_"
						+ newgoods.getGoodsName() + "." + test.GetFileExtension(file));
				String newimgpath = savePath + newimg.substring(newimg.lastIndexOf("image"));
				
				test.a(count + "----->zooms:" + ThumbnailatorTest.getImgWidth(imgpath) + "*"
						+ ThumbnailatorTest.getImgHeight(imgpath));
				test.a(count + "----->path:" + img);
				
				double scaleSize = Math.max((double) width9_16 / (double) ThumbnailatorTest.getImgWidth(imgpath), 1.0);
				test.a(count + "----->scaleSize:" + scaleSize);
				Thumbnails.of(imgpath).scale(scaleSize).toFile(newimgpath);
				
				test.a(count + "----->zooms:" + ThumbnailatorTest.getImgWidth(newimgpath) + "*"
						+ ThumbnailatorTest.getImgHeight(newimgpath));
				test.a(count + "----->path:" + newimg);
				
				if (ThumbnailatorTest.getImgWidth(newimgpath) < width9_16) {
					ThumbnailatorTest.buBai(newimgpath, newimgpath, width9_16, height9_16);
				}
				
				tempgoods.setImg15(newimg);
				test.DeleteFile(imgpath);
			}
		}
		
		if (newgoods.getImg16() != null && !newgoods.getImg16().equals("")
				&& !newgoods.getImg16().equals(oldgoods.getImg16())) {
			
			count++;
			String img = newgoods.getImg16();
			String imgpath = savePath + img.substring(img.lastIndexOf("image"));
			File file = new File(imgpath);
			
			if (file.exists()) {
				
				String newimg = img.replace(file.getName(), test.NameByTime() + "_" + new Random().nextInt(1000) + "_"
						+ newgoods.getGoodsName() + "." + test.GetFileExtension(file));
				String newimgpath = savePath + newimg.substring(newimg.lastIndexOf("image"));
				
				test.a(count + "----->zooms:" + ThumbnailatorTest.getImgWidth(imgpath) + "*"
						+ ThumbnailatorTest.getImgHeight(imgpath));
				test.a(count + "----->path:" + img);
				
				double scaleSize = Math.max((double) width9_16 / (double) ThumbnailatorTest.getImgWidth(imgpath), 1.0);
				test.a(count + "----->scaleSize:" + scaleSize);
				Thumbnails.of(imgpath).scale(scaleSize).toFile(newimgpath);
				
				test.a(count + "----->zooms:" + ThumbnailatorTest.getImgWidth(newimgpath) + "*"
						+ ThumbnailatorTest.getImgHeight(newimgpath));
				test.a(count + "----->path:" + newimg);
				
				if (ThumbnailatorTest.getImgWidth(newimgpath) < width9_16) {
					ThumbnailatorTest.buBai(newimgpath, newimgpath, width9_16, height9_16);
				}
				
				tempgoods.setImg16(newimg);
				test.DeleteFile(imgpath);
			}
		}
		
		test.a("按照像素要求更新goods的图片文件结束. . . ");
		return tempgoods;
	}
	
	public GoodsService getGoodsService() {
		return goodsService;
	}
	
	public void setGoodsService(GoodsService goodsService) {
		this.goodsService = goodsService;
	}
	
	public static List getList_goods() {
		return list_goods;
	}
	
	public static void setList_goods(List list_goods) {
		Task_SetGoodsImgSize.list_goods = list_goods;
	}
	
	public Log getLogger() {
		return logger;
	}
	
}
