package ar.edu.futbol5.confirmarequipos

import ar.edu.futbol5.excepciones.BusinessException
import ar.edu.futbol5.Partido

class PartidoAbierto implements PartidoInt{
	override generarEquipos(Partido partido){
		throw new BusinessException("Hubo un error")	
	}
	
}