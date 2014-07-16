package ar.edu.futbol5

import ar.edu.futbol5.inscripcion.CriterioInscripcion
import ar.edu.futbol5.inscripcion.ModoSolidario
import java.util.ArrayList
import java.util.List
import ar.edu.futbol5.inscripcion.ModoEstandar

class Jugador {

	@Property String nombre	
	@Property Double calificacion
	@Property List<Double> puntajes
	CriterioInscripcion criterioInscripcion
	
	new() {
		this.puntajes = new ArrayList<Double>
		this.calificacion = null
		this.criterioInscripcion = new ModoEstandar
		this.nombre = ""
	}
	
	new(String nombre, double calificacion, List<Double> puntajes) {
		this.calificacion = calificacion
		this.puntajes = puntajes	
		this.criterioInscripcion = new ModoEstandar
		this.nombre = nombre
	}
	
	def modoSolidario() {
		criterioInscripcion = new ModoSolidario
	}
	
	def boolean dejaLugarAOtro() {
		criterioInscripcion.dejaLugarAOtro()
	}

	override toString() {
		//"Jugador (" + calificacion + ") - modo " + criterioInscripcion.toString()
		nombre
	}
	
	def esPar(List<Jugador> jugadores) {
		val posicionJug = jugadores.indexOf(this)
		(posicionJug % 2) == 0
	}
	
	def esImpar(List<Jugador> jugadores){
		!this.esPar(jugadores)
	}

}

