package com.springrest.user.Exception;

import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@Slf4j
@RestControllerAdvice
public class UserDataNotAvailable extends Exception{
    public UserDataNotAvailable(String e) {
        super(e);
        log.info("User Not Available");
    }

    public UserDataNotAvailable() {

        super("Values are Missing");
        log.info("User Not Available");
    }

}
