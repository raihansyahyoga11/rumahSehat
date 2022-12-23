package TA_B_SYN_65.rumahSehat.controller;

import TA_B_SYN_65.rumahSehat.model.*;
import TA_B_SYN_65.rumahSehat.service.AppointmentService;
import TA_B_SYN_65.rumahSehat.service.TagihanService;
import TA_B_SYN_65.rumahSehat.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.User;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDateTime;
import java.util.List;

@Controller
public class TagihanController {
    @Qualifier("tagihanServiceImpl")
    @Autowired
    private TagihanService tagihanService;
    @Autowired
    private AppointmentService appointmentService;
    @Autowired
    private UserService userService;


    @GetMapping("tagihan/create/{kode}")
    public String createTagihan(@PathVariable String kode, Model model) {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        User authUser = (User) authentication.getPrincipal();
        String authUsername = authUser.getUsername();
        UserModel userModel = userService.getUserByUsername(authUsername);

        if(userModel.getRole().equals("DOKTER")) {
            AppointmentModel apt = appointmentService.getAppointmentByKode(kode);
            apt.setIsDone(true);

            TagihanModel tagihan = new TagihanModel();
            tagihan.setAppointment(apt);
            tagihan.setIsPaid(false);
            tagihan.setTanggalTerbuat(LocalDateTime.now());
            tagihan.setTanggalBayar(LocalDateTime.now().plusHours(6));

            if (apt.getResep() == null) {
                tagihan.setJumlahTagihan(apt.getDokter().getTarif());
            } else {
                ResepModel r = apt.getResep();
                int totalObat = 0;
                for (JumlahModel j : r.getListJumlah()) {
                    int harga = j.getKuantitas() * j.getObat().getHarga();
                    totalObat = totalObat + harga;
                }
                tagihan.setJumlahTagihan(apt.getDokter().getTarif() + totalObat);
            }

//            tagihanService.createTagihan(tagihan);

            model.addAttribute("appt", apt);
            return "appointment/detail-appointment";
        }
        return "auth/access-denied";
    }

    @GetMapping("/tagihan/viewall")
    public String listTagihan(Model model){
        List<TagihanModel> listTagihan = tagihanService.getListTagihan();
        model.addAttribute("listTagihan", listTagihan);
        return "viewall-tagihan";
    }
}

