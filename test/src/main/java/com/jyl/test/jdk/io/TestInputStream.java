package com.jyl.test.jdk.io;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;

public class TestInputStream {
	
	public static void main(String[] args) throws IOException {
		
		QrCodeUtils qrCodeUtils = new QrCodeUtils();
		
		InputStream ins = qrCodeUtils.setWidth(200).setHeight(200).encode("xxxxxxxx");
		
		OutputStream os = new FileOutputStream(TestInputStream.createTmpImage());
		int bytesRead = 0;
		byte[] buffer = new byte[8192];
		while ((bytesRead = ins.read(buffer, 0, 8192)) != -1) {
			os.write(buffer, 0, bytesRead);
		}
		os.close();
		ins.close();
	}

	public static File createTmpImage() throws IOException{
	
		File path = new File("D:/tmp/");
		return File.createTempFile("tmp", ".jpg", path);
	}
}
