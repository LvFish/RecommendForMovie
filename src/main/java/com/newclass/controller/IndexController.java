package com.newclass.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;


@Controller
@RequestMapping("")
public class IndexController {




    @RequestMapping(value = "/index", method = RequestMethod.GET)
    public String index() {


        return "index";
    }



    @RequestMapping(value = "/login" , method = RequestMethod.GET)
    public String login(String username , String password){

        if(username.equals("123")){
            return "index";
        }


        return "login";
    }

    @RequestMapping(value = "/home", method = RequestMethod.GET)
    public String home(){
        return "home";
    }





}
