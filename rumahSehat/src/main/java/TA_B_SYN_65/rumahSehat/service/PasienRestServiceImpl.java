package TA_B_SYN_65.rumahSehat.service;

import TA_B_SYN_65.rumahSehat.model.PasienModel;
import TA_B_SYN_65.rumahSehat.repository.AdminDb;
import TA_B_SYN_65.rumahSehat.repository.PasienDb;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.NoSuchElementException;
import java.util.Optional;

@Service
@Transactional
public class PasienRestServiceImpl implements PasienRestService{

    @Autowired
    PasienDb pasienDb;
    @Autowired
    AdminDb adminDb;

    @Override
    public PasienModel getPasienByUsername(String username){
        Optional<PasienModel> pasien = pasienDb.findByUsername(username);
        if (pasien.isPresent()){
            return pasien.get();
        } else {
            throw new NoSuchElementException();
        }
    }
    @Override
    public PasienModel topUpSaldoPasien(String username  ,PasienModel pasien){
        PasienModel pasien1 = getPasienByUsername(username);
        pasien1.setSaldo(pasien.getSaldo());
        return pasienDb.save(pasien1);
    }
}
