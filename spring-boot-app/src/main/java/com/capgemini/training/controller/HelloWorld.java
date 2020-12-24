package com.capgemini.training.controller;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.server.ResponseStatusException;

import com.capgemini.training.bean.User;


@RestController
@RequestMapping("/api")
public class HelloWorld {
	
	@Value("${application.message: Default Value}")
	String message;
	
	//http://localhost:8081/api/hello
	@GetMapping("/hello") 
	public String sayHello()
	{

		 return message;
	}
	
	//http://localhost:8081/api/users
	@GetMapping("/users")
	public User getUser()
	{
		return new User("admin","admin@123");
	}
	
	@PostMapping("/users")
	public ResponseEntity<User> saveUser(@RequestBody User user)
	{
		//saving data to db generally we do
		try {
		System.out.println(user);
		return new ResponseEntity<User>(user,HttpStatus.OK);
		}catch(ResponseStatusException e)
		{
			throw new ResponseStatusException(HttpStatus.BAD_REQUEST,e.getMessage());
		}
	}
}
