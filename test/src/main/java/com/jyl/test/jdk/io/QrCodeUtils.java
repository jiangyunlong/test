package com.jyl.test.jdk.io;

import java.awt.image.BufferedImage;
import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.InputStream;

import javax.imageio.ImageIO;
import javax.imageio.stream.ImageOutputStream;

import com.google.zxing.BarcodeFormat;
import com.google.zxing.MultiFormatWriter;
import com.google.zxing.client.j2se.MatrixToImageWriter;
import com.google.zxing.common.BitMatrix;

public class QrCodeUtils {  
	
	private int width = 200;
	private int height = 200;
	
	public InputStream encode(String url) {
        
        try {
            BitMatrix byteMatrix;
            byteMatrix = new MultiFormatWriter().encode(new String(url.getBytes("GBK"),"iso-8859-1"),  
                    BarcodeFormat.QR_CODE, width, height);
            BufferedImage image = MatrixToImageWriter.toBufferedImage(byteMatrix);
            return getImgStream(image);
        } catch (Exception e) {  
            e.printStackTrace();
        }
        return null;
    }  
    
    private InputStream getImgStream(BufferedImage bi){
    	InputStream is = null;
    	ImageOutputStream imOut;
    	ByteArrayOutputStream bs = new ByteArrayOutputStream();
        try {
            imOut = ImageIO.createImageOutputStream(bs);
            ImageIO.write(bi,"png",imOut);
            is= new ByteArrayInputStream(bs.toByteArray());
        } catch (IOException e) {
            e.printStackTrace();
        }
        return is;
    }
    
    public QrCodeUtils setWidth(int width) {
		this.width = width;
		return this;
	}

	public QrCodeUtils setHeight(int height) {
		this.height = height;
		return this;
	}
} 