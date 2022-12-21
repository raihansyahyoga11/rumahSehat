package TA_B_SYN_65.rumahSehat.service;

import TA_B_SYN_65.rumahSehat.model.DokterModel;
import TA_B_SYN_65.rumahSehat.model.UserModel;
import TA_B_SYN_65.rumahSehat.model.ApotekerModel;
import TA_B_SYN_65.rumahSehat.model.PasienModel;
import TA_B_SYN_65.rumahSehat.repository.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class UserServiceImpl implements UserService {

    @Autowired
    private UserDb userDb;

    @Autowired
    private ApotekerDb apotekerDb;

    @Autowired
    private DokterDb dokterDb;

    @Autowired
    private AdminDb adminDb;

    @Autowired
    private PasienDb pasienDb;


    @Override
    public UserModel getUserByUsername(String nama) {
        UserModel user = userDb.findByUsername(nama);
        return user;
    }


    @Override
    public UserModel addUser(UserModel user) {
        String pass = encrypt(user.getPassword());
        user.setPassword(pass);
        return userDb.save(user);
    }



    @Override
    public String encrypt(String password) {
        BCryptPasswordEncoder passwordEncoder = new BCryptPasswordEncoder();
        String hashedPassword = passwordEncoder.encode(password);
        return hashedPassword;
    }


    @Override
    public UserModel deleteUser(UserModel user) {
        userDb.delete(user);
        return user;
    }


    @Override
    public UserModel addUser(UserModel user, Integer tarif) {
        String pass = encrypt(user.getPassword());
        user.setPassword(pass);

        if (user.getRole().equals("DOKTER")) {
            DokterModel dokter = new DokterModel();
            dokter.setNama(user.getNama());
            dokter.setRole(user.getRole());
            dokter.setUsername(user.getUsername());
            dokter.setPassword(user.getPassword());
            dokter.setEmail(user.getEmail());
            dokter.setTarif(tarif);
            return dokterDb.save(dokter);

        } else if (user.getRole().equals("APOTEKER")) {
            ApotekerModel apoteker = new ApotekerModel();
            apoteker.setNama(user.getNama());
            apoteker.setRole(user.getRole());
            apoteker.setUsername(user.getUsername());
            apoteker.setPassword(user.getPassword());
            apoteker.setEmail(user.getEmail());
            return apotekerDb.save(apoteker);
        } else {
            return userDb.save(user);
        }


    }

    @Override
    public List<UserModel> getListUser() {

        return userDb.findAll();
    }

    @Override
    public List<DokterModel> getListDokter() {

        return dokterDb.findAll();
    }

    @Override
    public List<ApotekerModel> getListApoteker() {
        return apotekerDb.findAll();
    }

    @Override
    public List<PasienModel> getListPasien() {
        return pasienDb.findAll();
    }

    @Override
    public void deleteUserVoid(UserModel user) {
        userDb.delete(user);
    }
}
