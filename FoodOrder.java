package org.jsp.springBootUserFoodOrder.dto;


import java.time.LocalDate;

import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

import com.fasterxml.jackson.annotation.JsonIgnore;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import lombok.Data;

@Entity
@Data
public class FoodOrder {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int id;
	private String items;
	private double cost;
	@CreationTimestamp
	private LocalDate ordertime;
	@UpdateTimestamp
	private LocalDate deliverytime;
	@ManyToOne
	@JoinColumn
	@JsonIgnore
	private User user;

}
