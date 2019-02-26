package cn.laochou.utils;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;


import cn.laochou.controller.FileController;

public class FileUtils {
	
	// 读取文本文件
	public static String readTextFile(String filePath) {
		StringBuffer stringBuffer = new StringBuffer();
		try {
			BufferedReader bufferedReader = new BufferedReader(new FileReader(new File(filePath)));
			String line = null;
			while((line = bufferedReader.readLine()) != null) {
				stringBuffer.append(line).append("\n");
			}
			bufferedReader.close();
		} catch (Exception e) {
			e.printStackTrace();
		}
		return stringBuffer.toString();
	}
	
	public static void main(String[] args) {
		String content = readTextFile("E:\\TOMCAT\\apache-tomcat-9.0.16\\apache-tomcat-9.0.16\\webapps\\HOMEWORK\\upload\\A.txt");
		System.out.println(FileController.getRow(content));
		System.out.println(FileController.getCol(content));
	}
}
