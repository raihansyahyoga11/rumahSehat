package TA_B_SYN_65.rumahSehat.service;

import TA_B_SYN_65.rumahSehat.model.PasienModel;
import TA_B_SYN_65.rumahSehat.repository.PasienDb;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.List;

@Service
@Transactional
public class PasienServiceImpl implements PasienService {
    @Autowired
    PasienDb pasienDb;

    @Override
    public void addPasien(PasienModel pasien) {
        pasienDb.save(pasien);
    }

    @Override
    public List<PasienModel> getListPasien() {
        return pasienDb.findAll();
    }

    @Override
    public void deletePasien(PasienModel pasien) {
        pasienDb.delete(pasien);
    }
}
