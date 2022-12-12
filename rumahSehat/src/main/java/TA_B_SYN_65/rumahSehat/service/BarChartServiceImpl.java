package TA_B_SYN_65.rumahSehat.service;

import TA_B_SYN_65.rumahSehat.model.BarChartModel;
import TA_B_SYN_65.rumahSehat.model.JumlahModel;
import TA_B_SYN_65.rumahSehat.model.ObatModel;
import TA_B_SYN_65.rumahSehat.repository.BarChartDb;
import TA_B_SYN_65.rumahSehat.repository.JumlahDb;
import TA_B_SYN_65.rumahSehat.repository.ObatDb;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.List;

@Service
@Transactional
public class BarChartServiceImpl implements BarChartService {

    @Autowired
    BarChartDb barChartDb;

    @Autowired
    ObatService obatService;

    @Autowired
    JumlahDb jumlahdb;

    public void addBarChart(BarChartModel barChart) {
        barChartDb.save(barChart);
    }

    ;




}


