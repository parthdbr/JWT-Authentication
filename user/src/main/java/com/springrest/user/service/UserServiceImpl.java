package com.springrest.user.service;

import com.springrest.user.DTO.UserDTO;
import com.springrest.user.Exception.UserExistsException;
import com.springrest.user.model.User;
import com.springrest.user.configuration.NullAwareBeanUtilsBean;
import com.springrest.user.repository.UserRepository;
import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;
import org.jetbrains.annotations.NotNull;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import lombok.extern.slf4j.Slf4j;
import org.springframework.util.ObjectUtils;

import java.lang.reflect.InvocationTargetException;
import java.util.*;

@Service
@Slf4j
@AllArgsConstructor
@NoArgsConstructor
public class UserServiceImpl implements UserService {



    @Autowired
    UserRepository userRepository;

    @Autowired
    ModelMapper modelMapper;

    @Autowired
    NullAwareBeanUtilsBean nullAware;


   @Override
    public List<User> getAllUserData() {
        return userRepository.findBySoftDeleteIsFalse();
    }

    @Override
    public User getUserDataById(String id) {
        return userRepository.findByIdAndSoftDeleteIsFalse(id);
    }

    @Override
    public User createUser(@NotNull UserDTO userDTO) throws UserExistsException {
        User userExists = userRepository.findByEmailContainingAndSoftDeleteIsFalse(userDTO.getEmail());
        log.info("user exists:{}",userExists);
        log.info("inside service:");
        if (ObjectUtils.isEmpty(userExists)) {

            User user = modelMapper.map(userDTO, User.class);

            if(user.getRole() == null || user.getRole().isEmpty()){
                user.setRole("USER");
            } else {
                user.setRole("ADMIN");


            }

            return userRepository.save(user);

        }else {
            log.info("User Exists");
            throw new UserExistsException("User Already exists with this Email");
        }
}


    @Override
    public User updateUser(String id, @NotNull UserDTO userDTO) throws InvocationTargetException, IllegalAccessException {
        User userdata = userRepository.findByIdAndSoftDeleteIsFalse(id);

        nullAware.copyProperties(userdata,userDTO);

        return userRepository.save(userdata);
    }

    @Override
    public void deleteUser(String id) {
        User user = userRepository.findByIdAndSoftDeleteIsFalse(id);
        if (!ObjectUtils.isEmpty(user)) {
            user.setSoftDelete(true);
            userRepository.save(user);
        }
    }
}
