package com.example.oauth2client.controller;

import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * ToDO
 *
 * @author zhaowei
 * @date 2020/11/30 17:22
 */
@RestController
@RequestMapping("/user")
public class UserController {

    @GetMapping("/getCurrentLoginUser")
    public Object getCurrentLoginUser(Authentication authentication){
        return authentication.getPrincipal();
    }

    @PreAuthorize("hasAuthority('admin')")
    @GetMapping("/auth/admin")
    public Object authAdmin(Authentication authentication){
        return authentication.getPrincipal();
    }

}
