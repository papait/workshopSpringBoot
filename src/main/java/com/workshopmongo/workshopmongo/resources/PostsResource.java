package com.workshopmongo.workshopmongo.resources;

import java.net.URI;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import com.workshopmongo.workshopmongo.domain.Post;
import com.workshopmongo.workshopmongo.domain.User;
import com.workshopmongo.workshopmongo.dto.UserDto;
import com.workshopmongo.workshopmongo.services.PostService;
import com.workshopmongo.workshopmongo.services.UserService;

@RestController // Recurso REST
@RequestMapping("/posts") // Caminho do endpoint
public class PostsResource {

	@Autowired
	private PostService service;


	@RequestMapping(value = "/{id}", method = RequestMethod.GET) // ou GetMapping

	public ResponseEntity<Post> findById(@PathVariable String id) {

		Post obj = service.findById(id);

		return ResponseEntity.ok().body(obj); // ok metodo que vai instanciar o metodo response entity com
															// a respostas HTTP
	}



}
