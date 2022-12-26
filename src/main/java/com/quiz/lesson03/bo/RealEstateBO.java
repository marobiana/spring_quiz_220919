package com.quiz.lesson03.bo;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.quiz.lesson03.dao.RealEstateDAO;
import com.quiz.lesson03.model.RealEstate;

@Service
public class RealEstateBO {

	@Autowired
	private RealEstateDAO realEstateDAO;
	
	// input: controller가 넘겨주는 id
	// output: RealEstate 단건 => 컨트롤러한테 넘김
	public RealEstate getRealEstateById(int id) {
		return realEstateDAO.selectRealEstateById(id);
	}
	
	// input: controller 넘겨준 rentPrice
	// output: List<RealEstate> => 컨트롤러한테 넘김
	public List<RealEstate> getRealEstateListByRentPrice(int rentPrice) {
		return realEstateDAO.selectRealEstateListByRentPrice(rentPrice);
	}
	
	// input: 컨트롤러가 넘겨준 area, price
	// output: List<RealEstate> => 컨트롤러한테 넘김
	public List<RealEstate> getRealEstateListByAreaPrice(int area, int price) {
		return realEstateDAO.selectRealEstateListByAreaPrice(area, price);
	}
}



