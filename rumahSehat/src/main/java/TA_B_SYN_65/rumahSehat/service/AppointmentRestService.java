package TA_B_SYN_65.rumahSehat.service;

import TA_B_SYN_65.rumahSehat.model.AppointmentModel;

public interface AppointmentRestService {
    void createAppointment(AppointmentModel apt);
    AppointmentModel getAppointmentByKode(String kode);

}
