package com.quiz.lesson06;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.quiz.lesson06.bo.BookingBO;
import com.quiz.lesson06.model.Booking;

@RequestMapping("/booking")
@Controller
public class BookingController {
	
	@Autowired
	private BookingBO bookingBO;
	
	// 문제1) 예약 목록 화면
	@GetMapping("/booking_list_view")
	public String bookingListView(Model model) {
		List<Booking> bookingList = bookingBO.getBookingList();
		model.addAttribute("bookingList", bookingList);
		return "booking/bookingList";
	}
	
	// 문제1) 예약 삭제 AJAX 통신 요청
	@ResponseBody
	@DeleteMapping("/delete_booking")
	public Map<String, Object> deleteBooking(
			@RequestParam("id") int id) {
		
		Map<String, Object> result = new HashMap<>();
		int row = bookingBO.deleteBookingById(id);
		if (row > 0) {
			result.put("code", 1); // 성공
			result.put("result", "성공");
		} else {
			// {"code":500, "result":"실패", "error_message":"삭제하는데 실패했습니다."}
			result.put("code", 500);
			result.put("result", "실패");
			result.put("error_message", "삭제하는데 실패했습니다.");
		}
		
		return result;
	}
	
	// 문제2) 예약하기 화면
	@GetMapping("/reservation_view")
	public String reservationView() {
		return "booking/reservation";
	}
	
	// 문제2) 예약 추가 - AJAX 요청
	@ResponseBody
	@PostMapping("/add_booking")
	public Map<String, Object> addBooking(
			@RequestParam("name") String name, 
			@RequestParam("date") String date, 
			@RequestParam("day") int day,
			@RequestParam("headcount") int headcount,
			@RequestParam("phoneNumber") String phoneNumber) {
		
		// db insert
		bookingBO.addBooking(name, date, day, headcount, phoneNumber);
		
		// 응답값
		Map<String, Object> result = new HashMap<>();
		result.put("result", "성공");
		
		return result;
	}
	
	// 문제3) 메인 화면
	@GetMapping("/main_view")
	public String mainView() {
		return "booking/main";
	}
	
	// 문제3) 예약 내역 조회 - AJAX 통신 요청
	@ResponseBody
	@PostMapping("/get_booking")
	public Map<String, Object> getBooking(
			@RequestParam("name") String name, 
			@RequestParam("phoneNumber") String phoneNumber) {
		
		// db select - 최신 예약 정보 한 건
		Booking booking = bookingBO.getLatestBookingByNamePhoneNumber(name, phoneNumber);
		
		Map<String, Object> result = new HashMap<>();
		if (booking != null) {
			result.put("booking", booking);
			result.put("code", 1);
		} else {
			result.put("code", 500);
		}
		
		return result;
	}
}







