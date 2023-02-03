package com.front.zkoos.module.form.data;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Builder
@NoArgsConstructor
@AllArgsConstructor
@Data
public class Ratings {
	private int idCalificacion,idAlumno, idMateria ;

	private String nombre, apellidoPaterno, apellidoMaterno, nombreMateria;
	private double calificacion;

}
