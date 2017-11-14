package com.latesummer;

import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;
import com.latesummer.service.MailService;
public class MailTests extends BasicUtClass{
	@Autowired
	private MailService mailService;
	
	private String to = "865839251@qq.com";
	
	@Test
	public void sendSimpleMail() {
		mailService.sendSimpleMail(to, "主题：简单邮件", "测试邮件内容");
	}
	
	@Test
	public void sendAttachmentsMail() {
		mailService.sendAttachmentsMail(to, "主题：带附件的邮件", "有附件，请查收！", "C:\\Users\\0200283\\Desktop\\上线流程文档准备.docx");
	}
	
	@Test
	public void sendInlineResourceMail() {
		String rscId = "rscId001";
		mailService.sendInlineResourceMail(to,
				"主题：嵌入静态资源的邮件",
				"<html><body>这是有嵌入静态资源：<h1>测试</h1><img src=\'cid:" + rscId + "\' ></body></html>",
				"C:\\Users\\0200283\\Desktop\\上线流程文档准备.docx",
				rscId);
	}
}