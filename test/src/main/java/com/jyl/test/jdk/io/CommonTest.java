package com.jyl.test.jdk.io;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.io.OutputStreamWriter;
import java.io.PrintStream;
import java.io.RandomAccessFile;
import java.io.Reader;
import java.io.Writer;

public class CommonTest {
	
	public static void main(String[] args) throws IOException {
		
		CommonTest commonTest = new CommonTest();
		
		//commonTest.createNewFile();
		//commonTest.deleteFile();
		//commonTest.createDirectory();
		//commonTest.findDirFile();
		//commonTest.findDirFullFile();
		//commonTest.isPathDir();
		/*String fileName="D:"+File.separator;
        File f=new File(fileName);  
        commonTest.recursiveDirAllFile(f);*/
		//commonTest.writeFileByRandomAccess();
		//commonTest.WriteStrInFile();
		//commonTest.appendStrInFile();
		//commonTest.readFileContent();
		//commonTest.readFileContent2();
		//commonTest.readFileContent3();
		//commonTest.readFileContent4();
		//commonTest.readFileContent5();
		//commonTest.writeInFile();
		//commonTest.readContentFromFile();
		//commonTest.copyFile();
		//commonTest.convertOutputByte2Str();
		//commonTest.convertInputByte2Str();
		//commonTest.convertUpper2LowerInMemory();
		//commonTest.testPiped();
		//commonTest.usePrintStreamOutput();
		//commonTest.usePrintStreamPrintOutput();
		//commonTest.useOutputStream();
		//commonTest.systemOutRedirect();
		//commonTest.systemErrRedirect();
		commonTest.systemInRedirect();
	}
	
	/**
	 * 创建一个新文件
	 */
	public void createNewFile(){
		
		//使用File.separator可以使代码跨平台
		String fileName = "D:" + File.separator + "hello.txt";  
        File f = new File(fileName);  
        try{  
            f.createNewFile();  
        }catch (Exception e) {  
            e.printStackTrace();  
        }
	}
	
	/**
	 * 删除一个文件
	 */
	public void deleteFile(){
		String fileName = "D:" + File.separator + "hello.txt";  
        File f = new File(fileName);  
        if(f.exists()){  
            f.delete();  
        }else{  
            System.out.println("文件不存在");  
        }  
	}
	
	/**
	 * 创建一个文件夹
	 */
	public void createDirectory(){
		String fileName = "D:" + File.separator + "hello";  
        File f = new File(fileName);  
        f.mkdir();  
	}
	
	/**
	 * 列出指定目录的所有文件夹和文件（包含隐藏文件）
	 */
	public void findDirFile(){
		String fileName = "D:" + File.separator;  
        File f = new File(fileName);  
        String[] str = f.list();  
        for (int i = 0; i < str.length; i++) {  
            System.out.println(str[i]);  
        }  
	}
	
	/**
	 * 列出指定目录的所有文件夹和文件（包含隐藏文件）
	 * 列出文件完整路径
	 */
	public void findDirFullFile(){
		String fileName = "D:" + File.separator;  
        File f = new File(fileName);  
        File[] str = f.listFiles();  
        for (int i = 0; i < str.length; i++) {  
            System.out.println(str[i]);  
        }  
	}
	
	/**
	 * 判断指定路径是否为目录
	 */
	public void isPathDir(){
		String fileName = "D:" + File.separator;  
        File f = new File(fileName);  
        if(f.isDirectory()){  
            System.out.println("YES");  
        }else{  
            System.out.println("NO");  
        }  
	}
	
	/**
	 * 遍历指定目录下的所有内容
	 */
	public void recursiveDirAllFile(File f){
		if(f != null){  
            if(f.isDirectory()){  
                File[] fileArray = f.listFiles();  
                if(fileArray != null){  
                    for (int i = 0; i < fileArray.length; i++) {  
                        //递归调用  
                    	recursiveDirAllFile(fileArray[i]);  
                    }  
                }  
            }else{  
                System.out.println(f);  
            }  
        }  
	}
	
	/**
	 * 使用RandomAccessFile写入文件  
	 * @throws IOException 
	 */
	public void writeFileByRandomAccess() throws IOException{
		String fileName = "D:" + File.separator + "hello.txt";  
        File f = new File(fileName);  
        RandomAccessFile demo = new RandomAccessFile(f,"rw");  
        demo.writeBytes("asdsad");  
        demo.writeInt(12);  
        demo.writeBoolean(true);  
        demo.writeChar('A');  
        demo.writeFloat(1.21f);  
        demo.writeDouble(12.123);  
        demo.close();     
	}
	
	/**
	 * 字节流  
	 * 向文件中写入字符串  
	 * @throws IOException 
	 */
	public void WriteStrInFile() throws IOException{
		String fileName = "D:" + File.separator + "hello.txt";  
        File f = new File(fileName);
        OutputStream out = new FileOutputStream(f);  
        String str = "你好";  
        byte[] b = str.getBytes();  
        out.write(b);  
        out.close();  
	}
	
	/**
	 * 字节流  
	 * 向文件中追加新内容
	 * @throws IOException 
	 */
	public void appendStrInFile() throws IOException{
		String fileName = "D:" + File.separator + "hello.txt";  
        File f = new File(fileName);  
        OutputStream out = new FileOutputStream(f,true);  
        String str = "Rollen";  
        //String str="\r\nRollen";  可以换行  
        byte[] b = str.getBytes();  
        for (int i = 0; i < b.length; i++) {  
            out.write(b[i]);  
        }  
        out.close();  
	}
	
	/**
	 * 字节流  
	 * 读文件内容 
	 * @throws IOException 
	 */
	public void readFileContent() throws IOException{
		String fileName = "D:" + File.separator + "hello.txt";  
        File f = new File(fileName);  
        InputStream in = new FileInputStream(f);  
        byte[] b = new byte[1024];  
        in.read(b);  
        in.close();  
        System.out.println(new String(b));  
	}
	
	/**
	 * 字节流  
	 * 读文件内容 
	 * @throws IOException 
	 */
	public void readFileContent2() throws IOException{
		String fileName = "D:" + File.separator + "hello.txt";  
        File f = new File(fileName);  
        InputStream in = new FileInputStream(f);  
        byte[] b = new byte[1024];  
        int len = in.read(b);  
        in.close();  
        System.out.println("读入长度为："+len);  
        System.out.println(new String(b,0,len));  
	}
	
	/**
	 * 字节流  
	 * 读文件内容,节省空间  
	 * @throws IOException 
	 */
	public void readFileContent3() throws IOException{
		String fileName = "D:" + File.separator + "hello.txt";  
        File f = new File(fileName);  
        InputStream in = new FileInputStream(f);  
        byte[] b = new byte[(int)f.length()];  
        in.read(b);  
        System.out.println("文件长度为："+f.length());  
        in.close();  
        System.out.println(new String(b));  
	}
	
	/**
	 * 字节流  
	 * 读文件内容,节省空间  
	 * @throws IOException 
	 */
	public void readFileContent4() throws IOException{
		String fileName = "D:" + File.separator + "hello.txt";  
        File f = new File(fileName);  
        InputStream in = new FileInputStream(f);  
        byte[] b = new byte[(int)f.length()];  
        for (int i = 0; i < b.length; i++) {  
            b[i]=(byte)in.read();  
        }  
        in.close();  
        System.out.println(new String(b));  
	}
	
	/**
	 * 字节流  
	 *读文件  
	 * @throws IOException 
	 */
	public void readFileContent5() throws IOException{
		String fileName = "D:" + File.separator + "hello.txt";  
        File f = new File(fileName);  
        InputStream in = new FileInputStream(f);  
        byte[] b = new byte[1024];  
        int count = 0;  
        int temp = 0;  
        while((temp = in.read()) != (-1)){  
            b[count++] = (byte)temp;  
        }  
        in.close();  
        System.out.println(new String(b));  
	}
	
	/**
	 * 写入数据
	 * @throws IOException 
	 */
	public void writeInFile() throws IOException{
		String fileName = "D:" + File.separator + "hello.txt";  
        File f = new File(fileName);  
        Writer out = new FileWriter(f);  
        String str = "hello";  
        out.write(str);  
        out.close();  
	}
	
	/**
	 * 从文件中读出内容
	 * @throws IOException 
	 */
	public void readContentFromFile() throws IOException{
		String fileName = "D:" + File.separator + "hello.txt";
        File f = new File(fileName);  
        char[] ch = new char[100];  
        Reader read = new FileReader(f);  
        int temp = 0;  
        int count = 0;  
        while((temp = read.read()) != (-1)){  
            ch[count++] = (char)temp;  
        }  
        read.close();  
        System.out.println("内容为"+new String(ch,0,count));  
	}
	
	/**
	 * 文件复制
	 * @throws IOException 
	 */
	public void copyFile() throws IOException{
        File file1 = new File("D:" + File.separator + "hello.txt");  
        File file2 = new File("D:" + File.separator + "copyHello.txt");  
          
        if(!file1.exists()){  
            System.out.println("被复制的文件不存在");  
            System.exit(1);  
        }  
        InputStream input = new FileInputStream(file1);  
        OutputStream output = new FileOutputStream(file2);  
        if((input != null)&&(output != null)){  
            int temp = 0;  
            while((temp = input.read()) != (-1)){  
                output.write(temp);  
            }  
        }  
        input.close();  
        output.close();   
	}
	
	/**
	 * 字节输出流转换为字符输出流
	 * @throws IOException 
	 */
	public void convertOutputByte2Str() throws IOException{
		String fileName = "d:" + File.separator + "hello.txt";  
        File file = new File(fileName);  
        Writer out = new OutputStreamWriter(new FileOutputStream(file));  
        out.write("hello");  
        out.close();  
	}
	
	/**
	 * 字节输入流转换为字符输入流
	 * @throws IOException 
	 */
	public void convertInputByte2Str() throws IOException{
		String fileName = "d:" + File.separator + "hello.txt";  
        File file = new File(fileName);  
        Reader read = new InputStreamReader(new FileInputStream(file));  
        char[] b = new char[100];  
        int len = read.read(b);  
        System.out.println(new String(b,0,len));  
        read.close();  
	}
	
	/**
	 * 使用内存操作流降一个大写字母转换为小写字母
	 * @throws IOException 
	 */
	public void convertUpper2LowerInMemory() throws IOException{
		String str = "ROLLENHOLT";  
        ByteArrayInputStream input = new ByteArrayInputStream(str.getBytes());  
        ByteArrayOutputStream output = new ByteArrayOutputStream();  
        int temp=0;  
        while((temp=input.read())!=-1){  
            char ch=(char)temp;  
            output.write(Character.toLowerCase(ch));  
        }  
        String outStr=output.toString();  
        input.close();  
        output.close();  
        System.out.println(outStr);
	}
	
	/**
	 * 验证管道流
	 * 管道流主要可以进行两个线程之间的通信。
	 */
	public void testPiped(){
		Send send = new Send();  
        Receive receive = new Receive();  
        try{  
        	//管道连接  
            send.getOut().connect(receive.getInput());  
        }catch (Exception e) {  
            e.printStackTrace();  
        }
        new Thread(send).start();  
        new Thread(receive).start();  
    }
	
	/**
	 * 使用PrintStream输出
	 * @throws FileNotFoundException 
	 */
	public void usePrintStreamOutput() throws FileNotFoundException{
		PrintStream print = new PrintStream(new FileOutputStream(new File("d:" 
                + File.separator + "hello.txt")));  
        print.println(true);  
        print.println("Rollen");  
        print.close();  
	}
	
	/**
	 * 使用PrintStream格式化输出
	 * @throws FileNotFoundException 
	 */
	public void usePrintStreamPrintOutput() throws FileNotFoundException{
		PrintStream print = new PrintStream(new FileOutputStream(new File("d:" 
                + File.separator + "hello.txt")));  
        String name="Rollen";  
        int age=20;  
        print.printf("姓名：%s. 年龄：%d.",name,age);  
        print.close(); 
	}
	
	/**
	 * 使用OutputStream向屏幕上输出内容
	 */
	public void useOutputStream(){
		OutputStream out = System.out;  
        try{  
            out.write("hello".getBytes());  
        }catch (Exception e) {  
            e.printStackTrace();  
        }  
        try{  
            out.close();  
        }catch (Exception e) {  
            e.printStackTrace();  
        }  
	}
	
	/**
	 * 输出信息重定向
	 */
	public void systemOutRedirect(){
		// 此刻直接输出到屏幕  
        System.out.println("hello");  
        File file = new File("d:" + File.separator + "hello.txt");  
        try{  
            System.setOut(new PrintStream(new FileOutputStream(file)));
        }catch(FileNotFoundException e){  
            e.printStackTrace();  
        }  
        System.out.println("这些内容在文件中才能看到哦！");  
	}
	
	/**
	 * 输出错误信息重定向
	 */
	public void systemErrRedirect(){
		File file = new File("d:" + File.separator + "hello.txt");  
        System.err.println("这些在控制台输出");  
        try{  
            System.setErr(new PrintStream(new FileOutputStream(file)));  
        }catch(FileNotFoundException e){  
            e.printStackTrace();  
        }  
        System.err.println("这些在文件中才能看到哦！");
	}
	
	/**
	 * 输出重定向
	 */
	public void systemInRedirect(){
		File file = new File("d:" + File.separator + "hello.txt");  
        if(!file.exists()){  
            return;  
        }else{  
            try{  
                System.setIn(new FileInputStream(file));  
            }catch(FileNotFoundException e){  
                e.printStackTrace();  
            }  
            byte[] bytes = new byte[1024];  
            int len = 0;  
            try{  
                len = System.in.read(bytes);  
            }catch(IOException e){  
                e.printStackTrace();  
            }  
            System.out.println("读入的内容为：" + new String(bytes, 0, len));  
        }
	}
}
