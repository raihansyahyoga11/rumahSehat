package TA_B_SYN_65.rumahSehat.service;

import TA_B_SYN_65.rumahSehat.model.PasienModel;

import java.util.List;

public interface PasienService {
    void addPasien(PasienModel pasien);
    List<PasienModel> getListPasien();
    void deletePasien(PasienModel pasien);
}
