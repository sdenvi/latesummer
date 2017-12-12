package com.latesummer.util;

import java.io.BufferedOutputStream;
import java.io.File;
import java.io.FileOutputStream;

public class FileUtil {

	public static void uploadFile(byte[] file, String filePath, String fileName) throws Exception {
		
		//C:/Users/0200283/AppData/Local/Temp/tomcat-docbase.8921833096575767030.8080/upload
		File targetFile = new File(filePath);
		if (!targetFile.exists()) {
			targetFile.mkdirs();
		}
		BufferedOutputStream out = new BufferedOutputStream(
				new FileOutputStream(new File(filePath + fileName)));
		out.write(file);
		out.flush();
		out.close();
	}
}
