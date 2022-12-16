package TA_B_SYN_65.rumahSehat.repository;

import TA_B_SYN_65.rumahSehat.model.AppointmentModel;

import java.util.List;
import java.util.Optional;

import TA_B_SYN_65.rumahSehat.model.DokterModel;
import TA_B_SYN_65.rumahSehat.model.PasienModel;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface AppointmentDb extends JpaRepository<AppointmentModel, String> {
    Optional<AppointmentModel> findByKode(String kode);
    List<AppointmentModel> findByDokter(DokterModel dokter);
    List<AppointmentModel> findByPasien(PasienModel pasien);
}
