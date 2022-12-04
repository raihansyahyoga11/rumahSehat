package TA_B_SYN_65.rumahSehat.controller;

import TA_B_SYN_65.rumahSehat.model.*;
import TA_B_SYN_65.rumahSehat.security.jwt.JwtTokenUtil;
import TA_B_SYN_65.rumahSehat.service.AdminService;
import TA_B_SYN_65.rumahSehat.service.PasienRestService;
import TA_B_SYN_65.rumahSehat.service.PasienService;
import TA_B_SYN_65.rumahSehat.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.DisabledException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.*;

import java.security.Principal;
import java.util.ArrayList;
import java.util.List;

@RestController
@CrossOrigin
@RequestMapping("/api/mobile")
public class PasienController {
    @Autowired
    private AuthenticationManager authenticationManager;
    @Autowired
    private JwtTokenUtil jwtTokenUtil;


    @Autowired
    private UserDetailsService userDetailsService;

    @Autowired
    private UserService userService;

    @Autowired
    PasswordEncoder passwordEncoder;

    @Autowired
    PasienService pasienService;
    @Autowired
    PasienRestService pasienRestService;

    @Autowired
    AdminService adminService;

    @PostMapping( value = "/signin")
    public ResponseEntity<JwtResponse> authenticateLogin(@RequestBody JwtLoginRequest authenticationRequest) throws Exception {
        try {
            System.out.println("Masuk");

            Authentication authenticate = authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(authenticationRequest.getUsername(), authenticationRequest.getPassword()));
        System.out.println("sini");
            final String token = jwtTokenUtil.generateToken((UserDetails) authenticate.getPrincipal());
        System.out.println(token);
        System.out.println(authenticate.getName());
            return ResponseEntity.ok()
                    .body(new JwtResponse(token, authenticate.getName()));
        }
        catch (DisabledException e) {
            throw new Exception("USER_DISABLED", e);
        }
        catch (BadCredentialsException e) {
            throw new Exception("INVALID_CREDENTIALS", e);
        }
    }

    @PostMapping(value="/signupAdmin")
    public AdminModel signupAdmin(@RequestBody JwtSignUpRequest request) {
        System.out.println("hihi");
        AdminModel user = new AdminModel();
        user.setRole(request.getRole());
        user.setUsername(request.getUsername());
        user.setEmail(request.getEmail());
        user.setPassword(passwordEncoder.encode(request.getPassword()));
        user.setNama(request.getNama());
        AdminModel created = adminService.create(user);
        return created;
    }

    @PostMapping(value="/signupPasien")
    public PasienModel signupPasien(@RequestBody JwtSignUpRequest request) {
        System.out.println("haha");
        PasienModel user = new PasienModel();
        user.setUmur(request.getUmur());
        user.setRole(request.getRole());
        user.setSaldo(5000);
        user.setUsername(request.getUsername());
        user.setEmail(request.getEmail());
        user.setPassword(passwordEncoder.encode(request.getPassword()));
        user.setNama(request.getNama());
        PasienModel created = pasienService.create(user);
        return created;
    }
    @CrossOrigin
    @GetMapping(value="/profile/pasien")
    @ResponseBody
    private PasienModel retrievePasien(Principal principal){
        //try{
        return pasienRestService.getPasienByUsername(getPrincipal());
        //}catch(NoSuchElementException e){
        //throw new ResponseStatusException(
        // HttpStatus.NOT_FOUND,"Pasien dengan "+ getPrincipal() +" not found"
        //);
        //}
    }
    private String getPrincipal() {
        String userName = null;
        Object principal = SecurityContextHolder.getContext().getAuthentication().getPrincipal();

        if (principal instanceof UserDetails) {
            userName = ((UserDetails) principal).getUsername();
        } else {
            userName = principal.toString();
        }
        return userName;
    }
}
