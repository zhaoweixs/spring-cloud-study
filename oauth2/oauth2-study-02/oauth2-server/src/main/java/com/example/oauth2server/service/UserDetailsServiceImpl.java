package com.example.oauth2server.service;

import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.authority.AuthorityUtils;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import javax.annotation.PostConstruct;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
/**
 * ToDO
 *
 * @author zhaowei
 * @date 2020/12/1 10:40
 */
@Service
public class UserDetailsServiceImpl implements UserDetailsService {
    private List<User> userList = new ArrayList<>();

    @Autowired
    private PasswordEncoder passwordEncoder;

    @PostConstruct
    public void init(){
        userList.clear();
        String password = passwordEncoder.encode("123456");
        userList.add(new User("admin",password, AuthorityUtils.commaSeparatedStringToAuthorityList("ROLE_ADMIN")));
        userList.add(new User("zhaowei",password,AuthorityUtils.commaSeparatedStringToAuthorityList("ROLE_CLIENT")));
        userList.add(new User("dahuang",password,AuthorityUtils.commaSeparatedStringToAuthorityList("ROLE_FUCK")));
    }

    @Override
    public UserDetails loadUserByUsername(String s) throws UsernameNotFoundException {
        init();
        Optional<User> optionalUser = userList.stream().filter(u -> u.getUsername().equals(s)).findFirst();
        if (optionalUser.isPresent()){
            return optionalUser.get();
        }else {
            throw new UsernameNotFoundException("用户名错了");
        }
    }
}
