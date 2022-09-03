package com.workshopmongo.workshopmongo.resources.excption;

import java.time.Instant;

import javax.servlet.http.HttpServletRequest;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

import com.workshopmongo.workshopmongo.services.execpetion.ObjectNotFoundExecption;

@ControllerAdvice //Indicar class e reponsavel por tratar erros na minha requisição
public class ResourceExceptionHandler {

	@ExceptionHandler(ObjectNotFoundExecption.class) //Indica que quando ocorrer a execpetion ObjetocNot e pra exutar o metodo a baixo
	public ResponseEntity<StandartError> objectNotFound (ObjectNotFoundExecption e, HttpServletRequest request){
		String error = "Resource not found";
		HttpStatus status = HttpStatus.NOT_FOUND; //exceção 400
		StandartError err = new StandartError(Instant.now(),status.value(), error, e.getMessage(),request.getRequestURI());
		
		return ResponseEntity.status(status).body(err); //Responsa com corpo personalizado status com corpo da responsta ERR
	}
}
