package TA_B_SYN_65.rumahSehat.model;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.hibernate.annotations.OnDelete;
import org.hibernate.annotations.OnDeleteAction;
import reactor.util.annotation.Nullable;

import javax.persistence.*;
import java.io.Serializable;
import java.util.List;

@Setter
@Getter
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "line_chart")
public class LineChartModel implements Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long idLineChart;

    @Column(name = "is_bulan")
    private Boolean isBulan;

    @Column(name = "bulan")
    private String bulan;

    @Nullable
    @OneToMany(mappedBy = "lineChart", fetch = FetchType.EAGER, cascade = CascadeType.MERGE)
    @OnDelete(action = OnDeleteAction.CASCADE)
    private List<LineChartObatModel> listLineChartObat;

}

