package com.latesummer.service;

import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.thymeleaf.TemplateEngine;
import org.thymeleaf.context.Context;

import com.latesummer.BasicUtClass;
import com.latesummer.service.MailService;
public class MailTests extends BasicUtClass{
	@Autowired
	private MailService mailService;
	@Autowired
    private TemplateEngine templateEngine;
	
	private String to = "865839251@qq.com";
	
	@Test
	public void sendSimpleMail() {
		mailService.sendSimpleMail(to, "主题：简单邮件", "这是一封简单邮件！");
	}
	
	@Test
	public void sendAttachmentsMail() {
		mailService.sendAttachmentsMail(to, "主题：带附件的邮件", "有附件，请查收！", "C:\\Users\\0200283\\Desktop\\SearchTableruleRQ.xsd");
	}
	
	@Test
	public void sendTemplateMail() {
	    //创建邮件正文
	    Context context = new Context();
	    context.setVariable("id", "006");
	    String emailContent = templateEngine.process("emailTemplate", context);

	    mailService.sendHtmlMail(to,"主题：这是模板邮件",emailContent);
	}
	
	@Test
	public void sendHtmlMail() throws Exception {
	    String content="<html>\n" +
	            "<body>\n" +
	            "    <h3>hello world ! 这是一封Html邮件!</h3>\n" +
	            "</body>\n" +
	            "</html>";
	    mailService.sendHtmlMail(to,"test simple mail",content);
	}
	
	@Test
	public void sendInlineResourceMail() {
		String rscId = "rscId001";
		mailService.sendInlineResourceMail(to,
				"主题：嵌入静态资源的邮件",
				"<html><body>这是有嵌入静态资源：<h1>测试</h1><img src=\'cid:" + rscId + "\' ></body></html>",
				"C:\\Users\\0200283\\Desktop\\noPicture.jpg",
				rscId);
	}

}