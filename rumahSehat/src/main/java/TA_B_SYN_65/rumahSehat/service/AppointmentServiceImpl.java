package TA_B_SYN_65.rumahSehat.service;

import java.util.List;
import java.util.Optional;

import javax.transaction.Transactional;

import TA_B_SYN_65.rumahSehat.model.DokterModel;
import TA_B_SYN_65.rumahSehat.model.PasienModel;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import TA_B_SYN_65.rumahSehat.model.AppointmentModel;
import TA_B_SYN_65.rumahSehat.repository.AppointmentDb;

@Service
@Transactional
public class AppointmentServiceImpl implements AppointmentService{
    @Autowired
    AppointmentDb appointmentDb;
    
    @Override
    public AppointmentModel getAppointmentByKode(String kode) {
        Optional<AppointmentModel> appointment = appointmentDb.findByKode(kode);
        if (appointment.isPresent()) {
            return appointment.get();
        } else {
            return null;
        }
    }

    @Override
    public List<AppointmentModel> getListAppointment() {
        return appointmentDb.findAll();
    }

    @Override
    public void createAppointment(AppointmentModel appointment) {
        appointmentDb.save(appointment);
    }

    @Override
    public List<AppointmentModel> getListAppointmentByDokter(DokterModel dokter) {
        return appointmentDb.findByDokter(dokter);
    }

    @Override
    public List<AppointmentModel> getListAppointmentByPasien(PasienModel pasien) {
        return appointmentDb.findByPasien(pasien);
    }
}
