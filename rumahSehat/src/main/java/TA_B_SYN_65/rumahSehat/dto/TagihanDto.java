package TA_B_SYN_65.rumahSehat.dto;

import lombok.Data;

import java.time.LocalDateTime;

import TA_B_SYN_65.rumahSehat.model.TagihanModel;

@Data
public class TagihanDto {

   private String kode;
   private Boolean isPaid;
   private LocalDateTime tanggalTerbuat;
   private int jumlahTagihan;
   private String kodeAppointment;

   public TagihanDto(TagihanModel tagihan) {
      kode = tagihan.getKode();
      isPaid = tagihan.getIsPaid();
      tanggalTerbuat = tagihan.getTanggalTerbuat();
      jumlahTagihan = tagihan.getJumlahTagihan();
      kodeAppointment = tagihan.getAppointment().getKode();
   }
}
