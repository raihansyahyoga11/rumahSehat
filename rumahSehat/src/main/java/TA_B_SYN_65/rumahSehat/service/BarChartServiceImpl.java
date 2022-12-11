package TA_B_SYN_65.rumahSehat.service;

import TA_B_SYN_65.rumahSehat.model.BarChartModel;
import TA_B_SYN_65.rumahSehat.repository.BarChartDb;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;

@Service
@Transactional
public class BarChartServiceImpl implements BarChartService {

    @Autowired
    BarChartDb barChartDb;

    public void addBarChart(BarChartModel barChart) {
        barChartDb.save(barChart);
    };

}
