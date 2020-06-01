package com.ondiascode.hellorest2;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@SpringBootApplication
@RestController
public class HelloRest2Application {

	public static void main(String[] args) {
		SpringApplication.run(HelloRest2Application.class, args);
	}

	@RequestMapping("/greeting")
	public String helloGreeting(){
		return "Hello Rest";
	}

}
