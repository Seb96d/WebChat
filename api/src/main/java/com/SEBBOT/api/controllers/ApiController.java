package com.SEBBOT.api.controllers;


import com.SEBBOT.api.entities.Conversation;
import com.SEBBOT.api.entities.Message;
import com.SEBBOT.api.utilities.ChatInput;
import com.SEBBOT.api.utilities.CommonService;
import com.auth0.jwt.JWT;
import com.auth0.jwt.JWTVerifier;
import com.auth0.jwt.algorithms.Algorithm;
import com.auth0.jwt.interfaces.DecodedJWT;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import java.util.*;

import static org.springframework.http.HttpHeaders.AUTHORIZATION;

@RestController
public class ApiController {

    @Autowired
    CommonService commonService;


    @ResponseBody
    @RequestMapping(value = "/api/admin/conversations" , method = RequestMethod.POST)
    public ResponseEntity<List<Conversation>> conversations(){
        List<Conversation> response = commonService.getAdminConversations();
        return new ResponseEntity<>( response , HttpStatus.OK);
    }

    @ResponseBody
    @RequestMapping(value = "-" , method = RequestMethod.POST)
    public ResponseEntity<List<Message>> messages(@RequestBody ChatInput chatInput){
        List<Message> response = commonService.getAdminMessages(  chatInput.getId() );
        return new ResponseEntity<>( response , HttpStatus.OK);
    }

    @ResponseBody
    @RequestMapping(value = "/api/user/conversations" , method = RequestMethod.POST)
    public ResponseEntity<List<Conversation>> conversationsuser(HttpServletRequest request){

        String authHeader = request.getHeader(AUTHORIZATION);

        if( authHeader != null && authHeader.substring(0 ,7 ).equals("Bearer ")){
            try {
                String token = authHeader.substring( "Bearer ".length() );
                Algorithm algorithm = Algorithm.HMAC512( "seb".getBytes());
                JWTVerifier verifier = JWT.require(algorithm).build();
                DecodedJWT decodedJWT = verifier.verify(token);
                String username = decodedJWT.getSubject();

                List<Conversation> response = commonService.getConversations(username);
                return new ResponseEntity<>( response , HttpStatus.OK);


            } catch (Exception e){
                return new ResponseEntity<>(  HttpStatus.FORBIDDEN);
            }
        }

        return new ResponseEntity<>(  HttpStatus.FORBIDDEN);
    }

    @ResponseBody
    @RequestMapping(value = "/api/user/messages" , method = RequestMethod.POST)
    public ResponseEntity<List<Message>> messagesuser(@RequestBody ChatInput chatInput , HttpServletRequest request){

        String authHeader = request.getHeader(AUTHORIZATION);

        if( authHeader != null && authHeader.substring(0 ,7 ).equals("Bearer ")){
            try {
                String token = authHeader.substring( "Bearer ".length() );
                Algorithm algorithm = Algorithm.HMAC512( "seb".getBytes());
                JWTVerifier verifier = JWT.require(algorithm).build();
                DecodedJWT decodedJWT = verifier.verify(token);
                String username = decodedJWT.getSubject();

                List<Message> response = commonService.getMessages( chatInput.getId() , username );
                return new ResponseEntity<>( response , HttpStatus.OK);

            } catch (Exception e){
                return new ResponseEntity<>(  HttpStatus.FORBIDDEN);
            }
        }

        return new ResponseEntity<>(  HttpStatus.FORBIDDEN);
    }

}
