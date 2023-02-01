package com.front.zkoos.module.form;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.front.zkoos.module.form.dto.GeneralDto;
import org.springframework.util.MultiValueMap;

public interface IRatingService {

    GeneralDto saveRatings(Ratings ratings) throws JsonProcessingException;




    MultiValueMap<String, String> connectionHeader(String mediaType);
}
