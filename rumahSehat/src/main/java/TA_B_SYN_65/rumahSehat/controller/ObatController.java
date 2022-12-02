package TA_B_SYN_65.rumahSehat.controller;

import TA_B_SYN_65.rumahSehat.model.ObatModel;
import TA_B_SYN_65.rumahSehat.security.xml.Attributes;
import TA_B_SYN_65.rumahSehat.security.xml.ServiceResponse;
import TA_B_SYN_65.rumahSehat.service.ObatService;
import TA_B_SYN_65.rumahSehat.service.UserService;
import TA_B_SYN_65.rumahSehat.setting.Setting;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.boot.autoconfigure.web.ServerProperties;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContext;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.web.context.HttpSessionSecurityContextRepository;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.reactive.function.client.WebClient;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.security.Principal;
import java.util.List;

@Controller
public class ObatController {
    @Qualifier("obatServiceImpl")
    @Autowired
    private ObatService obatService;

    @GetMapping("/obat/viewall")
    public String listObat(Model model){
        List<ObatModel> listObat = obatService.getListObat();
        model.addAttribute("listObat", listObat);
        return "viewall-obat";
    }
    @GetMapping(value="/obat/ubahStok/{id}")
    public String updateObatFormPage(@PathVariable String id, Model model){
        ObatModel obat = obatService.getObatbyId(id);
        model.addAttribute("obat",obat);
        return "form-update-stokObat";
    }
    @PostMapping(value="/obat/ubahStok")
    public String updateObatSubmitPage(@ModelAttribute ObatModel obat, Model model, BindingResult result, RedirectAttributes redirectAttrs){
        if (result.hasErrors()) {
            redirectAttrs.addFlashAttribute("error", "The error occurred.");
            return "redirect:/obat/ubahStok/{id}";
        }

        ObatModel updatedObat = obatService.updateObat(obat);
        redirectAttrs.addFlashAttribute("success",
                String.format("Stok obat %s berhasil diperbarui", updatedObat.getNama()));

        model.addAttribute("nama",updatedObat.getNama());
        return "redirect:/obat/viewall";
    }
}
