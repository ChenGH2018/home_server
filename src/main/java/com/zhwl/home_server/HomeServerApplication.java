package com.zhwl.home_server;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
@MapperScan("com.zhwl.home_server.mapper")
public class HomeServerApplication {
	public static void main(String[] args) {
		SpringApplication.run(HomeServerApplication.class, args);
	}
}
