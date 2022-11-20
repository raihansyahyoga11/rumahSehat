package TA_B_SYN_65.rumahSehat.model;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

import org.hibernate.annotations.GenericGenerator;
import org.hibernate.annotations.OnDelete;
import org.hibernate.annotations.OnDeleteAction;
import org.springframework.format.annotation.DateTimeFormat;

import com.fasterxml.jackson.annotation.JsonIgnore;

import java.io.Serializable;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;

@Setter
@Getter
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "tagihan")
public class TagihanModel implements Serializable {

    @Id
    @GeneratedValue(generator = "system-uuid")
    @GenericGenerator(name = "system-uuid", strategy = "uuid")
    @Column(name = "kode", nullable = false)
    private String kode;

    @NotNull
    @Column(name = "tanggal_terbuat", nullable = false)
    @DateTimeFormat(pattern = "yyyy-MM-dd'T'HH:mm")
    private LocalDateTime tanggalTerbuat;

    @NotNull
    @Column(name = "tanggal_bayar", nullable = false)
    @DateTimeFormat(pattern = "yyyy-MM-dd'T'HH:mm")
    private LocalDateTime tanggalBayar;

    @NotNull
    @Column(name = "is_paid", nullable = false)
    private Boolean isPaid;

    @NotNull
    @Column(name = "jumlah_tagihan", nullable = false)
    private Integer jumlahTagihan;

    @OneToOne(mappedBy = "tagihan")
    private AppointmentModel appointment;

    // @OneToOne(fetch = FetchType.EAGER)
    // @JoinColumn(name = "kode_appointment", referencedColumnName = "kode", nullable = false)
    // @OnDelete(action = OnDeleteAction.CASCADE)
    // @JsonIgnore
    // private String kodeAppointment;     // Masih bingung



}
