package com.techienaman.usermanagementservice.model;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import java.math.BigInteger;
import java.time.LocalDate;

@Getter
@Setter
@NoArgsConstructor
@Entity
public class User {

    @Id
    @SequenceGenerator(name = "user_sequence", sequenceName = "user_sequence", allocationSize = 1)
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "user_sequence")
    private Long id;
    private String firstname;
    private String lastname;
    private LocalDate dob;
    private String city;
    private String mobileNumber;


    public User(String firstname,
                String lastname,
                LocalDate dob,
                String city,
                String mobileNumber) {
        this.firstname = firstname;
        this.lastname = lastname;
        this.dob = dob;
        this.city = city;
        this.mobileNumber = mobileNumber;
    }
}
