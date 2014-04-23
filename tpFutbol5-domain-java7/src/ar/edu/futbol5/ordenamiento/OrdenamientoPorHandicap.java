package ar.edu.futbol5.ordenamiento;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;

import ar.edu.futbol5.Jugador;
import ar.edu.futbol5.Partido;

public class OrdenamientoPorHandicap implements CriterioOrdenamiento {
	
	public List<Jugador> ordenar(Partido partido) {
		Collections.sort(partido.getInscriptos(), new Comparator<Jugador>() {
			@Override
			public int compare(Jugador jugador1, Jugador jugador2) {
				return calcularValor(jugador1).compareTo(calcularValor(jugador2));
			}
		});

		Collections.reverse(partido.getInscriptos());
		
		List<Jugador> jugadores=new ArrayList<Jugador>();
		jugadores.addAll(partido.getInscriptos());
		return jugadores;
	}
	
	public Double calcularValor(Jugador jugador) {
		return jugador.getCalificacion();
	}
	
}
