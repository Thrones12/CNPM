package com.example.WebBanVe.controllers.web;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/")
public class HomeController {
	@GetMapping(value = {"home", "index", ""})
	private String getHome() {
		return "web/views/home";
	}
}
