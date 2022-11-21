package TA_B_SYN_65.rumahSehat.model;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.hibernate.annotations.GenericGenerator;
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
public class ObatModel implements Serializable {
    @Id
    @GeneratedValue(generator = "system-uuid")
    @GenericGenerator(name = "system-uuid", strategy = "uuid")
    @Column(name = "id_obat", nullable = false)
    private String id;

    @NotNull
    @Size(max = 50)
    @Column(name = "nama_obat", nullable = false)
    private String nama;

    @NotNull
    @Column(name = "stok", nullable = false)
    private Integer stok = 100;

    @NotNull
    @Column(name = "harga", nullable = false)
    private Integer harga;

    // Relasi dengan ResepModel
    // @ManyToMany
    // @JoinTable(name = "resep_obat", joinColumns = @JoinColumn(name = "code"), inverseJoinColumns = @JoinColumn(name = "id_resep"))
    // List<ResepModel> listResep;

//    relasi dengan JumlahModel masih gagal
    @OneToMany(mappedBy = "obat", fetch = FetchType.LAZY, cascade = CascadeType.ALL)
    @OnDelete(action = OnDeleteAction.CASCADE)
    private List<JumlahModel> listJumlah;

}
