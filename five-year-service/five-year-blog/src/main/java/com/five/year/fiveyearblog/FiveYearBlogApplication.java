package com.five.year.fiveyearblog;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
@MapperScan("com.five.year.fiveyearblog.mapper")
public class FiveYearBlogApplication {

	public static void main(String[] args) {
		SpringApplication.run(FiveYearBlogApplication.class, args);
	}

}

