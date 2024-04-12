package com.kseb.generalcomplaintportal.controller;

import com.kseb.generalcomplaintportal.model.Designation;
import com.kseb.generalcomplaintportal.model.Staff;
import com.kseb.generalcomplaintportal.repository.DesignationRepository;
import com.kseb.generalcomplaintportal.repository.StaffRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import java.util.List;
import java.util.Optional;

@Controller
public class StaffController {

    @Autowired
    private StaffRepository staffRepository;
    @Autowired
    private DesignationRepository designationRepository;

    @GetMapping("/")
    private String redirectToHome() {
        return "redirect:/home";
    }

    @GetMapping("/home")
    public String showLoginForm(Model model) {
        List<Designation> designations = designationRepository.findAll();
        model.addAttribute("designations", designations);
        model.addAttribute("staff", new Staff());
        return "staff-login";
    }

    @PostMapping("/login")
    public  String loginStaff(@ModelAttribute Staff staff, RedirectAttributes redirectAttributes) {
        Staff staffFrmDB = staffRepository.findByUsername(staff.getUsername());
        String pwdFrmDB = (staffFrmDB != null) ? staffFrmDB.getPassword() : "";
        int desigFrmDB = (staffFrmDB != null) ? staffFrmDB.getDesign_id() : 0;
        if (staffFrmDB != null && pwdFrmDB.equals(staff.getPassword()) && desigFrmDB == staff.getDesign_id()) {
            if (desigFrmDB == 1 ) {
                redirectAttributes.addFlashAttribute("loginSuccess", "Admin logged in successfully");
                return "redirect:/admin-login/" + desigFrmDB;
            } else if (desigFrmDB == 2) {
                redirectAttributes.addFlashAttribute("loginSuccess", "Lineman logged in successfully");
                return "redirect:/lineman-login/" + desigFrmDB + "/" + staffFrmDB.getStaff_id();
            } else if (desigFrmDB == 3) {
                redirectAttributes.addFlashAttribute("loginSuccess", "Engineer logged in successfully");
                return "redirect:/executive-engineer-login/" + desigFrmDB;
            }else if (desigFrmDB == 4) {
                redirectAttributes.addFlashAttribute("loginSuccess", "Manager logged in successfully");
                return "redirect:/mdm-login/" + desigFrmDB;
            } else {
                redirectAttributes.addFlashAttribute("loginError", "Please select exact previlage");
            }
        } else {
            redirectAttributes.addFlashAttribute("loginError", "Please enter valid credentials");
            return "redirect:/home";
        }
        return "redirect:/home";

    }

    @GetMapping("/admin-login/{desigFrmDB}")
    public String showAdminLogin(@PathVariable("desigFrmDB") int designId, Model model) {
        return "admin-login";
    }

    @GetMapping("/lineman-login/{desigFrmDB}/{staffId}")
    public String showLinemanLogin(@PathVariable("desigFrmDB") int designId, @PathVariable("staffId") int staffId, Model model) {
        Optional<Staff> staffFrmDB = staffRepository.findById(staffId);
        Staff staffDtl = staffFrmDB.get();
        model.addAttribute("staffId", staffDtl.getStaff_id());
        model.addAttribute("staffName", staffDtl.getFirstName() + " " + staffDtl.getLastName());
        return "lineman-login";
    }

    @GetMapping("/executive-engineer-login/{desigFrmDB}")
    public String showEngineerLogin(@PathVariable("desigFrmDB") int designId, Model model) {
        return "executive-engineer-login";
    }

    @GetMapping("/mdm-login/{desigFrmDB}")
    public String showMdmLogin(@PathVariable("desigFrmDB") int designId, Model model) {
        return "mdm-login";
    }

    @GetMapping("/signup")
    public String showRegistrationForm(Model model) {
        List<Designation> designations = designationRepository.findAll();
        model.addAttribute("designations", designations);
        model.addAttribute("staff", new Staff());

        return "staff-registration";
    }

    @PostMapping("/register")
    public  String registerStaff(Staff staff, Model model, RedirectAttributes redirectAttributes) {
        staffRepository.save(staff);
        redirectAttributes.addFlashAttribute("RegistrationSuccess", "Registered Successfully");
        return "redirect:/home";
    }

    @GetMapping("/success")
    public String registrationSuccess() {
        return "success";
    }

    @GetMapping("/logout")
    public String logout() {
        return "redirect:/home";
    }
}
