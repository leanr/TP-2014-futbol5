package ar.edu.futbol5.ordenamiento

import ar.edu.futbol5.Partido
import java.util.ArrayList
import java.util.List

class OrdenamientoMix implements CriterioOrdenamiento {
	
	List<CriterioOrdenamiento> criterios 
	
	new() {
		criterios = new ArrayList<CriterioOrdenamiento>
	}
	
	override ordenar(Partido partido) {
		// BAD SMELL - Se repite el código de ordenar
		partido.inscriptos.sortBy(calcularValor()).clone.reverse
	}
	
	def addCriterio(CriterioOrdenamiento criterio) {
		criterios.add(criterio)
	}
	
	override calcularValor() {
		[ jugador | 
			criterios.fold(0d, [ acum, criterio | acum + criterio.calcularValor().apply(jugador) ]) / criterios.size
		]
	}
	
}