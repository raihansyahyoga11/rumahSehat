package TA_B_SYN_65.rumahSehat.controller;

import TA_B_SYN_65.rumahSehat.model.*;
import TA_B_SYN_65.rumahSehat.security.jwt.JwtTokenUtil;
import TA_B_SYN_65.rumahSehat.service.AdminService;
import TA_B_SYN_65.rumahSehat.service.PasienRestService;
import TA_B_SYN_65.rumahSehat.service.PasienService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.DisabledException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;
import java.util.NoSuchElementException;

@RestController
@CrossOrigin
@RequestMapping("/api/mobile")
public class PasienController {
    @Autowired
    private AuthenticationManager authenticationManager;
    @Autowired
    private JwtTokenUtil jwtTokenUtil;

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
            var authenticate = authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(authenticationRequest.getUsername(), authenticationRequest.getPassword()));
            final String token = jwtTokenUtil.generateToken((UserDetails) authenticate.getPrincipal());
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
        var user = new AdminModel();
        user.setRole(request.getRole());
        user.setUsername(request.getUsername());
        user.setEmail(request.getEmail());
        user.setPassword(passwordEncoder.encode(request.getPassword()));
        user.setNama(request.getNama());
        var created = adminService.create(user);
        return created;
    }

    @PostMapping(value="/signupPasien")
    public PasienModel signupPasien(@RequestBody JwtSignUpRequest request) {
        var user = new PasienModel();
        user.setUmur(request.getUmur());
        user.setRole(request.getRole());
        user.setSaldo(0);
        user.setUsername(request.getUsername());
        user.setEmail(request.getEmail());
        user.setPassword(passwordEncoder.encode(request.getPassword()));
        user.setNama(request.getNama());
        var created = pasienService.create(user);
        return created;
    }
    @PostMapping(value="/topUp")
    public PasienModel topupPasien(@RequestBody JwtSignUpRequest request) {
        PasienModel user = pasienRestService.getPasienByUsername(request.getUsername());
        user.setSaldo(request.getSaldo());
        PasienModel created = pasienService.create(user);
        return created;
    }

    @GetMapping(value="/profile/pasien")
    @ResponseBody
    public PasienModel retrievePasien(Authentication authentication){
        try{
            return pasienRestService.getPasienByUsername(authentication.getName());
        }catch(NoSuchElementException e){
            throw new ResponseStatusException(HttpStatus.NOT_FOUND,"Pasien dengan "+ authentication.getName() +" not found");
        }
    }

    @PutMapping(value="/topUp")
    public PasienModel topupPasien(@RequestBody PasienModel pasien1,Authentication authentication){
        try {
            PasienModel pasien = pasienRestService.getPasienByUsername(authentication.getName());
            pasien.setSaldo(pasien.getSaldo() + pasien1.getSaldo());
            return pasienService.create(pasien);
        } catch(NoSuchElementException e){
            throw new ResponseStatusException(HttpStatus.NOT_FOUND,"Pasien dengan "+ authentication.getName() +" not found");
        }
    }

}
