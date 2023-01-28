package com.springboot.bbsrestful;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.web.filter.HiddenHttpMethodFilter;

@SpringBootApplication
public class BbsRestfulApplication {

	public static void main(String[] args) {
		SpringApplication.run(BbsRestfulApplication.class, args);
	}

	// 이 부분 추가
	@Bean
	public HiddenHttpMethodFilter hiddenHttpMethodFilter(){
		return new HiddenHttpMethodFilter();
	}

}
