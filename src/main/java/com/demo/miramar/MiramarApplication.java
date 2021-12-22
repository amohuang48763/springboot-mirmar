package com.demo.miramar;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.web.servlet.support.SpringBootServletInitializer;

@SpringBootApplication
public class MiramarApplication {

	public static void main(String[] args) {
		SpringApplication.run(MiramarApplication.class, args);
		System.out.println("start.");
	}

}
