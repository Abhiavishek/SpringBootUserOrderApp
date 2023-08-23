package org.jsp.springBootUserFoodOrder.repository;

import java.util.List;

import org.jsp.springBootUserFoodOrder.dto.FoodOrder;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

public interface FoodOrderRepository extends JpaRepository<FoodOrder, Integer> {
	@Query("select f from FoodOrder f where f.user.id=?1")
	List<FoodOrder>findOrderByUserId(int id);

}
