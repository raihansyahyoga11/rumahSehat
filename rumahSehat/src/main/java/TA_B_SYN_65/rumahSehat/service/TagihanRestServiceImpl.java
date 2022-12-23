package TA_B_SYN_65.rumahSehat.service;

import java.time.LocalDateTime;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import TA_B_SYN_65.rumahSehat.model.PasienModel;
import TA_B_SYN_65.rumahSehat.model.TagihanModel;
import TA_B_SYN_65.rumahSehat.repository.TagihanDb;

@Service
@Transactional
public class TagihanRestServiceImpl implements TagihanRestService {

   @Autowired
   TagihanDb tagihanDb;

   @Autowired
   private PasienService pasienService;

   @Override
   public List<TagihanModel> retrieveListTagihan() {
      return tagihanDb.findAll();
   }

   @Override
   public TagihanModel getTagihanByCode(String code) {
      return tagihanDb.findByKode(code);
   }

   @Override
   public boolean pay(String kode, String username) {
      TagihanModel tagihan = tagihanDb.findByKode(kode);
      PasienModel pasien = pasienService.getPasienByUsername(username);
      if (tagihan.getJumlahTagihan() <= pasien.getSaldo()) {
         tagihan.setIsPaid(true);
         tagihan.setTanggalBayar(LocalDateTime.now());
         tagihanDb.save(tagihan);
         return true;
      } else {
         return false;
      }
   }

}
