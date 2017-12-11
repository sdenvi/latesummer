package com.latesummer.service.impl;

import java.io.IOException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.latesummer.domain.WeatherResponse;
import com.latesummer.service.WeatherDataService;
import com.latesummer.utils.RedisUtil;

/**
 *
 * 天气数据服务
 * @Author Jenvi Sue
 * @Date 2017/12/11 14:16
 */
@Service
public class WeatherDataServiceImpl implements WeatherDataService {

	@Autowired
	private RestTemplate restTemplate;
	@Autowired
    private RedisUtil redisUtil;

	private final Long EXPIRETIME = 24*3600L;
	private final String WEATHER_CACHE_NAME = "weather";
	private final String WEATHER_API = "http://wthrcdn.etouch.cn/weather_mini";
	//private final String WEATHER_API = "http://wthrcdn.etouch.cn/WeatherApi";

	@Override
	public WeatherResponse getDataByCityId(String cityId) {
		String uri = WEATHER_API + "?citykey=" + cityId;
		return this.doGetWeatherData(uri);
	}

	@Override
	public WeatherResponse getDataByCityName(String cityName) {
		String uri = WEATHER_API + "?city=" + cityName;
		return this.doGetWeatherData(uri);
	}
	
	/**
	 * 获取天气信息，并将数据写入Redis缓存
	 * @param uri
	 * @return
	 */
	/*private WeatherResponse doGetWeatherData(String uri){
		boolean isExists = redisUtil.exists(WEATHER_CACHE_NAME);
		String strBody = null;
		if (isExists) {
			strBody = redisUtil.get(WEATHER_CACHE_NAME).toString();
		} else {
			ResponseEntity<String> response = restTemplate.getForEntity(uri, String.class);
			if (response.getStatusCodeValue() == 200) {
				strBody = response.getBody();
			}
			redisUtil.set(WEATHER_CACHE_NAME, strBody, EXPIRETIME);
		}

		ObjectMapper mapper = new ObjectMapper();
		WeatherResponse weather = null;

		try {
			weather = mapper.readValue(strBody, WeatherResponse.class);
		} catch (IOException e) {
			e.printStackTrace();
		}

		return weather;
	}*/

	private WeatherResponse doGetWeatherData(String uri){
		ResponseEntity<String> response = restTemplate.getForEntity(uri, String.class);
		String strBody = null;
		
		if (response.getStatusCodeValue() == 200) {
			strBody = response.getBody();
		}

		ObjectMapper mapper = new ObjectMapper();
		WeatherResponse weather = null;

		try {
			weather = mapper.readValue(strBody, WeatherResponse.class);
		} catch (IOException e) {
			e.printStackTrace();
		}

		return weather;
	}

}
