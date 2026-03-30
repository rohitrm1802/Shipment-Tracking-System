package com.project.shipment_tracking_system.Security;

import com.project.shipment_tracking_system.Entity.User;
import com.project.shipment_tracking_system.Repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.Collections;
import java.util.List;

@Service
public class CustomUserDetailsService implements UserDetailsService {

    @Autowired
    private UserRepository userrepo;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        User user = userrepo.findByUsername(username).orElseThrow(() ->
                        new RuntimeException("Username Not Found"));

        if(username == null)
            throw new UsernameNotFoundException("User Not Found"+username);

        return new org.springframework.security.core.userdetails.User(
                user.getUsername(),
                user.getPassword(),
                List.of(new SimpleGrantedAuthority("ROLE_" +user.getRole().name()))
                //Collections.singleton(() -> "ROLE_" + user.getRole().name()) --> old
        );
    }
}
