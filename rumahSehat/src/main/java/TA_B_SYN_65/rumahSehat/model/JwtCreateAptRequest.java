package TA_B_SYN_65.rumahSehat.model;

import lombok.Data;

import java.io.Serializable;
import java.time.LocalDateTime;

@Data
public class JwtCreateAptRequest implements Serializable {
    private String pasien;
    private String dokter;
    private LocalDateTime datetime;
    private Boolean isDone = false;

}
