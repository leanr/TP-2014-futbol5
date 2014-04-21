package ar.edu.futbol5

import ar.edu.futbol5.excepciones.BusinessException
import ar.edu.futbol5.ordenamiento.CriterioOrdenamiento
import ar.edu.futbol5.ordenamiento.OrdenamientoPorHandicap
import java.util.ArrayList
import java.util.List

class Partido {

	@Property List<Jugador> inscriptos
	@Property Equipo equipo1
	@Property Equipo equipo2
	String estado
	@Property CriterioOrdenamiento criterioOrdenamiento
	@Property int distribucionEquipos // 5 es par/impar, 16 = 1,4,5,8,9 vs. 2,3,6,7,10

	new() {
		inscriptos = new ArrayList<Jugador>
		estado = "A"
		distribucionEquipos = 5 // par/impar
		criterioOrdenamiento = new OrdenamientoPorHandicap
	}

	def generarEquipos() {
		if (this.validarInscripcion == -1) {
			throw new BusinessException("Hubo un error")
		}
		this.distribuirEquipos(this.ordenarEquipos)
		estado = "G"
	}

	def validarInscripcion() {
		if (inscriptos.size < 10) {
			return -1
		}
		if (estado.equalsIgnoreCase("A")) {
			return -1
		}
		if (estado.equalsIgnoreCase("G")) {
			return -1
		}
		return 0
	}

	def distribuirEquipos(List<Jugador> jugadores) {
		equipo1 = new Equipo
		equipo2 = new Equipo
		if (distribucionEquipos == 5) {
			equipo1.jugadores = newArrayList(jugadores.get(0), jugadores.get(2), jugadores.get(4), jugadores.get(6),
				jugadores.get(8))
			equipo2.jugadores = newArrayList(jugadores.get(1), jugadores.get(3), jugadores.get(5), jugadores.get(7),
				jugadores.get(9))
		} else {

			// distribucionEquipos == 16 que ordena de esta manera
			equipo1.jugadores = newArrayList(jugadores.get(0), jugadores.get(3), jugadores.get(4), jugadores.get(7),
				jugadores.get(8))
			equipo2.jugadores = newArrayList(jugadores.get(1), jugadores.get(2), jugadores.get(5), jugadores.get(6),
				jugadores.get(9))
		}
	}

	def List<Jugador> ordenarEquipos() {
		criterioOrdenamiento.ordenar(this)
	}

	def void inscribir(Jugador jugador) {
		if (inscriptos.size < 10) {
			this.inscriptos.add(jugador)
		} else {
			if (this.hayAlgunJugadorQueCedaLugar()) {
				this.inscriptos.remove(this.jugadorQueCedeLugar())
				this.inscriptos.add(jugador)
			} else {
				throw new BusinessException("No hay más lugar")
			}
		}
	}

	def boolean hayAlgunJugadorQueCedaLugar() {
		inscriptos.exists[jugador|jugador.dejaLugarAOtro]
	}

	def Jugador jugadorQueCedeLugar() {
		if (!hayAlgunJugadorQueCedaLugar()) {
			return null
		}
		return inscriptos.filter[jugador|jugador.dejaLugarAOtro].get(0)
	}

	def void cerrar() {
		estado = "C"
	}
}
