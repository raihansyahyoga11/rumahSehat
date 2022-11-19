package TA_B_SYN_65.rumahSehat.model;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.hibernate.annotations.OnDelete;
import org.hibernate.annotations.OnDeleteAction;

import com.fasterxml.jackson.annotation.JsonIgnore;

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
@Table(name = "obat")
public class ObatModel extends UserModel implements Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "idObat")
    private String id_obat;

    @NotNull
    @Size(max = 50)
    @Column(name = "nama_obat", nullable = false)
    private String nama_obat;

    @NotNull
    @Column(name = "stok", nullable = false)
    private Integer stok;

    @NotNull
    @Column(name = "harga", nullable = false)
    private Integer harga;

    // Relasi dengan ResepModel
    // @ManyToMany
    // @JoinTable(name = "resep_obat", joinColumns = @JoinColumn(name = "code"), inverseJoinColumns = @JoinColumn(name = "id_resep"))
    // List<ResepModel> listResep;

    //relasi dengan JumlahModel masih gagal
    //@ManyToOne(fetch = FetchType.EAGER, optional = false)
    //@JoinColumn(name = "obat", referencedColumnName = "idObat", nullable = false )
    //@OnDelete(action = OnDeleteAction.CASCADE)
    //rprivate JumlahModel jumlah;

}
