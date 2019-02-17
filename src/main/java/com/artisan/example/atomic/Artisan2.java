package com.artisan.example.atomic;

import lombok.Data;

@Data
public class Artisan2 {
	String name;
	public volatile int age;
}
