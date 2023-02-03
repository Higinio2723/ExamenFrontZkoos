package com.front.zkoos.module.report;

import com.fasterxml.jackson.core.JsonProcessingException;
import org.springframework.http.ResponseEntity;

import java.io.FileNotFoundException;
import java.io.IOException;

public interface IReportRatingsService {

    public String downloadReportFiles(String idValidation) throws IOException;


}
