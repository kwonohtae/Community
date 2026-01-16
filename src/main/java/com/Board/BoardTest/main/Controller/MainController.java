package com.Board.BoardTest.main.Controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import lombok.extern.slf4j.Slf4j;

@Slf4j
@Controller
@RequestMapping("/main")
public class MainController {

	@GetMapping("/main")
	public String getConnect() {
		log.info("확인 :::: !!!!");
		return "main/Main";
	}
}
