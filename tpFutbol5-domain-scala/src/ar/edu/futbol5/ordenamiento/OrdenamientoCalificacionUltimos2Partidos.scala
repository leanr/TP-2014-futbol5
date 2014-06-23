package ar.edu.futbol5.ordenamiento

import ar.edu.futbol5.Partido
import ar.edu.futbol5.Jugador

class OrdenamientoCalificacionUltimos2Partidos extends CriterioOrdenamiento {
    override def ordenar(partido: Partido): Seq[Jugador] = 
		partido.inscriptos.sortBy(calcularValor).clone.reverse
		
	override def calcularValor = (jugador: Jugador) => {
	  val misPuntajes = jugador.puntajes.reverse.take(2)
	  val promedio = misPuntajes.sum / misPuntajes.size
	  promedio
	}
}