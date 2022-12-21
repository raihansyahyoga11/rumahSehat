package TA_B_SYN_65.rumahSehat.service;

import TA_B_SYN_65.rumahSehat.model.AdminModel;
import TA_B_SYN_65.rumahSehat.repository.UserDb;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class AdminServiceImpl implements AdminService{

    @Autowired
    private UserDb userDb;


    @Override
    public AdminModel create(AdminModel pengguna) {
        return userDb.save(pengguna);
    }
}
