package ar.edu.futbol5.ordenamiento;

import ar.edu.futbol5.Jugador;
import ar.edu.futbol5.Partido;

import java.util.Comparator;
import java.util.List;

public interface CriterioOrdenamiento {
	
	// BAD SMELL - Cada subclase tiene que hacer un sort by de la colección
	// la interfaz podría devolver únicamente el bloque
	public List<Jugador> ordenar(Partido partido);

	public Double calcularValor(Jugador jugador);
		 
}