package com.front.zkoos.module.report;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.front.zkoos.module.ratings.RatingsService;
import com.front.zkoos.util.connection.ConnectionService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpEntity;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.util.LinkedMultiValueMap;
import org.springframework.util.MultiValueMap;

import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStream;

public class ReportRatingsService implements IReportRatingsService{
    private final Logger logger = LoggerFactory.getLogger(ReportRatingsService.class);


    @Override
    public String  downloadReportFiles(String idStudent) throws IOException {

        ResponseEntity<byte[]> response;

        MultiValueMap<String, String> headers = this.connectionHeaderWithoutToken(MediaType.APPLICATION_JSON.toString());

        headers.add("content-length","55");
        headers.add("Access-Control-Allow-Headers", "x-requested-with x-uw-act-as");

        LinkedMultiValueMap<String, Object> body = new LinkedMultiValueMap<>();
        ConnectionService connectionService = new ConnectionService();

        logger.info("iniciando proceso {}",headers );
        HttpEntity<String> requestEntity = new HttpEntity<>(body.toString(), headers);

        response =  connectionService.postWithOctetStreamReturnRest("http://localhost:8083/report/student/"+idStudent,requestEntity);
        byte[] document = response.getBody();
        if(idStudent == null){
            idStudent = "all";
        }
        OutputStream out = new FileOutputStream("C:\\docuemts\\reporte_"+idStudent+".pdf");
        out.write(document);
        out.close();

        return null;
    }

    public  MultiValueMap<String, String> connectionHeaderWithoutToken(String mediaType){

        MultiValueMap<String, String> headers = new LinkedMultiValueMap<>();
        headers.add("", "gzip, deflate, br");
        headers.add("Connection", "keep-alive");
        headers.add("Accept", "*/*");
        headers.add("Content-Type", mediaType);

        return headers;
    }
}
