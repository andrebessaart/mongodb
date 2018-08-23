package com.andrebessa.workshopmongo.config;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.TimeZone;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Configuration;

import com.andrebessa.workshopmongo.domain.Post;
import com.andrebessa.workshopmongo.domain.User;
import com.andrebessa.workshopmongo.dto.AutorDTO;
import com.andrebessa.workshopmongo.dto.CommentDTO;
import com.andrebessa.workshopmongo.repository.PostRepository;
import com.andrebessa.workshopmongo.repository.UserRepository;

@Configuration
public class Instantiation implements CommandLineRunner{
	
	@Autowired
	private UserRepository userRepository;

	@Autowired
	private PostRepository postRepository;
	
	@Override
	public void run(String... args) throws Exception {
		
		SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");
		sdf.setTimeZone(TimeZone.getTimeZone("GMT"));
		
		userRepository.deleteAll();
		postRepository.deleteAll();
		
		User maria = new User(null, "Maria Brown", "maria@gmail.com");
		User alex = new User(null, "Alex Green", "alex@gmail.com");
		User bob = new User(null, "Bob Grey", "bob@gmail.com");
		
		List<User> usuarios = new ArrayList<>();
		Collections.addAll(usuarios, maria,alex,bob);
		
		userRepository.saveAll(usuarios); 
		
		Post post1 = new Post(null,sdf.parse("21/03/2018"),"Partiu viagem", "Vou viajar para São Paulo. Abraços!", new AutorDTO(maria));
		Post post2 = new Post(null,sdf.parse("23/03/2018"),"Bom dia", "Acordei feliz hoje!", new AutorDTO(maria));
		
		List<Post> posts = new ArrayList<>();
		Collections.addAll(posts, post1,post2);
		
		CommentDTO c1 = new CommentDTO("Boa viagem mano!", sdf.parse("21/03/2018"),new AutorDTO(alex));
		CommentDTO c2 = new CommentDTO("Aproveite", sdf.parse("22/03/2018"), new AutorDTO(bob));
		CommentDTO c3 = new CommentDTO("Tenha um ótimo dia!", sdf.parse("23/03/2018"), new AutorDTO(alex));
		
		List<CommentDTO> comments1 = new ArrayList<>();
		Collections.addAll(comments1, c1,c3);
		post1.getComments().addAll(comments1);
		
		List<CommentDTO> comments2 = new ArrayList<>();
		Collections.addAll(comments2, c2);
		post2.getComments().addAll(comments2);
	
		postRepository.saveAll(posts);
		
		maria.getPosts().addAll(posts);
		userRepository.save(maria);
		
	}

}
