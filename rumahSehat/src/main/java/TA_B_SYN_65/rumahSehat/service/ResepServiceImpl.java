package TA_B_SYN_65.rumahSehat.service;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import TA_B_SYN_65.rumahSehat.model.ResepModel;
import TA_B_SYN_65.rumahSehat.repository.ResepDb;

@Service
@Transactional
public class ResepServiceImpl implements ResepService {
    @Autowired
    ResepDb resepDb;

    @Override
    public void createResep(ResepModel resep) {
        resepDb.save(resep);
        
    }

    
}
