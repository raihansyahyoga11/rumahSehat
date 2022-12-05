package TA_B_SYN_65.rumahSehat.service;

import java.util.List;

import TA_B_SYN_65.rumahSehat.model.AppointmentModel;

public interface AppointmentService {
    AppointmentModel getAppointmentByKode(String kode);

    List<AppointmentModel> getListAppointment();

    void createAppointment(AppointmentModel appointment);
}
