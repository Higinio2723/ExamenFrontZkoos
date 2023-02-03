package com.front.zkoos.module.ratings;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.front.zkoos.module.form.data.Ratings;
import com.front.zkoos.module.ratings.dto.*;
import com.front.zkoos.util.connection.ConnectionService;
import com.google.gson.Gson;
import com.google.gson.JsonObject;

import java.util.LinkedList;
import java.util.List;

import org.json.JSONObject;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.*;
import org.springframework.stereotype.Service;
import org.springframework.util.LinkedMultiValueMap;
import org.springframework.util.MultiValueMap;

@Service
public class RatingsService implements IRatingService{

    private final Logger logger = LoggerFactory.getLogger(RatingsService.class);

  //data model
  	private List<RatingFormatDto> ratingList= new LinkedList<RatingFormatDto>();

    @Override
    public GeneralDto saveRatings(Ratings ratings) throws JsonProcessingException {

        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_JSON);

        JSONObject myObject = new JSONObject();

        // Cadenas de texto básicas
        myObject.put("idMateria", ratings.getIdMateria());
        myObject.put("calificacion", ratings.getCalificacion());

        // Objeto dentro de objeto
        JSONObject subdata = new JSONObject();
        subdata.put("nombre",ratings.getNombre());
        subdata.put("apellidoPaterno",ratings.getApellidoPaterno());
        subdata.put("apellidoMaterno",ratings.getApellidoMaterno());
        myObject.put("alumno", subdata);

        // Generar cadena de texto JSON
        System.out.print(myObject);

        HttpEntity<String> requestEntity = new HttpEntity<>(myObject.toString(), headers);
        ConnectionService connectionService = new ConnectionService();


        ResponseEntity<String>  dataResponse = connectionService.postRest("http://localhost:8083/ratings_det" , requestEntity);
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
        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_JSON);
        LinkedMultiValueMap<String, Object> body = new LinkedMultiValueMap<>();
        logger.info("iniciando proceso {}",headers );

        HttpEntity<MultiValueMap<String, Object>> requestEntity = new HttpEntity<>(body, headers);
        ConnectionService connectionService = new ConnectionService();

        JsonNode dataResponse  = connectionService.getRest("http://localhost:8083/ratings/students?idStudent=",requestEntity);
        logger.info("termino proceso {}",dataResponse );

        ObjectMapper objectMapper = new ObjectMapper();

        logger.info("################### termino proceso Validation{}", dataResponse);

        return null;
	}

	@Override
	public RatingGeneralDto search(String keyword) {
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
        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_JSON);
        LinkedMultiValueMap<String, Object> body = new LinkedMultiValueMap<>();
        logger.info("########### iniciando proceso {}",headers );

        HttpEntity<MultiValueMap<String, Object>> requestEntity = new HttpEntity<>(body, headers);
        ConnectionService connectionService = new ConnectionService();

        JsonNode dataResponse  = connectionService.getRest("http://localhost:8083/ratings/students?idStudent="+keyword,requestEntity);
        logger.info("###########  proceso {}",dataResponse );

        ObjectMapper objectMapper = new ObjectMapper();

        RatingGeneralDto result = objectMapper.convertValue(dataResponse, RatingGeneralDto.class);

        logger.info("############# termino proceso {}",result );


        return result;
	}

    @Override
    public GeneralDto updateRatings(Ratings ratings) throws JsonProcessingException {
        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_JSON);

        JsonObject properties = new JsonObject();
        properties.addProperty("id", ratings.getIdCalificacion());
        properties.addProperty("calificacion", ratings.getCalificacion());
        HttpEntity<String> requestEntity = new HttpEntity<>(properties.toString(), headers);
        ConnectionService connectionService = new ConnectionService();


        GeneralDto dataResponse = connectionService.putRest("http://localhost:8083/ratings" , requestEntity);
        logger.info("################### termino proceso Validation {}", dataResponse);

        return dataResponse;
    }

    @Override
    public GeneralDto deleteRatings(String idRatings) throws JsonProcessingException {
        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_JSON);

        JsonObject properties = new JsonObject();
        HttpEntity<String> requestEntity = new HttpEntity<>(properties.toString(), headers);
        ConnectionService connectionService = new ConnectionService();


        GeneralDto dataResponse = connectionService.deleteRest("http://localhost:8083/ratings/"+idRatings , requestEntity);
        logger.info("################### termino proceso Validation {}", dataResponse);

        return dataResponse;
    }

    @Override
    public SubjectDto getSubjectById(int idSubject) throws JsonProcessingException {
//        HttpHeaders headers = new HttpHeaders();
//        headers.setContentType(MediaType.APPLICATION_JSON);
//        LinkedMultiValueMap<String, Object> body = new LinkedMultiValueMap<>();
//        logger.info("########### iniciando proceso {}",headers );
//
//        HttpEntity<MultiValueMap<String, Object>> requestEntity = new HttpEntity<>(body, headers);
//        ConnectionService connectionService = new ConnectionService();
//
//        JsonNode dataResponse  = connectionService.getRest("http://localhost:8083/subjects/="+idSubject,requestEntity);
//        logger.info("###########  proceso {}",dataResponse );
//
//        ObjectMapper objectMapper = new ObjectMapper();
//
//        RatingGeneralDto result = objectMapper.convertValue(dataResponse, RatingGeneralDto.class);
//
//        logger.info("############# termino proceso {}",result );


        return null;
    }
}
