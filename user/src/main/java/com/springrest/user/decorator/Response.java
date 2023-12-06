package com.springrest.user.decorator;
import lombok.*;
import org.springframework.http.HttpStatus;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class Response {
    HttpStatus status;
    String Description;
    String Code;

}
