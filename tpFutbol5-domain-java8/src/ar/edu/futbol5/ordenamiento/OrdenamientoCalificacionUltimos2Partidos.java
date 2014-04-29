package ar.edu.futbol5.ordenamiento;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;
import java.util.stream.DoubleStream;

import ar.edu.futbol5.Jugador;
import ar.edu.futbol5.Partido;

public class OrdenamientoCalificacionUltimos2Partidos implements CriterioOrdenamiento {
	
	
	public List<Jugador> ordenar(Partido partido) {
		Collections.sort(
				partido.getInscriptos(), 
				(jugador1, jugador2) -> 
					calcularValor(jugador1).compareTo(calcularValor(jugador2)));

		Collections.reverse(partido.getInscriptos());
		
		List<Jugador> jugadores=new ArrayList<Jugador>();
		jugadores.addAll(partido.getInscriptos());
		return jugadores;
	}
	
	public Double calcularValor(Jugador jugador) {
		List<Double> puntajes=jugador.getPuntajes();
		List<Double> misPuntajes=new ArrayList<Double>(); 
		if(!puntajes.isEmpty()){
			misPuntajes.add(jugador.getPuntajes().get(puntajes.size()-1));
		}
		if(puntajes.size()>1){
			misPuntajes.add(jugador.getPuntajes().get(puntajes.size()-2));
		}
		
		return misPuntajes.stream().mapToDouble(Double::doubleValue).average().getAsDouble();
	}
	
}
