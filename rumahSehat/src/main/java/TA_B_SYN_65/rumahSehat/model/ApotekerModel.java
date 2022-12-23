package TA_B_SYN_65.rumahSehat.model;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;

import java.io.Serializable;
import java.util.List;


@Setter
@Getter
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Inheritance(strategy = InheritanceType.JOINED)
public class ApotekerModel extends UserModel implements Serializable {
    @OneToMany(mappedBy = "confirmer", fetch = FetchType.LAZY, cascade = CascadeType.ALL)
    private List<ResepModel> listResep;
}

