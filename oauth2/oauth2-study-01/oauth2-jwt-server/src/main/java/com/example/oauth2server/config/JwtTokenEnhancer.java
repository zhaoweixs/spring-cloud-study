package com.example.oauth2server.config;

import org.springframework.security.oauth2.common.DefaultOAuth2AccessToken;
import org.springframework.security.oauth2.common.OAuth2AccessToken;
import org.springframework.security.oauth2.provider.OAuth2Authentication;
import org.springframework.security.oauth2.provider.token.TokenEnhancer;

import java.util.HashMap;
import java.util.Map;

/**
 * ToDO
 *
 * @author zhaowei
 * @date 2020/11/30 19:15
 */
public class JwtTokenEnhancer implements TokenEnhancer {
    @Override
    public OAuth2AccessToken enhance(OAuth2AccessToken oAuth2AccessToken, OAuth2Authentication oAuth2Authentication) {
        Map<String,Object> extraMap = new HashMap<>();
        extraMap.put("extraKey","extraInfo");
        ((DefaultOAuth2AccessToken)oAuth2AccessToken).setAdditionalInformation(extraMap);
        return oAuth2AccessToken;
    }
}
