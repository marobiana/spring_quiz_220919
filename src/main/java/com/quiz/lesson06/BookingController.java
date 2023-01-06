package com.quiz.lesson06;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@RequestMapping("/booking")
@Controller
public class BookingController {
	
	// 예약 목록 화면
	@GetMapping("/booking_list_view")
	public String bookingListView() {
		return "booking/bookingList";
	}
	
	// 예약하기 화면
	@GetMapping("/reservation_view")
	public String reservationView() {
		return "booking/reservation";
	}
	
	// 메인 화면
	@GetMapping("/main_view")
	public String mainView() {
		return "booking/main";
	}
}
