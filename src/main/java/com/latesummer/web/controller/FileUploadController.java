package com.latesummer.web.controller;

import java.io.BufferedInputStream;
import java.io.BufferedOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStream;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.multipart.MultipartHttpServletRequest;

/**
 * 文件上传的Controller 
 * Create By Jenvi Sue On 2017年11月14日
 */
@Controller
@RequestMapping(value = "")
public class FileUploadController {
	
	public static final String CLASSPATH = new File(FileUploadController.class.getResource("/").getPath()).getPath() + File.separatorChar;

	@RequestMapping(value = "/upload", method = RequestMethod.GET)
	public String upload() {
		return "/fileupload";
	}

	@RequestMapping(value = "/upload/batch", method = RequestMethod.GET)
	public String batchUpload() {
		return "/mutifileupload";
	}

	/**
	 * 文件上传具体实现方法（单文件上传）
	 * @param file
	 * @return
	 */
	@PostMapping(value = "/upload")
	@ResponseBody
	public String upload(@RequestParam("file") MultipartFile file, HttpServletRequest request) {
		if (!file.isEmpty()) {
			// 这里只是简单例子，文件直接输出到项目路径下。
			// 实际项目中，文件需要输出到指定位置，需要在增加代码处理。
			// 还有关于文件格式限制、文件大小限制，详见：中配置。
			String contentType = file.getContentType();
	        String fileName = file.getOriginalFilename();
	        /*System.out.println("fileName-->" + fileName);
	        System.out.println("getContentType-->" + contentType);*/
	        String filePath = request.getSession().getServletContext().getRealPath("imgupload/");
			try {
				//FileUtil.uploadFile(file.getBytes(), filePath, fileName);
				
				BufferedOutputStream out = new BufferedOutputStream(
						new FileOutputStream(new File(file.getOriginalFilename())));
				out.write(file.getBytes());
				out.flush();
				out.close();
			} catch (FileNotFoundException e) {
				e.printStackTrace();
				return "上传失败," + e.getMessage();
			} catch (IOException e) {
				e.printStackTrace();
				return "上传失败," + e.getMessage();
			}
			return "上传成功";
		} else {
			return "上传失败，因为文件是空的.";
		}
	}

	/**
	 * 多文件上传 主要是使用了MultipartHttpServletRequest和MultipartFile
	 * @param request
	 * @return
	 */
	@RequestMapping(value = "/upload/batch", method = RequestMethod.POST)
	public @ResponseBody String batchUpload(HttpServletRequest request) {
		List<MultipartFile> files = ((MultipartHttpServletRequest) request).getFiles("file");
		MultipartFile file = null;
		BufferedOutputStream stream = null;
		for (int i = 0; i < files.size(); ++i) {
			file = files.get(i);
			if (!file.isEmpty()) {
				try {
					byte[] bytes = file.getBytes();
					stream = new BufferedOutputStream(new FileOutputStream(new File(file.getOriginalFilename())));
					stream.write(bytes);
					stream.close();
				} catch (Exception e) {
					stream = null;
					return "You failed to upload " + i + " => " + e.getMessage();
				}
			} else {
				return "You failed to upload " + i + " because the file was empty.";
			}
		}
		return "upload successful";
	}
	
	@RequestMapping("/download")
    public String downLoad(HttpServletResponse response){
        String filename="2.jpg";
        String filePath = "F:/test" ;
        File file = new File(filePath + "/" + filename);
        if(file.exists()){ //判断文件父目录是否存在
            response.setContentType("application/force-download");
            response.setHeader("Content-Disposition", "attachment;fileName=" + filename);
            
            byte[] buffer = new byte[1024];
            FileInputStream fis = null; //文件输入流
            BufferedInputStream bis = null;
            
            OutputStream os = null; //输出流
            try {
                os = response.getOutputStream();
                fis = new FileInputStream(file); 
                bis = new BufferedInputStream(fis);
                int i = bis.read(buffer);
                while(i != -1){
                    os.write(buffer);
                    i = bis.read(buffer);
                }
                
            } catch (Exception e) {
                // TODO Auto-generated catch block
                e.printStackTrace();
            }
            System.out.println("----------file download" + filename);
            try {
                bis.close();
                fis.close();
            } catch (IOException e) {
                // TODO Auto-generated catch block
                e.printStackTrace();
            }
        }
        return null;
    }
}