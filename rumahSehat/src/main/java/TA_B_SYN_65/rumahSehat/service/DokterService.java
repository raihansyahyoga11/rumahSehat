package TA_B_SYN_65.rumahSehat.service;

import TA_B_SYN_65.rumahSehat.model.DokterModel;

public interface DokterService {
    void addDokter(DokterModel dokter);
    DokterModel getDokterByUsername(String username);
}
