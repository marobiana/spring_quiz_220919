package com.quiz.lesson05;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@RequestMapping("/lesson05")
@Controller
public class WeatherHistoryController {

	// 목록 화면
	@GetMapping("/weather_history_view")
	public String weatherHistoryView() {
		return "weather/weatherHistory";
	}
	
	// 추가 화면
	@GetMapping("/add_weather_view")
	public String addWeatherView() {
		return "weather/addWeather";
	}
}
