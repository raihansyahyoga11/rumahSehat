package TA_B_SYN_65.rumahSehat.repository;

import TA_B_SYN_65.rumahSehat.model.PasienModel;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface PasienDb extends JpaRepository<PasienModel, String> {

}
