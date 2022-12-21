package TA_B_SYN_65.rumahSehat.service;

import TA_B_SYN_65.rumahSehat.model.PasienModel;
import TA_B_SYN_65.rumahSehat.repository.PasienDb;
import TA_B_SYN_65.rumahSehat.repository.UserDb;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.NoSuchElementException;
import java.util.Optional;

@Service
@Transactional
public class PasienServiceImpl implements PasienService{

    @Autowired
    private UserDb userDb;

    @Autowired
    private PasienDb pasienDb;

    @Override
    public PasienModel create(PasienModel pengguna) {
        return userDb.save(pengguna);
    }

    @Override
    public PasienModel getPasienByUsername(String username) {
        Optional<PasienModel> pasien = pasienDb.findByUsername(username);
        if (pasien.isPresent()){
            return pasien.get();
        } else {
            throw new NoSuchElementException();
        }
    }
}
