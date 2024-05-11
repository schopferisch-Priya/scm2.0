package com.scm.controllers;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;


//request handling
@Controller
public class PageController {

    //Request fire
    @RequestMapping("/home")
    public String home(Model model){

        //Sending data to view
        System.out.println("Home page handler");
        model.addAttribute("name","Substring Technologies");
        model.addAttribute("demoModel","Creation of scm project 2.0");
        model.addAttribute("projectLink","https://youtu.be/Nryrblg3vWw?si=J2DX0X7Lmb9pWBJ0");

        return "home";
    }


    //About route
    @RequestMapping("/about")
    public String aboutPage(Model model) {
        model.addAttribute("isLogin", true);
        System.out.println("About page loading..");
        return "about";
    }
    


    //Services
    @RequestMapping("/services")
    public String servicesPage() {
        System.out.println("Services page loading..");
        return "services";
    }

}
