package com.andrebessa.workshopmongo.resources;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.andrebessa.workshopmongo.domain.User;

@RestController
@RequestMapping(value="users/")
public class UserResource {
	
	//@RequestMapping(method=RequestMethod.GET)
	@GetMapping
	public ResponseEntity<List<User>> findAll() {
		
		User maria = new User("1", "Maria", "maria@gmail.com");
		User alex = new User("2", "Alex", "alex@gmail.com");
		
		List<User> list = new ArrayList<>();
		//list.add(maria);
		//list.add(alex);
		Collections.addAll(list, maria, alex);

		return ResponseEntity.ok().body(list);
	}

}
