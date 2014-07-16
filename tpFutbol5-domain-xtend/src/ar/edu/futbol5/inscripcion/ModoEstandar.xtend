package ar.edu.futbol5.inscripcion

import ar.edu.futbol5.inscripcion.CriterioInscripcion

class ModoEstandar implements CriterioInscripcion {

	override toString() {
		"Estándar"
	}
	
	override dejaLugarAOtro(){
		return false
	}
		
}