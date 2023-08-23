package org.jsp.springBootUserFoodOrder.controller;

import java.util.List;

import org.jsp.springBootUserFoodOrder.dto.FoodOrder;
import org.jsp.springBootUserFoodOrder.dto.ResponseStructure;
import org.jsp.springBootUserFoodOrder.service.FoodOrderService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class FoodOrderController {
	@Autowired
	private FoodOrderService foservice;
	@PostMapping("/foodorders/{id}")
	public ResponseEntity<ResponseStructure<FoodOrder>> saveOrder(@RequestBody FoodOrder fo, @PathVariable int id) {
		return foservice.saveOrder(fo, id);
	}
	
	@PutMapping("/foodorders/{id}")
	public ResponseEntity<ResponseStructure<FoodOrder>> updateOrder(@RequestBody FoodOrder fo, @PathVariable int id) {
		return foservice.updateOrder(fo, id);
	}
	@GetMapping("foodorders/{id}")
	public ResponseEntity<ResponseStructure<FoodOrder>> findByOrderId( @PathVariable int id) {
		return foservice.findByOrderId(id);
	}
	@DeleteMapping("foodorders/{id}")
	public ResponseEntity<ResponseStructure<String>> deleteOrder(@PathVariable int id) {
		return foservice.deleteOrder(id);
	}
	@GetMapping("/foodorders/userid/{id}")
	public ResponseEntity<ResponseStructure<List<FoodOrder>>> findOrderByUserId(@PathVariable int id){
		return foservice.findOrderByUserId(id);
	}

}
