package TA_B_SYN_65.rumahSehat.service;

import TA_B_SYN_65.rumahSehat.model.ObatModel;
import TA_B_SYN_65.rumahSehat.repository.ObatDb;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ChartServiceImpl implements ChartService {

    @Autowired
    private ObatDb obatDb;
    @Override
    public List<ObatModel> getListObat() {
        return obatDb.findAll();
    }
}
