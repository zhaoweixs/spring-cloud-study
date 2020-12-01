package com.example.oauth2codeclient.controller;

import com.fasterxml.jackson.databind.ObjectMapper;
import okhttp3.*;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.Authentication;
import org.springframework.security.oauth2.provider.authentication.OAuth2AuthenticationDetails;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;
import io.jsonwebtoken.*;

import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.util.Map;

/**
 * ToDO
 *
 * @author zhaowei
 * @date 2020/12/1 16:50
 */
@Controller
public class CodeController {

    @GetMapping("/index")
    public Object index(){
        return "index";
    }

    /**
     * 回调接口 接受授权码 并同步用授权码请求access_token
     * @param code
     * @return
     */
    @GetMapping("/login")
    public Object login(String code, Model model){
        String tokenUrl = "http://localhost:8081/oauth/token";
        OkHttpClient okHttpClient = new OkHttpClient();
        RequestBody requestBody = new FormBody.Builder()
                .add("grant_type","authorization_code")
                .add("code",code)
                .add("redirect_uri","http://localhost:8083/login")
                .build();
        Request request = new Request.Builder()
                .url(tokenUrl)
                .post(requestBody)
                .addHeader("Authorization","Basic Y29kZS1jbGllbnQ6Y29kZS1zZWNyZXQtODg4OA==")
                .build();
        try {
            Response response = okHttpClient.newCall(request).execute();
            String result = response.body().string();
            ObjectMapper objectMapper = new ObjectMapper();
            Map<String,Object> map = objectMapper.readValue(result,Map.class);
            String accessToken = map.get("access_token").toString();
            Claims claims = Jwts.parser().setSigningKey("testSignKey".getBytes(StandardCharsets.UTF_8))
                    .parseClaimsJws(accessToken)
                    .getBody();
            String username = claims.get("user_name").toString();
            model.addAttribute("username",username);
            model.addAttribute("accessToken",accessToken);
        } catch (IOException e) {
            e.printStackTrace();
        }


        return "index";

    }

    @org.springframework.web.bind.annotation.ResponseBody
    @GetMapping(value = "get")
    @PreAuthorize("hasAnyRole('ROLE_ADMIN')")
    public Object get(Authentication authentication) {
        //Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        authentication.getCredentials();
        OAuth2AuthenticationDetails details = (OAuth2AuthenticationDetails) authentication.getDetails();
        String token = details.getTokenValue();
        return token;
    }

}
