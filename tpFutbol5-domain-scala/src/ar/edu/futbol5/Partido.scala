package ar.edu.futbol5

import ar.edu.futbol5.excepciones.BusinessException
import ar.edu.futbol5.ordenamiento.CriterioOrdenamiento
import ar.edu.futbol5.ordenamiento.OrdenamientoPorHandicap
import scala.collection.mutable._

class Partido {
  var inscriptos: Seq[Jugador] = new ArrayBuffer[Jugador]()
  var equipo1: Equipo = _
  var equipo2: Equipo = _
  var estado: String = "A"
  var criterioOrdenamiento: CriterioOrdenamiento = new OrdenamientoPorHandicap()
  var distribucionEquipos: Int = 5

  def generarEquipos() {
    if (this.validarInscripcion() == -1) {
      throw new BusinessException("Hubo un error")
    }
    this.distribuirEquipos(this.ordenarEquipos())
    estado = "G"
  }

  private def validarInscripcion(): Int = {
    if (inscriptos.size < 10) {
      return -1
    }
    if (estado.equalsIgnoreCase("A")) {
      return -1
    }
    if (estado.equalsIgnoreCase("G")) {
      return -1
    }
    0
  }

  private def distribuirEquipos(jugadores: Seq[Jugador]) {
    equipo1 = new Equipo()
    equipo2 = new Equipo()
    if (distribucionEquipos == 5) {
      equipo1.jugadores = Seq(jugadores(0), jugadores(2), jugadores(4), jugadores(6), 
        jugadores(8))
      equipo2.jugadores = Seq(jugadores(1), jugadores(3), jugadores(5), jugadores(7), 
        jugadores(9))
    } else {
      equipo1.jugadores = Seq(jugadores(0), jugadores(3), jugadores(4), jugadores(7), 
        jugadores(8))
      equipo2.jugadores = Seq(jugadores(1), jugadores(2), jugadores(5), jugadores(6), 
        jugadores(9))
    }
  }

  def ordenarEquipos(): Seq[Jugador] = criterioOrdenamiento.ordenar(this)

  def inscribir(jugador: Jugador) {
    if (inscriptos.size < 10) {
      this.inscriptos :+ jugador
    } else {
      if (this.hayAlgunJugadorQueCedaLugar()) {
        this.inscriptos - this.jugadorQueCedeLugar
        this.inscriptos :+ jugador
      } else {
        throw new BusinessException("No hay más lugar")
      }
    }
  }

  private def hayAlgunJugadorQueCedaLugar(): Boolean = inscriptos.exists(_.dejaLugarAOtro)

  private def jugadorQueCedeLugar(): Jugador = {
    if (!hayAlgunJugadorQueCedaLugar) {
      return null
    }
    
    inscriptos.find(_.dejaLugarAOtro).get
  }

  def cerrar() {
    estado = "C"
  }
}
