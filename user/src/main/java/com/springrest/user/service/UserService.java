package com.springrest.user.service;

import com.springrest.user.DTO.UserDTO;
import com.springrest.user.Exception.UserExistsException;
import com.springrest.user.model.User;

import java.lang.reflect.InvocationTargetException;
import java.util.List;

public interface UserService {

    public List<User> getAllUserData();

    public User getUserDataById(String id);

    public User createUser (UserDTO userDTO) throws UserExistsException;

    public User updateUser(String id, UserDTO userDTO) throws InvocationTargetException, IllegalAccessException;

    public void deleteUser(String id);


}
