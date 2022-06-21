package com.seb.SebBot.controllers;

import com.seb.SebBot.entities.User;
import com.seb.SebBot.utils.CommonService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

@Controller
public class WebController {

    @Autowired
    CommonService commonService;

    BCryptPasswordEncoder bCryptPasswordEncoder =  new BCryptPasswordEncoder();

    @RequestMapping(value = "/" , method = RequestMethod.GET)
    public String index(){

        Authentication authetication = SecurityContextHolder.getContext().getAuthentication();
        if(authetication != null && authetication.getPrincipal() instanceof UserDetails && ((UserDetails)authetication.getPrincipal()).getAuthorities().stream().anyMatch( a -> a.getAuthority().equals("ROLE_ADMIN"))){
            return "redirect:/web/admin";
        }
        return "index";
    }

    @RequestMapping(value = "/web/login" , method = RequestMethod.GET)
    public String login(){
        return "login";
    }


    @RequestMapping(value = "/web/register" , method = RequestMethod.GET)
    public String register(){
        return "register";
    }

    @RequestMapping(value = "/web/admin" , method = RequestMethod.GET)
    public String admin(){
        return "admin";
    }

    @RequestMapping(value = "/web/register" , method = RequestMethod.POST)
    public String save( User user ){
        user.setPassword( bCryptPasswordEncoder.encode( user.getPassword() ));
        commonService.saveUser( user );
        return "redirect:/web/login";
    }
}
