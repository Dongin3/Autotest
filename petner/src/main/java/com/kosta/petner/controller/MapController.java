package com.kosta.petner.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

@Controller
public class MapController {

	//기존 map
	@RequestMapping(value = "/map", method = RequestMethod.GET)
	String map(Model model) {
		model.addAttribute("title", "kakaomap");
		model.addAttribute("page", "mypage/map");
		return "/layout/mypage_default";
	}
	
	//원의 반경을 이용한 map
	@RequestMapping(value = "/map2", method = RequestMethod.GET)
	String map2(Model model) {
		model.addAttribute("title", "kakaomap");
		model.addAttribute("page", "mypage/map2");
		return "/layout/mypage_default";
	}
	
}
