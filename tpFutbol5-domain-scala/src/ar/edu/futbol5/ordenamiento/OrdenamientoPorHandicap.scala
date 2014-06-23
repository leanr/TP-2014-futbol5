package ar.edu.futbol5.ordenamiento

import ar.edu.futbol5.Jugador
import ar.edu.futbol5.Partido

class OrdenamientoPorHandicap extends CriterioOrdenamiento {
  override def ordenar(partido: Partido): Seq[Jugador] = 
    partido.inscriptos.sortBy(calcularValor).clone.reverse

  override def calcularValor = _.calificacion 
}
