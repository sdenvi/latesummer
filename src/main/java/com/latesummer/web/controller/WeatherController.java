package com.latesummer.web.controller;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.latesummer.domain.WeatherResponse;
import com.latesummer.service.WeatherDataService;

import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiImplicitParams;
import io.swagger.annotations.ApiOperation;

/**
 * 获取天气信息
 */
@RestController
@RequestMapping("/weather")	
public class WeatherController {

	@Autowired
	private WeatherDataService weatherDataService;
	
	@ApiOperation(value = "根据城市ID查询天气",notes = "根据城市ID查询天气")
    @ApiImplicitParams({
    	@ApiImplicitParam(paramType="query", name = "cityId", value = "城市ID", required = true, dataType = "String"),
    })
	@GetMapping("/cityId")
	public WeatherResponse getReportByCityId(HttpServletRequest request) {
		String cityId = request.getParameter("cityId");
		return weatherDataService.getDataByCityId(cityId);
	}
	
	@ApiOperation(value = "根据城市名查询天气",notes = "根据城市名称查询天气")
    @ApiImplicitParams({
    	@ApiImplicitParam(paramType="query", name = "cityName", value = "城市名", required = true, dataType = "String"),
    })
	@GetMapping("/cityName")
	public WeatherResponse getReportByCityName(HttpServletRequest request) {
		String cityName = request.getParameter("cityName");
		return weatherDataService.getDataByCityName(cityName);
	}

}
