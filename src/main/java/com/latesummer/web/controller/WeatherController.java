package com.latesummer.web.controller;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import com.latesummer.domain.WeatherResponse;
import com.latesummer.service.WeatherDataService;

import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiImplicitParams;
import io.swagger.annotations.ApiOperation;

/**
 * 天气控制器.
 */
@Controller
@RequestMapping("/weather")	
public class WeatherController {

	@Autowired
	private WeatherDataService weatherDataService;
	
	@ApiOperation(value = "根据城市ID查询天气",notes = "根据城市ID查询天气")
    @ApiImplicitParams({
    	@ApiImplicitParam(paramType="query", name = "cityId", value = "城市ID", required = true, dataType = "String"),
    })
	@GetMapping("/cityId/{cityId}")
	public WeatherResponse getReportByCityId(@PathVariable("cityId") String cityId) {
		return weatherDataService.getDataByCityId(cityId);
	}
	
	@ApiOperation(value = "根据城市名查询天气",notes = "根据城市名称查询天气")
    @ApiImplicitParams({
    	@ApiImplicitParam(paramType="query", name = "cityName", value = "城市名", required = true, dataType = "String"),
    })
	//@GetMapping("/cityName/{cityName}")
	/*public WeatherResponse getReportByCityName(@PathVariable("cityName") String cityName) {
		return weatherDataService.getDataByCityName(cityName);
	}*/
	@GetMapping("/cityName")
	@ResponseBody
	public WeatherResponse getReportByCityName(HttpServletRequest request) {
		String cityName = request.getParameter("cityName");
		System.out.println("------------------------");
		System.out.println("cityName");
		System.out.println("------------------------");
		return weatherDataService.getDataByCityName(cityName);
	}

}
