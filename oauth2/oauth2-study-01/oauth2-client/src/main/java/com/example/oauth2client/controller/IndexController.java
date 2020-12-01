package com.example.oauth2client.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * ToDO
 *
 * @author zhaowei
 * @date 2020/12/1 9:35
 */
@RestController
@RequestMapping
public class IndexController {

    @Autowired
    private UserController userController;

    @GetMapping("/")
    public Object index(Authentication authentication){
        return userController.getCurrentLoginUser(authentication);
    }

}
