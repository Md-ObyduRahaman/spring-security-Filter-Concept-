package com.example.SecureApp.controllers;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class HomeController {

	@GetMapping("/myhello")
	public String sayHi() {

		return "hi spring security !!";
	}
}
