package TA_B_SYN_65.rumahSehat.service;

import TA_B_SYN_65.rumahSehat.model.BarChartObatModel;
import TA_B_SYN_65.rumahSehat.model.JumlahModel;
import TA_B_SYN_65.rumahSehat.model.ObatModel;
import TA_B_SYN_65.rumahSehat.repository.BarChartObatDb;
import TA_B_SYN_65.rumahSehat.repository.JumlahDb;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;

@Service
@Transactional
public class BarChartObatServiceImpl implements BarChartObatService{

    @Autowired
    BarChartObatDb barChartObatDb;

    @Autowired
    ObatService obatService;

    @Autowired
    JumlahDb jumlahDb;

    public void addBarChartObat(BarChartObatModel barChartObat) {
        barChartObatDb.save(barChartObat);
    }


    public Integer getKuantitas(BarChartObatModel barChartObat) {
        Integer kuantitas = 0;
        ObatModel obatDariChart = obatService.getObatbyId(barChartObat.getObatSelected().getId());
        for (JumlahModel obatdariRepository : jumlahDb.findAll())
            if (obatdariRepository.getResep().getAppointment().getTagihan() != null) {
                if (obatDariChart.getNama().equals(obatdariRepository.getObat().getNama()) && obatdariRepository.getResep().getAppointment().getTagihan().getIsPaid()) {
                    kuantitas = obatdariRepository.getKuantitas();
                    break;
                }
            }
        return kuantitas;
    }


    public Integer getTotalPendapatan(BarChartObatModel barChartObat) {
        Integer totalPendapatan = 0;
        ObatModel obatDariChart = obatService.getObatbyId(barChartObat.getObatSelected().getId());
        for (JumlahModel obatdariRepository : jumlahDb.findAll())
            if (obatdariRepository.getResep().getAppointment().getTagihan() != null) {
                if (obatDariChart.getNama().equals(obatdariRepository.getObat().getNama()) && obatdariRepository.getResep().getAppointment().getTagihan().getIsPaid()) {
                    Integer hargaObat = obatdariRepository.getObat().getHarga();
                    Integer pendapatan = hargaObat * obatdariRepository.getKuantitas();
                    totalPendapatan = hargaObat * pendapatan;
                    break;
                }

            }
        return totalPendapatan;
    }

}
