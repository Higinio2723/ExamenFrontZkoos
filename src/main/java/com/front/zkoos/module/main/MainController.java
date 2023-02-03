package com.front.zkoos.module.main;


import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class MainController {

    @GetMapping("/listData")
    public String listData() {
        return "search_ratings";
        
    }

    @GetMapping("/report")
    public String report() {
        return "jasperreport";

    }

}
