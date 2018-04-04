package com.latesummer.web.controller;

import com.alibaba.fastjson.JSONObject;
import com.google.common.collect.Maps;
import com.latesummer.domain.LearnResouce;
import com.latesummer.service.LearnService;
import org.apache.commons.lang3.math.NumberUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

/**
 * 主页
 * @RestController是Spring4之后加入的注解，
 * 原来在@Controller中返回json需要@ResponseBody来配合，
 * 如果直接用@RestController替代@Controller就不需要再配置@ResponseBody，默认返回json格式。
 * Create By Jenvi Sue On 2017年10月25日
 */

@Controller
public class IndexController {

    @Autowired
    private LearnService learnService;

    @RequestMapping("/main")
    public String main(){
        return "main";
    }

    @RequestMapping("/index")
    public String index(){
        return "admin/index";
    }

    @RequestMapping("/admin_list")
    public ModelAndView admin_list(HttpServletRequest request , HttpServletResponse response){
        List<LearnResouce> learnList =new ArrayList<LearnResouce>();
        //封装搜索参数
        // 取得当前页数,注意这是jqgrid自身的参数
        String page = request.getParameter("page");
        // 取得每页显示行数，,注意这是jqgrid自身的参数
        String rows = request.getParameter("rows");
        String author = request.getParameter("author");
        String title = request.getParameter("title");
        Map<String, String> params = Maps.newHashMap();
        params.put("author", author);
        params.put("title", title);
        //分页信息
        Sort sort = new Sort(Sort.Direction.ASC, "id");
        Pageable pageable = new PageRequest(NumberUtils.toInt(page, 1) - 1, NumberUtils.toInt(rows,10), sort);
        Page<LearnResouce> rs = this.learnService.learnResouceListByPage(params, pageable);
        ModelAndView modelAndView = new ModelAndView("/admin/admin-list");
        modelAndView.addObject("rs", rs);
        modelAndView.addObject("rows",  rs.getContent());
        modelAndView.addObject("total",  rs.getTotalPages());
        modelAndView.addObject("records",  rs.getTotalElements());
        return modelAndView;
    }

    @RequestMapping("/welcome")
    public String welcome(){
        return "admin/welcome";
    }
}
