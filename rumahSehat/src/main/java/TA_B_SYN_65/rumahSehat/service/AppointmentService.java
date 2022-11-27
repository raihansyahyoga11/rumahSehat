package TA_B_SYN_65.rumahSehat.service;

import TA_B_SYN_65.rumahSehat.model.AppointmentModel;

import java.util.List;

public interface AppointmentService {
    AppointmentModel getAppointmentByKode(String kode);
    List<AppointmentModel> getListAppointment();
    void createAppointment(AppointmentModel appointment);
}
