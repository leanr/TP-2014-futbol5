package ar.edu.futbol5.ordenamiento

import ar.edu.futbol5.Jugador
import ar.edu.futbol5.Partido
import java.util.List

interface CriterioOrdenamiento {
	
	// BAD SMELL - Cada subclase tiene que hacer un sort by de la colección
	// la interfaz podría devolver únicamente el bloque
	def List<Jugador> ordenar(Partido partido)

	def (Jugador) => Double calcularValor()
		 
}