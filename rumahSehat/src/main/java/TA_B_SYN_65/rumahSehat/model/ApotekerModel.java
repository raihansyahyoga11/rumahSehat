package TA_B_SYN_65.rumahSehat.model;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import java.io.Serializable;
import java.util.List;

@Setter
@Getter
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "apoteker")
public class ApotekerModel {
    // One to many relationship with Resep
    @OneToMany(mappedBy = "idResep", fetch = FetchType.LAZY, cascade = CascadeType.ALL)
    private List<ResepModel> listResep;
}
