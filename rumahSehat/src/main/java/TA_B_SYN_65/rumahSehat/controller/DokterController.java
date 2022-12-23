package TA_B_SYN_65.rumahSehat.controller;

import TA_B_SYN_65.rumahSehat.model.DokterModel;
import TA_B_SYN_65.rumahSehat.model.PasienModel;
import TA_B_SYN_65.rumahSehat.service.DokterService;
import TA_B_SYN_65.rumahSehat.service.PasienService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import java.util.ArrayList;

@Controller
public class DokterController {

    @Qualifier("dokterServiceImpl")
    @Autowired
    private DokterService dokterService;
    @Autowired
    private PasienService pasienService;

    @GetMapping("/createNew")
    public String createNewDokter(Model model) {
        var dok = new DokterModel();
        var pas = new PasienModel();

        dok.setUsername("ahmad");
        dok.setPassword("emonyhwh");
        dok.setEmail("ahmad@gmail.com");
        dok.setNama("dr. Ahmad Aminullah");
        dok.setTarif(75000);
        dok.setRole("DOKTER");
        dok.setListAppointment(new ArrayList<>());
        dokterService.addDokter(dok);

        pas.setUsername("budi");
        pas.setPassword("nomeprjct");
        pas.setEmail("budi@xmail.com");
        pas.setNama("Budiman Prasetyo");
        pas.setSaldo(900000);
        pas.setRole("PASIEN");
        pas.setUmur(26);
        pas.setListAppointment(new ArrayList<>());
        pasienService.create(pas);

        return "home";
    }

}
