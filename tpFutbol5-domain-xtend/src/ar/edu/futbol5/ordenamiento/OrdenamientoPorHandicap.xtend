package ar.edu.futbol5.ordenamiento

import ar.edu.futbol5.Partido
import ar.edu.futbol5.Jugador
import java.util.List

class OrdenamientoPorHandicap extends CriterioOrdenamiento {
	
	
	override calcularValor() {
		[ jugador | jugador.calificacion ]
	}
	
}