package ar.edu.futbol5.ordenamiento

import ar.edu.futbol5.Partido
import ar.edu.futbol5.Jugador
import java.util.List

class OrdenamientoCalificacionUltimos2Partidos implements CriterioOrdenamiento {
	
	override List<Jugador> ordenar(Partido partido) {
		partido.inscriptos.sortBy(calcularValor()).clone.reverse
	}
	
	
	override calcularValor() {
		[ jugador |
				val misPuntajes = jugador.getPuntajes.clone.reverse.take(2).toList
				val promedio = misPuntajes.fold(0d, [ acum, puntaje | acum + puntaje ]) / misPuntajes.size
				promedio
		]
	}
	
}