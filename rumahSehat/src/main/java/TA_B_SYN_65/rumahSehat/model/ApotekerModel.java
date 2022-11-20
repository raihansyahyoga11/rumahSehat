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
@Entity
@Table(name = "apoteker")
public class ApotekerModel extends UserModel implements Serializable {
    // One to many relationship with Resep masih gagal
     @OneToMany(mappedBy = "apoteker", fetch = FetchType.LAZY, cascade = CascadeType.ALL)
     private List<ResepModel> listResep;
}
