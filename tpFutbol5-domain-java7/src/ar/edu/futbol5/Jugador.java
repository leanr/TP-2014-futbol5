package ar.edu.futbol5;

import ar.edu.futbol5.inscripcion.CriterioInscripcion;
import ar.edu.futbol5.inscripcion.ModoSolidario;

import java.util.ArrayList;
import java.util.List;

import ar.edu.futbol5.inscripcion.ModoEstandar;

public class Jugador {

	private String nombre;
	private Double calificacion;
	private List<Double> puntajes;
	CriterioInscripcion criterioInscripcion;
	
	public Jugador() {
		this.puntajes = new ArrayList<Double>();
		this.calificacion = null;
		this.criterioInscripcion = new ModoEstandar();
		this.nombre = "";
	}
	
	public Jugador(String nombre, double calificacion, List<Double> puntajes) {
		this.calificacion = calificacion;
		this.puntajes = puntajes;
		this.criterioInscripcion = new ModoEstandar();
		this.nombre = nombre;
	}
	
	void modoSolidario() {
		criterioInscripcion = new ModoSolidario();
	}
	
	boolean dejaLugarAOtro() {
		if(criterioInscripcion instanceof ModoSolidario){
			return true;
		} else {
			return false;
		}
	}

	public String toString() {
		//"Jugador (" + calificacion + ") - modo " + criterioInscripcion.toString()
		return nombre;
	}

	public Double getCalificacion() {
		return calificacion;
	}

	public List<Double> getPuntajes() {
		return puntajes;
	}
}

