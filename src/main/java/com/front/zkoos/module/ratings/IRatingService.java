package com.front.zkoos.module.ratings;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.front.zkoos.module.form.dto.GeneralDto;
import com.front.zkoos.module.form.dto.RatingFormatDto;

import java.util.List;

import org.springframework.util.MultiValueMap;

public interface IRatingService {

    GeneralDto saveRatings(Ratings ratings) throws JsonProcessingException;
   
    MultiValueMap<String, String> connectionHeader(String mediaType);
    
    /**
	 * Retrieve all cars in the catalog.
	 * @return all cars
	 */
	public List<RatingFormatDto> findAll();
	
	/**
	 * search cars according to keyword in name and company.
	 * @param keyword for search
	 * @return list of car that match the keyword
	 */
	public List<RatingFormatDto> search(String keyword);
    
}
