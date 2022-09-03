package com.workshopmongo.workshopmongo.services.execpetion;

public class ObjectNotFoundExecption extends RuntimeException {

	private static final long serialVersionUID = 1L;
	
	public ObjectNotFoundExecption (String msg) {
		super(msg);	//Construtuor pra repassar a chamada pra super classe Runtime
	}

}
