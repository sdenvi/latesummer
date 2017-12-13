package com.latesummer.web.controller;


import com.alibaba.fastjson.JSONObject;
import com.google.common.collect.Maps;
import com.latesummer.domain.LearnResouce;
import com.latesummer.service.LearnService;
import com.latesummer.util.ServletUtil;
import com.latesummer.util.StringUtil;

import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiImplicitParams;

import org.apache.commons.lang3.math.NumberUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.domain.Sort.Direction;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import java.util.Collections;
import java.util.HashMap;
import java.util.Map;

/**
 * 教程页面
 *
 * @Author Jenvi Sue
 * @Date 2017/11/25 1:07
 */
@Controller
@RequestMapping("/learn")
public class LearnController {
	
    @Autowired
    private LearnService learnService;
    @RequestMapping("")
    public String learn(){
        return "learn-resource";
    }
    
    /** 创建线程安全的Map */
    static Map<Long, LearnResouce> learnResouce = Collections.synchronizedMap(new HashMap<Long, LearnResouce>()); 
    
    @ApiOperation(value = "学习资源分页列表",notes = "可根据条件搜索查询")
    @ApiImplicitParams({
    	@ApiImplicitParam(paramType="query", name = "author", value = "用户名", required = false, dataType = "String"),
    	@ApiImplicitParam(paramType="query", name = "title", value = "标题", required = false, dataType = "String"),
    })
    @RequestMapping(value = "/queryLeanList",method = RequestMethod.POST,produces="application/json;charset=UTF-8")
    public void queryLearnList(HttpServletRequest request , HttpServletResponse response){
		// 取得当前页数,注意这是jqgrid自身的参数
        String page = request.getParameter("page");
		// 取得每页显示行数，,注意这是jqgrid自身的参数
        String rows = request.getParameter("rows");
        String author = request.getParameter("author");
        String title = request.getParameter("title");

        //封装搜索参数
        Map<String, String> params = Maps.newHashMap();
        params.put("author", author);
        params.put("title", title);
        //分页信息
        Sort sort = new Sort(Direction.ASC, "id");
        Pageable pageable = new PageRequest(NumberUtils.toInt(page, 1) - 1, NumberUtils.toInt(rows,10), sort);
        Page<LearnResouce> rs = this.learnService.learnResouceListByPage(params, pageable);
        
        JSONObject jo=new JSONObject();
        jo.put("rows", rs.getContent());
		//总页数
        jo.put("total", rs.getTotalPages());
		//查询出的总记录数
        jo.put("records",rs.getTotalElements());
        ServletUtil.createSuccessResponse(200, jo, response);
    }
    
	/**
	 * 新添教程
	 * 
	 * @param request
	 * @param response
	 */
	@RequestMapping(value = "/add", method = RequestMethod.POST)
	public void addLearn(HttpServletRequest request, HttpServletResponse response) {
		JSONObject result = new JSONObject();
		LearnResouce po = this.getFormParameter(request, response);
		if (StringUtil.isNull(po.getAuthor())) {
			result.put("message", "作者不能为空!");
			result.put("flag", false);
			ServletUtil.createSuccessResponse(200, result, response);
			return;
		}
		if (StringUtil.isNull(po.getTitle())) {
			result.put("message", "教程名称不能为空!");
			result.put("flag", false);
			ServletUtil.createSuccessResponse(200, result, response);
			return;
		}
		if (StringUtil.isNull(po.getUrl())) {
			result.put("message", "地址不能为空!");
			result.put("flag", false);
			ServletUtil.createSuccessResponse(200, result, response);
			return;
		}
		LearnResouce learnResouce = new LearnResouce();
		learnResouce.setAuthor(po.getAuthor());
		learnResouce.setTitle(po.getTitle());
		learnResouce.setUrl(po.getUrl());
		int index = 1;
		learnService.add(learnResouce);
		if (index > 0) {
			result.put("message", "教程信息添加成功!");
			result.put("flag", true);
		} else {
			result.put("message", "教程信息添加失败!");
			result.put("flag", false);
		}
		ServletUtil.createSuccessResponse(200, result, response);
	}
    
    /**
     * 修改教程
     * @param request
     * @param response
     */
	@RequestMapping(value = "/update", method = RequestMethod.POST)
	public void updateLearn(HttpServletRequest request, HttpServletResponse response) {
		JSONObject result = new JSONObject();
		String id = request.getParameter("id");
		LearnResouce po = this.getFormParameter(request, response);
		LearnResouce learnResouce = learnService.queryLearnResouceById(Long.valueOf(id));
		if (StringUtil.isNull(po.getAuthor())) {
			result.put("message", "作者不能为空!");
			result.put("flag", false);
			ServletUtil.createSuccessResponse(200, result, response);
			return;
		}
		if (StringUtil.isNull(po.getTitle())) {
			result.put("message", "教程名称不能为空!");
			result.put("flag", false);
			ServletUtil.createSuccessResponse(200, result, response);
			return;
		}
		if (StringUtil.isNull(po.getUrl())) {
			result.put("message", "地址不能为空!");
			result.put("flag", false);
			ServletUtil.createSuccessResponse(200, result, response);
			return;
		}
		learnResouce.setAuthor(po.getAuthor());
		learnResouce.setTitle(po.getTitle());
		learnResouce.setUrl(po.getUrl());
		int index = 1;
		learnService.update(learnResouce);
		System.out.println("修改结果=" + index);
		if (index > 0) {
			result.put("message", "教程信息修改成功!");
			result.put("flag", true);
		} else {
			result.put("message", "教程信息修改失败!");
			result.put("flag", false);
		}
		ServletUtil.createSuccessResponse(200, result, response);
	}
	
    /**
     * 删除教程
     * @param request
     * @param response
     */
    @RequestMapping(value="/delete",method = RequestMethod.POST)
    @ResponseBody
    public void deleteUser(HttpServletRequest request , HttpServletResponse response){
        String ids = request.getParameter("ids");
        System.out.println("ids==="+ids);
        JSONObject result = new JSONObject();
        //删除操作
        int index = 1;
        learnService.deleteByIds(ids.split(","));
        if(index>0){
            result.put("message","教程信息删除成功!");
            result.put("flag",true);
        }else{
            result.put("message","教程信息删除失败!");
            result.put("flag",false);
        }
        ServletUtil.createSuccessResponse(200, result, response);
    }
    
    /**
	 * 封装参数
	 * 
	 * @param request
	 * @param response
	 * @return
	 */
	public LearnResouce getFormParameter(HttpServletRequest request, HttpServletResponse response) {
		LearnResouce learnResouce = new LearnResouce();
		// 封装参数
		String author = request.getParameter("author");
		String title = request.getParameter("title");
		String url = request.getParameter("url");
		if (!StringUtil.isNull(author)) {
			learnResouce.setAuthor(author);
		}
		if (!StringUtil.isNull(title)) {
			learnResouce.setTitle(title);
		}
		if (!StringUtil.isNull(url)) {
			learnResouce.setUrl(url);
		}
		return learnResouce;
	}
}