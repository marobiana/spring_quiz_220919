package com.quiz.lesson02.bo;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.quiz.lesson02.dao.StoreDAO;
import com.quiz.lesson02.model.Store;

@Service
public class StoreBO {
	
	@Autowired     // Dependency Injection (DI)
	private StoreDAO storeDAO;
	
	// input: 컨트롤러가 보내주지 않음 X
	// output: List<Store> => 컨트롤러한테 보냄
	public List<Store> getStoreList() {
		return storeDAO.selectStoreList();
	}
}






