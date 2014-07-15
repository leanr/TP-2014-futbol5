package ar.edu.futbol5.confirmarequipos

import ar.edu.futbol5.excepciones.BusinessException
import ar.edu.futbol5.Partido


class PartidoCerrado implements PartidoInt{
	override generarEquipos(Partido partido){
		
		partido.distribuirEquipos(partido.ordenarEquipos)
		partido.setEstado(new PartidoGenerado)
	}
	
	
	
}