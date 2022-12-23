package TA_B_SYN_65.rumahSehat.controller;


import TA_B_SYN_65.rumahSehat.model.*;
import TA_B_SYN_65.rumahSehat.service.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.User;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDateTime;
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
    @Autowired
    TagihanService tagihanService;

    @GetMapping("")
    public String viewAllAppointment(Model model) {
        var authentication = SecurityContextHolder.getContext().getAuthentication();
        var authUser = (User) authentication.getPrincipal();
        var authUsername = authUser.getUsername();
        var userModel = userService.getUserByUsername(authUsername);

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
        var authentication = SecurityContextHolder.getContext().getAuthentication();
        var authUser = (User) authentication.getPrincipal();
        var authUsername = authUser.getUsername();
        var userModel = userService.getUserByUsername(authUsername);

        if(userModel.getRole().equals("ADMIN") || userModel.getRole().equals("DOKTER")) {
            AppointmentModel apt = appointmentService.getAppointmentByKode(kode);
            model.addAttribute("appt", apt);
            return "appointment/detail-appointment";
        }
        return "auth/access-denied";
    }

    //tes postman create appoinment
    @PostMapping("/create")
    public AppointmentModel makeAppoinment(@RequestBody String kode,Model model,Authentication authentication){
        var appointment = new AppointmentModel();
        var pasien = pasienService.getPasienByUsername(authentication.getName());
        appointment.setIsDone(true);
        appointment.setDokter(dokterService.getDokterByUsername("dokter1"));
        appointment.setPasien(pasien);

        appointment.setWaktuAwal(LocalDateTime.now());

        appointmentService.createAppointment(appointment);
        
        return appointment;
    }

}
