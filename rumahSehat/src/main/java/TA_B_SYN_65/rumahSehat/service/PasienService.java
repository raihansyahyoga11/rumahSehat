package TA_B_SYN_65.rumahSehat.service;

import TA_B_SYN_65.rumahSehat.model.PasienModel;

public interface PasienService {
    PasienModel create(PasienModel pengguna);
    PasienModel getPasienByUsername(String username);

}
