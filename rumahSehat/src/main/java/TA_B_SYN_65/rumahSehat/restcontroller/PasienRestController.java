package TA_B_SYN_65.rumahSehat.restcontroller;

import TA_B_SYN_65.rumahSehat.model.AdminModel;
import TA_B_SYN_65.rumahSehat.model.JwtResponse;
import TA_B_SYN_65.rumahSehat.model.PasienModel;
import TA_B_SYN_65.rumahSehat.model.UserModel;
import TA_B_SYN_65.rumahSehat.service.AdminService;
import TA_B_SYN_65.rumahSehat.service.PasienRestService;
import io.jsonwebtoken.Jwt;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.core.context.SecurityContext;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;

import java.security.Principal;
import java.util.NoSuchElementException;

@RestController
@RequestMapping("/api/v1")
public class PasienRestController {

    @Autowired
    PasienRestService pasienRestService ;

    @Autowired
    AdminService adminService;

    @CrossOrigin
    @GetMapping(value="/profile/pasien")
    @ResponseBody
    private PasienModel retrievePasien(){
        //try{
            System.out.print("masuk22");
            return pasienRestService.getPasienByUsername(getPrincipal());
        //}catch(NoSuchElementException e){
            //throw new ResponseStatusException(
                    // HttpStatus.NOT_FOUND,"Pasien dengan "+ getPrincipal() +" not found"
                    //);
            //}
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

    @PutMapping(value="/topup/pasien/{username}")
    private PasienModel topupSaldo(@PathVariable("username") String username, @RequestBody PasienModel pasien){
        try{
            return pasienRestService.topUpSaldoPasien(username, pasien);
        }catch(NoSuchElementException e){
            throw new ResponseStatusException(
                    HttpStatus.NOT_FOUND,"Pasien dengan  "+ username +" not found"
            );
        }
    }
}
