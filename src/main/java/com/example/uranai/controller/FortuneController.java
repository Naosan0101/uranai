package com.example.uranai.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;

import com.example.uranai.entity.Fortune;
import com.example.uranai.form.FortuneForm;
import com.example.uranai.service.FortuneService;

import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
@Controller
public class FortuneController {
	
	private final FortuneService fortuneService;
	
	//ホーム画面
	@GetMapping("/")
	public String showHome() {
		return "index";
	}
	
	//占い画面
	@GetMapping("/fortune")
	public String showFortune(Model model) {
		model.addAttribute("fortuneForm", new FortuneForm());
		return "fortune";
	}
	
	@PostMapping("/result")
	public String showResult(
			FortuneForm form,
			Model model) {
		
		Fortune fortune = fortuneService.getFortune(
									form.getYear(),
									form.getMood());
		
		String result =
				"今日、" +
				fortune.getMonthResult() +
				"、" +
				fortune.getDayResult() +
				"と良いことがあるかも✨";
		
		model.addAttribute("result", result);
		
		return "result";
	}

}
