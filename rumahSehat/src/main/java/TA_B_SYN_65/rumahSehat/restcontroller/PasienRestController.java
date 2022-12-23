package TA_B_SYN_65.rumahSehat.restcontroller;

import TA_B_SYN_65.rumahSehat.model.PasienModel;
import TA_B_SYN_65.rumahSehat.service.AdminService;
import TA_B_SYN_65.rumahSehat.service.PasienRestService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContext;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;

import java.util.NoSuchElementException;

@CrossOrigin
@RestController
@RequestMapping("/api/v1")
public class PasienRestController {

    @Autowired
    PasienRestService pasienRestService ;

    @Autowired
    AdminService adminService;


    @GetMapping(value="/profile/pasien")
    @ResponseBody
    private PasienModel retrievePasien(){
        return pasienRestService.getPasienByUsername(getPrincipal());
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
