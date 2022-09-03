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
import com.workshopmongo.workshopmongo.services.UserService;

@RestController // Recurso REST
@RequestMapping("/users") // Caminho do endpoint
public class UserResource {

	@Autowired
	private UserService service;

	@RequestMapping(method = RequestMethod.GET) // ou GetMapping
	public ResponseEntity<List<UserDto>> findAll() { // Objeto reponseentity encapsula uma estrutura necessaria para
														// retrnar respostas HTTP

		List<User> list = service.findAll();
		List<UserDto> listDto = list.stream().map(x -> new UserDto(x)).collect(Collectors.toList()); // Converter list
																										// user para
																										// userdto
																										// Transforme
																										// minha List em
																										// Steam
																										// percorro ela
																										// r adicono
																										// cada objeto
																										// dela
																										// instanciando
																										// um userDTO,
																										// depois abr
																										// eua uma lista
																										// dto
		return ResponseEntity.ok().body(listDto); // ok metodo que vai instanciar o metodo response entity com a
													// respostas HTTP
	}

	@RequestMapping(value = "/{id}", method = RequestMethod.GET) // ou GetMapping

	public ResponseEntity<UserDto> findById(@PathVariable String id) {

		User obj = service.findById(id);

		return ResponseEntity.ok().body(new UserDto(obj)); // ok metodo que vai instanciar o metodo response entity com
															// a respostas HTTP
	}

	@RequestMapping(value = "/{id}/posts", method = RequestMethod.GET) // ou GetMapping

	public ResponseEntity<List<Post>> findPost(@PathVariable String id) {

		User obj = service.findById(id);

		return ResponseEntity.ok().body(obj.getPosts()); //retornar no corpo com os postos associado ao user
	}

	@RequestMapping(method = RequestMethod.POST) // ou GetMapping
	public ResponseEntity<Void> insert(@RequestBody UserDto bjdto) {

		User obj = service.fromDTO(bjdto);
		obj = service.insert(obj);
		URI uri = ServletUriComponentsBuilder.fromCurrentRequest().path("/{id}").buildAndExpand(obj.getId()).toUri(); // Cabeçalho
																														// com
																														// a
																														// URL
																														// com
																														// o
																														// novo
																														// recurso
																														// criado
		return ResponseEntity.created(uri).build(); // Created recebe o cabeçalho contrno a loclaização do o novo
													// recurso criado
	}

	@RequestMapping(value = "/{id}", method = RequestMethod.DELETE) // ou GetMapping

	public ResponseEntity<Void> deleteById(@PathVariable String id) {

		service.delete(id);

		return ResponseEntity.noContent().build(); // Responsa retornar nadsa 204
	}

	@RequestMapping(value = "/{id}", method = RequestMethod.PUT) // ou GetMapping
	public ResponseEntity<User> update(@RequestBody UserDto bjdto, @PathVariable String id) {

		User obj = service.fromDTO(bjdto);
		obj.setId(id); // Garantir que o obj tem o id da requisião
		obj = service.update(obj);
		return ResponseEntity.noContent().build();
	}

}
