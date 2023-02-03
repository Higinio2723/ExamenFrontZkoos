package com.front.zkoos.module.ratings.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Builder
@NoArgsConstructor
@AllArgsConstructor
@Data
public class RatingDataDto {

   private Integer id_t_usuario;
   private String nombre;
   private String apellido;
   private String materia;
   private Double calificacion;
   private String fecha_registro;



}

