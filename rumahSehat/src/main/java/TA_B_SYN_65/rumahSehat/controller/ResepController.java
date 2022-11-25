package TA_B_SYN_65.rumahSehat.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import TA_B_SYN_65.rumahSehat.model.AppointmentModel;
import TA_B_SYN_65.rumahSehat.model.JumlahModel;
import TA_B_SYN_65.rumahSehat.model.ObatModel;
import TA_B_SYN_65.rumahSehat.model.ResepModel;
import TA_B_SYN_65.rumahSehat.service.AppointmentService;
import TA_B_SYN_65.rumahSehat.service.ObatService;
import TA_B_SYN_65.rumahSehat.service.ResepService;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@Controller
@RequestMapping("/resep")
public class ResepController {
    @Qualifier("resepServiceImpl")
    @Autowired
    private ResepService resepService;

    @Autowired
    private ObatService obatService;

    @Autowired
    private AppointmentService appointmentService;

    // Create Resep => Yang boleh cuma dokter
    @GetMapping("/create/{kodeAppointment}")
    public String createResepFormPage(@PathVariable String kodeAppointment, Model model) {
        ResepModel resep = new ResepModel();
        List<ObatModel> listObat = obatService.getListObat();

        List<JumlahModel> listJumlahNew = new ArrayList<>();
        resep.setListJumlahObat(listJumlahNew);
        AppointmentModel appointment = appointmentService.getAppointmentByKode(kodeAppointment);

        resep.getListJumlahObat().add(new JumlahModel());

        
        model.addAttribute("resep", resep);
        model.addAttribute("listObat", listObat);
        model.addAttribute("appointment", appointment);

        return "form-create-resep";
    }

    @PostMapping(value = "/create/{kodeAppointment}")
    public String createResepSubmit(@PathVariable String kodeAppointment, 
                                    @ModelAttribute ResepModel resep, 
                                    Model model) {
        if(resep.getListJumlahObat() == null) {
            resep.setListJumlahObat(new ArrayList<>());
        }

        for (JumlahModel temp: resep.getListJumlahObat()) {
            temp.setResep(resep);
        }
        resep.setCreatedAt(LocalDateTime.now());
        AppointmentModel appointment = appointmentService.getAppointmentByKode(kodeAppointment);
        resep.setAppointment(appointment);
        resepService.createResep(resep);
        model.addAttribute("resep", resep);
        return "create-resep";
    }

    @PostMapping(value = "/create/{kodeAppointment}", params = {"addRowObat"})
    private String addRowObatMultiple(@PathVariable String kodeAppointment,
                                    @ModelAttribute ResepModel resep,
                                    Model model
    ){
        if(resep.getListJumlahObat() == null || resep.getListJumlahObat().size() == 0) {
            resep.setListJumlahObat(new ArrayList<>());
        }

        resep.getListJumlahObat().add(new JumlahModel());
        List<ObatModel> listObat = obatService.getListObat();

        AppointmentModel appointment = appointmentService.getAppointmentByKode(kodeAppointment);

        model.addAttribute("resep", resep);
        model.addAttribute("listObat", listObat);
        model.addAttribute("appointment", appointment);

        return "form-create-resep";
    }


    @PostMapping(value = "/create/{kodeAppointment}", params = {"deleteRowObat"})
    private String deleteRowObatMultiple(@PathVariable String kodeAppointment,
                                        @ModelAttribute ResepModel resep,
                                        @RequestParam(value = "deleteRowObat") Integer row,
                                        Model model) {
        final Integer rowId = Integer.valueOf(row);
        resep.getListJumlahObat().remove(rowId.intValue());

        List<ObatModel> listObat = obatService.getListObat();
        AppointmentModel appointment = appointmentService.getAppointmentByKode(kodeAppointment);

        model.addAttribute("resep", resep);
        model.addAttribute("listObat", listObat);
        model.addAttribute("appointment", appointment);

        return "form-create-resep";
    }
}
