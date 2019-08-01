package com.chicex.PictureCloud;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.builder.SpringApplicationBuilder;
import org.springframework.boot.web.servlet.support.SpringBootServletInitializer;


@SpringBootApplication
public class PictureCloudApplication extends SpringBootServletInitializer {
	public static void main(String[] args) {
		//参数加载
		for(int i = 0; args.length > i; i++){
			switch (args[i]){
				case "-u" : ApplicationStartUpConfig.username = args[++i];break;
				case "-p" : ApplicationStartUpConfig.password = args[++i];break;
			}
		}
		SpringApplication.run(PictureCloudApplication.class, args);
	}

	/*
	*打jar包用
	*/
	@Override
	protected SpringApplicationBuilder configure(SpringApplicationBuilder builder){
		return builder.sources(this.getClass());
	}
}
