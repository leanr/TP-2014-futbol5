package ar.edu.futbol5.distribucion


import ar.edu.futbol5.Partido
import ar.edu.futbol5.Jugador
import java.util.List

class Distribucion14589 implements DistribucionInterfaz {
	@Property List<Integer>  listPosicionesValidas =  newArrayList(0,3,4,7,8)
	
	
	override distribuirEquipos(Partido partido , List<Jugador> jugadores) {
		
				
	val equipoA = jugadores.filter[jug|jug.es14589(jugadores)]
	val equipoB = jugadores.filter[jug|jug.esOtraPosicion(jugadores)]
	
	partido.setEquipo1(equipoA)
	partido.setEquipo2(equipoB)
	
		
	}
	
	def es14589(Jugador jugador,List <Jugador> jugadores){
		val posicionJug = (jugadores.indexOf(jugador))
		listPosicionesValidas.contains(posicionJug)
		
		}

	
	def esOtraPosicion(Jugador jugador,List <Jugador> jugadores){

	val posicionJug = (jugadores.indexOf(jugador))
	!listPosicionesValidas.contains(posicionJug)
}
	

	
	}