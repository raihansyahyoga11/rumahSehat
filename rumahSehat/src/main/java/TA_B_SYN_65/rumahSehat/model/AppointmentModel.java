package TA_B_SYN_65.rumahSehat.model;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.hibernate.annotations.GenericGenerator;
import org.hibernate.annotations.OnDelete;
import org.hibernate.annotations.OnDeleteAction;
import org.springframework.format.annotation.DateTimeFormat;

import com.fasterxml.jackson.annotation.JsonIgnore;

import javax.persistence.*;
import javax.validation.constraints.NotNull;

import java.io.Serializable;
import java.time.LocalDateTime;
import java.util.List;

@Setter
@Getter
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "appointment")
public class AppointmentModel implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private String kode;      // udah bener

    @NotNull
    @Column(name = "is_done", nullable = false)
    private Boolean isDone;

    @NotNull
    @Column(name = "waktu_awal", nullable = false)
    @DateTimeFormat(pattern = "yyyy-MM-dd'T'HH:mm")
    private LocalDateTime waktuAwal;

     @ManyToOne(fetch = FetchType.EAGER, optional = false)
     @JoinColumn(name = "uuid_dokter", referencedColumnName = "id", nullable = false)
     @OnDelete(action = OnDeleteAction.CASCADE)
     private DokterModel dokter;

     @ManyToOne(fetch = FetchType.EAGER, optional = false)
     @JoinColumn(name = "uuid_pasien", referencedColumnName = "id", nullable = false)
     @OnDelete(action = OnDeleteAction.CASCADE)
     private PasienModel pasien;

     @OneToOne(mappedBy = "appointment", cascade = CascadeType.ALL)
     private TagihanModel kodeTagihan;

    // Relasi dengan Resep
    @OneToOne(mappedBy = "appointment", fetch = FetchType.LAZY, cascade = CascadeType.ALL)
    private ResepModel resep;
}
