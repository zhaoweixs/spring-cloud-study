package com.example.oauth2server.controller;

import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * ToDO
 *
 * @author zhaowei
 * @date 2020/11/30 15:30
 */
@RestController
@RequestMapping("/user")
public class UserController {

    @GetMapping("/getCurrentLoginUser")
    public Object getCurrentLoginUser(Authentication authentication){
        return authentication.getPrincipal();
    }

}
