package com.workshopmongo.workshopmongo.services;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.workshopmongo.workshopmongo.domain.User;
import com.workshopmongo.workshopmongo.dto.UserDto;
import com.workshopmongo.workshopmongo.repository.UserRepository;
import com.workshopmongo.workshopmongo.services.execpetion.ObjectNotFoundExecption;

@Service
public class UserService {

	@Autowired //Mecanismo de injeção de depend (É feita a definição do objeto pelo proprio srping que cria o objt
	private UserRepository repository;
	
	public List <User> findAll (){
		return repository.findAll();
	
	}
	
	public User findById(String id) {
		Optional<User> obj = repository.findById(id);
		return obj.orElseThrow(() -> new ObjectNotFoundExecption("Objeto n encontrado"));
	}
	
	public User insert (User obj) {
		return repository.insert(obj);
	}
	
	public void delete (String id) {
		findById(id);  //Busca pra verificar se o ID existe, caso não ele lança uma eXcpetion 
		repository.deleteById(id);
	}
	
	public User update (User obj) {
		User newObjt = findById(obj.getId());	//bUSCA OBJ DO BANCO DE COLOCAR NO NEWOBJT
		updateData(newObjt, obj);	//Metodo reponsavel com trocar os dados
		return repository.save(newObjt);				
	}
	
	private void updateData(User newObjt, User obj) {
		newObjt.setName(obj.getName());
		newObjt.setEmail(obj.getEmail());
		
	}

	public User fromDTO(UserDto objDto) {
		return new User(objDto.getId(),objDto.getEmail(), objDto.getName());
	}
}
