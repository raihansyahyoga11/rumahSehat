package TA_B_SYN_65.rumahSehat.repository;

import TA_B_SYN_65.rumahSehat.model.JumlahModel;

import TA_B_SYN_65.rumahSehat.model.ObatModel;

        import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

@Repository
public interface JumlahDb extends JpaRepository<JumlahModel, String> {
    @Query(value = "SELECT kuantitas from resep LEFT JOIN jumlah on resep.id_resep = jumlah.id_resep \n" +
            "LEFT JOIN appointment on appointment.kode = resep.kode_appointment\n" +
            "LEFT JOIN tagihan on  tagihan.kode = appointment.kode_tagihan\n" +
            "WHERE tagihan.is_paid = true AND id_obat = :idObat", nativeQuery = true)
    List<Integer> findByKuantitasByIdObat(@Param("idObat") ObatModel idObat);

    @Query(value = "SELECT sum(kuantitas) as totalkuantitas from resep LEFT JOIN jumlah on resep.id_resep = jumlah.id_resep \n" +
            "LEFT JOIN appointment on appointment.kode = resep.kode_appointment LEFT JOIN tagihan on tagihan.kode = appointment.kode_tagihan \n" +
            "WHERE tagihan.is_paid = true AND id_obat = :idObat and tagihan.tanggal_bayar BETWEEN :awal and :akhir "
            , nativeQuery = true)
    List<Integer> findByKuantitasPerHari(@Param("idObat") ObatModel idObat ,@Param("awal") String awal,@Param("akhir") String akhir);

    @Query(value = "SELECT sum(kuantitas) as totalkuantitas from resep LEFT JOIN jumlah on resep.id_resep = jumlah.id_resep \n" +
            "LEFT JOIN appointment on appointment.kode = resep.kode_appointment LEFT JOIN tagihan on tagihan.kode = appointment.kode_tagihan \n" +
            "WHERE tagihan.is_paid = true AND id_obat = :idObat and tagihan.tanggal_bayar BETWEEN :awal and :akhir "
            , nativeQuery = true)
    List<Integer> findByKuantitasPerBulan(@Param("idObat") ObatModel idObat ,@Param("awal") String awal,@Param("akhir") String akhir);
}