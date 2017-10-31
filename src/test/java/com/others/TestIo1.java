package com.others;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.Date;

public class TestIo1 implements Runnable{
	//文件路径
	private final String path = "E:/TEST/";
	
	private final String maleStr = "男";
	private final String feMaleStr = "女";
	
	//线程数量
	private int threadNum = 4;
	//文件名数组
	private String[] fileNames;
	//任务池游标
	private int cur = 0;
	//男性
	private int male = 0;
	//女性
	private int feMale = 0;
	
	public static void main(String[] args) {
		TestIo1 testIo = new TestIo1();
		testIo.calcuPerson();
		
     }
	 public void calcuPerson(){
		 System.out.println("统计开始" + new Date());
		 this.setFileName();
		 for(int i=0;i<threadNum;i++){
			 System.out.println("启动线程"+(i+1));
			 new Thread(this).start();
		 }
	 }
	
     public void setFileName() {
        File f = new File(path);
        if (!f.exists()) {
             return;
         }
         File fa[] = f.listFiles();
         if(fa.length>0){
        	 this.fileNames = new String[fa.length];
         }
         for (int i = 0; i < fa.length; i++) {
             File fs = fa[i];
             //System.out.println(fs.getName());
             fileNames[i] = fs.getName();
         }
     }
   //返回任务池游标
 	private synchronized int[] getCur(){
 		int oldCur = cur;
 		if(cur < fileNames.length){
 			cur += 100;
 		if(cur >= fileNames.length){
 			cur = fileNames.length;
 			}
 		return new int[]{oldCur,cur};
 		}
 		return new int[]{-1,-1};
 	}
 	//统计男女生人数
 	private synchronized void calcuGender(int operate){
 		if(operate == 0){
 			this.feMale ++;
 		}else{
 			this.male ++;
 		}
 	}
 	
	@Override
	public void run(){
		while(cur < fileNames.length){
			int[] cueNew = getCur();
			int curStart = cueNew[0];
			int curEnd = cueNew[1];
			if(curEnd > 0){
				for (int j = curStart; j < curEnd; j++) {
					StringBuilder sb = new StringBuilder();
					try {
						FileReader fr = new FileReader(path + fileNames[j]);  
						char[] buf = new char[1024];  
				        int len = 0;  
				        while((len=fr.read(buf))!=-1){  
				        	sb.append(new String(buf,0,len));  
				        }
				        fr.close(); 
					} catch (FileNotFoundException e) {
						e.printStackTrace();
					} catch (IOException e) {
						e.printStackTrace();
					}
					String content = sb.toString();
					if(content.indexOf(feMaleStr)>0){
						calcuGender(0);
					}
					if(content.indexOf(maleStr)>0){
						calcuGender(1);
					}
				}
			}
			if(curEnd%10000==0){
				System.out.println("已处理超过"+curEnd+"个文件");
			}
			if(curEnd >= fileNames.length){
				System.out.println("统计结束" + new Date());
				System.out.println("男生数量："+male+"/"+"女生数量："+feMale);
			}
		}
	}
}
