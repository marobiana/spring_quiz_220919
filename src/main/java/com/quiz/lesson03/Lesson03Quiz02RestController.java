package com.quiz.lesson03;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.quiz.lesson03.bo.RealEstateBO;
import com.quiz.lesson03.model.RealEstate;

@RestController
public class Lesson03Quiz02RestController {

	@Autowired
	private RealEstateBO realEstateBO;
	
	// http://localhost:8080/lesson03/quiz02/1
	@RequestMapping("/lesson03/quiz02/1")
	public String quiz02_1() {
//		realtorId : 3
//		address : 푸르지용 리버 303동 1104호
//		area : 89
//		type : 매매
//		price : 100000
		RealEstate realEstate = new RealEstate();
		realEstate.setRealtorId(3);
		realEstate.setAddress("푸르지용 리버 303동 1104호");
		realEstate.setArea(89);
		realEstate.setType("매매");
		realEstate.setPrice(100000);
		
		int row = realEstateBO.addRealEstate(realEstate);
		return "입력 성공:" + row;
	}
	
	// http://localhost:8080/lesson03/quiz02/2?realtor_id=5
	@RequestMapping("/lesson03/quiz02/2")
	public String quiz02_2(
			@RequestParam("realtor_id") int realtorId) {
		
//		address : 썅떼빌리버 오피스텔 814호
//		area : 45
//		type : 월세
//		price : 100000
//		rentPrice : 120
		
		return "입력 성공:" + realEstateBO.addRealEstateAsField(
				realtorId, "썅떼빌리버 오피스텔 814호", 45, "월세", 100000, 120);
	}
	
	// http://localhost:8080/lesson03/quiz03/1?id=8&type=전세&price=70000
	@RequestMapping("/lesson03/quiz03/1")
	public String quiz03_1(
			@RequestParam("id") int id,
			@RequestParam("type") String type,
			@RequestParam("price") int price) {
		
		int row = realEstateBO.updateRealEstateById(id, type, price);
		return "수정 성공:" + row;
	}
	
	// http://localhost:8080/lesson03/quiz04/1?id=21
	@RequestMapping("/lesson03/quiz04/1")
	public String quiz04(
			@RequestParam("id") int id) {
		
		realEstateBO.deleteRealEstateById(id);
		return "삭제 성공";
	}
	
}




