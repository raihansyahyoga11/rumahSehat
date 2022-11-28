package TA_B_SYN_65.rumahSehat.service;

import TA_B_SYN_65.rumahSehat.model.AppointmentModel;

<<<<<<< HEAD
public interface AppointmentService {
    AppointmentModel getAppointmentByKode(String kode);
=======
import java.util.List;

public interface AppointmentService {
    AppointmentModel getAppointmentByKode(String kode);
    List<AppointmentModel> getListAppointment();
    void createAppointment(AppointmentModel appointment);
>>>>>>> 635bfcfef9c8a4ce83dcb100515dc0f378d76842
}
