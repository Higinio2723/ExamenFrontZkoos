package com.front.zkoos.util.connection;

import com.fasterxml.jackson.databind.JsonNode;
import org.springframework.http.HttpEntity;
import org.springframework.http.ResponseEntity;
import org.springframework.util.MultiValueMap;

public interface IConnectionService {

    ResponseEntity<String> postRest(String uri, HttpEntity<MultiValueMap<String, Object>> requestEntity);

    JsonNode postBodyRest(String uri, HttpEntity<String> requestEntity);

    JsonNode getRest(String uri, HttpEntity<MultiValueMap<String, Object>> requestEntity );

    ResponseEntity<String> getStringRest(String uri, HttpEntity<MultiValueMap<String, Object>> requestEntity );

    ResponseEntity<byte[]> postWithOctetStreamReturnRest(String url, HttpEntity<String> requestEntity );
}
