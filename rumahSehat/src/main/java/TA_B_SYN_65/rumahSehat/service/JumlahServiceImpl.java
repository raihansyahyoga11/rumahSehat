package TA_B_SYN_65.rumahSehat.service;

import java.util.List;

import javax.transaction.Transactional;

import TA_B_SYN_65.rumahSehat.model.ObatModel;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import TA_B_SYN_65.rumahSehat.model.JumlahModel;
import TA_B_SYN_65.rumahSehat.repository.JumlahDb;

@Service
@Transactional
public class JumlahServiceImpl implements JumlahService {

    @Autowired
    JumlahDb jumlahDb;
    
    @Override
    public List<JumlahModel> getAllJumlah() {
        return jumlahDb.findAll();
    }

    @Override
    public Integer getKuantitasPemasukkan(ObatModel Obat){
        List<Integer> iny= jumlahDb.findByKuantitasByIdObat(Obat);
        return iny.get(0);

    }
    
}
