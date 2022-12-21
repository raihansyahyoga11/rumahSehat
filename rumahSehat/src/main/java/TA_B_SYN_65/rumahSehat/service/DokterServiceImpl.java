package TA_B_SYN_65.rumahSehat.service;

import TA_B_SYN_65.rumahSehat.repository.DokterDb;
import TA_B_SYN_65.rumahSehat.model.DokterModel;
import TA_B_SYN_65.rumahSehat.repository.UserDb;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;

@Service
@Transactional
public class DokterServiceImpl implements DokterService {
    @Autowired
    DokterDb dokterDb;

    @Autowired
    UserDb userDb;

    @Override
    public void addDokter(DokterModel dokter) {
        userDb.save(dokter);
    }

    @Override
    public DokterModel getDokterByUsername(String username) {
        return dokterDb.findByUsername(username);
    }
}
