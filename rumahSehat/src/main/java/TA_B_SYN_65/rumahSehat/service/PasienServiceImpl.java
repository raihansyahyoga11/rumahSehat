package TA_B_SYN_65.rumahSehat.service;

import TA_B_SYN_65.rumahSehat.model.PasienModel;
import TA_B_SYN_65.rumahSehat.repository.PasienDb;
import TA_B_SYN_65.rumahSehat.repository.UserDb;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.List;

@Service
@Transactional
public class PasienServiceImpl implements PasienService {
    @Autowired
    private UserDb userDb;

    @Override
    public PasienModel create(PasienModel pengguna) {
        return userDb.save(pengguna);
    }

}
