package com.latesummer.web.controller;

import java.util.*;

import com.alibaba.fastjson.JSONObject;
import com.google.common.collect.Maps;
import com.latesummer.domain.LearnResouce;
import com.latesummer.service.LearnService;
import com.latesummer.util.ServletUtil;
import com.latesummer.util.StringUtil;
import org.apache.commons.lang3.math.NumberUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.domain.Sort.Direction;
import org.springframework.data.web.PageableDefault;
import org.springframework.web.bind.annotation.*;

import com.latesummer.domain.User;
import com.latesummer.service.UserService;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@RestController
@RequestMapping(value = "/user")
public class UserController {

	@Autowired
	private UserService userService;
	/** 创建线程安全的Map */
	static Map<Long, User> user = Collections.synchronizedMap(new HashMap<Long, User>());

	@RequestMapping("/list")
	public ModelAndView list(HttpServletRequest request){
		List<User> userList =new ArrayList<User>();
		//封装搜索参数
		// 取得当前页数
		String currentPage = request.getParameter("currentPage");
		// 取得每页显示行数
		String rows = request.getParameter("rows");
		String id = request.getParameter("id");
		String name = request.getParameter("name");
		Map<String, String> params = Maps.newHashMap();
		params.put("id", id);
		params.put("name", name);
		//分页信息
		Sort sort = new Sort(Sort.Direction.ASC, "id");
		Pageable pageable = new PageRequest(NumberUtils.toInt(currentPage, 1) - 1, NumberUtils.toInt(rows,10), sort);
		Page<User> rs = this.userService.userListByPage(params, pageable);
		ModelAndView modelAndView = new ModelAndView("admin/user/user-list");
		modelAndView.addObject("rows",  rs.getContent());
		modelAndView.addObject("currentPage",  currentPage);
		modelAndView.addObject("total",  rs.getTotalPages());
		modelAndView.addObject("records",  rs.getTotalElements());
		return modelAndView;
	}

	@RequestMapping("/edit")
	public ModelAndView edit(HttpServletRequest request, HttpServletResponse response){
		ModelAndView modelAndView = new ModelAndView("admin/user/user-add");
		String id = request.getParameter("id");
		User user = userService.queryLearnResouceById(Long.valueOf(id));
		modelAndView.addObject("user",user);
		return modelAndView;
	}

	@RequestMapping(value = "/updateUser", method = RequestMethod.POST)
	public void updateUser(HttpServletRequest request, HttpServletResponse response) {
		JSONObject result = new JSONObject();
		String id = request.getParameter("id");
		User po = this.getFormParameter(request, response);
		User learnResouce = userService.queryLearnResouceById(Long.valueOf(id));
		if (StringUtil.isNull(po.getName())) {
			result.put("message", "姓名不能为空!");
			result.put("flag", false);
			ServletUtil.createSuccessResponse(200, result, response);
			return;
		}
		if (StringUtil.isNull(po.getEmail())) {
			result.put("message", "邮箱不能为空!");
			result.put("flag", false);
			ServletUtil.createSuccessResponse(200, result, response);
			return;
		}
		if (StringUtil.isNull(po.getCertificateNum())) {
			result.put("message", "证件号不能为空!");
			result.put("flag", false);
			ServletUtil.createSuccessResponse(200, result, response);
			return;
		}
		learnResouce.setName(po.getName());
		learnResouce.setEmail(po.getEmail());
		learnResouce.setCertificateNum(po.getCertificateNum());
		int index = 1;
		userService.update(learnResouce);
		if (index > 0) {
			result.put("message", "修改成功!");
			result.put("flag", true);
		} else {
			result.put("message", "修改失败!");
			result.put("flag", false);
		}
		ServletUtil.createSuccessResponse(200, result, response);
	}

	@RequestMapping(value="/deleteUsers",method = RequestMethod.POST)
	@ResponseBody
	public void deleteUsers(HttpServletRequest request , HttpServletResponse response){
		String ids = request.getParameter("ids");
		System.out.println("ids==="+ids);
		JSONObject result = new JSONObject();
		//删除操作
		int index = 1;
		userService.deleteByIds(ids.split(","));
		if(index>0){
			result.put("message","用户信息删除成功!");
			result.put("flag",true);
		}else{
			result.put("message","用户信息删除失败!");
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
	public User getFormParameter(HttpServletRequest request, HttpServletResponse response) {
		User user = new User();
		// 封装参数
		String name = request.getParameter("name");
		String email = request.getParameter("email");
		String certificateNum = request.getParameter("certificateNum");
		if (!StringUtil.isNull(name)) {
			user.setName(name);
		}
		if (!StringUtil.isNull(email)) {
			user.setEmail(email);
		}
		if (!StringUtil.isNull(certificateNum)) {
			user.setCertificateNum(certificateNum);
		}
		return user;
	}

}