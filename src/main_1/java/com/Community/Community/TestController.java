package com.community.community;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import lombok.extern.slf4j.Slf4j;

@Slf4j
@Controller
@RequestMapping("/test")
public class TestController {

	@GetMapping("/")
	public String getConnect() {
		log.info("확인 :::: !!!!");
		return "test/test";
	}
}
