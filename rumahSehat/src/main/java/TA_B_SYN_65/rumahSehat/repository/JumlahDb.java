package TA_B_SYN_65.rumahSehat.repository;


import java.util.List;
import java.util.Optional;


import TA_B_SYN_65.rumahSehat.model.JumlahModel;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface JumlahDb extends JpaRepository<JumlahModel, String> {
}