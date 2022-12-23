package TA_B_SYN_65.rumahSehat.service;


import TA_B_SYN_65.rumahSehat.model.LineChartModel;
import TA_B_SYN_65.rumahSehat.model.ObatModel;

import java.util.LinkedHashMap;
import java.util.List;


public interface LineChartService {
    void addLineChart(LineChartModel lineChart);
    List<String> pilihanBulan();
    LinkedHashMap<String,Integer> hitungDalamBulan(String bulan , ObatModel idObat,Integer harga);
    LinkedHashMap<String,Integer> hitungDalamTahun(String bulan , ObatModel idObat,Integer harga);

}
