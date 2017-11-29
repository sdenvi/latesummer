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

import com.latesummer.utils.FileUtil;

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
			String contentType = file.getContentType();
	        String fileName = file.getOriginalFilename();
	        //获取的的tamcat的路径，部署项目后相当于项目的路径
	        //String filePath = request.getSession().getServletContext().getRealPath("upload/");
	        //上传到工程根目录下的upload文件夹
	        String filePath = "upload/";
	        
	        /**
	        System.out.println("fileName-->" + fileName);
	        System.out.println("getContentType-->" + contentType);
	        */
	        
			try {
				FileUtil.uploadFile(file.getBytes(), filePath, fileName);
			} catch (FileNotFoundException e) {
				e.printStackTrace();
				return "You failed to upload  => " + e.getMessage();
			} catch (IOException e) {
				e.printStackTrace();
				return "You failed to upload  => " + e.getMessage();
			} catch (Exception e) {
				e.printStackTrace();
				return "You failed to upload  => " + e.getMessage();
			}
			return "upload successful";
		} else {
			return "You failed to upload because the file was empty.";
		}
	}

	/**
	 * 多文件上传 主要是使用了MultipartHttpServletRequest和MultipartFile
	 * @param request
	 * @return
	 */
	@PostMapping(value = "/upload/batch")
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