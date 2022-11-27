package TA_B_SYN_65.rumahSehat.service;

import TA_B_SYN_65.rumahSehat.model.AppointmentModel;
import TA_B_SYN_65.rumahSehat.model.DokterModel;

import java.util.List;
import java.util.Optional;

public interface AppointmentService {
    AppointmentModel getAppointmentByKode(String kode);
    List<AppointmentModel> getListAppointment();
    void createAppointment(AppointmentModel appointment);
    List<AppointmentModel> getListAppointmentByDokter(DokterModel dokter);
}
