package com.workshopmongo.workshopmongo.config;

import java.text.SimpleDateFormat;
import java.util.Arrays;
import java.util.TimeZone;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Configuration;

import com.workshopmongo.workshopmongo.domain.Post;
import com.workshopmongo.workshopmongo.domain.User;
import com.workshopmongo.workshopmongo.dto.AutorDTO;
import com.workshopmongo.workshopmongo.dto.CommentDTO;
import com.workshopmongo.workshopmongo.repository.PostRepository;
import com.workshopmongo.workshopmongo.repository.UserRepository;

@Configuration //Spring entender que isso é uma configuraçlão
public class Instantiation implements CommandLineRunner {

	@Autowired
	private UserRepository userReposit;
	
	@Autowired
	private PostRepository postReposit;
	
	
	@Override
	public void run(String... args) throws Exception {
		
		SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");
		sdf.setTimeZone(TimeZone.getTimeZone("GTM"));
		
		postReposit.deleteAll();
		userReposit.deleteAll(); //Limap coleção do mongo
		
		User maria = new User(null, "Maria Brown", "maria@gmail.com");
		User alex = new User(null, "Alex Green", "alex@gmail.com");
		User bob = new User(null, "Bob Grey", "bob@gmail.com");
		
		//Salvar os objetos na coleção de user
		userReposit.saveAll(Arrays.asList(maria,alex, bob)); //Usuario com ID proprios
		
		Post post1 = new Post (null, sdf.parse("21/03/2018"), "Partiu viagem", "Vou viajar para são Paulo. abraços",new AutorDTO(maria)); //Persistir os obj user antes de relacionar
		Post post2 = new Post(null, sdf.parse("23/03/2018"), "Bom dia", "Acordei feliz hoje!",new AutorDTO(maria));
		
		CommentDTO c1 = new CommentDTO ("Boa viagem mano", sdf.parse("21/03/2018"), new AutorDTO(alex));
		CommentDTO c2 = new CommentDTO ("Aproveita", sdf.parse("22/03/2018"), new AutorDTO(bob));
		CommentDTO c3 = new CommentDTO ("Have a nice day", sdf.parse("23/03/2018"), new AutorDTO(alex));
		
		
		post1.getComments().addAll(Arrays.asList(c1,c2));
		post2.getComments().add(c3);
		
		
		postReposit.saveAll(Arrays.asList(post1,post2));
		
		maria.getPosts().addAll(Arrays.asList(post1,post2));
		userReposit.save(maria);
		
	}

}
