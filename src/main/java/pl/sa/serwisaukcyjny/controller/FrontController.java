package pl.sa.serwisaukcyjny.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class FrontController {

//    Service service;
//
//    public FrontController(Service service) {
//        this.service = service;
//    }

//    @GetMapping("/")
//    public String home() {
//        return "index";
//    }

   @GetMapping("/categories")
    public String Categories() {
        return "categories";

    }


}