package TA_B_SYN_65.rumahSehat.service;

import TA_B_SYN_65.rumahSehat.model.ObatModel;
import TA_B_SYN_65.rumahSehat.repository.ObatDb;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;

import java.util.List;
import java.util.Optional;

@Service
@Transactional
public class ObatServiceImpl implements ObatService {
    @Autowired
    ObatDb obatDb;

    @Override
    public List<ObatModel> getListObat() {
        return obatDb.findAll();
    }

    @Override
    public ObatModel getObatbyId(String id) {
        Optional<ObatModel> obat = obatDb.findById(id);
        if (obat.isPresent()) {
            return obat.get();
        } else
            return null;
    }

    @Override
    public ObatModel updateObat(ObatModel obat) {
        return obatDb.save(obat);
    }

}
