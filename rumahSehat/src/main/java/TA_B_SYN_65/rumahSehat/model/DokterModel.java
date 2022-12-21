package TA_B_SYN_65.rumahSehat.model;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.*;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import java.io.Serializable;
import java.util.List;

@Setter
@Getter
@AllArgsConstructor
@NoArgsConstructor
@Entity
@JsonIgnoreProperties(value={"listAppointment"}, allowSetters = true)
@Inheritance(strategy = InheritanceType.JOINED)
public class DokterModel extends UserModel implements Serializable {

    @NotNull
    @Column(name = "tarif")
    private Integer tarif;

    @OneToMany(mappedBy = "dokter", fetch = FetchType.LAZY, cascade = CascadeType.ALL)
    private List<AppointmentModel> listAppointment;
}
