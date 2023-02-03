package com.front.zkoos.module.ratings.dto;

import lombok.*;

@Builder
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@ToString
public class RatingsDataDto {

    private Integer id;

    private Integer idAlumno;

    private Integer idMateria;
    private Double calificacion;

}
