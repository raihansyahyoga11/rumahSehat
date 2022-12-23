package TA_B_SYN_65.rumahSehat.model;

import lombok.*;
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

    @Column(name = "stok", columnDefinition = "integer default 100")
    private Integer stok;

    @NotNull
    @Column(name = "harga", nullable = false)
    private Integer harga;

    @JsonIgnore
    @OneToMany(mappedBy = "obat", fetch = FetchType.LAZY, cascade = CascadeType.ALL)
    @OnDelete(action = OnDeleteAction.CASCADE)
    private List<JumlahModel> listJumlah;

    @OneToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "id_bar_chart_obat", referencedColumnName = "idBarChartObat", nullable = true)
    @OnDelete(action = OnDeleteAction.CASCADE)
    private BarChartObatModel chartObat;

    @OneToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "id_line_chart_obat", referencedColumnName = "idLineChartObat", nullable = true)
    @OnDelete(action = OnDeleteAction.CASCADE)
    private LineChartObatModel chartObatLine;

}

