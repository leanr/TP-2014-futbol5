package ar.edu.futbol5.ordenamiento

import ar.edu.futbol5.Jugador
import ar.edu.futbol5.Partido
import scala.collection.mutable._

trait CriterioOrdenamiento {
  def ordenar(partido: Partido): Seq[Jugador]
  val calcularValor: (Jugador) => Double
}
