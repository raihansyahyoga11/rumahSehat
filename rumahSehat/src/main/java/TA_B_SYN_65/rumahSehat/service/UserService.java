package TA_B_SYN_65.rumahSehat.service;

import TA_B_SYN_65.rumahSehat.model.*;

import java.util.List;

public interface UserService {

    UserModel addUser(UserModel user);

    UserModel addUser(UserModel user, Integer tarifDokter);

    UserModel getUserByUsername(String name);

    public  String encrypt(String password);

    UserModel deleteUser(UserModel user);

    List<UserModel> getListUser();

    List<DokterModel> getListDokter();

    List<ApotekerModel> getListApoteker();

    List<PasienModel> getListPasien();

    void deleteUserVoid(UserModel user);
}
