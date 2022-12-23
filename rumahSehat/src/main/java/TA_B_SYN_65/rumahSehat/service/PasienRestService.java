package TA_B_SYN_65.rumahSehat.service;

import TA_B_SYN_65.rumahSehat.model.AdminModel;
import TA_B_SYN_65.rumahSehat.model.PasienModel;

public interface PasienRestService {
    PasienModel getPasienByUsername(String username);

    PasienModel topUpSaldoPasien(String usename,PasienModel pasien);
}
