package com.transaction.monitoring.monitoringservice.config;

/**
 * Created by Admin on 7/23/20.
 */
import java.util.ArrayList;

import com.transaction.monitoring.monitoringservice.entity.Partner;
import com.transaction.monitoring.monitoringservice.repository.PartnerRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

@Service
public class JwtUserDetailsService implements UserDetailsService {

    @Autowired
    private PartnerRepository partnerRepository;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        System.out.println("-----Inside JwtUserDetailsService----"+username);
        Partner user =  partnerRepository.findByUsername(username);
        System.out.println("-----Inside JwtUserDetailsService--UserName--"+user.getUsername());
        System.out.println("-----Inside JwtUserDetailsService--Key--"+user.getClkey());
        if (user == null) {
            throw new UsernameNotFoundException("User not found with username: " + username);
        }
        return new org.springframework.security.core.userdetails.User(user.getUsername(), user.getClkey(),
                new ArrayList<>());
    }

}

