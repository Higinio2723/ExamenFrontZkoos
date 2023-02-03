package com.front.zkoos.util;

import lombok.Data;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Configuration;

@Configuration
@Data
public class MyProperties {

    @Value("${path.applications}")
    private String pathApplications;

}
