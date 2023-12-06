package com.springrest.user.decorator;

import lombok.*;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class DataResponse<T> {
    T data;

    Response status;
}
