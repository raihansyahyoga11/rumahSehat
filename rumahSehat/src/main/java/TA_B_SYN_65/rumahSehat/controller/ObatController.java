package TA_B_SYN_65.rumahSehat.controller;

import TA_B_SYN_65.rumahSehat.model.BarChartModel;
import TA_B_SYN_65.rumahSehat.model.BarChartObatModel;
import TA_B_SYN_65.rumahSehat.model.ObatModel;
import TA_B_SYN_65.rumahSehat.service.BarChartObatService;
import TA_B_SYN_65.rumahSehat.service.BarChartService;
import TA_B_SYN_65.rumahSehat.service.ObatService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.*;

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
    public String listObat(Model model){
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
        for (int i=0; i< barChart.getListBarChartObat().size();i++) {
            barChart.getListBarChartObat().get(i).setBarChart(barChart);
        }
        barChartService.addBarChart(barChart);
        Map<String, Integer> data = new LinkedHashMap<String, Integer>();
        String jenisBarChart = "";
        if (barChart.getIsBarChartKuantitas() == true) {
            jenisBarChart = "Kuantitas";
            for (int i=0; i< barChart.getListBarChartObat().size();i++) {
                barChart.getListBarChartObat().get(i).setBarChart(barChart);
                data.put((obatService.getObatById(barChart.getListBarChartObat().get(i).getObatSelected().getId())).get().getNama(), (obatService.getObatById(barChart.getListBarChartObat().get(i).getObatSelected().getId()).get().getStok()));
                System.out.println((obatService.getObatById(barChart.getListBarChartObat().get(i).getObatSelected().getId())).get().getNama());
                System.out.println((obatService.getObatById(barChart.getListBarChartObat().get(i).getObatSelected().getId())).get().getStok());

            }
        }
        else {
            jenisBarChart = "Total Pendapatan";
            for (int i=0; i< barChart.getListBarChartObat().size();i++) {
                barChart.getListBarChartObat().get(i).setBarChart(barChart);
                data.put((obatService.getObatById(barChart.getListBarChartObat().get(i).getObatSelected().getId())).get().getNama(), (obatService.getObatById(barChart.getListBarChartObat().get(i).getObatSelected().getId()).get().getStok()));
                System.out.println((obatService.getObatById(barChart.getListBarChartObat().get(i).getObatSelected().getId())).get().getNama());
                System.out.println((obatService.getObatById(barChart.getListBarChartObat().get(i).getObatSelected().getId())).get().getStok());

            }
        }
        System.out.println(data.size());
        model.addAttribute("data", data);
        model.addAttribute("jenisBarChart", jenisBarChart);
        return "obat/chart-obat";
    }


    @PostMapping(value = "/obat/barChartObat", params = {"addRowChart"})
    private String addRowObatMultiple(
            @ModelAttribute BarChartModel barChart,
            Model model
    ) {
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
        }
        else if (barChart.getListBarChartObat().size() < 8) {
            System.out.println("jim");
            barChart.getListBarChartObat().add(new BarChartObatModel());
            model.addAttribute("barChart", barChart);
            model.addAttribute("listObat", listObat);
//            model.addAttribute("listBarChart", newBarChartObat);
            return "obat/form-chart-obat";
        }
        else {
            System.out.println("cihuy1");
            model.addAttribute("barChart", barChart);
            model.addAttribute("listObat", listObat);
//            model.addAttribute("listBarChart", newBarChartObat);
            model.addAttribute("message", "Maaf, anda sudah mencapai jumlah maksimal obat yang dapat ditampilkan");
            return "obat/form-chart-obat";
        }

    }

    @PostMapping(value = "/obat/barChartObat", params = {"deleteRowChart"})
    private String deleteRowObatMultiple(
            @ModelAttribute BarChartModel barChart,
            @RequestParam("deleteRowChart") Integer row,
            Model model
    ) {
        List<ObatModel> listObat = obatService.getListObat();
        final Integer rowId = Integer.valueOf(row);
        barChart.getListBarChartObat().remove(rowId.intValue());
        model.addAttribute("barChart", barChart);
        model.addAttribute("listObat", listObat);
        return "obat/form-chart-obat";
    }


}
