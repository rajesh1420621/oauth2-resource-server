package com.pxp.resourceserver.controller;

import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.oauth2.jwt.Jwt;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class ResourceController {

    @RequestMapping(value = "test", method = RequestMethod.GET)
    public String test(){
        return "Redirect endpoint";
    }

    @RequestMapping(value = "private", method = RequestMethod.GET)
    public String privateResource(){
        return "This is a private resource";
    }

    @RequestMapping(value = "public", method = RequestMethod.GET)
    public String publicResource(){
        return "This is a public resource";
    }

    @RequestMapping(value = "auth", method = RequestMethod.GET)
    public void auth(@AuthenticationPrincipal Jwt jwt){
        System.out.println(jwt.getClaims());
    }
}
