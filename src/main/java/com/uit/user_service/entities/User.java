package com.uit.user_service.entities;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.Column;
import javax.persistence.Entity;
import java.time.LocalDateTime;
@Entity
@Getter
@Setter
public class User extends BaseEntity {
    @Column(unique = true)
    private String username;
    //@Column(unique = true)
    private String email;
    private String password;
    private String phone;
    private String firstname;
    private String lastname;
    private LocalDateTime dateOfBirth;
    private String address;
    private String avatar;
    private Boolean gender;
    private String role;
    private Boolean is2FA;
//    voucher
    private Boolean isActive;
//    private String loginWith;

    //ver email
    @Column(name = "verification_code", length = 64)
    private String verificationCode;

    private boolean enabled;
}
