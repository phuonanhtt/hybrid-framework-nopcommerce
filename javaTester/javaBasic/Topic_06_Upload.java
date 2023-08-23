package javaBasic;

import java.io.File;

public class Topic_06_Upload {
	static String[] fileNames = {"space.jpg","star.jpg","tree.jpg"};	
	
	static String fullFileName = "";
	
	public static void main(String[] args) {
		String filePath = System.getProperty("user.dir") + File.separator + "uploadFiles" + File.separator;
		System.out.println(filePath);
		
		for (String file : fileNames) {
			fullFileName = fullFileName + filePath + file + "\n";
			System.out.println(fullFileName);
		}
		fullFileName = fullFileName.trim();
	}
}
