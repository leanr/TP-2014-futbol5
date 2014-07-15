package ar.edu.futbol5

import ar.edu.futbol5.excepciones.BusinessException
import ar.edu.futbol5.ordenamiento.CriterioOrdenamiento
import ar.edu.futbol5.ordenamiento.OrdenamientoPorHandicap
import java.util.ArrayList
import java.util.List
import ar.edu.futbol5.distribucion.DistribucionInterfaz
import ar.edu.futbol5.distribucion.Distribucion14589
import ar.edu.futbol5.confirmarequipos.PartidoInt
import ar.edu.futbol5.confirmarequipos.PartidoAbierto
import ar.edu.futbol5.confirmarequipos.PartidoCerrado

class Partido {

	@Property List<Jugador> inscriptos
	@Property List<Jugador> equipo1
	@Property List<Jugador> equipo2
	@Property PartidoInt estado
	@Property CriterioOrdenamiento criterioOrdenamiento
	@Property DistribucionInterfaz criterioDistribucionEquipos 

	new() {
		inscriptos = new ArrayList<Jugador>
		estado = new PartidoAbierto
		criterioDistribucionEquipos = new Distribucion14589 
		criterioOrdenamiento = new OrdenamientoPorHandicap
	}

	def generarEquipos() {
		if (inscriptos.size < 10){
		estado.generarEquipos(this)
		}
				
		
	}

	

	def distribuirEquipos(List<Jugador> jugadores) {
		criterioDistribucionEquipos.distribuirEquipos(this,jugadores)
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
		inscriptos.filter[jugador|jugador.dejaLugarAOtro].get(0)
	}

	def void cerrar() {
	this.setEstado(new PartidoCerrado)
	}
}
