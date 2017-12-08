package com.latesummer.config;

import java.nio.charset.Charset;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.web.client.RestTemplateBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.converter.StringHttpMessageConverter;
import org.springframework.web.client.RestTemplate;

/**
 * REST配置.
 */
@Configuration
public class RestConfiguration {

	@Autowired  
    private RestTemplateBuilder builder;  

    @Bean  
    public RestTemplate restTemplate() {  
        return builder.build();  
    }
    
    @Bean
    public RestTemplate getRestTemplate(){
        StringHttpMessageConverter m = new StringHttpMessageConverter(Charset.forName("UTF-8"));  
        return new RestTemplateBuilder().additionalMessageConverters(m).build();
    }

}
