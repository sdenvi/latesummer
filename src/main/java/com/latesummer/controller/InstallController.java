package com.latesummer.controller;


import lombok.extern.slf4j.Slf4j;

import java.nio.file.Files;
import java.nio.file.Paths;

import javax.persistence.metamodel.SetAttribute;
import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpMethod;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping(value = "/install")
@Slf4j
public class InstallController extends BaseController {

    /**
     * 安装页
     *
     * @return
     */
    @RequestMapping(value = "/", method = RequestMethod.GET)
    public String index(HttpServletRequest request) {
        //boolean existInstall = Files.exists(Paths.get(AttachController.CLASSPATH + "install.lock"));
    	boolean existInstall = false;
        //int allow_reinstall = TaleConst.OPTIONS.getInt("allow_install", 0);
    	
    	int allow_reinstall = 0;

        if (allow_reinstall == 1) {
            request.setAttribute("is_install", false);
        } else {
            request.setAttribute("is_install", existInstall);
        }
        return "install";
    }
}
