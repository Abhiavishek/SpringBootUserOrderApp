package org.jsp.springBootUserFoodOrder.dao;

import java.util.List;
import java.util.Optional;

import org.jsp.springBootUserFoodOrder.dto.FoodOrder;
import org.jsp.springBootUserFoodOrder.repository.FoodOrderRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

@Repository
public class FoodOrderDao {
    @Autowired
	private FoodOrderRepository forepository;
    
    public FoodOrder saveOrder(FoodOrder foodOrder) {
    	return forepository.save(foodOrder);
    }
    
    public FoodOrder updateOrder(FoodOrder foodOrder) {
    	return forepository.save(foodOrder);
    }
    
    public void deleteOrder(int id) {
    	 forepository.deleteById(id);
    }
    
    public Optional<FoodOrder> findByOrderId(int id) {
    	return forepository.findById(id);
    }
    
    public List<FoodOrder> findOrderByUserId(int id){
    	return forepository.findOrderByUserId(id);
    }
}
