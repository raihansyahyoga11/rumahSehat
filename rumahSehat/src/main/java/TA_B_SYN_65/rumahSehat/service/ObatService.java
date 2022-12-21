package TA_B_SYN_65.rumahSehat.service;

import TA_B_SYN_65.rumahSehat.model.ObatModel;

import java.util.List;

public interface ObatService {

    List<ObatModel> getListObat();

    ObatModel getObatbyId(String id);

    ObatModel updateObat(ObatModel obat);

}
