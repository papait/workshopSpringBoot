package com.workshopmongo.workshopmongo.repository;

import java.util.Date;
import java.util.List;

import org.springframework.boot.autoconfigure.orm.jpa.JpaProperties;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.data.mongodb.repository.Query;
import org.springframework.stereotype.Repository;

import com.workshopmongo.workshopmongo.domain.Post;
import com.workshopmongo.workshopmongo.domain.User;

@Repository // ou @Component
public interface PostRepository extends MongoRepository<Post, String>{

	List<Post> findByTitleContainingIgnoreCase (String text); //SpringData criar a a queary
	
	
	@Query("{ 'title' : { $regex: ?0, $options: 'i' } }")	//Olha documentação do mongo db ?0 primeiro parametro que vem no métodod 0,1,2...
	List<Post> searchTitle (String text);	//Crio a  query manualmente
	
	@Query ("{ $and: [ { date: { $gte: ?1 } }, { date: { $lte: ?2} }, { $or: [ { 'title' : { $regex: ?0, $options: 'i' } }, { 'body' : { $regex: ?0, $options: 'i' } },{ 'comments.text' : { $regex: ?0, $options: 'i' } } ] } ] }")
	List <Post> fullsearch (String text, Date minDate, Date maxDate);
}
