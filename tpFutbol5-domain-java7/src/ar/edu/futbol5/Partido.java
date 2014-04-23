package ar.edu.futbol5;

import ar.edu.futbol5.excepciones.BusinessException;
import ar.edu.futbol5.ordenamiento.CriterioOrdenamiento;
import ar.edu.futbol5.ordenamiento.OrdenamientoPorHandicap;
import ar.edu.futbol5.utilitarios.Lists;

import java.util.ArrayList;
import java.util.List;

public class Partido {

	private List<Jugador> inscriptos;
	private Equipo equipo1;
	private Equipo equipo2;
	private String estado;
	private CriterioOrdenamiento criterioOrdenamiento;
	private int distribucionEquipos; // 5 es par/impar, 16 = 1,4,5,8,9 vs. 2,3,6,7,10

	public Partido() {
		inscriptos = new ArrayList<Jugador>();
		estado = "A";
		distribucionEquipos = 5; // par/impar
		criterioOrdenamiento = new OrdenamientoPorHandicap();
	}

	public void generarEquipos() {
		if (this.validarInscripcion() == -1) {
			throw new BusinessException("Hubo un error");
		}
		this.distribuirEquipos(this.ordenarEquipos());
		estado = "G";
	}

	private int validarInscripcion() {
		if (inscriptos.size() < 10) {
			return -1;
		}
		if (estado.equalsIgnoreCase("A")) {
			return -1;
		}
		if (estado.equalsIgnoreCase("G")) {
			return -1;
		}
		return 0;
	}

	private void distribuirEquipos(List<Jugador> jugadores) {
		equipo1 = new Equipo();
		equipo2 = new Equipo();
		if (distribucionEquipos == 5) {
			equipo1.setJugadores(Lists.newArrayList(jugadores.get(0),jugadores.get(2),jugadores.get(4),jugadores.get(6),jugadores.get(8)));
			
			equipo2.setJugadores(Lists.newArrayList(jugadores.get(1),jugadores.get(3),jugadores.get(5),jugadores.get(7),jugadores.get(9)));
		} else {
			// distribucionEquipos == 16 que ordena de esta manera
			equipo1.setJugadores(Lists.newArrayList(jugadores.get(0),jugadores.get(3),jugadores.get(4),jugadores.get(7),jugadores.get(8)));
			
			equipo2.setJugadores(Lists.newArrayList(jugadores.get(1),jugadores.get(2),jugadores.get(5),jugadores.get(6),jugadores.get(9)));
		}
	}

	public List<Jugador> ordenarEquipos() {
		return criterioOrdenamiento.ordenar(this);
	}

	void inscribir(Jugador jugador) {
		if (inscriptos.size() < 10) {
			this.inscriptos.add(jugador);
		} else {
			if (this.hayAlgunJugadorQueCedaLugar()) {
				this.inscriptos.remove(this.jugadorQueCedeLugar());
				this.inscriptos.add(jugador);
			} else {
				throw new BusinessException("No hay más lugar");
			}
		}
	}

	private boolean hayAlgunJugadorQueCedaLugar() {
		for (Jugador inscripto : inscriptos) {
			if(inscripto.dejaLugarAOtro()){
				return true;
			}
		}
		return false;
	}

	private Jugador jugadorQueCedeLugar() {
		if (!hayAlgunJugadorQueCedaLugar()) {
			return null;
		}
		
		List<Jugador> jugadores=new ArrayList<Jugador>();
		for (Jugador inscripto : inscriptos) {
			if(inscripto.dejaLugarAOtro()){
				jugadores.add(inscripto);
			}
		}
		
		return jugadores.get(0);
	}

	public void cerrar() {
		estado = "C";
	}

	public List<Jugador> getInscriptos() {
		return inscriptos;
	}

	public void setCriterioOrdenamiento(CriterioOrdenamiento criterioOrdenamiento) {
		this.criterioOrdenamiento = criterioOrdenamiento;
	}

	public void setDistribucionEquipos(int distribucionEquipos) {
		this.distribucionEquipos = distribucionEquipos;
	}

	public Equipo getEquipo1() {
		return equipo1;
	}

	public Equipo getEquipo2() {
		return equipo2;
	}
	
}
