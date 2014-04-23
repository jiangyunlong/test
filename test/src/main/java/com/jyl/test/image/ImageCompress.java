package com.jyl.test.image;

import java.awt.Image;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.Iterator;

import javax.imageio.ImageIO;
import javax.imageio.ImageReader;
import javax.imageio.stream.ImageInputStream;

public class ImageCompress {
	/**
	 * @author 林宇民(llade)
	 *         按原始比例缩小图片至targetLength宽度大小，并写入源文件（覆盖）。如果图片目标实际小于targetLength
	 *         ，则保持图像不变。 此方法在WEB应用中，可以方便裁剪提交上来的过大的图像，而不失真。
	 * @param imgsrc
	 * @param targetLength
	 * */

	public static void reduceImgWidth(String imgsrc, int targetLength) {
		reduceImg(imgsrc, targetLength, true);
	}

	/**
	 * @author 林宇民(llade)
	 *         按原始比例缩小图片至targetLength高度大小，并写入源文件（覆盖）。如果图片目标实际小于targetLength
	 *         ，则保持图像不变。 此方法在WEB应用中，可以方便裁剪提交上来的过大的图像，而不失真。
	 * @param imgsrc
	 * @param targetLength
	 * */

	public static void reduceImgHeight(String imgsrc, int targetLength) {
		reduceImg(imgsrc, targetLength, false);
	}

	/**
	 * @author 林宇民(llade)
	 *         按原始比例缩小图片至targetLength大小，并写入源文件（覆盖）。如果图片目标实际小于targetLength
	 *         ，则保持图像不变。 isWidth参数表示targetLength 指的是宽度还是高度，true为宽度。
	 *         此方法在WEB应用中，可以方便裁剪提交上来的过大的图像，而不失真。
	 * @param imgsrc
	 * @param targetLength
	 * @param isWidth
	 */

	public static void reduceImg(String imgsrc, int targetLength,
			boolean isWidth) {
		try {
			File srcfile = new File(imgsrc);
			if (!srcfile.exists()) {
				return;
			}
			Image src = ImageIO.read(srcfile);

			// 原始图像高和宽
			int width = src.getWidth(null);
			int height = src.getHeight(null);

			int widthdist = 0;
			int heightdist = 0;

			// 确定图像的缩放后的高和宽
			if (isWidth) {
				if (targetLength >= width)
					return;
				double scale = targetLength * 1.0 / width;
				widthdist = targetLength;
				heightdist = (int) (height * scale);
			} else {
				if (targetLength >= height)
					return;
				double scale = targetLength * 1.0 / height;
				widthdist = (int) (width * scale);
				heightdist = targetLength;
			}

			BufferedImage tag = new BufferedImage((int) widthdist,
					(int) heightdist, BufferedImage.TYPE_INT_RGB);

			tag.getGraphics().drawImage(
					src.getScaledInstance(widthdist, heightdist,
							Image.SCALE_SMOOTH), 0, 0, null);
			String formatName = getFormatName(srcfile);// 此句必须在new
			// FileOutputStream之前，因为是替换原图，FileOutputStream对象未关闭之前，ImageInputStream无法获得文件格式。
			// System.out.println(formatName);
			FileOutputStream out = new FileOutputStream(srcfile);
			ImageIO.write(tag, formatName, out);
			out.flush();
			out.close();

		} catch (IOException ex) {
			ex.printStackTrace();
		}
	}

	private static String getFormatName(File o) {
		try {
			// Create an image input stream on the image
			ImageInputStream iis = ImageIO.createImageInputStream(o);

			// Find all image readers that recognize the image format
			Iterator<ImageReader> iter = ImageIO.getImageReaders(iis);
			if (!iter.hasNext()) {
				// No readers found
				return null;
			}

			// Use the first reader
			ImageReader reader = (ImageReader) iter.next();

			// Close stream
			iis.close();

			// Return the format name
			return reader.getFormatName();
		} catch (IOException e) {
		}
		// The image could not be read
		return null;
	}

	public static void main(String[] args) {
		// 压缩 图片
		reduceImg("D:/test/20.jpg", 400, true);
		System.out.println("==3==");
	}
}
