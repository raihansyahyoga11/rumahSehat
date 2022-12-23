package TA_B_SYN_65.rumahSehat.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.User;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import TA_B_SYN_65.rumahSehat.model.ApotekerModel;
import TA_B_SYN_65.rumahSehat.model.AppointmentModel;
import TA_B_SYN_65.rumahSehat.model.JumlahModel;
import TA_B_SYN_65.rumahSehat.model.ObatModel;
import TA_B_SYN_65.rumahSehat.model.ResepModel;
import TA_B_SYN_65.rumahSehat.model.UserModel;
import TA_B_SYN_65.rumahSehat.service.AppointmentService;
import TA_B_SYN_65.rumahSehat.service.JumlahService;
import TA_B_SYN_65.rumahSehat.service.ObatService;
import TA_B_SYN_65.rumahSehat.service.ResepService;
import TA_B_SYN_65.rumahSehat.service.UserService;

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

    @Autowired
    private UserService userService;

    @Autowired
    private JumlahService jumlahService;

    // Create Resep => Yang boleh cuma dokter
    @GetMapping("/create/{kodeAppointment}")
    public String createResepFormPage(@PathVariable String kodeAppointment, Model model) {

        var authentication = SecurityContextHolder.getContext().getAuthentication();
        var authUser = (User) authentication.getPrincipal();
        var authUsername = authUser.getUsername();
        var userModel = userService.getUserByUsername(authUsername);
        
        if (userModel.getRole().equals("DOKTER")) {
            var resep = new ResepModel();
            List<ObatModel> listObat = obatService.getListObat();

            List<JumlahModel> listAllJumlah = jumlahService.getAllJumlah();
            
            List<JumlahModel> listJumlahNew = new ArrayList<>();

            resep.setListJumlah(listJumlahNew);
            resep.getListJumlah().add(new JumlahModel());
            
            model.addAttribute("resep", resep);
            model.addAttribute("listObat", listObat);
            model.addAttribute("listAllJumlah", listAllJumlah);
            model.addAttribute("listJumlah", listJumlahNew);
            model.addAttribute("kodeAppointment", kodeAppointment);

            return "resep/form-create-resep";
        }
        return "auth/access-denied";
    }

    @PostMapping(value = "/create/{kodeAppointment}", params = {"addRowObat"})
    public String addRowObatMultiple(@PathVariable String kodeAppointment,
                                    @ModelAttribute ResepModel resep,
                                    Model model
    ){
        List<ObatModel> listObat = obatService.getListObat();
        if(resep.getListJumlah() == null){
            resep.setListJumlah(new ArrayList<>());
        }
        resep.getListJumlah().add(new JumlahModel());
        List<JumlahModel> listAllJumlah = jumlahService.getAllJumlah();

        model.addAttribute("resep", resep);
        model.addAttribute("listJumlah", listAllJumlah);
        model.addAttribute("listObat", listObat);

        return "resep/form-create-resep";
    }

    @PostMapping(value = "/create/{kodeAppointment}", params = {"deleteRowObat"})
    public String deleteRowObatMultiple(@PathVariable String kodeAppointment,
                                        @ModelAttribute ResepModel resep,
                                        @RequestParam(value = "deleteRowObat") Integer row,
                                        Model model) {
        
        List<ObatModel> listObat = obatService.getListObat();

        resep.getListJumlah().remove(row.intValue());

        List<JumlahModel> listJumlah = resep.getListJumlah();

        model.addAttribute("resep", resep);
        model.addAttribute("listJumlah", listJumlah);
        model.addAttribute("listObat", listObat);

        return "resep/form-create-resep";
    }

    @PostMapping(value = "/create/{kodeAppointment}")
    public String createResepSubmit(@PathVariable String kodeAppointment, 
                                    @ModelAttribute ResepModel resep, 
                                    Model model,
                                    RedirectAttributes redirectAttrs) {
        
        AppointmentModel appointment = appointmentService.getAppointmentByKode(kodeAppointment);
        appointment.setResep(resep);
        resep.setAppointment(appointment);

        if(resep.getListJumlah() == null){
            resep.setListJumlah(new ArrayList<>());
        }else{
            var count = 0;
            for (JumlahModel jumlah : resep.getListJumlah()){
                jumlah.setResep(resep);
                jumlah.setObat(resep.getListJumlah().get(count).getObat());
                jumlah.setKuantitas(resep.getListJumlah().get(count).getKuantitas());
                count++;
            }
        }

        resep.setIsDone(false);
        resep.setCreatedAt(LocalDateTime.now());

        resepService.createResep(resep);

        redirectAttrs.addFlashAttribute("success",
                String.format("Resep dengan Id %s berhasil ditambahkan!", resep.getId()));

        return "redirect:/resep/viewall";
    }

    @GetMapping("/detail/{id}")
    public String viewDetailResep(@PathVariable Long id, Model model) {
        
        var authentication = SecurityContextHolder.getContext().getAuthentication();
        var authUser = (User) authentication.getPrincipal();
        var authUsername = authUser.getUsername();
        var userModel = userService.getUserByUsername(authUsername);
        
        if (userModel.getRole().equals("DOKTER") || userModel.getRole().equals("ADMIN") 
            || userModel.getRole().equals("APOTEKER")) {
            
            ResepModel resep = resepService.findResepById(id);
            if (resep == null){
                return "/error/404.html";
            }
            ApotekerModel apoteker = resep.getConfirmer();
    



            String namaDokter = resep.getAppointment().getDokter().getNama();
            String namaPasien = resep.getAppointment().getPasien().getNama();

            List<JumlahModel> listJumlahObat = resep.getListJumlah();

            if ((apoteker != null)) {
                String namaApoteker = apoteker.getNama();
            }
            var namaApoteker = "Tidak Ada";

            model.addAttribute("resep", resep);
            model.addAttribute("namaDokter", namaDokter);
            model.addAttribute("namaPasien", namaPasien);
            model.addAttribute("listJumlahObat", listJumlahObat);
            model.addAttribute("namaApoteker", namaApoteker);


            return "/resep/viewdetail-resep";
        }
        return "auth/access-denied";
    }

    @GetMapping(value = "/viewall")
    public String listResep(Model model){
        List<ResepModel> listResep = resepService.getAllResep();
        model.addAttribute("listResep", listResep);
        return "resep/viewall-resep";
    }
}