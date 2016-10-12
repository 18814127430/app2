package utils;

import java.awt.Color;
import java.awt.Graphics2D;
import java.awt.Image;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStream;

import javax.imageio.ImageIO;

import net.coobird.thumbnailator.Thumbnails;
import net.coobird.thumbnailator.geometry.Positions;

/**
 * 
 * @author yaohucaizi
 * 
 */
public class ThumbnailatorTest {
	
	/**
	 * 
	 * @param args
	 * @throws IOException
	 */
	public static void main(String[] args) throws IOException {
		
		String path = "C:\\Users\\10919\\Workspaces\\MyEclipse Professional 2014\\.metadata\\.me_tcat7\\webapps\\app2\\attached/image/20160901/20160901165520_198.jpg";
		File file = new File(path);
		test.a(file.getParent());
		
		ThumbnailatorTest thumbnailatorTest = new ThumbnailatorTest();
		//		thumbnailatorTest.test1();
		//		thumbnailatorTest.test2();
		//		thumbnailatorTest.test3();
		//		thumbnailatorTest.test4();
		//		thumbnailatorTest.test5();
		//		thumbnailatorTest.test6();
		//		thumbnailatorTest.test7();
		//		thumbnailatorTest.test8();
		//		thumbnailatorTest.test9();
	}
	
	/**
	 * 指定大小进行缩放
	 * 
	 * @throws IOException
	 */
	private void test1() throws IOException {
		/*
		 * size(width,height) 若图片横比200小，高比300小，不变 若图片横比200小，高比300大，高缩小到300，图片比例不变
		 * 若图片横比200大，高比300小，横缩小到200，图片比例不变 若图片横比200大，高比300大，图片按比例缩小，横为200或高为300
		 */
		Thumbnails.of("images/test.jpg").size(200, 300).toFile("C:/image_200x300.jpg");
		Thumbnails.of("images/test.jpg").size(2560, 2048).toFile("C:/image_2560x2048.jpg");
	}
	
	/**
	 * 按照比例进行缩放
	 * 
	 * @throws IOException
	 */
	private void test2() throws IOException {
		/**
		 * scale(比例)
		 */
		Thumbnails.of("images/test.jpg").scale(0.25f).toFile("C:/image_25%.jpg");
		Thumbnails.of("images/test.jpg").scale(1.10f).toFile("C:/image_110%.jpg");
	}
	
	/**
	 * 不按照比例，指定大小进行缩放
	 * 
	 * @throws IOException
	 */
	private void test3() throws IOException {
		/**
		 * keepAspectRatio(false) 默认是按照比例缩放的
		 */
		Thumbnails.of("images/test.jpg").size(120, 120).keepAspectRatio(false).toFile("C:/image_120x120.jpg");
	}
	
	/**
	 * 旋转
	 * 
	 * @throws IOException
	 */
	private void test4() throws IOException {
		/**
		 * rotate(角度),正数：顺时针 负数：逆时针
		 */
		Thumbnails.of("images/test.jpg").size(1280, 1024).rotate(90).toFile("C:/image+90.jpg");
		Thumbnails.of("images/test.jpg").size(1280, 1024).rotate(-90).toFile("C:/iamge-90.jpg");
	}
	
	/**
	 * 水印
	 * 
	 * @throws IOException
	 */
	private void test5() throws IOException {
		/**
		 * watermark(位置，水印图，透明度)
		 */
		Thumbnails.of("images/test.jpg").size(1280, 1024)
				.watermark(Positions.BOTTOM_RIGHT, ImageIO.read(new File("images/watermark.png")), 0.5f)
				.outputQuality(0.8f).toFile("C:/image_watermark_bottom_right.jpg");
		Thumbnails.of("images/test.jpg").size(1280, 1024)
				.watermark(Positions.CENTER, ImageIO.read(new File("images/watermark.png")), 0.5f).outputQuality(0.8f)
				.toFile("C:/image_watermark_center.jpg");
	}
	
	/**
	 * 裁剪
	 * 
	 * @throws IOException
	 */
	private void test6() throws IOException {
		/**
		 * 图片中心400*400的区域
		 */
		Thumbnails.of("images/test.jpg").sourceRegion(Positions.CENTER, 400, 400).size(200, 200).keepAspectRatio(false)
				.toFile("C:/image_region_center.jpg");
		/**
		 * 图片右下400*400的区域
		 */
		Thumbnails.of("images/test.jpg").sourceRegion(Positions.BOTTOM_RIGHT, 400, 400).size(200, 200)
				.keepAspectRatio(false).toFile("C:/image_region_bootom_right.jpg");
		/**
		 * 指定坐标
		 */
		Thumbnails.of("images/test.jpg").sourceRegion(600, 500, 400, 400).size(200, 200).keepAspectRatio(false)
				.toFile("C:/image_region_coord.jpg");
	}
	
	/**
	 * 转化图像格式
	 * 
	 * @throws IOException
	 */
	private void test7() throws IOException {
		/**
		 * outputFormat(图像格式)
		 */
		Thumbnails.of("images/test.jpg").size(1280, 1024).outputFormat("png").toFile("C:/image_1280x1024.png");
		Thumbnails.of("images/test.jpg").size(1280, 1024).outputFormat("gif").toFile("C:/image_1280x1024.gif");
	}
	
	/**
	 * 输出到OutputStream
	 * 
	 * @throws IOException
	 */
	private void test8() throws IOException {
		/**
		 * toOutputStream(流对象)
		 */
		OutputStream os = new FileOutputStream("C:/image_1280x1024_OutputStream.png");
		Thumbnails.of("images/test.jpg").size(1280, 1024).toOutputStream(os);
	}
	
	/**
	 * 输出到BufferedImage
	 * 
	 * @throws IOException
	 */
	private void test9() throws IOException {
		/**
		 * asBufferedImage() 返回BufferedImage
		 */
		BufferedImage thumbnail = Thumbnails.of("images/test.jpg").size(1280, 1024).asBufferedImage();
		ImageIO.write(thumbnail, "jpg", new File("C:/image_1280x1024_BufferedImage.jpg"));
	}
	
	/**
	 * 得到图片的宽
	 * 
	 * @param imgpath
	 *            图片绝对路径
	 * @return width
	 */
	public static int getImgWidth(String imgpath) {
		File file = new File(imgpath);
		BufferedImage bi = null;
		try {
			bi = ImageIO.read(file);
		} catch (IOException e) {
			e.printStackTrace();
		}
		
		int width = bi.getWidth();
		return width;
	}
	
	/**
	 * 得到图片的高
	 * 
	 * @param imgpath
	 *            图片绝对路径
	 * @return height
	 */
	public static int getImgHeight(String imgpath) {
		File file = new File(imgpath);
		BufferedImage bi = null;
		try {
			bi = ImageIO.read(file);
		} catch (IOException e) {
			e.printStackTrace();
		}
		
		int height = bi.getHeight();
		return height;
	}
	
	/**
	 * 比例不对时是否需要补白
	 * 
	 * @param srcImg
	 *            源图像文件地址
	 * @param destImg
	 *            缩放后的图像地址
	 * @param width
	 *            缩放后的宽度
	 * @param height
	 *            缩放后的高度
	 * @throws IOException
	 */
	public final static void buBai(String srcImg, String destImg, int width, int height) throws IOException {
		BufferedImage src = ImageIO.read(new File(srcImg)); // 读入文件
		Image tag = src.getScaledInstance(src.getWidth(), src.getHeight(), Image.SCALE_DEFAULT);
		BufferedImage image = new BufferedImage(width, height, BufferedImage.TYPE_INT_RGB);
		Graphics2D g = image.createGraphics();
		g.setColor(Color.white);
		g.fillRect(0, 0, width, height);
		
		int x = Math.max(0, (width - tag.getWidth(null)) / 2);
		int y = Math.max(0, (height - tag.getHeight(null)) / 2);
		
		//		test.a("分辨率1：" + tag.getWidth(null) + "*" + tag.getHeight(null));
		//		test.a("分辨率2：" + width + "*" + height);
		//		test.a("分辨率3：" + x + "*" + y);
		
		g.drawImage(tag, x, y, tag.getWidth(null), tag.getHeight(null), Color.white, null);
		g.dispose();
		ImageIO.write((BufferedImage) image, "JPG", new File(destImg));
		return;
		
	}
}
