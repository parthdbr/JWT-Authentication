package com.springrest.user.Exception;

import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@Slf4j
@RestControllerAdvice
@ControllerAdvice
public class MissingValues extends Exception{


        public MissingValues(String e) {
            super(e);
            log.info("MissingValues Exception");
        }

        public MissingValues() {

            super("Values are Missing");
            log.info("MissingValues Exception");
        }
}
