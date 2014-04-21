package ar.edu.futbol5.ordenamiento

import ar.edu.futbol5.Partido

class OrdenamientoCalificacionUltimos2Partidos implements CriterioOrdenamiento {
	
	override ordenar(Partido partido) {
		// BAD SMELL - las últimas dos calificaciones podría ser un método en jugador
		// Y además en lugar de recibir el partido podría devolver simplemente el bloque de ordenamiento
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