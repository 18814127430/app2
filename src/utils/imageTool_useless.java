package utils;

import java.awt.AlphaComposite;
import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Image;
import java.awt.RenderingHints;
import java.awt.Toolkit;
import java.awt.color.ColorSpace;
import java.awt.geom.AffineTransform;
import java.awt.image.AffineTransformOp;
import java.awt.image.BufferedImage;
import java.awt.image.ColorConvertOp;
import java.awt.image.CropImageFilter;
import java.awt.image.FilteredImageSource;
import java.awt.image.ImageFilter;
import java.io.BufferedInputStream;
import java.io.BufferedOutputStream;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.net.URL;

import javax.imageio.ImageIO;

import com.opensymphony.xwork2.util.logging.Logger;
import com.opensymphony.xwork2.util.logging.LoggerFactory;

/**
 * 图片处理工具类：<br>
 * 功能：缩放图像、切割图像、图像类型转换、彩色转黑白、文字水印、图片水印等(图片失真问题仍未解决！！！！！！！！！！！！！）
 * 
 * @author Administrator
 */
public class imageTool_useless {
	protected static Logger logger = LoggerFactory.getLogger(imageTool_useless.class);
	
	/**
	 * 几种常见的图片格式
	 */
	public static String IMAGE_TYPE_GIF = "gif";// 图形交换格式
	public static String IMAGE_TYPE_JPG = "jpg";// 联合照片专家组
	public static String IMAGE_TYPE_JPEG = "jpeg";// 联合照片专家组
	public static String IMAGE_TYPE_BMP = "bmp";// 英文Bitmap（位图）的简写，它是Windows操作系统中的标准图像文件格式
	public static String IMAGE_TYPE_PNG = "png";// 可移植网络图形
	public static String IMAGE_TYPE_PSD = "psd";// Photoshop的专用格式Photoshop
	
	/**
	 * 程序入口：用于测试
	 * 
	 * @param args
	 * @throws Exception
	 */
	public static void main(String[] args) throws Exception {
		
		String p1 = "e:/";
		String p2 = "e:/ooooooo/";
		String s1 = "e:/a.jpg";
		String s2 = "e:/2.jpg";
		String s3 = "e:/3.jpg";
		
		imageTool_useless.zoomsCale(s2, p1 + test.NameByTime() + "--------zoomsCale.jpg", 2, true);
		imageTool_useless.zoomsWH(s1, p1 + test.NameByTime() + "--------zoomsCale.jpg", 1125, 1125, true);
		
		int scale = 1125 / imageTool_useless.getImgWidth(s1);
		imageTool_useless.zoomsCale(s1, test.NameByTime() + "--------zoomsCale.jpg", scale, true);
		
		imageTool_useless.rotate_Degree(s1, p1 + test.NameByTime() + "rotate_Degree.jpg", 30);
		imageTool_useless.rotateShunShiZhen90(s1, p1 + test.NameByTime() + "rotateShunShiZhen90.jpg");
		imageTool_useless.rotateOppO(s1, p1 + test.NameByTime() + "rotateOppO.jpg");
		imageTool_useless.rotateNiShiZhen90(s1, p1 + test.NameByTime() + "rotateNiShiZhen90.jpg");
		
		imageTool_useless.downloadImg("http://t3.qlogo.cn/mbloghead/79c1deae900c9607ceec/180", p1 + test.NameByTime()
				+ "makeImg.jpg");
		
		// 方法一：按比例缩放
		imageTool_useless.zoomsCale(s2, p1 + test.NameByTime() + "scale.jpg", 2, false);
		imageTool_useless.zoomsCale(s2, p1 + test.NameByTime() + "scale.jpg", 2, true);
		
		// 方法二：按高度和宽度缩放 
		imageTool_useless.zoomsWH(s2, p1 + test.NameByTime() + "scale2.jpg", 300, 600, true);
		
		// 方法一：按指定起点坐标和宽高切割
		imageTool_useless.cut(s2, p2 + test.NameByTime() + "cut.jpg", 0, 0, 400, 400);
		// 方法二：指定切片的行数和列数 imageTool.cutRC(s2, p2, 2, 2); 
		// 方法三：指定切片的宽度和高度 
		imageTool_useless.cutWH(s2, p2, 300, 300);
		
		imageTool_useless.trans(s2, "GIF", p1 + test.NameByTime() + "convert.gif");
		
		imageTool_useless.gray(s2, p1 + test.NameByTime() + "gray.jpg");
		
		// 方法一： 
		imageTool_useless.pressText1("我是水印文字", s2, p1 + test.NameByTime() + "pressText.jpg", "宋体", Font.BOLD, Color.white,
				80, 0, 0, 0.5f);
		// 方法二： 
		imageTool_useless.pressText2("我也是水印文字", s2, p1 + test.NameByTime() + "pressText2.jpg", "黑体", 36, Color.white, 80,
				0, 0, 0.5f);
		
		// 给图片添加图片水印：
		imageTool_useless.pressImage(s1, s2, p1 + test.NameByTime() + "pressImage.jpg", 0, 0, 0.5f);
		
		imageTool_useless.x_Pic(s1, s2, p1 + test.NameByTime() + "x_Pic1.jpg");
		imageTool_useless.x_Pic(s1, s2, p1 + test.NameByTime() + "x_Pic2.jpg");
		
		String str = p1 + test.NameByTime() + "y_Pic1.jpg";
		imageTool_useless.y_Pic(s1, s2, p1 + test.NameByTime() + "y_Pic2.jpg", 900);
		imageTool_useless.y_Pic(s1, s2, str, 1900);
		imageTool_useless.y_Pic(s3, str, p1 + test.NameByTime() + "y_Pic3.jpg", 1200);
		
		imageTool_useless.rotateOppO(s1, p1 + test.NameByTime() + "rotateOppO.jpg");
		imageTool_useless.imageMisro(s1, p1 + test.NameByTime() + "imageMisro1.jpg", 1);
		imageTool_useless.imageMisro(s1, p1 + test.NameByTime() + "imageMisro0.jpg", 0);
	}
	
	/**
	 * 得到图片的基本信息
	 * 
	 * @param imgpath
	 *            图片绝对路径
	 * @return size
	 */
	public static long getImgSize(String imgpath) {
		File file = new File(imgpath);
		long size = file.length();
		return size;
	}
	
	/**
	 * 得到图片的基本信息
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
	 * 得到图片的基本信息
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
	 * 生成图片函数
	 * 
	 * @param imgUrl
	 *            网站地址保存的绝对路径地址
	 * @param destImageFile
	 *            文件路径地址
	 * @throws IOException
	 */
	public static void downloadImg(String imgUrl, String destImageFile) throws IOException {
		// 创建流  
		BufferedInputStream in = new BufferedInputStream(new URL(imgUrl).openStream());
		
		File img = new File(destImageFile);
		// 生成图片  
		BufferedOutputStream out = new BufferedOutputStream(new FileOutputStream(img));
		byte[] buf = new byte[2048];
		int length = in.read(buf);
		while (length != -1) {
			out.write(buf, 0, length);
			length = in.read(buf);
		}
		in.close();
		out.close();
		return;
	}
	
	/**
	 * 缩放图像（按比例缩放）
	 * 
	 * @param srcImageFile
	 *            源图像文件地址
	 * @param destImageFile
	 *            缩放后的图像地址
	 * @param scale
	 *            缩放比例
	 * @param flag
	 *            缩放选择:true 放大; false 缩小;
	 * @throws IOException
	 */
	public final static void zoomsCale(String srcImageFile, String destImageFile, int scale, boolean flag)
			throws IOException {
		BufferedImage src = ImageIO.read(new File(srcImageFile)); // 读入文件
		int width = src.getWidth(); // 得到源图宽
		int height = src.getHeight(); // 得到源图长
		if (flag) {// 放大
			width = width * scale;
			height = height * scale;
		}
		else {// 缩小
			width = width / scale;
			height = height / scale;
		}
		Image image = src.getScaledInstance(width, height, Image.SCALE_DEFAULT);
		BufferedImage tag = new BufferedImage(width, height, BufferedImage.TYPE_INT_RGB);
		Graphics g = tag.getGraphics();
		g.drawImage(image, 0, 0, null); // 绘制缩小后的图
		g.dispose();
		ImageIO.write(tag, "JPEG", new File(destImageFile));// 输出到文件流
		return;
	}
	
	/**
	 * 缩放图像（按高度和宽度缩放）
	 * 
	 * @param srcImageFile
	 *            源图像文件地址
	 * @param destImageFile
	 *            缩放后的图像地址
	 * @param width
	 *            缩放后的宽度
	 * @param height
	 *            缩放后的高度
	 * @param bb
	 *            比例不对时是否需要补白：true为补白; false为不补白;
	 * @throws IOException
	 */
	public final static void zoomsWH(String srcImageFile, String destImageFile, int width, int height, boolean bb)
			throws IOException {
		double ratio = 0.0; // 缩放比例
		File f = new File(srcImageFile);
		BufferedImage bi = ImageIO.read(f);
		Image itemp = bi.getScaledInstance(width, height, bi.SCALE_SMOOTH);
		// 计算比例
		if ((bi.getHeight() > height) || (bi.getWidth() > width)) {
			if (bi.getHeight() > bi.getWidth()) {
				ratio = (new Integer(height)).doubleValue() / bi.getHeight();
			}
			else {
				ratio = (new Integer(width)).doubleValue() / bi.getWidth();
			}
			AffineTransformOp op = new AffineTransformOp(AffineTransform.getScaleInstance(ratio, ratio), null);
			itemp = op.filter(bi, null);
		}
		if (bb) {//补白
			BufferedImage image = new BufferedImage(width, height, BufferedImage.TYPE_INT_RGB);
			Graphics2D g = image.createGraphics();
			g.setColor(Color.white);
			g.fillRect(0, 0, width, height);
			if (width == itemp.getWidth(null))
				g.drawImage(itemp, 0, (height - itemp.getHeight(null)) / 2, itemp.getWidth(null), itemp.getHeight(null),
						Color.white, null);
			else
				g.drawImage(itemp, (width - itemp.getWidth(null)) / 2, 0, itemp.getWidth(null), itemp.getHeight(null),
						Color.white, null);
			g.dispose();
			itemp = image;
		}
		BufferedImage im = convertToBufferedImage(itemp);
		ImageIO.write(im, "JPG", new File(destImageFile));
		return;
	}
	
	/**
	 * 将Image对象转为BufferedImage
	 * 
	 * @param image
	 * @return
	 */
	public static BufferedImage convertToBufferedImage(Image image) {
		BufferedImage newImage = new BufferedImage(image.getWidth(null), image.getHeight(null), BufferedImage.TYPE_INT_RGB);
		Graphics2D g = newImage.createGraphics();
		g.drawImage(image, 0, 0, null);
		g.dispose();
		return newImage;
	}
	
	/**
	 * 图像切割(按指定起点坐标和宽高切割)
	 * 
	 * @param srcImageFile
	 *            源图像地址
	 * @param destImageFile
	 *            切片后的图像地址
	 * @param x
	 *            目标切片起点坐标X
	 * @param y
	 *            目标切片起点坐标Y
	 * @param width
	 *            目标切片宽度
	 * @param height
	 *            目标切片高度
	 * @throws
	 */
	public static void cut(String srcImageFile, String destImageFile, int x, int y, int width, int height)
			throws IOException {
		// 读取源图像
		BufferedImage bi = ImageIO.read(new File(srcImageFile));
		int srcWidth = bi.getHeight(); // 源图宽度
		int srcHeight = bi.getWidth(); // 源图高度
		if (srcWidth > 0 && srcHeight > 0) {
			Image image = bi.getScaledInstance(srcWidth, srcHeight, Image.SCALE_DEFAULT);
			// 四个参数分别为图像起点坐标和宽高
			// 即: CropImageFilter(int x,int y,int width,int height)
			ImageFilter cropFilter = new CropImageFilter(x, y, width, height);
			Image img = Toolkit.getDefaultToolkit().createImage(new FilteredImageSource(image.getSource(), cropFilter));
			BufferedImage tag = new BufferedImage(width, height, BufferedImage.TYPE_INT_RGB);
			Graphics g = tag.getGraphics();
			g.drawImage(img, 0, 0, width, height, null); // 绘制切割后的图
			g.dispose();
			// 输出为文件
			ImageIO.write(tag, "JPEG", new File(destImageFile));
		}
		return;
	}
	
	/**
	 * 图像切割（指定切片的行数和列数）
	 * 
	 * @param srcImageFile
	 *            源图像地址
	 * @param descDir
	 *            切片目标文件夹
	 * @param rows
	 *            目标切片行数。默认2，必须是范围 [1, 20] 之内
	 * @param cols
	 *            目标切片列数。默认2，必须是范围 [1, 20] 之内
	 * @throws IOException
	 */
	public static void cutRC(String srcImageFile, String descDir, int rows, int cols) throws IOException {
		if (rows <= 0 || rows > 20)
			rows = 2; // 切片行数
		if (cols <= 0 || cols > 20)
			cols = 2; // 切片列数
		// 读取源图像
		BufferedImage bi = ImageIO.read(new File(srcImageFile));
		int srcWidth = bi.getHeight(); // 源图宽度
		int srcHeight = bi.getWidth(); // 源图高度
		if (srcWidth > 0 && srcHeight > 0) {
			Image img;
			ImageFilter cropFilter;
			Image image = bi.getScaledInstance(srcWidth, srcHeight, Image.SCALE_DEFAULT);
			int destWidth = srcWidth; // 每张切片的宽度
			int destHeight = srcHeight; // 每张切片的高度
			// 计算切片的宽度和高度
			if (srcWidth % cols == 0) {
				destWidth = srcWidth / cols;
			}
			else {
				destWidth = (int) Math.floor(srcWidth / cols) + 1;
			}
			if (srcHeight % rows == 0) {
				destHeight = srcHeight / rows;
			}
			else {
				destHeight = (int) Math.floor(srcWidth / rows) + 1;
			}
			// 循环建立切片
			// 改进的想法:是否可用多线程加快切割速度
			for (int i = 0; i < rows; i++) {
				for (int j = 0; j < cols; j++) {
					// 四个参数分别为图像起点坐标和宽高
					// 即: CropImageFilter(int x,int y,int width,int height)
					cropFilter = new CropImageFilter(j * destWidth, i * destHeight, destWidth, destHeight);
					img = Toolkit.getDefaultToolkit().createImage(new FilteredImageSource(image.getSource(), cropFilter));
					BufferedImage tag = new BufferedImage(destWidth, destHeight, BufferedImage.TYPE_INT_RGB);
					Graphics g = tag.getGraphics();
					g.drawImage(img, 0, 0, null); // 绘制缩小后的图
					g.dispose();
					// 输出为文件
					ImageIO.write(tag, "JPEG", new File(descDir + "_r" + i + "_c" + j + ".jpg"));
				}
			}
		}
		return;
	}
	
	/**
	 * 图像切割（指定切片的宽度和高度）
	 * 
	 * @param srcImageFile
	 *            源图像地址
	 * @param descDir
	 *            切片目标文件夹
	 * @param destWidth
	 *            目标切片宽度。默认200
	 * @param destHeight
	 *            目标切片高度。默认150
	 * @throws IOException
	 */
	public static void cutWH(String srcImageFile, String descDir, int destWidth, int destHeight) throws IOException {
		if (destWidth <= 0)
			destWidth = 200; // 切片宽度
		if (destHeight <= 0)
			destHeight = 150; // 切片高度
		// 读取源图像
		BufferedImage bi = ImageIO.read(new File(srcImageFile));
		int srcWidth = bi.getHeight(); // 源图宽度
		int srcHeight = bi.getWidth(); // 源图高度
		if (srcWidth > destWidth && srcHeight > destHeight) {
			Image img;
			ImageFilter cropFilter;
			Image image = bi.getScaledInstance(srcWidth, srcHeight, Image.SCALE_DEFAULT);
			int cols = 0; // 切片横向数量
			int rows = 0; // 切片纵向数量
			// 计算切片的横向和纵向数量
			if (srcWidth % destWidth == 0) {
				cols = srcWidth / destWidth;
			}
			else {
				cols = (int) Math.floor(srcWidth / destWidth) + 1;
			}
			if (srcHeight % destHeight == 0) {
				rows = srcHeight / destHeight;
			}
			else {
				rows = (int) Math.floor(srcHeight / destHeight) + 1;
			}
			// 循环建立切片
			// 改进的想法:是否可用多线程加快切割速度
			for (int i = 0; i < rows; i++) {
				for (int j = 0; j < cols; j++) {
					// 四个参数分别为图像起点坐标和宽高
					// 即: CropImageFilter(int x,int y,int width,int height)
					cropFilter = new CropImageFilter(j * destWidth, i * destHeight, destWidth, destHeight);
					img = Toolkit.getDefaultToolkit().createImage(new FilteredImageSource(image.getSource(), cropFilter));
					BufferedImage tag = new BufferedImage(destWidth, destHeight, BufferedImage.TYPE_INT_RGB);
					Graphics g = tag.getGraphics();
					g.drawImage(img, 0, 0, null); // 绘制缩小后的图
					g.dispose();
					// 输出为文件
					ImageIO.write(tag, "JPEG", new File(descDir + "_r" + i + "_c" + j + ".jpg"));
				}
			}
		}
		return;
	}
	
	/**
	 * 图像类型转换：GIF->JPG、GIF->PNG、PNG->JPG、PNG->GIF(X)、BMP->PNG
	 * 
	 * @param srcImageFile
	 *            源图像地址
	 * @param formatName
	 *            包含格式非正式名称的 String：如JPG、JPEG、GIF等
	 * @param destImageFile
	 *            目标图像地址
	 * @throws IOException
	 */
	public static void trans(String srcImageFile, String formatName, String destImageFile) throws IOException {
		File f = new File(srcImageFile);
		f.canRead();
		f.canWrite();
		BufferedImage src = ImageIO.read(f);
		ImageIO.write(src, formatName, new File(destImageFile));
		return;
	}
	
	/**
	 * 彩色转为黑白
	 * 
	 * @param srcImageFile
	 *            源图像地址
	 * @param destImageFile
	 *            目标图像地址
	 * @throws IOException
	 */
	public static void gray(String srcImageFile, String destImageFile) throws IOException {
		BufferedImage src = ImageIO.read(new File(srcImageFile));
		ColorSpace cs = ColorSpace.getInstance(ColorSpace.CS_GRAY);
		ColorConvertOp op = new ColorConvertOp(cs, null);
		src = op.filter(src, null);
		ImageIO.write(src, "JPEG", new File(destImageFile));
		return;
	}
	
	/**
	 * 给图片添加文字水印
	 * 
	 * @param pressText
	 *            水印文字
	 * @param srcImageFile
	 *            源图像地址
	 * @param destImageFile
	 *            目标图像地址
	 * @param fontName
	 *            水印的字体名称
	 * @param fontStyle
	 *            水印的字体样式
	 * @param color
	 *            水印的字体颜色
	 * @param fontSize
	 *            水印的字体大小
	 * @param x
	 *            修正值
	 * @param y
	 *            修正值
	 * @param alpha
	 *            透明度：alpha 必须是范围 [0.0, 1.0] 之内（包含边界值）的一个浮点数字
	 * @throws IOException
	 */
	public static void pressText1(String pressText, String srcImageFile, String destImageFile, String fontName,
			int fontStyle, Color color, int fontSize, int x, int y, float alpha) throws IOException {
		
		File img = new File(srcImageFile);
		Image src = ImageIO.read(img);
		int width = src.getWidth(null);
		int height = src.getHeight(null);
		BufferedImage image = new BufferedImage(width, height, BufferedImage.TYPE_INT_RGB);
		Graphics2D g = image.createGraphics();
		g.drawImage(src, 0, 0, width, height, null);
		g.setColor(color);
		g.setFont(new Font(fontName, fontStyle, fontSize));
		g.setComposite(AlphaComposite.getInstance(AlphaComposite.SRC_ATOP, alpha));
		// 在指定坐标绘制水印文字
		g.drawString(pressText, (width - (getTextLength(pressText) * fontSize)) / 2 + x, (height - fontSize) / 2 + y);
		g.dispose();
		ImageIO.write((BufferedImage) image, "JPEG", new File(destImageFile));// 输出到文件流
		return;
	}
	
	/**
	 * 给图片添加文字水印
	 * 
	 * @param pressText
	 *            水印文字
	 * @param srcImageFile
	 *            源图像地址
	 * @param destImageFile
	 *            目标图像地址
	 * @param fontName
	 *            字体名称
	 * @param fontStyle
	 *            字体样式
	 * @param color
	 *            字体颜色
	 * @param fontSize
	 *            字体大小
	 * @param x
	 *            修正值
	 * @param y
	 *            修正值
	 * @param alpha
	 *            透明度：alpha 必须是范围 [0.0, 1.0] 之内（包含边界值）的一个浮点数字
	 * @throws IOException
	 */
	public static void pressText2(String pressText, String srcImageFile, String destImageFile, String fontName,
			int fontStyle, Color color, int fontSize, int x, int y, float alpha) throws IOException {
		
		File img = new File(srcImageFile);
		Image src = ImageIO.read(img);
		int width = src.getWidth(null);
		int height = src.getHeight(null);
		BufferedImage image = new BufferedImage(width, height, BufferedImage.TYPE_INT_RGB);
		Graphics2D g = image.createGraphics();
		g.drawImage(src, 0, 0, width, height, null);
		g.setColor(color);
		g.setFont(new Font(fontName, fontStyle, fontSize));
		g.setComposite(AlphaComposite.getInstance(AlphaComposite.SRC_ATOP, alpha));
		// 在指定坐标绘制水印文字
		g.drawString(pressText, (width - (getTextLength(pressText) * fontSize)) / 2 + x, (height - fontSize) / 2 + y);
		g.dispose();
		ImageIO.write((BufferedImage) image, "JPEG", new File(destImageFile));
		return;
	}
	
	/**
	 * 给图片添加图片水印
	 * 
	 * @param pressImg
	 *            水印图片
	 * @param srcImageFile
	 *            源图像地址
	 * @param destImageFile
	 *            目标图像地址
	 * @param x
	 *            修正值。 默认在中间
	 * @param y
	 *            修正值。 默认在中间
	 * @param alpha
	 *            透明度：alpha 必须是范围 [0.0, 1.0] 之内（包含边界值）的一个浮点数字
	 * @throws IOException
	 */
	public static void pressImage(String pressImg, String srcImageFile, String destImageFile, int x, int y, float alpha)
			throws IOException {
		
		File img = new File(srcImageFile);
		Image src = ImageIO.read(img);
		int wideth = src.getWidth(null);
		int height = src.getHeight(null);
		BufferedImage image = new BufferedImage(wideth, height, BufferedImage.TYPE_INT_RGB);
		Graphics2D g = image.createGraphics();
		g.drawImage(src, 0, 0, wideth, height, null);
		// 水印文件
		Image src_biao = ImageIO.read(new File(pressImg));
		int wideth_biao = src_biao.getWidth(null);
		int height_biao = src_biao.getHeight(null);
		g.setComposite(AlphaComposite.getInstance(AlphaComposite.SRC_ATOP, alpha));
		g.drawImage(src_biao, (wideth - wideth_biao) / 2, (height - height_biao) / 2, wideth_biao, height_biao, null);
		// 水印文件结束
		g.dispose();
		ImageIO.write((BufferedImage) image, "JPEG", new File(destImageFile));
		
		return;
	}
	
	/**
	 * 计算text的长度（一个中文算两个字符）
	 * 
	 * @param text
	 *            文字
	 * @return
	 */
	public final static int getTextLength(String text) {
		int length = 0;
		for (int i = 0; i < text.length(); i++) {
			if (new String(text.charAt(i) + "").getBytes().length > 1) {
				length += 2;
			}
			else {
				length += 1;
			}
		}
		return length / 2;
	}
	
	/**
	 * 横向合成图片
	 * 
	 * @param firstSrcImageFile
	 *            用于合成的图片1地址
	 * @param secondSrcImageFile
	 *            用于合成的图片2地址
	 * @param destImageFile
	 *            目标图像地址
	 * @throws IOException
	 */
	public static void x_Pic(String firstSrcImageFile, String secondSrcImageFile, String destImageFile) throws IOException {
		/* 1 读取第一张图片 */
		File fileOne = new File(firstSrcImageFile);
		BufferedImage imageFirst = ImageIO.read(fileOne);
		int width = imageFirst.getWidth();// 图片宽度
		int height = imageFirst.getHeight();// 图片高度
		int[] imageArrayFirst = new int[width * height];// 从图片中读取RGB
		imageArrayFirst = imageFirst.getRGB(0, 0, width, height, imageArrayFirst, 0, width);
		
		/* 1 对第二张图片做相同的处理 */
		File fileTwo = new File(secondSrcImageFile);
		BufferedImage imageSecond = ImageIO.read(fileTwo);
		int widthTwo = imageSecond.getWidth();// 图片宽度
		int heightTwo = imageSecond.getHeight();// 图片高度
		int[] imageArraySecond = new int[widthTwo * heightTwo];
		imageArraySecond = imageSecond.getRGB(0, 0, widthTwo, heightTwo, imageArraySecond, 0, widthTwo);
		
		int h = height;
		if (height < heightTwo) {
			h = heightTwo;
		}
		
		// 生成新图片
		BufferedImage imageResult = new BufferedImage(width + widthTwo, h, BufferedImage.TYPE_INT_RGB);
		imageResult.setRGB(0, 0, width, height, imageArrayFirst, 0, width);// 设置左半部分的RGB
		imageResult.setRGB(width, 0, widthTwo, heightTwo, imageArraySecond, 0, widthTwo);// 设置右半部分的RGB
		File outFile = new File(destImageFile);
		ImageIO.write(imageResult, "jpg", outFile);// 写图片
		return;
	}
	
	/**
	 * 纵向合成图片
	 * 
	 * @param firstSrcImageFile
	 *            上面源图路径
	 * @param secondSrcImageFile
	 *            下面源图路径
	 * @param destImageFile
	 *            合成图片路径
	 * @param width
	 *            指定合成图片的宽度
	 * @throws IOException
	 */
	public static void y_Pic(String firstSrcImageFile, String secondSrcImageFile, String destImageFile, int width)
			throws IOException {
		int w = width;
		int h = 0;
		/* 1 读取第一张图片 */
		File file_1 = new File(firstSrcImageFile);
		BufferedImage image_buffer_1 = ImageIO.read(file_1);
		int width_1 = image_buffer_1.getWidth();// 图片宽度
		int height_1 = image_buffer_1.getHeight();// 图片高度
		
		/* 1 对第二张图片做相同的处理 */
		File file_2 = new File(secondSrcImageFile);
		BufferedImage image_buffer_2 = ImageIO.read(file_2);
		int width_2 = image_buffer_2.getWidth();// 图片宽度
		int height_2 = image_buffer_2.getHeight();// 图片高度
		
		w = Math.min(w, width_1);
		w = Math.min(w, width_2);
		height_1 = height_1 * w / width_1;
		height_2 = height_2 * w / width_2;
		h = height_1 + height_2;
		
		BufferedImage newImage1 = new BufferedImage(w, height_1, image_buffer_1.getType());
		Graphics g1 = newImage1.getGraphics();
		g1.drawImage(image_buffer_1, 0, 0, w, height_1, null);
		g1.dispose();
		image_buffer_1 = newImage1;
		
		BufferedImage newImage2 = new BufferedImage(w, height_2, image_buffer_2.getType());
		Graphics g2 = newImage2.getGraphics();
		g2.drawImage(image_buffer_2, 0, 0, w, height_2, null);
		g2.dispose();
		image_buffer_2 = newImage2;
		
		int[] imageArrayFirst = new int[w * height_1];// 从图片中读取RGB
		imageArrayFirst = image_buffer_1.getRGB(0, 0, w, height_1, imageArrayFirst, 0, w);
		
		int[] imageArraySecond = new int[w * height_2];
		imageArraySecond = image_buffer_2.getRGB(0, 0, w, height_2, imageArraySecond, 0, w);
		
		// 生成新图片
		BufferedImage imageResult = new BufferedImage(w, h, BufferedImage.TYPE_INT_RGB);
		imageResult.setRGB(0, 0, w, height_1, imageArrayFirst, 0, w);// 设置左半部分的RGB
		imageResult.setRGB(0, height_1, w, height_2, imageArraySecond, 0, w);// 设置右半部分的RGB
		File outFile = new File(destImageFile);
		ImageIO.write(imageResult, "jpg", outFile);// 写图片
		
		return;
	}
	
	/**
	 * 旋转图片为指定角度
	 * 
	 * @param srcImageFile
	 *            源图路径
	 * @param destImageFile
	 *            输出图片路径
	 * @param degree
	 *            旋转角度
	 * @return
	 * @throws IOException
	 */
	public static void rotate_Degree(String srcImageFile, String destImageFile, final int degree) throws IOException {
		
		BufferedImage bufferedimage = ImageIO.read(new File(srcImageFile));
		
		int w = bufferedimage.getWidth();
		int h = bufferedimage.getHeight();
		int type = bufferedimage.getColorModel().getTransparency();
		BufferedImage img;
		Graphics2D graphics2d;
		(graphics2d = (img = new BufferedImage(h, w, type)).createGraphics()).setRenderingHint(
				RenderingHints.KEY_INTERPOLATION, RenderingHints.VALUE_INTERPOLATION_BILINEAR);
		graphics2d.rotate(Math.toRadians(degree), w / 2, h / 2 + (w > h ? (w - h) / 2 : (h - w) / 2));
		graphics2d.drawImage(bufferedimage, 0, 0, null);
		graphics2d.dispose();
		
		ImageIO.write(img, "jpg", new File(destImageFile));// 写图片
		return;
	}
	
	/**
	 * 图片逆时针旋转90度
	 * 
	 * @param srcImageFile
	 *            源图路径
	 * @param destImageFile
	 *            输出图片路径
	 * @return
	 * @throws IOException
	 */
	public static void rotateNiShiZhen90(String srcImageFile, String destImageFile) throws IOException {
		
		BufferedImage bufferedimage = ImageIO.read(new File(srcImageFile));
		int w = bufferedimage.getWidth();
		int h = bufferedimage.getHeight();
		int type = bufferedimage.getColorModel().getTransparency();
		BufferedImage img;
		Graphics2D graphics2d;
		(graphics2d = (img = new BufferedImage(h, w, type)).createGraphics()).setRenderingHint(
				RenderingHints.KEY_INTERPOLATION, RenderingHints.VALUE_INTERPOLATION_BILINEAR);
		graphics2d.rotate(Math.toRadians(270), w / 2, h / 2 + (w - h) / 2);
		graphics2d.drawImage(bufferedimage, 0, 0, null);
		graphics2d.dispose();
		
		ImageIO.write(img, "jpg", new File(destImageFile));// 写图片
		return;
	}
	
	/**
	 * 图片顺时针旋转90度
	 * 
	 * @param srcImageFile
	 *            源图路径
	 * @param destImageFile
	 *            输出图片路径
	 * @return
	 * @throws IOException
	 */
	public static void rotateShunShiZhen90(String srcImageFile, String destImageFile) throws IOException {
		
		BufferedImage bufferedimage = ImageIO.read(new File(srcImageFile));
		int w = bufferedimage.getWidth();
		int h = bufferedimage.getHeight();
		int type = bufferedimage.getColorModel().getTransparency();
		BufferedImage img;
		Graphics2D graphics2d;
		(graphics2d = (img = new BufferedImage(h, w, type)).createGraphics()).setRenderingHint(
				RenderingHints.KEY_INTERPOLATION, RenderingHints.VALUE_INTERPOLATION_BILINEAR);
		graphics2d.rotate(Math.toRadians(90), w / 2 - (w - h) / 2, h / 2);
		graphics2d.drawImage(bufferedimage, 0, 0, null);
		graphics2d.dispose();
		
		ImageIO.write(img, "jpg", new File(destImageFile));// 写图片
		
		return;
	}
	
	/**
	 * 对转
	 * 
	 * @param srcImageFile
	 *            源图路径
	 * @param destImageFile
	 *            输出图片路径
	 * @throws Exception
	 */
	public static void rotateOppO(String srcImageFile, String destImageFile) throws Exception {
		BufferedImage bufferedimage = ImageIO.read(new File(srcImageFile));
		int w = bufferedimage.getWidth();
		int h = bufferedimage.getHeight();
		int type = bufferedimage.getColorModel().getTransparency();
		BufferedImage img;
		Graphics2D graphics2d;
		(graphics2d = (img = new BufferedImage(w, h, type)).createGraphics()).setRenderingHint(
				RenderingHints.KEY_INTERPOLATION, RenderingHints.VALUE_INTERPOLATION_BILINEAR);
		graphics2d.rotate(Math.toRadians(180), w / 2, h / 2);
		graphics2d.drawImage(bufferedimage, 0, 0, null);
		graphics2d.dispose();
		ImageIO.write(img, "jpg", new File(destImageFile));
		return;
	}
	
	/**
	 * 图片镜像处理
	 * 
	 * @param srcImageFile
	 *            源图路径
	 * @param destImageFile
	 *            输出图片路径
	 * @param FX
	 *            0 为上下反转 1 为左右反转
	 */
	public static void imageMisro(String srcImageFile, String destImageFile, int FX) {
		try {
			BufferedImage bufferedimage = ImageIO.read(new File(srcImageFile));
			int w = bufferedimage.getWidth();
			int h = bufferedimage.getHeight();
			int[][] datas = new int[w][h];
			for (int i = 0; i < h; i++) {
				for (int j = 0; j < w; j++) {
					datas[j][i] = bufferedimage.getRGB(j, i);
				}
			}
			int[][] tmps = new int[w][h];
			if (FX == 0) {
				for (int i = 0, a = h - 1; i < h; i++, a--) {
					for (int j = 0; j < w; j++) {
						tmps[j][a] = datas[j][i];
					}
				}
			}
			else if (FX == 1) {
				for (int i = 0; i < h; i++) {
					for (int j = 0, b = w - 1; j < w; j++, b--) {
						tmps[b][i] = datas[j][i];
					}
				}
			}
			for (int i = 0; i < h; i++) {
				for (int j = 0; j < w; j++) {
					bufferedimage.setRGB(j, i, tmps[j][i]);
				}
			}
			ImageIO.write(bufferedimage, "jpg", new File(destImageFile));
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	/**
	 * 简易图片识别原理
	 * 
	 * @param srcImageFile
	 *            源图路径
	 */
	public static String discernImg(String srcImageFile) {
		String str = "";
		try {
			File fileOne = new File(srcImageFile);
			BufferedImage bi = ImageIO.read(fileOne);
			// 获取图像的宽度和高度
			int width = bi.getWidth();
			int height = bi.getHeight();
			
			// 扫描图片
			for (int i = 0; i < height; i++) {
				for (int j = 0; j < width; j++) {// 行扫描
					int dip = bi.getRGB(j, i);
					if (dip == -1)
						str = str + " ";
					else
						str = str + "♦";
				}
			}
			System.out.println(str);
		} catch (Exception e) {
			logger.error("图片识别出错", e);
		}
		return str;
	}
}
