package TA_B_SYN_65.rumahSehat.service;

import TA_B_SYN_65.rumahSehat.model.UserModel;

import java.util.List;

public interface UserService {

    UserModel addUser(UserModel user);
    String encrypt(String password);

    UserModel getUserByUsername(String username);

    List<UserModel> findAll();

    void deleteUser(UserModel user);
}
