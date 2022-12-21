package TA_B_SYN_65.rumahSehat.service;

import TA_B_SYN_65.rumahSehat.model.AppointmentModel;
import TA_B_SYN_65.rumahSehat.model.JumlahModel;
import TA_B_SYN_65.rumahSehat.model.TagihanModel;
import TA_B_SYN_65.rumahSehat.repository.TagihanDb;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.time.LocalDateTime;
import java.util.List;

@Service
@Transactional
public class TagihanServiceImpl implements TagihanService {
    @Autowired
    TagihanDb tagihanDb;

    @Override
    public List<TagihanModel> getListTagihan() {
        return tagihanDb.findAll();
    }

    @Override
    public void createTagihan(AppointmentModel appointment) {

        TagihanModel tagihan = new TagihanModel();
        tagihan.setAppointment(appointment);

        int jumlahTagihan = 0;

        jumlahTagihan += appointment.getDokter().getTarif();

        if (appointment.getResep() != null)
            for (JumlahModel obat : appointment.getResep().getListJumlah()) {
                jumlahTagihan += obat.getObat().getHarga() * obat.getKuantitas();
            }

        tagihan.setJumlahTagihan(jumlahTagihan);

        tagihan.setIsPaid(false);
        tagihan.setTanggalTerbuat(LocalDateTime.now());
        tagihan.setKode("TempKode");
        tagihanDb.save(tagihan);
        tagihan.setKode("BILL-" + TagihanModel.count);
        TagihanModel.count++;
    }

    @Override
    public void save(TagihanModel tagihan) {
        tagihanDb.save(tagihan);

    }
}