package com.wisely.ch8_4;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
//@Import({DynamicDataSourceRegister.class})
@MapperScan("com.wisely.ch8_4.mapper.*")
public class Ch84Application {

	public static void main(String[] args) {
		SpringApplication.run(Ch84Application.class, args);
	}
}
