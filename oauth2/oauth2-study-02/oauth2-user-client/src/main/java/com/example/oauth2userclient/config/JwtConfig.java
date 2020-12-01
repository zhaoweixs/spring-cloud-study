package com.example.oauth2userclient.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.oauth2.provider.token.TokenEnhancer;
import org.springframework.security.oauth2.provider.token.TokenStore;
import org.springframework.security.oauth2.provider.token.store.JwtAccessTokenConverter;
import org.springframework.security.oauth2.provider.token.store.JwtTokenStore;

/**
 * ToDO
 *
 * @author zhaowei
 * @date 2020/11/30 19:04
 */
@Configuration
public class JwtConfig {

    @Bean
    public TokenStore jwtTokenStore(JwtAccessTokenConverter jwtAccessTokenConverter){
        return new JwtTokenStore(jwtAccessTokenConverter);
    }

    @Bean
    public JwtAccessTokenConverter jwtAccessTokenConverter(){
        JwtAccessTokenConverter jwtAccessTokenConverter = new JwtAccessTokenConverter();
        jwtAccessTokenConverter.setSigningKey("testSignKey");
        jwtAccessTokenConverter.setVerifierKey("testSignKey");
        return jwtAccessTokenConverter;
    }

    @Bean
    public TokenEnhancer jwtTokenEnhancer(){
        return new JwtTokenEnhancer();
    }

}
