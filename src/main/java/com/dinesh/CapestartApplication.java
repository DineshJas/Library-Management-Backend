package com.dinesh;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

@SpringBootApplication
@RestController
public class CapestartApplication {

	public static void main(String[] args) {
		SpringApplication.run(CapestartApplication.class, args);
	}
	
	/**
	 * Ping & Pong
	 * 
	 * For testing the server status 
	 * 
	 * @return String
	 */
	@RequestMapping(value="/ping", method = RequestMethod.GET)
	public String ping(){
		return "pong";
	}

}
