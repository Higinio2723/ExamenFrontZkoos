package com.front.zkoos.util.connection;

import lombok.Data;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Configuration;
import org.springframework.stereotype.Component;

@Configuration
@Component("myProperties")
@Data
public class MyProperties {

    @Value("${HTTP_SCHOOL_SERVER}")
    private String httpSchoolUrl;

}
