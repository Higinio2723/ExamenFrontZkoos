package com.front.zkoos.module.ratings.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Builder
@NoArgsConstructor
@AllArgsConstructor
@Data
public class StudentDto {

    private Integer idStudent;

    private String nombre;

    private String apellidoMaterno;

    private String apellidoPaterno;

}
