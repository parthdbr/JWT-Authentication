package com.springrest.user.controller;

import com.springrest.user.DTO.UserDTO;
import com.springrest.user.Exception.*;
import com.springrest.user.model.User;
import com.springrest.user.decorator.DataResponse;
import com.springrest.user.decorator.ListDataResponse;
import com.springrest.user.decorator.Response;
import com.springrest.user.repository.UserRepository;
import com.springrest.user.service.UserService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.util.ObjectUtils;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;


@RestController
@RequestMapping("/api")
@Slf4j
public class UserController {
    @Autowired
    private UserService userService;
    @Autowired
    UserRepository userRepository;


    @GetMapping("/userdata")
    public ListDataResponse<User> getAllUserData()   {
        log.info("Inside Controller");
        List<User> user = userRepository.findAll();
        ListDataResponse<User> response = new ListDataResponse<>();
        try{
            if(!(user.isEmpty())) {
                if(userService.getAllUserData() != null) {
                    response.setData(userService.getAllUserData());
                    response.setStatus(new Response(HttpStatus.OK, "Data Fetched", "200"));
                }else
                    response.setStatus(new Response(HttpStatus.NOT_FOUND, "No Data Available", "404"));
            }else{
                response.setStatus(new Response(HttpStatus.NOT_FOUND, "No Data Available", "404"));
            }
        }catch(Exception e) {
            response.setStatus(new Response(HttpStatus.NOT_FOUND, "No Data Available", "404"));
        }
        return response;
    }

    @GetMapping("/userdata/{id}")
    public DataResponse<User> getUserDataById(@PathVariable("id") String id)   {
        Optional<User> userdata = userRepository.findById(id);
        DataResponse<User> response = new DataResponse<>();
        try {
            if (userdata.isPresent()) {
                    response.setData(userService.getUserDataById(id));
                    response.setStatus(new Response(HttpStatus.OK, "Data Fetched", "200"));
            } else {
                response.setStatus(new Response(HttpStatus.NOT_FOUND, "No Data Available", "404"));
            }
        }catch(Exception e) {
            response.setStatus(new Response(HttpStatus.NOT_FOUND, "No Data Available", "404"));
        }
        return response ;
    }

    @PostMapping("/userdata")
    public DataResponse<User> createUser(@RequestBody UserDTO userDTO)  throws UserExistsException {
        DataResponse<User> response = new DataResponse<>();
        try {
                response.setData(userService.createUser(userDTO));
                response.setStatus(new Response(HttpStatus.CREATED, "Data Created", "201"));
        }catch(Exception e) {

            response.setStatus(new Response(HttpStatus.NO_CONTENT, "Data Not Created", "204"));
        }
        return response;
   }

    @PutMapping("/userdata/{id}")
    public DataResponse<User> updateUser(@PathVariable("id") String id, @RequestBody UserDTO userDTO) throws UserNotAvailable {
        DataResponse<User> response = new DataResponse<>();
        try {
            if (!ObjectUtils.isEmpty(userDTO)){
                response.setData(userService.updateUser(id, userDTO));
                response.setStatus(new Response(HttpStatus.ACCEPTED, "Data Modified", "202"));
            }else
                response.setStatus(new Response(HttpStatus.NOT_MODIFIED, "Dataaaa Not Modified", "304"));
        }catch(Exception e) {
            log.info("Some error updating user");
            response.setStatus(new Response(HttpStatus.NOT_MODIFIED, "Data Not Modified", "304"));
        }
        return response;
    }


    @DeleteMapping("/userdata/{id}")
    public DataResponse<User> deleteUser(@PathVariable("id") String id) throws userNotDeleted{
        DataResponse<User> response = new DataResponse<>();
        try{
            if(userRepository.findById(id).isPresent()){
                userService.deleteUser(id);
                response.setStatus(new Response(HttpStatus.OK, "Data deleted" , "200"));
            }else {
                response.setStatus(new Response(HttpStatus.NOT_FOUND, "Data not found", "404"));
            }
        }catch(Exception e) {
            response.setStatus(new Response(HttpStatus.NOT_FOUND, "Data not found", "404"));
        }
        return response;
    }
}
