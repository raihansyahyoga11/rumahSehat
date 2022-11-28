package TA_B_SYN_65.rumahSehat.repository;

<<<<<<< HEAD
=======

>>>>>>> 635bfcfef9c8a4ce83dcb100515dc0f378d76842
import TA_B_SYN_65.rumahSehat.model.ObatModel;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

<<<<<<< HEAD
@Repository
public interface ObatDb extends JpaRepository<ObatModel, String> {
=======
import java.util.Optional;

@Repository
public interface ObatDb extends JpaRepository<ObatModel, String> {
//    Optional<ObatModel> findByName(String obat);

>>>>>>> 635bfcfef9c8a4ce83dcb100515dc0f378d76842
}
