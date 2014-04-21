package ar.edu.futbol5.excepciones

import java.lang.RuntimeException

class BusinessException extends RuntimeException {

	new(String msg){
		super(msg)
	}	
	
}