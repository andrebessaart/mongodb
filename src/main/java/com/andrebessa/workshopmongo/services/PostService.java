package com.andrebessa.workshopmongo.services;

import java.util.Date;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.andrebessa.workshopmongo.domain.Post;
import com.andrebessa.workshopmongo.repository.PostRepository;
import com.andrebessa.workshopmongo.services.exception.ObjectNotFoundException;

@Service
public class PostService {
	
	@Autowired
	private PostRepository repo;
	
	public List<Post> findAll() {
		
		return repo.findAll();
	}
	
	public Post findById(String id) {
		
		Optional<Post> user = repo.findById(id);
		
		return user.orElseThrow(() -> new ObjectNotFoundException("Objeto não encontrado"));
	}
	
	public List<Post> findByTitle(String text){
		return repo.searchTitle(text);
	}
	
	public List<Post> fullSearch(String text, Date minData, Date maxData){
		
		maxData = new Date(maxData.getTime() + 24 * 60 * 60 * 1000);
		return repo.fullSearch(text, minData, maxData);
	}

}
