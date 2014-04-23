package ar.edu.futbol5.ordenamiento

import ar.edu.futbol5.Partido

class OrdenamientoCalificacionUltimos2Partidos implements CriterioOrdenamiento {
	
	override ordenar(Partido partido) {
		partido.getInscriptos.sortBy (calcularValor()).clone.reverse
	}
	
	override calcularValor() {
		[ jugador |
				val misPuntajes = jugador.getPuntajes.clone.reverse.take(2).toList
				val promedio = misPuntajes.fold(0d, [ acum, puntaje | acum + puntaje ]) / misPuntajes.size
				promedio
		]
	}
	
}