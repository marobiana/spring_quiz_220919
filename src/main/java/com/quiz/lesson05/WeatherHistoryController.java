package com.quiz.lesson05;

import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.quiz.lesson05.bo.WeatherHistoryBO;
import com.quiz.lesson05.model.WeatherHistory;

import jakarta.servlet.http.HttpServletResponse;

@RequestMapping("/lesson05")
@Controller
public class WeatherHistoryController {

	@Autowired
	private WeatherHistoryBO weahterHistoryBO;
	
	// 목록 화면
	@GetMapping("/weather_history_view")
	public String weatherHistoryView(Model model) {
		List<WeatherHistory> weatherHistoryList = weahterHistoryBO.getWeatherHistoryList();
		model.addAttribute("weatherHistory", weatherHistoryList);
		return "weather/weatherHistory";
	}
	
	// 추가 화면
	@GetMapping("/add_weather_view")
	public String addWeatherView() {
		return "weather/addWeather";
	}
	
	// 추가 => 결과화면 목록 화면 이동(redirect)
	@PostMapping("/add_weather")
	public String addWeather(
			//@RequestParam("date") @DateTimeFormat(pattern="yyyy-MM-dd") Date date,
			@RequestParam("date") String date, // String으로 인서트를 해도 DB에서는 date 타입으로 잘 저장된다.
			@RequestParam("weather") String weather,
			@RequestParam("microDust") String microDust,
			@RequestParam("temperatures") double temperatures, 
			@RequestParam("precipitation") double precipitation,
			@RequestParam("windSpeed") double windSpeed,
			HttpServletResponse response) {
		
		// db insert
		weahterHistoryBO.addWeatherHistory(date, weather, microDust, temperatures, precipitation, windSpeed);
		
		// 목록 화면으로 리다이렉트
		//response.sendRedirect("/lesson05/weather_history_view");
		return "redirect:/lesson05/weather_history_view";
	}
	
}





