package ar.edu.futbol5.distribucion

import ar.edu.futbol5.Partido
import ar.edu.futbol5.Jugador
import java.util.List

class DistribucionParImpar implements DistribucionInterfaz {
	override distribuirEquipos(Partido partido,List<Jugador> jugadores) {
		
		
		val equipoA= newArrayList(jugadores.get(0), jugadores.get(2), jugadores.get(4), jugadores.get(6),jugadores.get(8))
		val equipoB= newArrayList(jugadores.get(1), jugadores.get(3), jugadores.get(5), jugadores.get(7),jugadores.get(9))
		
		partido.setEquipo1(equipoA)
		partido.setEquipo2(equipoB)

	}



}
