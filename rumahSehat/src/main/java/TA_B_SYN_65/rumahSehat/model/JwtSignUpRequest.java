package TA_B_SYN_65.rumahSehat.model;

import lombok.Data;

import java.io.Serializable;

@Data
public class JwtSignUpRequest implements Serializable {
    private String username;
    private String password;
    private String email;
    private String nama;
    private Integer umur;
    private String role;
    private Integer saldo = 0;

}
