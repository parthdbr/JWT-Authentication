package com.springrest.user.controller;

import com.springrest.user.DTO.LoginDTO;
import com.springrest.user.auth.JwtUtil;
import com.springrest.user.decorator.AuthResponse;
import com.springrest.user.decorator.Response;
import com.springrest.user.model.User;
import com.springrest.user.repository.UserRepository;
import com.springrest.user.service.JwtUserDetailService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.DisabledException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RestController
@CrossOrigin
@RequestMapping("/api/auth")
@Slf4j
public class JwtAuthenticationController {
    @Autowired
    private UserRepository userRepository;

    @Autowired
    private AuthenticationManager authenticationManager;

    @Autowired
    private JwtUtil jwtUtil;

    @Autowired
    private JwtUserDetailService userDetailService;

    @PostMapping("/login")
    public AuthResponse loginUser(LoginDTO loginDTO) {

        AuthResponse AuthResponse = new AuthResponse();

        try{
            Authentication auth = authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(loginDTO.getUsername(), loginDTO.getPassword()));

            SecurityContextHolder.getContext().setAuthentication(auth);

            if (auth.isAuthenticated()) {
                UserDetails userDetails = userDetailService.loadUserByUsername(loginDTO.getUsername());
                String token = jwtUtil.generateToken(userDetails.getUsername(), userDetails.getPassword());

                Map<String, Object> data = new HashMap<>();
                data.put("accessToken", token);

                AuthResponse.setStatus(new Response(HttpStatus.OK, data.get("accessToken").toString(), "200"));

            } else {

                AuthResponse.setStatus(new Response(HttpStatus.UNAUTHORIZED, "User login failed", "401"));
            }



        }catch(Exception e){
            AuthResponse.setStatus(new Response(HttpStatus.UNAUTHORIZED, "User login failed", "401"));
        }

        return AuthResponse;

    }

        /*Map<String, Object> responseMap = new HashMap<>();

        try{
            Authentication auth = authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(username, password));

            SecurityContextHolder.getContext().setAuthentication(auth);

            if(auth.isAuthenticated()) {
                log.info("Logged in");
                UserDetails userDetails = userDetailService.loadUserByUsername(username);
//                String token = jwtUtil.generateToken(userDetails);

               User user = userRepository.findByEmailContainingAndSoftDeleteIsFalse(auth.getName());

               String role = user.getRole();

               String token = jwtUtil.generateToken(userDetails.getUsername(), role);

                responseMap.put("ROLE" , role);
                responseMap.put("message","logged in");
                responseMap.put("token", token);
                return ResponseEntity.ok(responseMap);
            }else{
               responseMap.put("message","Invalid Credentials");
                return ResponseEntity.status(401).body(responseMap);
            }
        }catch (DisabledException e) {
            e.printStackTrace();
            responseMap.put("error", true);
            responseMap.put("message", "User is disabled");
            return ResponseEntity.status(500).body(responseMap);
        } catch (BadCredentialsException e) {
            responseMap.put("error", true);
            responseMap.put("message", "Invalid Credentials");
            return ResponseEntity.status(401).body(responseMap);
        } catch (Exception e) {
            e.printStackTrace();
            responseMap.put("error", true);
            responseMap.put("message", "Something went wrong");
            return ResponseEntity.status(500).body(responseMap);
        }
    }
*/
    /*
    public ResponseEntity<?> createAuthenticationToken(@RequestBody JwtRequest authenticationRequest) throws Exception {
        authenticate(authenticationRequest.getEmail(), authenticationRequest.getPassword());
        final UserDetails userDetails = userDetailService.loadUserByUsername(authenticationRequest.getEmail());
        final String token = jwtUtil.generateToken(userDetails);
        return ResponseEntity.ok(new JwtResponse(token));
    }

    private void authenticate(String email, String password) throws Exception {
        try{
            authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(email, password));
        }catch(BadCredentialsException e){
            throw new Exception("INVALID_CREDENTIALS "+e);
        }
    }
*/
}
