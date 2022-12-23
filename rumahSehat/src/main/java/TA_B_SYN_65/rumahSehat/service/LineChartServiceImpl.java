package TA_B_SYN_65.rumahSehat.service;

import TA_B_SYN_65.rumahSehat.model.LineChartModel;
import TA_B_SYN_65.rumahSehat.model.ObatModel;
import TA_B_SYN_65.rumahSehat.repository.JumlahDb;
import TA_B_SYN_65.rumahSehat.repository.LineChartDb;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.*;

@Service
@Transactional
public class LineChartServiceImpl implements LineChartService {
    List<String>listBULAN = null;
    @Autowired
    LineChartDb lineChartDb;
    @Autowired
    JumlahDb jumlahDb;


    @Override
    public void addLineChart(LineChartModel lineChart) {
        lineChartDb.save(lineChart);
    };

    @Override
    public List<String> pilihanBulan() {
        List<String> listBulan = new ArrayList<>();
        listBulan.add("Januari");
        listBulan.add("Februari");
        listBulan.add("Maret");
        listBulan.add("April");
        listBulan.add("Mei");
        listBulan.add("Juni");
        listBulan.add("Juli");
        listBulan.add("Agustus");
        listBulan.add("September");
        listBulan.add("Oktober");
        listBulan.add("November");
        listBulan.add("Desember");
        listBULAN = listBulan;
        return listBulan;

    };
    @Override
    public LinkedHashMap<String,Integer> hitungDalamBulan(String bulan, ObatModel idObat,Integer harga){
        LinkedHashMap<String,Integer> data = new LinkedHashMap<>();
        Integer noBulan = listBULAN.indexOf(bulan)+1;
        String m = noBulan.toString();
        if (noBulan<10){
            m = "0"+noBulan;
        }
        for (int i = 1; i < 32; i++) {
            String awal = "2022-"+m+"-"+i+" 00:00:00";
            String akhir = "2022-"+m+"-"+i+" 23:59:59";
            List<Integer> hasil = jumlahDb.findByKuantitasPerHari(idObat,awal,akhir);
            if (hasil.get(0)== null||hasil== null){
                data.put(i+"-"+m+"-2022",0);
            }
            else{
                data.put(i+"-"+m+"-2022",(hasil.get(0)*harga));
            }
        }
        return data;

    }
    @Override
    public LinkedHashMap<String,Integer> hitungDalamTahun(String bulan, ObatModel idObat,Integer harga){
        LinkedHashMap<String,Integer> data = new LinkedHashMap<>();
        Integer noBulan = listBULAN.indexOf(bulan)+1;
        String m = noBulan.toString();
        for (int i = 1; i < 13; i++) {
            if (i< 10){
                String awal = "2022-0"+i+"-01 00:00:00";
                String akhir = "2022-0"+i+"-31 23:59:59";
                List<Integer> hasil = jumlahDb.findByKuantitasPerBulan(idObat,awal,akhir);
                if (hasil.get(0)== null||hasil== null){
                    data.put(i+"-2022",0);
                }
                else{
                    data.put(i+"-2022",hasil.get(0)*harga);
                }
            }
            else{
                String awal = "2022-"+i+"-01 00:00:00";
                String akhir = "2022-"+i+"-31 23:59:59";
                List<Integer> hasil = jumlahDb.findByKuantitasPerBulan(idObat,awal,akhir);
                if (hasil.get(0)== null||hasil== null){
                    data.put(i+"-2022",0);
                }
                else{
                    data.put(i+"-2022",hasil.get(0)*harga);
                }
            }

        }
        return data;

    }


}
