package ar.edu.futbol5

import ar.edu.futbol5.inscripcion.CriterioInscripcion
import ar.edu.futbol5.inscripcion.ModoSolidario
import ar.edu.futbol5.inscripcion.ModoEstandar

class Jugador {
  var nombre: String = ""
  var calificacion: Double = null
  var puntajes: Seq[Double] = new ArrayBuffer[Double]()
  var criterioInscripcion: CriterioInscripcion = new ModoEstandar()

  def this(nombre: String, calificacion: Double, puntajes: Seq[Double]) {
    this.calificacion = calificacion
    this.puntajes = puntajes
    this.criterioInscripcion = new ModoEstandar()
    this.nombre = nombre
  }

  def modoSolidario() {
    criterioInscripcion = new ModoSolidario()
  }

  def dejaLugarAOtro: Boolean = {
    if (criterioInscripcion.isInstanceOf[ModoSolidario]) {
      true
    } else {
      false
    }
  }

  override def toString(): String = nombre //"Jugador (" + calificacion + ") - modo " + criterioInscripcion.toString()
}
