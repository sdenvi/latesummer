package com.latesummer;

import com.alibaba.druid.pool.DruidDataSource;

import springfox.documentation.swagger2.annotations.EnableSwagger2;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.core.env.Environment;
import javax.sql.DataSource;

@EnableSwagger2
@SpringBootApplication
public class Application {

	public static void main(String[] args) {
		SpringApplication.run(Application.class, args);
	}

	@Autowired
	private Environment env;

	//destroy-method="close"的作用是当数据库连接不使用的时候,就把该连接重新放到数据池中,方便下次使用调用.
	@Bean(destroyMethod = "close")
	public DataSource dataSource() {
		DruidDataSource dataSource = new DruidDataSource();
		dataSource.setUrl(env.getProperty("spring.datasource.url"));
		//用户名
		dataSource.setUsername(env.getProperty("spring.datasource.username"));
		//密码
		dataSource.setPassword(env.getProperty("spring.datasource.password"));
		dataSource.setDriverClassName(env.getProperty("spring.datasource.driver-class-name"));
		//初始化时建立物理连接的个数
		dataSource.setInitialSize(Integer.valueOf(env.getProperty("spring.datasource.initial-size")));
		//最大连接池数量
		dataSource.setMaxActive(Integer.valueOf(env.getProperty("spring.datasource.initial-size")));
		//最小连接池数量
		dataSource.setMinIdle(Integer.valueOf(env.getProperty("spring.datasource.min-idle")));
		//获取连接时最大等待时间，单位毫秒。
		dataSource.setMaxWait(Integer.valueOf(env.getProperty("spring.datasource.max-wait")));
		//用来检测连接是否有效的sql
		dataSource.setValidationQuery(env.getProperty("spring.datasource.validation-query"));
		//申请连接时执行validationQuery检测连接是否有效
		dataSource.setTestOnBorrow(Boolean.valueOf(env.getProperty("spring.datasource.test-on-borrow")));
		//建议配置为true，不影响性能，并且保证安全性。
		dataSource.setTestWhileIdle(Boolean.valueOf(env.getProperty("spring.datasource.test-while-idle")));
		//是否缓存preparedStatement，也就是PSCache
		dataSource.setPoolPreparedStatements(Boolean.valueOf(env.getProperty("spring.datasource.pool-prepared-statements")));
		return dataSource;
	}
}