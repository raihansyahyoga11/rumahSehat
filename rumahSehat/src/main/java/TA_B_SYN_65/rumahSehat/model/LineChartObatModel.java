package TA_B_SYN_65.rumahSehat.model;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.hibernate.annotations.GenericGenerator;
import org.hibernate.annotations.OnDelete;
import org.hibernate.annotations.OnDeleteAction;
import reactor.util.annotation.Nullable;

import javax.persistence.*;

@Setter
@Getter
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "line_chart_obat")
public class LineChartObatModel {
    @Id
    @GeneratedValue(generator = "system-uuid")
    @GenericGenerator(name = "system-uuid", strategy = "uuid")
    private String idLineChartObat;

    @Column(name = "list_bulan")
    private String listBulan;

    @Column(name = "list_tahun")
    private String listTahun;

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "id_line_chart", referencedColumnName = "idLineChart", nullable = true)
    @OnDelete(action = OnDeleteAction.CASCADE)
    private LineChartModel lineChart;

    @Nullable
    @OneToOne(mappedBy = "chartObatLine", fetch = FetchType.LAZY, cascade = CascadeType.ALL)
    private ObatModel obatSelected;
}
