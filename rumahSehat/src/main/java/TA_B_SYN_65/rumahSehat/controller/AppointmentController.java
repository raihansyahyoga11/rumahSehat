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

        if(userModel.getRole().equals("ADMIN") || userModel.getRole().equals("DOKTER") || userModel.getRole().equals("PASIEN")) {
            List<AppointmentModel> listAppointment = appointmentService.getListAppointment();
            model.addAttribute("listAppointment", listAppointment);
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
