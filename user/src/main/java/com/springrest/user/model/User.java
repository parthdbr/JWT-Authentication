package com.springrest.user.model;

import jakarta.persistence.CascadeType;
import jakarta.persistence.FetchType;
import jakarta.persistence.ManyToMany;
import lombok.*;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.DBRef;
import org.springframework.data.mongodb.core.mapping.Document;

import java.util.HashSet;
import java.util.List;
import java.util.Set;

@Document(collection = "userdata")
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class User {

    @Id
     String id;

     String fname;

     String lname;

     String contact;

     String city;

     String email;

     String password;

     String role;

     boolean softDelete;


}
