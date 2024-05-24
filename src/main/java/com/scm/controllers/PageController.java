package com.scm.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.scm.entities.User;
import com.scm.forms.UserForm;
import com.scm.helpers.Message;
import com.scm.helpers.MessageType;
import com.scm.services.UserService;

import jakarta.servlet.http.HttpSession;
import jakarta.validation.Valid;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;

//request handling
@Controller
public class PageController {

    @Autowired
    private UserService userService;

    // Request fire
    @RequestMapping("/home")
    public String home(Model model) {

        // Sending data to view
        System.out.println("Home page handler");
        model.addAttribute("name", "Substring Technologies");
        model.addAttribute("demoModel", "Creation of scm project 2.0");
        model.addAttribute("projectLink", "https://youtu.be/Nryrblg3vWw?si=J2DX0X7Lmb9pWBJ0");

        return "home";
    }

    // About route
    @RequestMapping("/about")
    public String aboutPage(Model model) {
        model.addAttribute("isLogin", true);
        System.out.println("About page loading..");
        return "about";
    }

    // Services
    @RequestMapping("/services")
    public String servicesPage() {
        System.out.println("Services page loading..");
        return "services";
    }

    // Contact Page
    @GetMapping("/contact")
    public String contact() {
        return new String("contact");
    }

    // Login Page
    @GetMapping("/login")
    public String login() {
        return new String("login");
    }

    // Register Page
    @GetMapping("/register")
    public String register(Model model) {
        UserForm userForm = new UserForm();
        // default data bhi daal skte h
        // userForm.setName("Priya");
        // userForm.setAbout("This is about section.");
        model.addAttribute("userForm", userForm);
        return "register";
    }

    // processing register
    @RequestMapping(value="/do-register", method=RequestMethod.POST)
    public String processRegister(@Valid @ModelAttribute UserForm userForm, BindingResult rBindingResult, HttpSession session) {
        System.out.println("Processing registration");
        
        //fetch form data- user form 
        System.out.println(userForm);

        //validate form data
        if(rBindingResult.hasErrors()){
            return "register";
        }

        // save to database - USERsERVICE

        //UserForm --> User

        // User user= User.builder()
        // .name(userForm.getName())
        // .email(userForm.getEmail())
        // .password(userForm.getPassword())
        // .about(userForm.getAbout())
        // .phoneNumber(userForm.getPhoneNumber())
        // .profilePic("C:\\Users\\Acer\\OneDrive\\Desktop\\My photo.jpg")
        // .build();

        User user = new User();
        user.setName(userForm.getName());
        user.setEmail(userForm.getEmail());
        user.setPassword(userForm.getPassword());
        user.setAbout(userForm.getAbout());
        user.setPhoneNumber(userForm.getPhoneNumber());
        user.setProfilePic("C:\\Users\\Acer\\OneDrive\\Desktop\\My photo.jpg");

        User savedUser = userService.saveUser(user);
        System.out.println("user saved:");

        //message = "Registration Successful"
        // Add the message :
        Message message= Message.builder().content("Registration Successful").type(MessageType.blue).build();
        session.setAttribute("message", message);
        //redirected to login page
        return "redirect:/register";
    }
}
