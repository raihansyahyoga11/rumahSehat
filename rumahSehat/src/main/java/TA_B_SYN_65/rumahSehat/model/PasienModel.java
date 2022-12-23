package TA_B_SYN_65.rumahSehat.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;

import javax.validation.constraints.NotNull;

import java.io.Serializable;
import java.util.List;



@Setter
@Getter
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Inheritance(strategy = InheritanceType.JOINED)
public class PasienModel extends UserModel implements Serializable {

    @NotNull
    @Column(name = "saldo")
    private Integer saldo;

    @Column(name = "umur")
    private Integer umur;
    
    @JsonIgnore
    @OneToMany(mappedBy = "pasien", fetch = FetchType.LAZY, cascade = CascadeType.ALL)
    private List<AppointmentModel> listAppointment;
}
