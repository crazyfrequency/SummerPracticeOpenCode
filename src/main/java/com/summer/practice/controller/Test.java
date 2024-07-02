package com.summer.practice.controller;

import org.springframework.web.bind.annotation.*;


@RestController
@RequestMapping("/test")
public class Test {
	
	@GetMapping
	public String test() {
		return "test";
	}

}
