package TA_B_SYN_65.rumahSehat.service;

import TA_B_SYN_65.rumahSehat.model.ResepModel;

import java.util.List;

public interface ResepService {
    void createResep(ResepModel resep);

    List<ResepModel> getAllResep();

    ResepModel findResepById(Long id);
}
