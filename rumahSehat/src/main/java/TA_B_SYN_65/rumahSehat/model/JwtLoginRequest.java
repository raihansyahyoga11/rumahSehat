package TA_B_SYN_65.rumahSehat.model;

import lombok.Data;

import java.io.Serializable;

@Data
public class JwtLoginRequest implements Serializable {
    private String username;
    private String password;
}
