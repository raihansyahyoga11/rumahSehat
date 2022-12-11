package TA_B_SYN_65.rumahSehat.service;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import TA_B_SYN_65.rumahSehat.model.ResepModel;
import TA_B_SYN_65.rumahSehat.repository.ResepDb;

import java.util.List;
import java.util.Optional;

@Service
@Transactional
public class ResepServiceImpl implements ResepService {
    @Autowired
    ResepDb resepDb;

    @Override
    public void createResep(ResepModel resep) {
        resepDb.save(resep);
        
    }
    public List<ResepModel> getAllResep() {
        return resepDb.findAll();
    }
    @Override
    public ResepModel findResepById(Long id) {
        Optional<ResepModel> resep = resepDb.findById(id);
        if (resep.isPresent()) {
            return resep.get();
        } else {
            return null;
        }
    };
}
