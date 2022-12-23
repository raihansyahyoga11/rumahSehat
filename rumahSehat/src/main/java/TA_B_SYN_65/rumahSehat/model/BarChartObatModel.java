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

import java.io.Serializable;


@Setter
@Getter
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "bar_chart_obat")
public class BarChartObatModel implements Serializable {
    @Id
    @GeneratedValue(generator = "system-uuid")
    @GenericGenerator(name = "system-uuid", strategy = "uuid")
    private String idBarChartObat;


    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "id_bar_chart", referencedColumnName = "idBarChart", nullable = true)
    @OnDelete(action = OnDeleteAction.CASCADE)
    private BarChartModel barChart;


    @Nullable
    @OneToOne(mappedBy = "chartObat", fetch = FetchType.LAZY, cascade = CascadeType.ALL)
    private ObatModel obatSelected;
}
