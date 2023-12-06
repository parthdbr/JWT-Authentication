package com.springrest.user.decorator;

import com.springrest.user.model.User;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class Unauthorized {
    User data;
    Response status;
}
