package ar.edu.futbol5.ordenamiento

import ar.edu.futbol5.Partido
import ar.edu.futbol5.Jugador
import java.util.List

class OrdenamientoPorHandicap implements CriterioOrdenamiento {
	
	override List<Jugador> ordenar(Partido partido) {
		partido.inscriptos.sortBy(calcularValor()).clone.reverse
	}
	
	override calcularValor() {
		[ jugador | jugador.calificacion ]
	}
	
}