package TA_B_SYN_65.rumahSehat.controller;

import TA_B_SYN_65.rumahSehat.model.ApotekerModel;
import TA_B_SYN_65.rumahSehat.model.DokterModel;
import TA_B_SYN_65.rumahSehat.model.PasienModel;
import TA_B_SYN_65.rumahSehat.model.UserModel;
import TA_B_SYN_65.rumahSehat.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.User;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

@CrossOrigin
@Controller
@RequestMapping("/user")
public class UserController {
    @Autowired
    private UserService userService;


    @GetMapping("/listAllUsers")
    public String listAllUser(Model model) {
        List<UserModel> listUser = userService.getListUser();

        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        User user = (User) authentication.getPrincipal();


        String username = user.getUsername();
        UserModel userModel = userService.getUserByUsername(username);

        model.addAttribute("listUser", listUser);
        model.addAttribute("user", userModel);
        return "auth/viewall-user";
    }

    @GetMapping("/manajemenUser")
    public String viewAllUser(Model model) {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        User authenticationUser = (User) authentication.getPrincipal();

        String authenticationUsername = authenticationUser.getUsername();
        UserModel userModel = userService.getUserByUsername(authenticationUsername);
        if(userModel.getRole().equals("ADMIN")){
            List<String> role = new ArrayList<>();
            role.add("ADMIN");
            role.add("APOTEKER");
            role.add("DOKTER");
            model.addAttribute("listRole", role);
            return "auth/manajemen-user";
        } else{
            return "auth/access-denied";
        }
    }

    @GetMapping(value = "/add/{userRole}")
    private String addUserFormPage(@PathVariable String userRole, Model model){
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        User authenticationUser = (User) authentication.getPrincipal();


        String authenticationUsername = authenticationUser.getUsername();
        UserModel userModel = userService.getUserByUsername(authenticationUsername);
        UserModel user = new UserModel();

        if(userModel.getRole().equals("ADMIN")){

            if(userRole.equals("DOKTER") || userRole.equals("APOTEKER")){
                List<String> listRole = new ArrayList<>();
                listRole.add("ADMIN");
                listRole.add("APOTEKER");
                listRole.add("DOKTER");
                model.addAttribute("userRole", userRole);
                model.addAttribute("user", user);
                model.addAttribute("listRole", listRole);
                return "auth/form-add-user";
            } else{
                return "auth/access-denied";
            }
        } else{
            return "auth/access-denied";
        }
    }

    @PostMapping(value="/add")
    private String addUserSubmit(@RequestParam String role, @ModelAttribute UserModel user, Model model, Integer tarifDokter){
        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        User authenticationUser = (User) auth.getPrincipal();

        String authenticationUsername = authenticationUser.getUsername();
        UserModel userModel = userService.getUserByUsername(authenticationUsername);

        if(userModel.getRole().equals("ADMIN")){
            if(role.equals("DOKTER")){
                user.setRole("DOKTER");
                userService.addUser(user, tarifDokter);
                model.addAttribute("namaUser", user.getUsername());
                return "auth/add-user";

            } else if (role.equals("APOTEKER")){
                user.setRole("APOTEKER");
                userService.addUser(user, tarifDokter);
                model.addAttribute("namaUser", user.getUsername());
                return "auth/add-user";

            }
            else{
                return "auth/access-denied";
            }
        }
        else{
            return "auth/access-denied";
        }
    }

    @GetMapping(value = "/view/{userRole}")
    public String getUser(@PathVariable String userRole, Model model){
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        User authenticationUser = (User) authentication.getPrincipal();
        String authenticationUsername = authenticationUser.getUsername();
        UserModel userModel = userService.getUserByUsername(authenticationUsername);

        if(userModel.getRole().equals("ADMIN")) {
            if(userRole.equals("PASIEN")){
                List<PasienModel> listPasien = userService.getListPasien();
                model.addAttribute("userRole", userRole);
                model.addAttribute("listUser", listPasien);
                return "auth/viewall-user";

            } else if (userRole.equals("DOKTER")){
                List<DokterModel> listDokter = userService.getListDokter();
                model.addAttribute("userRole", userRole);
                model.addAttribute("listUser", listDokter);
                return "auth/viewall-user";

            } else if (userRole.equals("APOTEKER")){
                List<ApotekerModel> listApoteker = userService.getListApoteker();
                model.addAttribute("userRole", userRole);
                model.addAttribute("listUser", listApoteker);
                return "auth/viewall-user";

            } else{
                return "auth/access-denied";
            }
        } else{
            return "auth/access-denied";
        }
    }

    @GetMapping("/delete/{username}")
    public String deleteUser (@PathVariable String username, Model model) {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        User authUser = (User) authentication.getPrincipal();


        String authUsername = authUser.getUsername();
        UserModel userModel = userService.getUserByUsername(authUsername);


        if(userModel.getRole().equals("ADMIN")){
            if(userModel.getUsername().equals(username)){
                return "auth/failed-delete-user";


            } else{
                UserModel user = userService.getUserByUsername(username);
                userService.deleteUser(user);
                model.addAttribute( "username",user.getUsername());
                return "auth/delete-user";
            }
        }
        else {
            return "auth/access-denied";
        }

    }
}
