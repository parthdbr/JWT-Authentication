package com.springrest.user.Exception;

import lombok.extern.slf4j.Slf4j;

@Slf4j
public class userNotDeleted extends Exception{
    public userNotDeleted(String e) {
        super(e);
        log.info("User Not Deleted Exception");
    }

    public userNotDeleted() {
        super();
        log.info("User Not Deleted Exception");
    }
}
