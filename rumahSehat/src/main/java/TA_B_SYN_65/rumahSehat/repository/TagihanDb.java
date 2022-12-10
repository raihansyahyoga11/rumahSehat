package TA_B_SYN_65.rumahSehat.repository;


import TA_B_SYN_65.rumahSehat.model.TagihanModel;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface TagihanDb extends JpaRepository<TagihanModel, Long> {
    Optional<TagihanModel> findByKode(Long kode);

}
