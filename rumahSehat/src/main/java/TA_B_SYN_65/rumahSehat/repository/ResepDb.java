package TA_B_SYN_65.rumahSehat.repository;


import TA_B_SYN_65.rumahSehat.model.ResepModel;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ResepDb extends JpaRepository<ResepModel, String> {
}
