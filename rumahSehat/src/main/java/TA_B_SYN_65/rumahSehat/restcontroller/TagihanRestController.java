package TA_B_SYN_65.rumahSehat.restcontroller;

import java.util.ArrayList;
import java.util.List;
import java.util.NoSuchElementException;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.server.ResponseStatusException;

import TA_B_SYN_65.rumahSehat.dto.TagihanDto;
import TA_B_SYN_65.rumahSehat.dto.TagihanDto;
import TA_B_SYN_65.rumahSehat.model.TagihanModel;
import TA_B_SYN_65.rumahSehat.repository.TagihanDb;
import TA_B_SYN_65.rumahSehat.service.PasienService;
import TA_B_SYN_65.rumahSehat.service.TagihanRestService;

@RestController
@CrossOrigin()
@RequestMapping("api/v1")
public class TagihanRestController {

   @Autowired
   private TagihanDb tagihanDb;

   @Autowired
   private TagihanRestService tagihanRestService;

   @GetMapping(value = "/tagihan/{code}")
   private TagihanDto retrieveTagihan(@PathVariable("code") String code) {
      try {
         TagihanModel tagihan = tagihanRestService.getTagihanByCode(code);
         return new TagihanDto(tagihan);

      } catch (NoSuchElementException e) {
         throw new ResponseStatusException(
               HttpStatus.NOT_FOUND, "Code Tagihan " + code + " not found");
      }
   }

   @GetMapping(value = "/list-tagihan")
   private List<TagihanDto> retrieveListTagihan(Model model, HttpServletRequest request) {
      String username = request.getUserPrincipal().getName();
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

}
