package TA_B_SYN_65.rumahSehat.controller;


import TA_B_SYN_65.rumahSehat.model.AppointmentModel;
import TA_B_SYN_65.rumahSehat.model.DokterModel;
import TA_B_SYN_65.rumahSehat.model.PasienModel;
import TA_B_SYN_65.rumahSehat.model.UserModel;
import TA_B_SYN_65.rumahSehat.service.AppointmentService;
import TA_B_SYN_65.rumahSehat.service.DokterService;
import TA_B_SYN_65.rumahSehat.service.PasienService;
import TA_B_SYN_65.rumahSehat.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
<<<<<<< HEAD
=======
import org.springframework.cglib.core.Local;
>>>>>>> e0777bd319f8ffe92696629ad6ab567ab6b9dc6f
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.User;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@CrossOrigin
@Controller
@RequestMapping("/appointment")
public class AppointmentController {
    @Autowired
    UserService userService;
    @Autowired
    DokterService dokterService;
    @Autowired
    PasienService pasienService;
    @Autowired
    AppointmentService appointmentService;

    @GetMapping("")
    public String viewAllAppointment(Model model) {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        User authUser = (User) authentication.getPrincipal();
        String authUsername = authUser.getUsername();
        UserModel userModel = userService.getUserByUsername(authUsername);

//        DokterModel d = new DokterModel();
//        d.setUsername("ahmad");
//        d.setPassword("emonyhwh");
//        d.setEmail("ahmad@gmail.com");
//        d.setNama("dr. Ahmad Aminullah");
//        d.setTarif(75000);
//        d.setRole("DOKTER");
//        d.setListAppointment(new ArrayList<>());
//        d.setIsSso(false);
//        dokterService.addDokter(d);
<<<<<<< HEAD

//        PasienModel p = new PasienModel();
//        p.setUsername("rijal");
//        p.setPassword("#dragneel2211");
//        p.setEmail("rijal@gmail.com");
//        p.setNama("Muhtarur Rijal");
//        p.setRole("PASIEN");
//        p.setListAppointment(new ArrayList<>());
//        p.setUmur(23);
//        p.setSaldo(150000);
//        p.setIsSso(false);
//        pasienService.create(p);
//
//        AppointmentModel appt = appointmentService.getAppointmentByKode("4028b88184bc1a2f0184bc1a9c390000");
//        appt.setIsDone(true);
//        appt.setDokter(dokterService.getDokterByUsername("archee"));
//        appt.setPasien(pasienService.getPasienByUsername("rijal"));
=======
//
//        PasienModel p = new PasienModel();
//        p.setUsername("tazkiya");
//        p.setPassword("tazkiyamy");
//        p.setEmail("tazkiya@gmail.com");
//        p.setNama("Tazkiya Mutia");
//        p.setRole("PASIEN");
//        p.setListAppointment(new ArrayList<>());
//        p.setUmur(24);
//        p.setSaldo(160000);
//        p.setIsSso(false);
//        pasienService.create(p);
//
//        AppointmentModel appt = new AppointmentModel();
//        appt.setDokter(d);
//        appt.setPasien(p);
>>>>>>> e0777bd319f8ffe92696629ad6ab567ab6b9dc6f
//        appt.setWaktuAwal(LocalDateTime.now());
//        appt.setIsDone(false);
//        appointmentService.createAppointment(appt);


        if(userModel.getRole().equals("ADMIN")) {
            List<AppointmentModel> listAppointment = appointmentService.getListAppointment();
            model.addAttribute("listAppointment", listAppointment);
<<<<<<< HEAD
            return "appointment/viewall-appointment";
        } else if (userModel.getRole().equals("DOKTER")) {
            DokterModel dokterLogin = dokterService.getDokterByUsername(userModel.getUsername());
            List<AppointmentModel> listAppointment = appointmentService.getListAppointmentByDokter(dokterLogin);
            model.addAttribute("listAppointment", listAppointment);
            return "appointment/viewall-appointment";
        } else {
            return "auth/access-denied";
        }
    }

    @GetMapping("/detail/{kode}")
    public String viewAppointmentDetail(@PathVariable String kode, Model model) {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        User authUser = (User) authentication.getPrincipal();
        String authUsername = authUser.getUsername();
        UserModel userModel = userService.getUserByUsername(authUsername);

        if(userModel.getRole().equals("ADMIN") || userModel.getRole().equals("DOKTER")) {
            AppointmentModel apt = appointmentService.getAppointmentByKode(kode);
            model.addAttribute("appt", apt);
            return "appointment/detail-appointment";
        }

=======
            return "appointment/admin-viewall-appointment";
        }
>>>>>>> e0777bd319f8ffe92696629ad6ab567ab6b9dc6f
        return "auth/access-denied";
    }
}
