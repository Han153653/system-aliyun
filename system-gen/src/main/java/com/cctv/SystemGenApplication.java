package com.cctv;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ComponentScan;

@SpringBootApplication
@ComponentScan(basePackages = {"com.cctv.*"})
@MapperScan(basePackages={"com.cctv.dao"})
public class SystemGenApplication {

	public static void main(String[] args) {
		SpringApplication.run(SystemGenApplication.class, args);
	}
}
