package TA_B_SYN_65.rumahSehat.repository;

import TA_B_SYN_65.rumahSehat.model.DokterModel;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface DokterDb extends JpaRepository<DokterModel, String> {
    DokterModel findByUsername(String username);
}
