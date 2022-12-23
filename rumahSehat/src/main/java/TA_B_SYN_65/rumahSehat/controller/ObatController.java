package TA_B_SYN_65.rumahSehat.controller;

import TA_B_SYN_65.rumahSehat.model.*;
import TA_B_SYN_65.rumahSehat.model.BarChartModel;
import TA_B_SYN_65.rumahSehat.model.BarChartObatModel;
import TA_B_SYN_65.rumahSehat.service.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

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
    LineChartService lineChartService;

    @Autowired
    JumlahService jumlahService;

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
        var newBarChart = new BarChartModel();

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
        for (var i = 0; i < barChart.getListBarChartObat().size(); i++) {
            barChart.getListBarChartObat().get(i).setBarChart(barChart);
        }
        barChartService.addBarChart(barChart);
        Map<String, Integer> data = new LinkedHashMap<>();
        var jenisBarChart = "";

        if (barChart.getIsBarChartKuantitas()) {
            jenisBarChart = "Kuantitas";
            for (var i = 0; i < barChart.getListBarChartObat().size(); i++) {
                barChart.getListBarChartObat().get(i).setBarChart(barChart);
                String namaObat = (obatService
                        .getObatbyId(barChart.getListBarChartObat().get(i).getObatSelected().getId())).getNama();
                Integer kuantitas = barChartObatService.getKuantitas(barChart.getListBarChartObat().get(i));
                data.put(namaObat, kuantitas);
            }

        } else {
            jenisBarChart = "Total Pendapatan";
            for (var i = 0; i < barChart.getListBarChartObat().size(); i++) {
                barChart.getListBarChartObat().get(i).setBarChart(barChart);
                String namaObat = (obatService
                        .getObatbyId(barChart.getListBarChartObat().get(i).getObatSelected().getId())).getNama();
                Integer totalPendapatan = barChartObatService.getTotalPendapatan(barChart.getListBarChartObat().get(i));
                data.put(namaObat, totalPendapatan);

            }
        }
        model.addAttribute("data", data);
        model.addAttribute("jenisBarChart", jenisBarChart);
        return "obat/chart-obat";
    }

    @PostMapping(value = "/obat/barChartObat", params = { "addRowChart" })
    public String addRowObatMultiple(
            @ModelAttribute BarChartModel barChart,
            Model model) {
        List<ObatModel> listObat = obatService.getListObat();
        if (barChart.getListBarChartObat() == null || barChart.getListBarChartObat().isEmpty()) {
            barChart.setListBarChartObat(new ArrayList<>());
            barChart.getListBarChartObat().add(new BarChartObatModel());
            model.addAttribute("barChart", barChart);
            model.addAttribute("listObat", listObat);
            return "obat/form-chart-obat";
        } else if (barChart.getListBarChartObat().size() < 8) {
            barChart.getListBarChartObat().add(new BarChartObatModel());
            model.addAttribute("barChart", barChart);
            model.addAttribute("listObat", listObat);
            return "obat/form-chart-obat";
        } else {
            model.addAttribute("barChart", barChart);
            model.addAttribute("listObat", listObat);
            model.addAttribute("message", "Maaf, anda sudah mencapai jumlah maksimal obat yang dapat ditampilkan");
            return "obat/form-chart-obat";
        }

    }

    @PostMapping(value = "/obat/barChartObat", params = { "deleteRowChart" })
    public String deleteRowObatMultiple(
            @ModelAttribute BarChartModel barChart,
            @RequestParam("deleteRowChart") Integer row,
            Model model) {
        List<ObatModel> listObat = obatService.getListObat();
        barChart.getListBarChartObat().remove(row.intValue());
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
    @GetMapping(value ="/obat/lineChartObat")
    public String lineChartObat(Model model){
        var lineChart = new LineChartModel();

        List<LineChartObatModel> newLineChartObat = new ArrayList<>();
        lineChart.setListLineChartObat(newLineChartObat);
        lineChart.getListLineChartObat().add(new LineChartObatModel());
        List<String> listBulan = lineChartService.pilihanBulan();
        List<ObatModel> listObat = obatService.getListObat();
        lineChart.setListLineChartObat(new ArrayList<>());

        model.addAttribute("listBulan", listBulan);
        model.addAttribute("listLineChart", newLineChartObat);
        model.addAttribute("lineChart", lineChart);
        model.addAttribute("listObat", listObat);
        return "obat/form-line-chart";

    }
    @PostMapping(value = "/obat/lineChartObat", params = {"addRowChart"})
    public String addRowLineObatMultiple(@ModelAttribute LineChartModel lineChart, Model model) {
        List<String> listBulan = lineChartService.pilihanBulan();
        List<ObatModel> listObat = obatService.getListObat();
        if (lineChart.getListLineChartObat() == null || lineChart.getListLineChartObat().isEmpty()) {
            lineChart.setListLineChartObat(new ArrayList<>());
            lineChart.getListLineChartObat().add(new LineChartObatModel());

            model.addAttribute("lineChart", lineChart);
            model.addAttribute("listObat", listObat);
            model.addAttribute("listBulan", listBulan);
            return "obat/form-line-chart";
        }
        else if (lineChart.getListLineChartObat().size() < 6) {
            lineChart.getListLineChartObat().add(new LineChartObatModel());
            model.addAttribute("lineChart", lineChart);
            model.addAttribute("listObat", listObat);
            model.addAttribute("listBulan", listBulan);
            return "obat/form-line-chart";
        }
        else {
            model.addAttribute("lineChart", lineChart);
            model.addAttribute("listObat", listObat);
            model.addAttribute("listBulan", listBulan);
            model.addAttribute("message", "Maaf, anda sudah mencapai jumlah maksimal obat yang dapat ditampilkan");
            return "obat/form-chart-obat";
        }

    }

    @PostMapping(value = "/obat/lineChartObat", params = {"deleteRowChart"})
    public String deleteRowLineMultiple(@ModelAttribute LineChartModel lineChart, @RequestParam("deleteRowChart") Integer row,
            Model model) {
        List<ObatModel> listObat = obatService.getListObat();
        lineChart.getListLineChartObat().remove(row.intValue());
        model.addAttribute("lineChart", lineChart);
        model.addAttribute("listObat", listObat);
        return "obat/form-line-chart";
    }
    @PostMapping("/obat/lineChartObat")
    public String chartObatLineSubmit(@ModelAttribute LineChartModel lineChart, Model model) {
        var periode = "Data Tahunan";
        var size = 0;
        String[] listNama = new String[5];
        LinkedHashMap<String, Integer> data[] = new LinkedHashMap[5];
        if (lineChart.getListLineChartObat() == null) {
            lineChart.setListLineChartObat(new ArrayList<>());
        }
        for (int i=0; i< lineChart.getListLineChartObat().size();i++) {
            lineChart.getListLineChartObat().get(i).setLineChart(lineChart);
        }
        lineChartService.addLineChart(lineChart);
        if(lineChart.getIsBulan()) {
            periode = "Data Bulan " + lineChart.getBulan();
            for (LineChartObatModel obat:lineChart.getListLineChartObat()) {
                LinkedHashMap<String, Integer> datas;
                var obats = obat.getObatSelected();
                Integer hargas = obatService.getObatbyId(obat.getObatSelected().getId()).getHarga();
                String namaObat = obatService.getObatbyId(obat.getObatSelected().getId()).getNama();
                datas = lineChartService.hitungDalamBulan(lineChart.getBulan(),obats,hargas) ;
                data[size] =datas;
                listNama[size]= namaObat;
                size++;
            }
        }
        else{
            for (LineChartObatModel obat:lineChart.getListLineChartObat()) {
                LinkedHashMap<String, Integer> datas;
                ObatModel obats = obat.getObatSelected();
                Integer hargas = obatService.getObatbyId(obat.getObatSelected().getId()).getHarga();
                String namaObat = obatService.getObatbyId(obat.getObatSelected().getId()).getNama();
                datas = lineChartService.hitungDalamTahun(lineChart.getBulan(),obats,hargas) ;
                data[size] =datas;
                listNama[size]= namaObat;
                size++;

            }
        }
        size = lineChart.getListLineChartObat().size();


        model.addAttribute("lineChart", lineChart);
        model.addAttribute("listNama",listNama);
        model.addAttribute("data", data);
        model.addAttribute("size", size);
        model.addAttribute("periode", periode);


        return "obat/line-chart-obat";
    }


}
