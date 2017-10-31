package com.others;

import java.io.FileOutputStream;
import java.io.IOException;

public class ProduceFiles {

	// 文件路径
	private final String path = "E:/TEST/";
	private final String fileName = "student";
	private final String stuff = ".txt";
	private int ID = 1;

	public static void main(String args[]) {
		ProduceFiles produceFiles = new ProduceFiles();
		try {
			produceFiles.produce();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	public void produce() throws IOException {
		for (int i = 1; i < 100001; i++) {
			StringBuilder sb = new StringBuilder();
			//sb.append("ID:").append(ID).append("\n");
			if (i % 2 > 0) {
				sb.append("sex:男").append("\n");
				sb.append("Name:张家颖").append("\n");
			} else {
				sb.append("sex:女").append("\n");
				sb.append("Name:女孩").append("\n");
			}
			sb.append("ID:").append(ID).append("\n");
			sb.append("birthday:1990/04/01");
			String s = sb.toString();
			ID++;
			FileOutputStream fos = new FileOutputStream(path + fileName + i + stuff);
			fos.write(s.getBytes());
			fos.close();
		}
	}
}
