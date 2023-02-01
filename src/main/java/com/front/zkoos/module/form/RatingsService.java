package com.front.zkoos.module.form;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.front.zkoos.module.form.dto.GeneralDto;
import com.front.zkoos.module.form.dto.RatingsDataDto;
import com.front.zkoos.util.connection.ConnectionService;
import com.front.zkoos.util.connection.MyProperties;
import com.google.gson.Gson;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.util.LinkedMultiValueMap;
import org.springframework.util.MultiValueMap;

@Service
public class RatingsService implements IRatingService{

    private final Logger logger = LoggerFactory.getLogger(RatingsService.class);

    @Override
    public GeneralDto saveRatings(Ratings ratings) throws JsonProcessingException {

        RatingsDataDto request = RatingsDataDto.builder()
                .idAlumno(ratings.getIdAlumno())
                .idMateria(ratings.getIdMateria())
                .calificacion(ratings.getCalificacion())
                .build();

        logger.info("############## request {}",request.toString());

        MultiValueMap<String, String> headers = this.connectionHeader(MediaType.APPLICATION_JSON.toString());

        LinkedMultiValueMap<String, Object> body = new LinkedMultiValueMap<>();
        body.add("raw",request);

        logger.info("iniciando proceso validation {}", headers);
        HttpEntity<MultiValueMap<String, Object>> requestEntity = new HttpEntity<>(body, headers);

        ConnectionService connectionService = new ConnectionService();

        ResponseEntity<String>  dataResponse = connectionService.postRest("http://localhost:8083/ratings" , requestEntity);
        logger.info("termino proceso Validation{}", dataResponse);
        String data = dataResponse.getBody();

        Gson gson = new Gson();
        GeneralDto result = gson.fromJson(data,GeneralDto.class);

        logger.info("validationDtoResponse {}",result);

        return result;

    }

    @Override
    public  MultiValueMap<String, String> connectionHeader(String mediaType){

        MultiValueMap<String, String> headers = new LinkedMultiValueMap<>();
        headers.add("Accept-Encoding", "gzip, deflate, br");
        headers.add("Connection", "keep-alive");
        headers.add("Accept", "*/*");
        headers.add("Content-Type", mediaType);

        return headers;
    }
}
