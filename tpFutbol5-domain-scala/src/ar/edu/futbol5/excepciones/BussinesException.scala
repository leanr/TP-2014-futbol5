package ar.edu.futbol5.excepciones

import java.lang.RuntimeException

class BusinessException(msg: String) extends RuntimeException(msg) {}
