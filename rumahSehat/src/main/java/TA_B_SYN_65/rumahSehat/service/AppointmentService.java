package TA_B_SYN_65.rumahSehat.service;

import java.util.List;

import TA_B_SYN_65.rumahSehat.model.AppointmentModel;
import TA_B_SYN_65.rumahSehat.model.DokterModel;
import TA_B_SYN_65.rumahSehat.model.PasienModel;

public interface AppointmentService {
    AppointmentModel getAppointmentByKode(String kode);
    List<AppointmentModel> getListAppointment();
    void createAppointment(AppointmentModel appointment);
    List<AppointmentModel> getListAppointmentByDokter(DokterModel dokter);
    List<AppointmentModel> getListAppointmentByPasien(PasienModel pasien);
}
