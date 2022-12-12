package TA_B_SYN_65.rumahSehat.service;

import TA_B_SYN_65.rumahSehat.model.BarChartObatModel;

public interface BarChartObatService {
    void addBarChartObat(BarChartObatModel barChartObat);
    Integer getKuantitas(BarChartObatModel barChartObat);

    Integer getTotalPendapatan(BarChartObatModel barChartObat);

}
