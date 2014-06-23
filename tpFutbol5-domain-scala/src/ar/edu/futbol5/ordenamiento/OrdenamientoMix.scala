package ar.edu.futbol5.ordenamiento

import scala.collection.mutable.ArrayBuffer
import ar.edu.futbol5.Jugador
import ar.edu.futbol5.Partido

class OrdenamientoMix extends CriterioOrdenamiento {
	val criterios = new ArrayBuffer[CriterioOrdenamiento]()
	
	def addCriterio(criterio: CriterioOrdenamiento) {
	  criterios :+ criterio
	} 
	
	override def ordenar(partido: Partido): Seq[Jugador] = 
		partido.inscriptos.sortBy(calcularValor).clone.reverse	
	
	override def calcularValor = (jugador: Jugador) => 
	  criterios.foldLeft(0d)((acum, criterio) => acum + criterio.calcularValor(jugador)) / criterios.size
}