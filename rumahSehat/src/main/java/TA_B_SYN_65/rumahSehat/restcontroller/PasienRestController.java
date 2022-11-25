//package TA_B_SYN_65.rumahSehat.restcontroller;
//
//import TA_B_SYN_65.rumahSehat.model.PasienModel;
//import TA_B_SYN_65.rumahSehat.service.PasienRestService;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.http.HttpStatus;
//import org.springframework.web.bind.annotation.*;
//import org.springframework.web.server.ResponseStatusException;
//
//import java.util.NoSuchElementException;
//
//@RestController
//@RequestMapping("/api/v1")
//public class PasienRestController {
//
//    @Autowired
//    PasienRestService pasienRestService ;
//
//    @CrossOrigin
//    @GetMapping(value="/profile/pasien/{username}")
//    private PasienModel retrievePasien(@PathVariable("username") String username){
//        try{
//            return pasienRestService.getPasienByUsername(username);
//        }catch(NoSuchElementException e){
//            throw new ResponseStatusException(
//                    HttpStatus.NOT_FOUND,"Pasien dengan  "+ username +" not found"
//            );
//        }
//    }
//
//    @PutMapping(value="/topup/pasien/{username}")
//    private PasienModel topupSaldo(@PathVariable("username") String username, @RequestBody PasienModel pasien){
//        try{
//            return pasienRestService.topUpSaldoPasien(username, pasien);
//        }catch(NoSuchElementException e){
//            throw new ResponseStatusException(
//                    HttpStatus.NOT_FOUND,"Pasien dengan  "+ username +" not found"
//            );
//        }
//    }
//}
