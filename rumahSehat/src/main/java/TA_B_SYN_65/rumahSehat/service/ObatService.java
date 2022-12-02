package TA_B_SYN_65.rumahSehat.service;

import TA_B_SYN_65.rumahSehat.model.ObatModel;

import java.math.BigInteger;
import java.util.List;
import java.util.Optional;

public interface ObatService {

    List<ObatModel> getListObat();
    Optional<ObatModel> getObatById(String id);


}
