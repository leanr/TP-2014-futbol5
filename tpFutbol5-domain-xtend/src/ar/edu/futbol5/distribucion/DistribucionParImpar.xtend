package ar.edu.futbol5.distribucion

import ar.edu.futbol5.Partido
import ar.edu.futbol5.Jugador
import java.util.List

class DistribucionParImpar implements DistribucionInterfaz {
	override distribuirEquipos(Partido partido,List<Jugador> jugadores) {
		
		
		val equipoA= jugadores.obtenerLosPares()
		val equipoB= jugadores.obtenerLosImpares()
		
		partido.setEquipo1(equipoA)
		partido.setEquipo2(equipoB)

	}

	def obtenerLosPares(List<Jugador> jugadores){
		jugadores.filter[jug|jug.esPar(jugadores)]
	
}

def obtenerLosImpares(List<Jugador> jugadores){
		jugadores.filter[jug|jug.esImpar(jugadores)]
	
}



}
