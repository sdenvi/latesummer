package com.others;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;

public class TestIo implements Runnable {
	// 文件路径
	private final String path = "E:/TEST/";

	private final String maleStr = "男";
	private final String feMaleStr = "女";

	// 线程数量
	private int threadNum = 10;
	// 文件名数组
	private String[] fileNames;
	// 任务池游标
	private int cur = 0;
	// 男性
	private int male = 0;
	// 女性
	private int feMale = 0;
	//时间统计
	private Long startTime; 
	private Long endTime; 

	public static void main(String[] args) {
		TestIo testIo = new TestIo();
		testIo.calcuPerson();
	}

	public void calcuPerson() {
		startTime =  System.currentTimeMillis();;
		//System.out.println("统计开始：" + startTime);
		this.setFileName();
		for (int i = 0; i < threadNum; i++) {
			System.out.println("启动线程：" + (i + 1));
			new Thread(this).start();
		}
	}

	public void setFileName() {
		File f = new File(path);
		if (!f.exists()) {
			return;
		}
		File fa[] = f.listFiles();
		if (fa.length > 0) {
			this.fileNames = new String[fa.length];
		}
		for (int i = 0; i < fa.length; i++) {
			File fs = fa[i];
			// System.out.println(fs.getName());
			fileNames[i] = fs.getName();
		}
	}

	// 返回任务池游标
	private synchronized int[] getCur() {
		int oldCur = cur;
		if (cur < fileNames.length) {
			cur += 100;
			if (cur >= fileNames.length) {
				cur = fileNames.length;
			}
			return new int[] { oldCur, cur };
		}
		return new int[] { -1, -1 };
	}

	// 统计男女生人数
	private synchronized void calcuGender(int operate) {
		if (operate == 0) {
			this.feMale++;
		} else {
			this.male++;
		}
	}

	@Override
	public void run() {
		while (cur < fileNames.length) {
			int[] cueNew = getCur();
			int curStart = cueNew[0];
			int curEnd = cueNew[1];
			if (curEnd > 0) {
				for (int j = curStart; j < curEnd; j++) {
					StringBuilder sb = new StringBuilder();
					try {
						FileReader fr = new FileReader(path + fileNames[j]);
						char[] buf = new char[1024];
						int len = 0;
						while ((len = fr.read(buf)) != -1) {
							sb.append(new String(buf, 0, len));
						}
						fr.close();
					} catch (FileNotFoundException e) {
						e.printStackTrace();
					} catch (IOException e) {
						e.printStackTrace();
					}
					String content = sb.toString();
					if (content.indexOf(feMaleStr) > 0) {
						calcuGender(0);
					}
					if (content.indexOf(maleStr) > 0) {
						calcuGender(1);
					}
				}
			}
			if (curEnd >= fileNames.length) {
				endTime = System.currentTimeMillis();
				//System.out.println("统计结束：" + endTime);
				System.out.println("花费时间：" + computeAndDisplayElapsedTime(startTime, endTime));
				System.out.println("男生数量：" + male + "/" + "女生数量：" + feMale);
			}
			if (curEnd % 10000 == 0) {
				System.out.println("已处理超过" + curEnd + "个文件");
			}
		}

	}
	
	/**
	 * 计算两个时间点直接逝去的毫秒数
	 *
	 */
	public String computeAndDisplayElapsedTime(Long startTime, Long endTime) {
		float seconds = (endTime - startTime) / 1000F;
		return  Float.toString(seconds) + " seconds.";
	}
}
