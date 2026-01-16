package com.Board.BoardTest.main.Controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

import lombok.extern.slf4j.Slf4j;

@Slf4j
@Controller
public class mainController {

	@GetMapping("/")
	public String getMain() {
		return "main";
	}
}
