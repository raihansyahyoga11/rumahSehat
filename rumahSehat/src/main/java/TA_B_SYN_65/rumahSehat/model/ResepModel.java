package TA_B_SYN_65.rumahSehat.model;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.hibernate.annotations.OnDelete;
import org.hibernate.annotations.OnDeleteAction;
import org.springframework.format.annotation.DateTimeFormat;

import com.fasterxml.jackson.annotation.JsonIgnore;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

import java.io.Serializable;
import java.time.LocalDateTime;
import java.util.List;

@Setter
@Getter
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "resep")
public class ResepModel extends UserModel implements Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id", nullable = false)
    private Long id;

//    @NotNull
//    @Size(max = 50)
//    @Column(name = "nama_obat", nullable = false)
//    private String nama_obat;

    @NotNull
    @Column(name = "is_done", nullable = false)
    private Boolean isDone;

    @NotNull
    @Column(name = "created_at", nullable = false)
    @DateTimeFormat(pattern = "yyyy-MM-dd'T'HH:mm")
    private LocalDateTime createdAt;

    @ManyToOne(fetch = FetchType.EAGER, optional = false)
    @JoinColumn(name = "confirmer_uuid", referencedColumnName = "uuid", nullable = false)
    @OnDelete(action = OnDeleteAction.CASCADE)
    private ApotekerModel apoteker;

    @OneToMany(mappedBy = "resep", fetch = FetchType.LAZY, cascade = CascadeType.ALL)
    List<JumlahModel> listJumlah;

    // @ManyToMany(mappedBy = "listResep")
    // List<ObatModel> listObat;

    // ManyToOne Relationship with Appointment
    //@ManyToOne(fetch = FetchType.EAGER, optional = false)
    //@JoinColumn(name = "kode", referencedColumnName = "kode", nullable = false)
    //@OnDelete(action = OnDeleteAction.CASCADE)
    //private AppointmentModel appointment;

    // confirmer_uuid => Admin ???

    // relasi dengan jumlah model masih gagal
    // @ManyToOne(fetch = FetchType.EAGER, optional = false)
    //@JoinColumn(name = "resep", referencedColumnName = "id_resep", nullable = false )
    //@OnDelete(action = OnDeleteAction.CASCADE)
    //private JumlahModel jumlah;



}
