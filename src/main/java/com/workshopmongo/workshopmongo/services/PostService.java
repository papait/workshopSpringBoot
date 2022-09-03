package com.workshopmongo.workshopmongo.services;

import java.util.Date;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.workshopmongo.workshopmongo.domain.Post;
import com.workshopmongo.workshopmongo.domain.User;
import com.workshopmongo.workshopmongo.dto.UserDto;
import com.workshopmongo.workshopmongo.repository.PostRepository;
import com.workshopmongo.workshopmongo.repository.UserRepository;
import com.workshopmongo.workshopmongo.services.execpetion.ObjectNotFoundExecption;

@Service
public class PostService {

	@Autowired //Mecanismo de injeção de depend (É feita a definição do objeto pelo proprio srping que cria o objt
	private PostRepository repository;
	
	public Post findById(String id) {
		Optional<Post> obj = repository.findById(id);
		return obj.orElseThrow(() -> new ObjectNotFoundExecption("Objeto n encontrado"));
	}
	
	public List <Post> findByTitle (String text){
		return repository.searchTitle(text);
	}
	
	public List <Post> fullRearch (String text, Date minDate, Date maxDate){
		maxDate = new Date (maxDate.getTime() + 24*60*60*1000); //Macete pegar data dia seguinte
		return repository.fullsearch(text, minDate, maxDate);
	}
}
