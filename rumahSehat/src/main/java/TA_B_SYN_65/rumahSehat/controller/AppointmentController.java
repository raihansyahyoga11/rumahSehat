package TA_B_SYN_65.rumahSehat.controller;


import TA_B_SYN_65.rumahSehat.model.*;
import TA_B_SYN_65.rumahSehat.service.AppointmentService;
import TA_B_SYN_65.rumahSehat.service.DokterService;
import TA_B_SYN_65.rumahSehat.service.PasienService;
import TA_B_SYN_65.rumahSehat.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.User;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

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

//    @GetMapping("/api/{username}")
//    public List<AppointmentModel> listAppointment(@PathVariable String username, Model model) {
//        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
//        User authUser = (User) authentication.getPrincipal();
//        String authUsername = authUser.getUsername();
//        UserModel userModel = userService.getUserByUsername(authUsername);
//
//        List<AppointmentModel> listAppointment = appointmentService.getListAppointment();
//
//        return listAppointment;
//    }
//    @PostMapping(value = "/create")
//    public AppointmentModel createAppointment(@RequestBody JwtSignUpRequest request) {
//
//    }

    @GetMapping("")
    public String viewAllAppointment(Model model) {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        User authUser = (User) authentication.getPrincipal();
        String authUsername = authUser.getUsername();
        UserModel userModel = userService.getUserByUsername(authUsername);

//        AppointmentModel apt = new AppointmentModel();
//        apt.setDokter(dokterService.getDokterByUsername("hanan"));
//        apt.setPasien(pasienService.getPasienByUsername("aaminullah"));
//        apt.setIsDone(false);
//        apt.setWaktuAwal(LocalDateTime.now());
//        appointmentService.createAppointment(apt);

        if(userModel.getRole().equals("ADMIN")) {
            List<AppointmentModel> listAppointment = appointmentService.getListAppointment();
            model.addAttribute("listAppointment", listAppointment);
            return "appointment/viewall-appointment";
        } else if (userModel.getRole().equals("DOKTER")) {
            DokterModel dokterLogin = dokterService.getDokterByUsername(userModel.getUsername());
            List<AppointmentModel> listAppointment = appointmentService.getListAppointmentByDokter(dokterLogin);
            model.addAttribute("listAppointment", listAppointment);
            return "appointment/viewall-appointment";
        } else if (userModel.getRole().equals("PASIEN")) {
            PasienModel pasienLogin = pasienService.getPasienByUsername(userModel.getUsername());
            List<AppointmentModel> listAppointment = appointmentService.getListAppointmentByPasien(pasienLogin);
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
        return "auth/access-denied";
    }
}
