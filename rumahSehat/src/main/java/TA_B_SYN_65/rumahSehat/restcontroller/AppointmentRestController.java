package TA_B_SYN_65.rumahSehat.restcontroller;


import TA_B_SYN_65.rumahSehat.model.AppointmentModel;
import TA_B_SYN_65.rumahSehat.model.DokterModel;
import TA_B_SYN_65.rumahSehat.model.JwtCreateAptRequest;
import TA_B_SYN_65.rumahSehat.service.AppointmentService;
import TA_B_SYN_65.rumahSehat.service.DokterService;
import TA_B_SYN_65.rumahSehat.service.PasienService;
import TA_B_SYN_65.rumahSehat.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDateTime;
import java.util.List;

@RestController
@RequestMapping("api/mobile/appointment")
public class AppointmentRestController {
    @Autowired
    UserService userService;
    @Autowired
    DokterService dokterService;
    @Autowired
    PasienService pasienService;
    @Autowired
    AppointmentService appointmentService;

    @GetMapping("/create")
    public List<DokterModel> getAllDokter() {
        return userService.getListDokter();
    }

    @PostMapping(value = "/create")
    public AppointmentModel createAppointment(@RequestBody JwtCreateAptRequest request) {
        AppointmentModel apt = new AppointmentModel();
        apt.setPasien(pasienService.getPasienByUsername(request.getPasien()));
        apt.setDokter(dokterService.getDokterByUsername(request.getDokter()));
        apt.setWaktuAwal(LocalDateTime.now());
        apt.setIsDone(request.getIsDone());
        appointmentService.createAppointment(apt);
        return apt;
    }

    @GetMapping("/list-appointment/{username}")
    public List<AppointmentModel> getUserAppointment(@PathVariable String username) {
        return appointmentService.getListAppointmentByPasien(pasienService.getPasienByUsername(username));
    }
}
