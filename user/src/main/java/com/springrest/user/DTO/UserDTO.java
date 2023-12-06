package com.springrest.user.DTO;
import lombok.*;
import org.springframework.data.annotation.Id;

import java.util.List;
import java.util.Set;


@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class UserDTO {

    String fname;

    String lname;

    String contact;

    String city;

    String email;

    String password;

    String role;
}
