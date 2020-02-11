package com.db.votacao.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
public class HelloController {
	
	private static final String WELCOME = "Seja bem vindo a votacao";

	@RequestMapping("/")
	@ResponseBody
	public String hello() {
		return WELCOME;
	}	
}
