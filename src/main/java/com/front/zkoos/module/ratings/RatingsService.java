package com.front.zkoos.module.ratings;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.front.zkoos.module.form.dto.GeneralDto;
import com.front.zkoos.module.form.dto.RatingFormatDto;
import com.front.zkoos.util.connection.ConnectionService;
import com.google.gson.Gson;
import com.google.gson.JsonObject;

import java.util.LinkedList;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.*;
import org.springframework.stereotype.Service;
import org.springframework.util.LinkedMultiValueMap;
import org.springframework.util.MultiValueMap;
import org.zkoss.zk.ui.select.annotation.WireVariable;

@Service
public class RatingsService implements IRatingService{

    private final Logger logger = LoggerFactory.getLogger(RatingsService.class);

  //data model
  	private List<RatingFormatDto> ratingList= new LinkedList<RatingFormatDto>();

    @Override
    public GeneralDto saveRatings(Ratings ratings) throws JsonProcessingException {

        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_JSON);

        JsonObject properties = new JsonObject();
        properties.addProperty("idAlumno", ratings.getIdAlumno());
        properties.addProperty("idMateria", ratings.getIdMateria());
        properties.addProperty("calificacion", ratings.getCalificacion());
        HttpEntity<String> requestEntity = new HttpEntity<>(properties.toString(), headers);
        ConnectionService connectionService = new ConnectionService();

//        logger.info("################## myProperties {}",myProperties.getHttpSchoolUrl());

        ResponseEntity<String>  dataResponse = connectionService.postRest("http://localhost:8083/ratings" , requestEntity);
        logger.info("################### termino proceso Validation{}", dataResponse);
        String data = dataResponse.getBody();
        Gson gson = new Gson();
        GeneralDto result = gson.fromJson(data,GeneralDto.class);

        logger.info("################ validationDtoResponse {}",result);

        return result;
    }

    @Override
    public  MultiValueMap<String, String> connectionHeader(String mediaType){

        MultiValueMap<String, String> headers = new LinkedMultiValueMap<>();
        headers.add("Connection", "keep-alive");
        headers.add("Accept", "*/*");
        headers.add("Content-Type", mediaType);
        return headers;
    }

	@Override
	public List<RatingFormatDto> findAll() {
		// ratingList
		return null;
	}

	@Override
	public List<RatingFormatDto> search(String keyword) {
//		List<Car> result = new LinkedList<Car>();
//		if (keyword==null || "".equals(keyword)){
//			result = carList;
//		}else{
//			for (Car c: carList){
//				if (c.getModel().toLowerCase().contains(keyword.toLowerCase())
//					||c.getMake().toLowerCase().contains(keyword.toLowerCase())){
//					result.add(c);
//				}
//			}
//		}
		return null;
	}
}
