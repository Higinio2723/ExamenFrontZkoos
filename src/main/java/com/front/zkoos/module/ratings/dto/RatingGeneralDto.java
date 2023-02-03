package com.front.zkoos.module.ratings.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Builder
@NoArgsConstructor
@AllArgsConstructor
@Data
public class RatingGeneralDto {

  private List<RatingFormatDto> listData;

  private Double promedio;



}
