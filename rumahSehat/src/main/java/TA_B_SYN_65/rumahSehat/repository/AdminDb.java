package TA_B_SYN_65.rumahSehat.repository;

import TA_B_SYN_65.rumahSehat.model.AdminModel;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface AdminDb extends JpaRepository<AdminModel, String> {
    Optional<AdminModel> findByUsername(String username);
}
