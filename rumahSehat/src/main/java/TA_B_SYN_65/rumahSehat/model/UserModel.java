package TA_B_SYN_65.rumahSehat.model;


import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.hibernate.annotations.GenericGenerator;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import java.io.Serializable;

@Setter
@Getter
@AllArgsConstructor
@NoArgsConstructor
@MappedSuperclass

public class UserModel implements Serializable {
    @Id
    @GeneratedValue(generator = "system-uuid")
    @GenericGenerator(name = "system-uuid", strategy = "uuid")
    private String uuid;

    @NotNull
    @Size(max = 50)
    @Column(name = "nama", nullable = false)
    private String nama;

    @NotNull
    @Size(max = 50)
    @Column(name = "role", nullable = false)
    private String role;

    @NotNull
    @Size(max = 50)
    @Column(name = "username", nullable = false, unique = true)
    private String username;

    @NotNull
    @Lob
    @Column(name = "password", nullable = false)
    private String password;


    @NotNull
    @Column(name = "email", nullable = false, unique = true)
    private String email;


}
