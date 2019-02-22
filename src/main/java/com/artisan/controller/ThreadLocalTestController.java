package com.artisan.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.artisan.example.threadLocal.ArtisanUser;
import com.artisan.example.threadLocal.RequestHolder;

@RestController
@RequestMapping("/artisan/threadLocal")
public class ThreadLocalTestController {

	
	@GetMapping("/getCurrentUser")
	public ArtisanUser getCurrentUser() {
		return RequestHolder.getCurrentUser();
	}
}
