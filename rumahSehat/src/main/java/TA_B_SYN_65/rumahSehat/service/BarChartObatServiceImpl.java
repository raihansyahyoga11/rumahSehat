package TA_B_SYN_65.rumahSehat.service;

import TA_B_SYN_65.rumahSehat.model.BarChartObatModel;
import TA_B_SYN_65.rumahSehat.repository.BarChartObatDb;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;

@Service
@Transactional
public class BarChartObatServiceImpl implements BarChartObatService{

    @Autowired
    BarChartObatDb barChartObatDb;

    public void addBarChartObat(BarChartObatModel barChartObat) {
        barChartObatDb.save(barChartObat);
    }

}
