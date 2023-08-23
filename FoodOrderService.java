package org.jsp.springBootUserFoodOrder.service;

import java.util.List;
import java.util.Optional;

import org.jsp.springBootUserFoodOrder.dao.FoodOrderDao;
import org.jsp.springBootUserFoodOrder.dao.UserDao;
import org.jsp.springBootUserFoodOrder.dto.FoodOrder;
import org.jsp.springBootUserFoodOrder.dto.ResponseStructure;
import org.jsp.springBootUserFoodOrder.dto.User;
import org.jsp.springBootUserFoodOrder.exception.IdNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

@Service
public class FoodOrderService {
	@Autowired
	private FoodOrderDao fdao;
	@Autowired
	private UserDao udao;
	
	
	public ResponseEntity<ResponseStructure<FoodOrder>>saveOrder(FoodOrder fo, int id){
		Optional<User>recUser = udao.findById(id);
		ResponseStructure<FoodOrder>structure = new ResponseStructure<>();
		if(recUser.isPresent()) {
			User u = recUser.get();
			u.getFoodorder().add(fo);
			fo.setUser(u);
			udao.updateUser(u);
			fdao.saveOrder(fo);
			structure.setData(fo);
			structure.setMessage("Order added with id " + fo.getId());
			structure.setStatusCode(HttpStatus.CREATED.value());
			return new ResponseEntity<ResponseStructure<FoodOrder>>(structure, HttpStatus.CREATED);
		}
		else {
			throw new IdNotFoundException();
		}
	}
	
	public ResponseEntity<ResponseStructure<FoodOrder>>updateOrder(FoodOrder fo, int id){
		Optional<User>recUser = udao.findById(id);
		ResponseStructure<FoodOrder>structure = new ResponseStructure<>();
		if(recUser.isPresent()) {
			fo.setUser(recUser.get());
			fdao.updateOrder(fo);
			structure.setData(fo);
			structure.setMessage("Order updated with id " + fo.getId());
			structure.setStatusCode(HttpStatus.ACCEPTED.value());
			return new ResponseEntity<ResponseStructure<FoodOrder>>(structure, HttpStatus.ACCEPTED);
		}
		else {
			throw new IdNotFoundException();
		}
	}
	
	public ResponseEntity<ResponseStructure<FoodOrder>>findByOrderId(int id){
		Optional<FoodOrder> recOrder = fdao.findByOrderId(id);
		ResponseStructure<FoodOrder> structure = new ResponseStructure<>();
		if(recOrder.isPresent()) {
			structure.setData(recOrder.get());
			structure.setMessage("Order id present");
			structure.setStatusCode(HttpStatus.OK.value());
			return new ResponseEntity<ResponseStructure<FoodOrder>>(structure,HttpStatus.OK);
		}
		else {
			throw new IdNotFoundException();
		}
	}
	
	 public ResponseEntity<ResponseStructure<String>>deleteOrder(int id){
	    	ResponseStructure<String> structure = new ResponseStructure<>();
	    	Optional<FoodOrder> recfood = fdao.findByOrderId(id);
	    	if(recfood.isPresent()) {
	    		fdao.deleteOrder(id);
	    		structure.setData("product deleted");
	    		structure.setMessage("Product Found");
	    		structure.setStatusCode(HttpStatus.OK.value());
	    	    return new ResponseEntity<ResponseStructure<String>>(structure,HttpStatus.OK);
	    	}
	    	throw new IdNotFoundException();
	    }
	    
	
	
	public ResponseEntity<ResponseStructure<List<FoodOrder>>>findOrderByUserId(int id){
		ResponseStructure<List<FoodOrder>> structure = new ResponseStructure<>();
		structure.setData(fdao.findOrderByUserId(id));
		structure.setMessage("Order found with user id");
		structure.setStatusCode(HttpStatus.OK.value());
		return new ResponseEntity<ResponseStructure<List<FoodOrder>>>(structure, HttpStatus.OK);
	}

}
