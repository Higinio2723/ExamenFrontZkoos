package com.front.zkoos.module.ratings;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.front.zkoos.module.form.data.Ratings;
import com.front.zkoos.module.ratings.dto.GeneralDto;
import com.front.zkoos.module.ratings.dto.RatingFormatDto;

import java.util.List;

import com.front.zkoos.module.ratings.dto.RatingGeneralDto;
import org.springframework.util.MultiValueMap;

public interface IRatingService {

    GeneralDto saveRatings(Ratings ratings) throws JsonProcessingException;
   
    MultiValueMap<String, String> connectionHeader(String mediaType);
    
    /**
	 * Retrieve all ratings
	 * @return all cars
	 */
	List<RatingFormatDto> findAll();
	
	/**
	 * search cars according to keyword in id Alumno
	 * @param keyword for search
	 * @return list of car that match the keyword
	 */
	RatingGeneralDto search(String keyword);


	GeneralDto updateRatings(Ratings ratings) throws JsonProcessingException;


	GeneralDto deleteRatings(String idRatings) throws JsonProcessingException;
    
}
