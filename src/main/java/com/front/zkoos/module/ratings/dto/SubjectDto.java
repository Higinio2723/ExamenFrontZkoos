package com.front.zkoos.module.ratings.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Builder
@NoArgsConstructor
@AllArgsConstructor
@Data
public class SubjectDto {

    private Integer idMateria;

    private String nombre;

}
