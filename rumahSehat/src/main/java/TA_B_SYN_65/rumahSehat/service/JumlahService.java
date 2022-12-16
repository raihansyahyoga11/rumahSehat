package TA_B_SYN_65.rumahSehat.service;
import TA_B_SYN_65.rumahSehat.model.JumlahModel;
import TA_B_SYN_65.rumahSehat.model.ObatModel;

import java.util.List;

public interface JumlahService {
    List<JumlahModel> getAllJumlah();
    Integer getKuantitasPemasukkan(ObatModel idObat);
}
