package com.transaction.monitoring.monitoringservice.controller;

/**
 * Created by Admin on 7/23/20.
 */

import com.transaction.monitoring.monitoringservice.config.JwtTokenUtils;
import com.transaction.monitoring.monitoringservice.config.JwtUserDetailsService;
import com.transaction.monitoring.monitoringservice.model.JwtRequest;
import com.transaction.monitoring.monitoringservice.model.JwtResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.DisabledException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.web.bind.annotation.*;

@RestController
@CrossOrigin
@RequestMapping("/api")
public class JwtAuthenticationController {

    @Autowired
    private AuthenticationManager authenticationManager;

    @Autowired
    private JwtTokenUtils jwtTokenUtil;

    @Autowired
    private JwtUserDetailsService userDetailsService;

    @PostMapping("/authenticate")
    public ResponseEntity<?> createAuthenticationToken(@RequestBody JwtRequest authenticationRequest) throws Exception {
        System.out.println("---iniside createAuthenticationToken-----");
        System.out.println("---iniside authenticationRequest--username---"+authenticationRequest.getUsername());
        System.out.println("---iniside authenticationRequest---password--"+authenticationRequest.getsKey());
        authenticate(authenticationRequest.getUsername(), authenticationRequest.getsKey());
        System.out.println("---iniside createAuthenticationToken--1---");

        final UserDetails userDetails = userDetailsService
                .loadUserByUsername(authenticationRequest.getUsername());

        System.out.println("---iniside createAuthenticationToken---2--");

        System.out.println("Username-----"+userDetails.getUsername());

        System.out.println("password-----"+userDetails.getPassword());



        final String token = jwtTokenUtil.generateToken(userDetails);

        return ResponseEntity.ok(new JwtResponse(token));
    }

    private void authenticate(String username, String password) throws Exception {
        try {
            authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(username, password));
        } catch (DisabledException e) {
            throw new Exception("USER_DISABLED", e);
        } catch (BadCredentialsException e) {
            throw new Exception("INVALID_CREDENTIALS", e);
        }
    }
}
