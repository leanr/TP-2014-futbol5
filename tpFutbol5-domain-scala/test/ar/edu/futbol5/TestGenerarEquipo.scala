package ar.edu.futbol5

import java.util.ArrayList
import java.util.List
import org.junit.Assert
import org.junit.Before
import org.junit.Test
import ar.edu.futbol5.excepciones.BusinessException
import ar.edu.futbol5.ordenamiento.OrdenamientoCalificacionUltimos2Partidos
import ar.edu.futbol5.ordenamiento.OrdenamientoMix
import ar.edu.futbol5.ordenamiento.OrdenamientoPorHandicap
import scala.collection.mutable.ArrayBuffer

class TestGenerarEquipo {

  private var partidoPocosJugadores: Partido = _
  private var partidoOk: Partido = _
  private var partido1: Partido = _
  private var sytek: Jugador = _
  private var chicho: Jugador = _
  private var pato: Jugador = _
  private var lechu: Jugador = _
  private var rodri: Jugador = _
  private var mike: Jugador = _
  private var dodi: Jugador = _
  private var roly: Jugador = _
  private var eric: Jugador = _
  private var leo: Jugador = _
  private var ferme: Jugador = _

  @Before
  def init() {
    partidoPocosJugadores = new Partido()
    for (i <- 0 until 6) {
      inscribir(partidoPocosJugadores, new Jugador())
    }
    partidoOk = new Partido()
    partido1 = new Partido()
    sytek = new Jugador("sytek", 3d, Seq(5d, 8d))
    chicho = new Jugador("chicho", 5d, Seq(6d, 8d, 6d))
    pato = new Jugador("pato", 8d, Seq(9d, 8d))
    lechu = new Jugador("lechu", 6d, Seq(7d, 9d))
    rodri = new Jugador("rodri", 4d, Seq(5d, 8d))
    mike = new Jugador("mike", 1d, Seq(4d, 10d, 6d, 8d))
    dodi = new Jugador("dodi", 7d, Seq(6d, 7d))
    roly = new Jugador("roly", 9d, Seq(6d, 6d, 9d))
    eric = new Jugador("eric", 6d, Seq(9d, 4d, 3d, 10d))
    eric.modoSolidario()
    leo = new Jugador("leo", 2d, Seq(6d, 6d, 6d))
    leo.modoSolidario()
    ferme = new Jugador("ferme", 10d, Seq(9d, 10d, 7d))
    ferme.modoSolidario()
    inscribir(partidoOk, sytek)
    inscribir(partidoOk, chicho)
    inscribir(partidoOk, pato)
    inscribir(partidoOk, lechu)
    inscribir(partidoOk, rodri)
    inscribir(partidoOk, mike)
    inscribir(partidoOk, dodi)
    inscribir(partidoOk, eric)
    inscribir(partidoOk, leo)
    inscribir(partidoOk, ferme)
    inscribir(partido1, sytek)
    inscribir(partido1, chicho)
    inscribir(partido1, pato)
    inscribir(partido1, lechu)
    inscribir(partido1, rodri)
    inscribir(partido1, mike)
    inscribir(partido1, dodi)
    inscribir(partido1, roly)
    inscribir(partido1, eric)
    inscribir(partido1, leo)
    inscribir(partido1, ferme)
  }

  @Test(expected = classOf[BusinessException])
  def pocosInscriptosNoGeneranEquipos() {
    partidoPocosJugadores.generarEquipos()
  }

  @Test(expected = classOf[BusinessException])
  def partidoSinIniciarNoPuedeGenerarEquipos() {
    for (i <- 0 until 3) {
      inscribir(partidoPocosJugadores, new Jugador())
    }
    partidoPocosJugadores.generarEquipos()
  }

  @Test
  def inscripcionJugadorNuevoDesplazaAPrimerSolidario() {
    inscribir(partido1, roly)
    Assert.assertTrue(partido1.inscriptos.contains(roly))
    Assert.assertFalse(partido1.inscriptos.contains(eric))
    Assert.assertTrue(partido1.inscriptos.contains(ferme))
  }

  @Test
  def generarEquiposPorHandicap() {
    println("******************************************")
    println("ordenamiento por handicap")
    val jugadores = partido1.ordenarEquipos()
    for (jugador <- jugadores) {
      println("Jugador: " + jugador + " - calificacion: " + jugador.calificacion)
    }
    Assert.assertEquals(Seq(ferme, roly, pato, dodi, lechu, chicho, rodri, sytek, leo, mike), jugadores)
  }

  @Test
  def generarEquiposPorCalificacionUltimos2Partidos() {
    partido1.criterioOrdenamiento = new OrdenamientoCalificacionUltimos2Partidos()
    println("******************************************")
    println("ordenamiento por ultimas 2 calificaciones")
    val jugadores = partido1.ordenarEquipos()
    for (jugador <- jugadores) {
      val puntajes = jugador.puntajes
      val misPuntajes = new ArrayBuffer[Double]()
      if (!puntajes.isEmpty) {
        misPuntajes :+ jugador.puntajes(puntajes.size - 1)
      }
      if (puntajes.size > 1) {
        misPuntajes :+ jugador.puntajes(puntajes.size - 2)
      }
      var promedio = 0d
      for (puntaje <- misPuntajes) {
        promedio += puntaje
      }
      promedio /= misPuntajes.size
      println("Jugador: " + jugador + " puntajes: " + jugador.puntajes + 
        " ult.puntajes: " + 
        misPuntajes + 
        " promedio: " + 
        promedio)
    }
    Assert.assertEquals(Seq(ferme, pato, lechu, roly, mike, chicho, dodi, rodri, sytek, leo), jugadores)
  }

  @Test
  def generarEquiposPorMixDeCriterios() {
    val ordenamientoMix = new OrdenamientoMix()
    ordenamientoMix.addCriterio(new OrdenamientoCalificacionUltimos2Partidos())
    ordenamientoMix.addCriterio(new OrdenamientoPorHandicap())
    partido1.criterioOrdenamiento = ordenamientoMix
    println("******************************************")
    println("ordenamiento por mix")
    val jugadores = partido1.ordenarEquipos()
    println(jugadores)
    Assert.assertEquals(Seq(ferme, roly, pato, lechu, dodi, chicho, rodri, sytek, leo, mike), jugadores)
  }

  @Test
  def distribuirEquiposParEImpar() {
    partido1.cerrar()
    partido1.generarEquipos()
    Assert.assertEquals(Seq(ferme, pato, lechu, rodri, leo), partido1.equipo1.jugadores)
    Assert.assertEquals(Seq(roly, dodi, chicho, sytek, mike), partido1.equipo2.jugadores)
  }

  @Test
  def distribuirEquipos14589() {
    partido1.distribucionEquipos = 16 // ordenamiento
    partido1.cerrar()
    partido1.generarEquipos()
    Assert.assertEquals(Seq(ferme, dodi, lechu, sytek, leo), partido1.equipo1.jugadores)
    Assert.assertEquals(Seq(roly, pato, chicho, rodri, mike), partido1.equipo2.jugadores)
  }

  @Test(expected = classOf[BusinessException])
  def generarEquiposCuandoSeCierra() {
    partido1.distribucionEquipos = 16 // ordenamiento
    partido1.cerrar()
    partido1.generarEquipos()
    partido1.generarEquipos()
  }

  /** *************************************************************************
    * METODOS AUXILIARES DE LOS TESTS
    ****************************************************************************/
  def inscribir(partido: Partido, jugador: Jugador) {
    partido.inscribir(jugador)
  }
}