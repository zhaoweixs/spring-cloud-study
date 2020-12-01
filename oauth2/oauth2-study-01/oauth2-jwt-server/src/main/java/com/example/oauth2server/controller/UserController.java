package com.example.oauth2server.controller;

import io.jsonwebtoken.Jwts;
import org.springframework.security.core.Authentication;
import org.springframework.util.StreamUtils;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;
import java.nio.charset.StandardCharsets;

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
    public Object getCurrentLoginUser(Authentication authentication, HttpServletRequest request){
        String header = request.getHeader("Authorization");
        String token = header.substring("bearer ".length());
        return Jwts.parser().setSigningKey("testSignKey".getBytes(StandardCharsets.UTF_8)).parseClaimsJws(token)
                .getBody();
    }

}
