package com.alurachallenge.forohub.security;

import com.alurachallenge.forohub.domain.user.UserRepositosy;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;

public class AutenticationService implements UserDetailsService {

    @Autowired
    private UserRepositosy userRepositosy;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        var user =  userRepositosy.findByEmail(username);

        if (user==null){
            throw new UsernameNotFoundException("Usuario no encontrado: "+username);
        }
        return user;

    }
}
