package ar.edu.futbol5.inscripcion

import ar.edu.futbol5.inscripcion.CriterioInscripcion

class ModoSolidario implements CriterioInscripcion {

	override toString() {
		"Solidario"
	}
	
	override dejaLugarAOtro(){
		return true
	}
	
}
