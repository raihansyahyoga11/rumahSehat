package TA_B_SYN_65.rumahSehat.restcontroller;

import java.util.ArrayList;
import java.util.List;
import java.util.NoSuchElementException;

import TA_B_SYN_65.rumahSehat.service.PasienRestService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.server.ResponseStatusException;
import org.springframework.security.core.context.SecurityContext;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.userdetails.UserDetails;

import TA_B_SYN_65.rumahSehat.dto.TagihanDto;
import TA_B_SYN_65.rumahSehat.model.TagihanModel;
import TA_B_SYN_65.rumahSehat.repository.TagihanDb;
import TA_B_SYN_65.rumahSehat.service.TagihanRestService;

@RestController
@CrossOrigin
@RequestMapping("api/v1")
public class TagihanRestController {

   @Autowired
   private TagihanDb tagihanDb;

   @Autowired
   private TagihanRestService tagihanRestService;

   @Autowired
   PasienRestService pasienRestService ;

   Logger logger = LoggerFactory.getLogger(TagihanRestController.class);

   @GetMapping(value = "/tagihan/{code}")
   private TagihanDto retrieveTagihan(@PathVariable("code") String code) {
      try {
         logger.info("Retrieve Tagihan Method");
         TagihanModel tagihan = tagihanRestService.getTagihanByCode(code);
         return new TagihanDto(tagihan);

      } catch (NoSuchElementException e) {
         logger.error("Tagihan not found");
         throw new ResponseStatusException(
               HttpStatus.NOT_FOUND, "Code Tagihan " + code + " not found");
      }
   }

   private String getPrincipal() {
      String userName = null;
      SecurityContext context = SecurityContextHolder.getContext();
      Authentication authentication = context.getAuthentication();
      Object principal = authentication.getPrincipal();

      if (principal instanceof UserDetails) {
         userName = ((UserDetails) principal).getUsername();
      } else {
         userName = principal.toString();
      }
      return userName;
   }

   @GetMapping(value = "/list-tagihan")
   private List<TagihanDto> retrieveListTagihan(Model model) {
      String username = "pasien";
      List<TagihanModel> listTagihan = new ArrayList<>();

      for (TagihanModel tagihan : tagihanDb.findAll()) {
         if (tagihan.getAppointment().getPasien().getUsername().equals(username)) {
            listTagihan.add(tagihan);
         }
      }

      List<TagihanDto> tagihanDtos = new ArrayList<>();

      for (TagihanModel tagihan : listTagihan) {
         tagihanDtos.add(new TagihanDto(tagihan));
      }

      return tagihanDtos;
   }

   @PostMapping(value = "/tagihan/pay/{code}")
   public int pelunasanTagihan(@PathVariable("code") String code) {
      String username = "pasien";
      boolean pembayaranLunas = tagihanRestService.pay(code, username);
      if (pembayaranLunas) {
         return 1;
      } else {
         return 0;
      }
   }

}
