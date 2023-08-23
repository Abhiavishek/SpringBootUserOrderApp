package org.jsp.springBootUserFoodOrder.service;

import java.util.List;
import java.util.Optional;

import org.jsp.springBootUserFoodOrder.dao.UserDao;
import org.jsp.springBootUserFoodOrder.dto.ResponseStructure;
import org.jsp.springBootUserFoodOrder.dto.User;
import org.jsp.springBootUserFoodOrder.exception.IdNotFoundException;
import org.jsp.springBootUserFoodOrder.exception.InvalidCredentialException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

@Service
public class UserService {
	@Autowired
	private UserDao udao;
	
	public ResponseEntity<ResponseStructure<User>>saveuser(User u){
		ResponseStructure<User> structure = new ResponseStructure<>();
		structure.setData(udao.saveUser(u));
		structure.setMessage("User Saved with Id : "+u.getId());
		structure.setStatusCode(HttpStatus.CREATED.value());
		return new ResponseEntity<ResponseStructure<User>>(structure, HttpStatus.CREATED);
	}
	
	public ResponseEntity<ResponseStructure<User>>updateuser(User u){
		ResponseStructure<User> structure = new ResponseStructure<>();
		structure.setData(udao.updateUser(u));
		structure.setMessage("User Saved with Id : "+u.getId());
		structure.setStatusCode(HttpStatus.ACCEPTED.value());
		return new ResponseEntity<ResponseStructure<User>>(structure, HttpStatus.ACCEPTED);
	}
	
	public ResponseEntity<ResponseStructure<User>>findById(int id){
		ResponseStructure<User> structure = new ResponseStructure<>();
		Optional<User>recuser = udao.findById(id);
		if(recuser.isPresent()) {
			structure.setData(recuser.get());
			structure.setMessage("Successfully fetched");
			structure.setStatusCode(HttpStatus.OK.value());
			return new ResponseEntity<ResponseStructure<User>>(structure, HttpStatus.OK);
		}
		else {
			throw new IdNotFoundException();
		}
	}
	
	public ResponseEntity<ResponseStructure<List<User>>> findAll(){
		ResponseStructure<List<User>> structure = new ResponseStructure<>();
		structure.setData(udao.findAll());
		structure.setMessage("List of All Users");
		structure.setStatusCode(HttpStatus.OK.value());
		return new ResponseEntity<ResponseStructure<List<User>>>(structure, HttpStatus.OK);
		
	}
	
	public ResponseEntity<ResponseStructure<User>>verifyUser(long phone, String password){
		ResponseStructure<User> structure = new ResponseStructure<>();
		Optional<User>recuser = udao.verifyUser(phone, password);
		if(recuser.isPresent()) {
			structure.setData(recuser.get());
			structure.setMessage("User Verified Successfully");
			structure.setStatusCode(HttpStatus.OK.value());
			return new ResponseEntity<ResponseStructure<User>>(structure, HttpStatus.OK);
		}
		else {
			throw new InvalidCredentialException();
		}
	}
	
	public ResponseEntity<ResponseStructure<User>>verifyUser(String email, String password){
		ResponseStructure<User> structure = new ResponseStructure<>();
		Optional<User>recuser = udao.verifyUser(email, password);
		if(recuser.isPresent()) {
			structure.setData(recuser.get());
			structure.setMessage("User Verified Successfully");
			structure.setStatusCode(HttpStatus.OK.value());
			return new ResponseEntity<ResponseStructure<User>>(structure, HttpStatus.OK);
		}
		else {
			throw new InvalidCredentialException();
		}
	}
	
	public ResponseEntity<ResponseStructure<String>>deleteUser(int id){
		ResponseStructure<String> structure = new ResponseStructure<>();
		Optional<User>recuser = udao.findById(id);
		if(recuser.isPresent()) {
			structure.setData("user found");
			structure.setMessage("user deleted");
			structure.setStatusCode(HttpStatus.OK.value());
			udao.deleteUser(id);
			return new ResponseEntity<ResponseStructure<String>>(structure,HttpStatus.OK);
		}
		else {
			structure.setData("User Not found");
			structure.setMessage("User not deleted");
			structure.setStatusCode(HttpStatus.NOT_FOUND.value());
			return new ResponseEntity<ResponseStructure<String>>(structure, HttpStatus.NOT_FOUND);
		}
	}

}
