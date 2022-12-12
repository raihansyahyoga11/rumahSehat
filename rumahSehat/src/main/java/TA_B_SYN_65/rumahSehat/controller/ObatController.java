package TA_B_SYN_65.rumahSehat.controller;

import TA_B_SYN_65.rumahSehat.model.*;
import TA_B_SYN_65.rumahSehat.model.BarChartModel;
import TA_B_SYN_65.rumahSehat.model.BarChartObatModel;
import TA_B_SYN_65.rumahSehat.security.xml.Attributes;
import TA_B_SYN_65.rumahSehat.security.xml.ServiceResponse;
import TA_B_SYN_65.rumahSehat.service.BarChartObatService;
import TA_B_SYN_65.rumahSehat.service.BarChartService;
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
import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

@Controller
public class ObatController {
    @Qualifier("obatServiceImpl")
    @Autowired
    private ObatService obatService;

    @Autowired
    BarChartService barChartService;

    @Autowired
    BarChartObatService barChartObatService;

    @GetMapping("/obat/viewall")
    public String listObat(Model model) {
        List<ObatModel> listObat = obatService.getListObat();
        model.addAttribute("listObat", listObat);
        return "viewall-obat";
    }

    @GetMapping("/obat/barChartObat")
    public String ChartObatForm(Model model) {
        BarChartModel newBarChart = new BarChartModel();

        List<BarChartObatModel> newBarChartObat = new ArrayList<>();
        newBarChart.setListBarChartObat(newBarChartObat);

        newBarChart.getListBarChartObat().add(new BarChartObatModel());

        List<ObatModel> listObat = obatService.getListObat();

        newBarChart.setListBarChartObat(new ArrayList<>());
        model.addAttribute("barChart", newBarChart);
        model.addAttribute("listBarChart", newBarChartObat);
        model.addAttribute("listObat", listObat);
        return "obat/form-chart-obat";
    }

    @PostMapping("/obat/barChartObat")
    public String chartObatSubmit(@ModelAttribute BarChartModel barChart, Model model) {
        if (barChart.getListBarChartObat() == null) {
            barChart.setListBarChartObat(new ArrayList<>());
        }
        System.out.println(barChart.getListBarChartObat().size());
        for (int i = 0; i < barChart.getListBarChartObat().size(); i++) {
            barChart.getListBarChartObat().get(i).setBarChart(barChart);
        }
        barChartService.addBarChart(barChart);
        Map<String, Integer> data = new LinkedHashMap<String, Integer>();
        String jenisBarChart = "";

        if (barChart.getIsBarChartKuantitas() == true) {
            jenisBarChart = "Kuantitas";
            for (int i = 0; i < barChart.getListBarChartObat().size(); i++) {
                barChart.getListBarChartObat().get(i).setBarChart(barChart);
                String namaObat = (obatService
                        .getObatbyId(barChart.getListBarChartObat().get(i).getObatSelected().getId())).getNama();
                Integer kuantitas = barChartObatService.getKuantitas(barChart.getListBarChartObat().get(i));
                data.put(namaObat, kuantitas);
            }

        } else {
            jenisBarChart = "Total Pendapatan";
            for (int i = 0; i < barChart.getListBarChartObat().size(); i++) {
                barChart.getListBarChartObat().get(i).setBarChart(barChart);
                String namaObat = (obatService
                        .getObatbyId(barChart.getListBarChartObat().get(i).getObatSelected().getId())).getNama();
                Integer totalPendapatan = barChartObatService.getTotalPendapatan(barChart.getListBarChartObat().get(i));
                data.put(namaObat, totalPendapatan);

            }
        }
        System.out.println(data.size());
        model.addAttribute("data", data);
        model.addAttribute("jenisBarChart", jenisBarChart);
        return "obat/chart-obat";
    }

    @PostMapping(value = "/obat/barChartObat", params = { "addRowChart" })
    private String addRowObatMultiple(
            @ModelAttribute BarChartModel barChart,
            Model model) {
        System.out.println("seengganya masuk sini");
        List<ObatModel> listObat = obatService.getListObat();
        if (barChart.getListBarChartObat() == null || barChart.getListBarChartObat().size() == 0) {
            System.out.println("cihuy");
            barChart.setListBarChartObat(new ArrayList<>());
            barChart.getListBarChartObat().add(new BarChartObatModel());
            System.out.println(barChart.getListBarChartObat().size());
            model.addAttribute("barChart", barChart);
            model.addAttribute("listObat", listObat);
            return "obat/form-chart-obat";
        } else if (barChart.getListBarChartObat().size() < 8) {
            System.out.println("jim");
            barChart.getListBarChartObat().add(new BarChartObatModel());
            model.addAttribute("barChart", barChart);
            model.addAttribute("listObat", listObat);
            // model.addAttribute("listBarChart", newBarChartObat);
            return "obat/form-chart-obat";
        } else {
            System.out.println("cihuy1");
            model.addAttribute("barChart", barChart);
            model.addAttribute("listObat", listObat);
            // model.addAttribute("listBarChart", newBarChartObat);
            model.addAttribute("message", "Maaf, anda sudah mencapai jumlah maksimal obat yang dapat ditampilkan");
            return "obat/form-chart-obat";
        }

    }

    @PostMapping(value = "/obat/barChartObat", params = { "deleteRowChart" })
    private String deleteRowObatMultiple(
            @ModelAttribute BarChartModel barChart,
            @RequestParam("deleteRowChart") Integer row,
            Model model) {
        List<ObatModel> listObat = obatService.getListObat();
        final Integer rowId = Integer.valueOf(row);
        barChart.getListBarChartObat().remove(rowId.intValue());
        model.addAttribute("barChart", barChart);
        model.addAttribute("listObat", listObat);
        return "obat/form-chart-obat";
    }

    @GetMapping(value = "/obat/ubahStok/{id}")
    public String updateObatFormPage(@PathVariable String id, Model model) {
        ObatModel obat = obatService.getObatbyId(id);
        model.addAttribute("obat", obat);
        return "form-update-stokObat";
    }

    @PostMapping(value = "/obat/ubahStok")
    public String updateObatSubmitPage(@ModelAttribute ObatModel obat, Model model, BindingResult result,
            RedirectAttributes redirectAttrs) {
        if (result.hasErrors()) {
            redirectAttrs.addFlashAttribute("error", "The error occurred.");
            return "redirect:/obat/ubahStok/{id}";
        }

        ObatModel updatedObat = obatService.updateObat(obat);
        redirectAttrs.addFlashAttribute("success",
                String.format("Stok obat %s berhasil diperbarui", updatedObat.getNama()));

        model.addAttribute("nama", updatedObat.getNama());
        return "redirect:/obat/viewall";
    }
}
