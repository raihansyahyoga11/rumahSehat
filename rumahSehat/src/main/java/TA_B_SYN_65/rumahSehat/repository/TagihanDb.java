package TA_B_SYN_65.rumahSehat.repository;


import TA_B_SYN_65.rumahSehat.model.TagihanModel;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface TagihanDb extends JpaRepository<TagihanModel, String> {
//    TagihanModel findByCode(String kode);

}
