package com.front.zkoos.module.form;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.front.zkoos.module.form.dto.GeneralDto;
import com.front.zkoos.util.connection.ConnectionService;
import com.google.gson.Gson;
import com.google.gson.JsonObject;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.*;
import org.springframework.stereotype.Service;
import org.springframework.util.LinkedMultiValueMap;
import org.springframework.util.MultiValueMap;

@Service
public class RatingsService implements IRatingService{

    private final Logger logger = LoggerFactory.getLogger(RatingsService.class);

    @Override
    public GeneralDto saveRatings(Ratings ratings) throws JsonProcessingException {

        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_JSON);

        JsonObject properties = new JsonObject();
        properties.addProperty("idAlumno", 1);
        properties.addProperty("idMateria", 2);
        properties.addProperty("calificacion", 9.8);
        HttpEntity<String> requestEntity = new HttpEntity<>(properties.toString(), headers);
        ConnectionService connectionService = new ConnectionService();

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
}
