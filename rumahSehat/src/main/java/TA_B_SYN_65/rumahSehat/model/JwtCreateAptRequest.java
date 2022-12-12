package TA_B_SYN_65.rumahSehat.model;

import lombok.Data;

import java.io.Serializable;
import java.time.LocalDateTime;

@Data
public class JwtCreateAptRequest implements Serializable {
    private PasienModel pasien;
    private DokterModel dokter;
    private LocalDateTime datetime;
    private Boolean isDone = false;

}
