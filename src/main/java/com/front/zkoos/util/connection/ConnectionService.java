package com.front.zkoos.util.connection;

import com.fasterxml.jackson.databind.JsonNode;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.http.converter.StringHttpMessageConverter;
import org.springframework.stereotype.Service;
import org.springframework.util.MultiValueMap;
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
    public ResponseEntity<String> putRest(String uri, HttpEntity<String> requestEntity){
        RestTemplate restTemplate = new RestTemplate();
        ResponseEntity<String> response = null;
        try{

            restTemplate.getMessageConverters()
                    .add(0, new StringHttpMessageConverter(StandardCharsets.UTF_8));

            response = restTemplate.exchange(uri,
                    HttpMethod.PUT, requestEntity,
                    String.class);

        }catch(Exception ex){
            //Exception
            logger.error(ex.getMessage(),ex);
        }
        return response;
    }

    @Override
    public ResponseEntity<String> deleteRest(String uri, HttpEntity<String> requestEntity){
        RestTemplate restTemplate = new RestTemplate();
        ResponseEntity<String> response = null;
        try{

            restTemplate.getMessageConverters()
                    .add(0, new StringHttpMessageConverter(StandardCharsets.UTF_8));

            response = restTemplate.exchange(uri,
                    HttpMethod.DELETE, requestEntity,
                    String.class);

        }catch(Exception ex){
            //Exception
            logger.error(ex.getMessage(),ex);
        }
        return response;
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


}
