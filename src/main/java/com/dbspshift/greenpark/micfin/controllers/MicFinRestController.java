package com.dbspshift.greenpark.micfin.controllers;

import com.dbspshift.greenpark.micfin.beans.MFI;
import com.dbspshift.greenpark.micfin.beans.MicroEntrepreneur;
import com.dbspshift.greenpark.micfin.beans.User;
import com.dbspshift.greenpark.micfin.services.CustomUserDetailsService;
import com.dbspshift.greenpark.micfin.services.MFIService;
import com.dbspshift.greenpark.micfin.services.MicroEntrepreneurService;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

import javax.validation.Valid;
import java.util.List;

/**
 * Created by gayathrig on 15/07/2019.
 */

@Controller
@RequestMapping("/micfin/api/")
//@CrossOrigin(origins = "http://localhost:4200")
public class MicFinRestController {

    private final Logger log = LogManager.getLogger(MicFinRestController.class);

    private MFIService mfiService;
    private MicroEntrepreneurService microEntrepreneurService;

    @Autowired
    public MicFinRestController(MFIService mfiService, MicroEntrepreneurService microEntrepreneurService) {
        this.mfiService = mfiService;
        this.microEntrepreneurService = microEntrepreneurService;
    }

    @RequestMapping(method = RequestMethod.GET, path = "/")
    public @ResponseBody String NoRequest(){
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        return "Welcome to DBS MicFin " + authentication.getName();
    }

    //Register and MFI.
    @RequestMapping(method = RequestMethod.POST, path = "/mfi")
    public @ResponseBody MFI registerMicroEntrepreneur(@RequestBody MFI mfi) throws Exception {
        log.debug("Request received in register MFI" + mfi);
        return (mfiService.registerMFI(mfi));
    }

    //@BankGet all the MFI details
    @RequestMapping(method = RequestMethod.GET, path= "/mfis")
    public @ResponseBody List<MFI> getAllMFIs() throws Exception{
        log.debug("Request received in getAllMFIs");
        return mfiService.getAllMFIs();
    }

    //@Bank 1. This api is for the Bank to get details about the MFI.
    //@MFI 2. For the MFI to get their own details
    @RequestMapping(method = RequestMethod.GET, path = "/mfi/{id}")
    public @ResponseBody String findMFIById(@PathVariable String id) throws Exception {
        log.debug("Request received in getMFIById for " + id);
        return String.valueOf((mfiService.getMFIById(id)));
    }

    //@Bank 1. This api is for the Bank to update details about the MFI.
    //@MFI 2. For the MFI to update their own details
    @RequestMapping(method = RequestMethod.PUT, path = "/mfi")
    public @ResponseBody MFI updateMFI(@RequestBody MFI mfi) throws Exception {
        log.debug("Request received in updateMFI for ");
        return mfiService.updateMFI(mfi);
    }

    @RequestMapping(method = RequestMethod.DELETE, path = "/mfi/{id}")
    public @ResponseBody String deleteMFIById(@PathVariable String id) throws Exception{
        log.debug("Request received to delete MFI for " + id);
        return mfiService.deleteMFI(id);
    }

    //-------------------------------------------MICRO ENTREPRENEURS CALLS--------------------------------------------------
    @RequestMapping(method = RequestMethod.GET, path= "/mfi/{id}/micro-entrepreneurs")
    public @ResponseBody List<MicroEntrepreneur> getAllMicroEntrepreneurs(@PathVariable String id) throws Exception{
        log.debug("Request received in getAllMicroEntrepreneurs");
        return microEntrepreneurService.getAllMicroEntrepreneursByMFIId(id);
    }

    //Register and MFI.
    @RequestMapping(method = RequestMethod.POST, path = "/mfi/{id}/micro-entrepreneur")
    public @ResponseBody MicroEntrepreneur registerMicroEntrepreneur(@RequestBody MicroEntrepreneur microEntrepreneur) throws Exception {
        log.debug("Request received in register micro entrepreneur" + microEntrepreneur);
        return (microEntrepreneurService.registerMicroEntrepreneur(microEntrepreneur));
    }

    //Get a particular micro entrepreneur.
    @RequestMapping(method = RequestMethod.GET, path= "/mfi/micro-entrepreneurs/{microEntId}")
    public @ResponseBody MicroEntrepreneur getMicroEntrepreneur(@PathVariable String microEntId) throws Exception{
        log.debug("Request received in getMicroEntrepreneur" + microEntId);
        return (microEntrepreneurService.getMicroEntrepreneurById(microEntId));
    }

    @RequestMapping(method = RequestMethod.PUT, path = "/mfi/micro-entrepreneurs/{microEntId")
    public @ResponseBody MicroEntrepreneur updateMicroEntrepreneur(@RequestBody MicroEntrepreneur microEntrepreneur) throws Exception{
        log.debug("Request received in updateMicroEntrepreneur" + microEntrepreneur);
        return (microEntrepreneurService.updateMicroEntrepreneur(microEntrepreneur));
    }


    //------------------------------------------LOGIN STUFF----------------------------------------------------------------------------------
    @Autowired
    private CustomUserDetailsService userService;

/*    @RequestMapping(value = "/login", method = RequestMethod.GET)
    public @ResponseBody String login() {
        log.info("In Login Contoller, Login");
        //ModelAndView modelAndView = new ModelAndView();
        //modelAndView.setViewName("login");
        return "Welcome to LOGIN";
    }*/

    /*@RequestMapping(value = "/signup", method = RequestMethod.GET)
    public ModelAndView signup() {
        log.info("In Login Contoller, SignUp");
        ModelAndView modelAndView = new ModelAndView();
        User user = new User();
        modelAndView.addObject("user", user);
        modelAndView.setViewName("signup");
        return modelAndView;
    }*/

    @RequestMapping(value = "/signup", method = RequestMethod.POST)
    public @ResponseBody String createNewUser(@Valid User user, BindingResult bindingResult) {
        //ModelAndView modelAndView = new ModelAndView();
        User userExists = userService.findUserByEmail(user.getEmail());
        if (userExists != null) {
            bindingResult
                    .rejectValue("email", "error.user",
                            "There is already a user registered with the username provided");
        }
        if (bindingResult.hasErrors()) {
            //modelAndView.setViewName("signup");
        } else {
            userService.saveUser(user);
            //modelAndView.addObject("successMessage", "User has been registered successfully");
            //modelAndView.addObject("user", new User());
            //modelAndView.setViewName("login");

        }
        return bindingResult.hasErrors()?"Failed":"Success";
    }

    /*@RequestMapping(value = "/dashboard", method = RequestMethod.GET)
    public ModelAndView dashboard() {
        ModelAndView modelAndView = new ModelAndView();
        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        User user = userService.findUserByEmail(auth.getName());
        modelAndView.addObject("currentUser", user);
        modelAndView.addObject("fullName", "Welcome " + user.getFullName());
        modelAndView.addObject("adminMessage", "Content Available Only for Users with Admin Role");
        modelAndView.setViewName("dashboard");
        return modelAndView;
    }*/

    /*@RequestMapping(value = {"/","/home"}, method = RequestMethod.GET)
    public ModelAndView home() {
        ModelAndView modelAndView = new ModelAndView();
        modelAndView.setViewName("home");
        return modelAndView;
    }*/

}
