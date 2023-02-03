package com.front.zkoos.util.connection;

import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.front.zkoos.module.ratings.dto.GeneralDto;
import com.front.zkoos.module.ratings.dto.RatingsErrorDto;
import com.google.gson.Gson;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.http.converter.StringHttpMessageConverter;
import org.springframework.stereotype.Service;
import org.springframework.util.MultiValueMap;
import org.springframework.web.client.HttpStatusCodeException;
import org.springframework.web.client.RestClientException;
import org.springframework.web.client.RestTemplate;

import java.nio.charset.StandardCharsets;

@Service
public class ConnectionService implements IConnectionService{

    private final Logger logger = LoggerFactory.getLogger(ConnectionService.class);

    @Override
    public ResponseEntity<String> postRest(String uri, HttpEntity<String> requestEntity){
        RestTemplate restTemplate = new RestTemplate();
        ResponseEntity<String> response = null;
        try{

            restTemplate.getMessageConverters()
                    .add(0, new StringHttpMessageConverter(StandardCharsets.UTF_8));

            response = restTemplate.exchange(uri,
                    HttpMethod.POST, requestEntity,
                    String.class);

        }catch(Exception ex){
            //Exception
            logger.error(ex.getMessage(),ex);
        }
        return response;
    }


    @Override
    public GeneralDto putRest(String uri, HttpEntity<String> requestEntity){
        RestTemplate restTemplate = new RestTemplate();
        ResponseEntity<String> response = null;
        GeneralDto result = null;
        RatingsErrorDto ratingsErrorDto = null;
        try{

            restTemplate.getMessageConverters()
                    .add(0, new StringHttpMessageConverter(StandardCharsets.UTF_8));

            response = restTemplate.exchange(uri,
                    HttpMethod.PUT, requestEntity,
                    String.class);

            String data = (String)response.getBody();
            Gson gson = new Gson();
            result = gson.fromJson(data,GeneralDto.class);

            logger.info("################ validationDtoResponse {}",result);

        }catch(HttpStatusCodeException e){
            try {
                String errorpayload = e.getResponseBodyAsString();
                logger.info("{}",errorpayload);

                ObjectMapper mapper = new ObjectMapper();
                JsonNode actualObj = mapper.readTree(errorpayload);

                ratingsErrorDto = this.getMessageValid(actualObj);

                result = GeneralDto.builder()
                        .success(ratingsErrorDto.getStatus())
                        .msg(ratingsErrorDto.getMessage())
                        .build();
            }catch (Exception jsonEx){
                logger.error(jsonEx.getMessage(),jsonEx);
            }
            //do whatever you want
        } catch(RestClientException e){
            //no response payload, tell the user sth else
        }
        return result;
    }

    @Override
    public GeneralDto deleteRest(String uri, HttpEntity<String> requestEntity){
        RestTemplate restTemplate = new RestTemplate();
        ResponseEntity<String> response = null;
        GeneralDto result = null;
        RatingsErrorDto ratingsErrorDto = null;
        try{

            restTemplate.getMessageConverters()
                    .add(0, new StringHttpMessageConverter(StandardCharsets.UTF_8));

            response = restTemplate.exchange(uri,
                    HttpMethod.DELETE, requestEntity,
                    String.class);

            String data = (String)response.getBody();
            Gson gson = new Gson();
            result = gson.fromJson(data,GeneralDto.class);

            logger.info("################ validationDtoResponse {}",result);

        }catch(HttpStatusCodeException e){
            try {
                String errorpayload = e.getResponseBodyAsString();
                logger.info("{}",errorpayload);

                ObjectMapper mapper = new ObjectMapper();
                JsonNode actualObj = mapper.readTree(errorpayload);

                ratingsErrorDto = this.getMessageValid(actualObj);

                   result = GeneralDto.builder()
                           .success(ratingsErrorDto.getStatus())
                           .msg(ratingsErrorDto.getMessage())
                           .build();
            }catch (Exception jsonEx){
                logger.error(jsonEx.getMessage(),jsonEx);
            }
            //do whatever you want
        } catch(RestClientException e){
            //no response payload, tell the user sth else
        }
        return result;
    }

    @Override
    public JsonNode postBodyRest(String uri, HttpEntity<String> requestEntity){
        JsonNode map = null;

        try{
            RestTemplate restTemplate = new RestTemplate();

            ResponseEntity<JsonNode> response = restTemplate.exchange(uri,
                    HttpMethod.POST, requestEntity,
                    JsonNode.class);
            map = response.getBody();

        }catch(Exception ex){
            //Exception
            logger.error(ex.getMessage(),ex);
        }
        return map;
    }


    @Override
    public JsonNode getRest(String uri, HttpEntity<MultiValueMap<String, Object>> requestEntity ){
        JsonNode map = null;

        try{
            RestTemplate restTemplate = new RestTemplate();

            ResponseEntity<JsonNode> response = restTemplate.exchange(uri,
                    HttpMethod.GET, requestEntity,
                    JsonNode.class);
            map = response.getBody();

        }catch(Exception ex){
            //Exception
            logger.error(ex.getMessage(),ex);
        }
        return map;
    }


    @Override
    public ResponseEntity<String> getStringRest(String uri, HttpEntity<MultiValueMap<String, Object>> requestEntity ){

        ResponseEntity<String> response = null;

        try{
            RestTemplate restTemplate = new RestTemplate();

            response = restTemplate.exchange(uri,
                    HttpMethod.GET, requestEntity,
                    String.class);

        }catch(Exception ex){
            //Exception
            logger.error(ex.getMessage(),ex);
        }
        return response;
    }




    @Override
    public ResponseEntity<byte[]> postWithOctetStreamReturnRest(String url, HttpEntity<String> requestEntity ){
        ResponseEntity<byte[]> result = null;

        try{
            RestTemplate restTemplate = new RestTemplate();

            result = restTemplate.exchange(url,
                    HttpMethod.POST, requestEntity,
                    byte[].class);



        }catch(Exception ex){
            //Exception
            logger.error(ex.getMessage(),ex);
        }
        return result;
    }

    public RatingsErrorDto getMessageValid(JsonNode actualObj){
        RatingsErrorDto datosResult = null;


        logger.info("actualObj {}",actualObj.get("message").asText());
        if(actualObj.get("status").toString().equals("400")){
            datosResult = RatingsErrorDto.builder()
                    .status("400")
                    .message(actualObj.get("message").asText())
                    .build();
        }else if(actualObj.get("status").toString().equals("404")){
            datosResult = RatingsErrorDto.builder()
                    .status("500")
                    .message(actualObj.get("message").asText())
                    .build();

        }else if(actualObj.get("status").toString().equals("500")){
            datosResult = RatingsErrorDto.builder()
                    .status("500")
                    .message(actualObj.get("message").asText())
                    .build();

        }
        return datosResult;

    }

}
