package TA_B_SYN_65.rumahSehat.controller;

import TA_B_SYN_65.rumahSehat.model.TagihanModel;
import TA_B_SYN_65.rumahSehat.security.xml.Attributes;
import TA_B_SYN_65.rumahSehat.security.xml.ServiceResponse;
//import TA_B_SYN_65.rumahSehat.service.TagihanService;
import TA_B_SYN_65.rumahSehat.service.TagihanService;
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
import org.springframework.web.bind.annotation.*;
import org.springframework.web.reactive.function.client.WebClient;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.security.Principal;
import java.util.List;

@Controller
public class TagihanController {
    @Qualifier("tagihanServiceImpl")
    @Autowired
    private TagihanService tagihanService;

    @GetMapping("/tagihan/viewall")
    public String listTagihan(Model model){
        List<TagihanModel> listTagihan = tagihanService.getListTagihan();
        model.addAttribute("listTagihan", listTagihan);
        return "viewall-tagihan";
    }
    @GetMapping("/tagihan/{kode}")
    public String viewDetailTagihanPage(@PathVariable(value = "kode") String kode, Model model) {
        TagihanModel tagihan = tagihanService.getKodeTagihan(Long.parseLong(kode));
        model.addAttribute("tagihan", tagihan);
        return "view-tagihan";
    }
}

