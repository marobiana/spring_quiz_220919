package com.quiz.lesson06;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.quiz.lesson06.bo.FavoriteBO;
import com.quiz.lesson06.model.Favorite;

@RequestMapping("/lesson06")
@Controller
public class Lesson06Controller {

	@Autowired
	private FavoriteBO favoriteBO;
	
	// 즐겨찾기 추가 화면
	@GetMapping("/quiz01/add_favorite_view")
	public String addFavoriteView() {
		return "lesson06/quiz01/addFavorite";
	}
	
	// 즐겨찾기 추가 - AJAX 통신 요청
	@ResponseBody
	@PostMapping("/quiz01/add_favorite")
	public Map<String, String> addFavorite(
			@RequestParam("name") String name,
			@RequestParam("url") String url) {
		
		// db insert
		favoriteBO.addFavorite(name, url);
		
		// 성공 값 응답값
		Map<String, String> result = new HashMap<>();
		result.put("result", "성공");
		
		return result;   // jackson => JSON String
	}
	
	// 즐겨찾기 목록 화면
	@GetMapping("/quiz01/after_add_favorite_view")
	public String afterAddFavoriteView(Model model) {
		// 데이터 DB select
		List<Favorite> favoriteList = favoriteBO.getFavoriteList();
		
		// model에 담기
		model.addAttribute("favoriteList", favoriteList);
		
		return "lesson06/quiz01/afterAddFavorite";
	}
	
}




