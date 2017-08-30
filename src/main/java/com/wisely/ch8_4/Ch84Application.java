package com.wisely.ch8_4;

import com.wisely.ch8_4.config.ds.fix.dynamic.DynamicDataSourceRegister;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Import;

@SpringBootApplication
@Import({DynamicDataSourceRegister.class})
public class Ch84Application {
//		extends SpringBootServletInitializer {

	/*@Override
	protected SpringApplicationBuilder configure(SpringApplicationBuilder application) {
		return application.sources(Ch84Application.class);
	}*/

	public static void main(String[] args) {
		SpringApplication.run(Ch84Application.class, args);
	}
}
