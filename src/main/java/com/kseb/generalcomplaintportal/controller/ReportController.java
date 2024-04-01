package com.kseb.generalcomplaintportal.controller;

import com.kseb.generalcomplaintportal.model.Staff;
import com.kseb.generalcomplaintportal.repository.StaffRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

import java.util.Optional;

@Controller
public class ReportController {

    @Autowired
    private StaffRepository staffRepository;

    @GetMapping("/view-report-list/{headerName}")
    public String viewReportList(Model model, @PathVariable("headerName") String pageName){
        model.addAttribute("headerName", pageName);
        return "report-list";
    }

    @GetMapping("/view-report-list/{headerName}/{staffId}")
    public String viewReportList(Model model, @PathVariable("headerName") String pageName, @PathVariable("staffId") int staffId){

        Optional<Staff> staffFrmDB = staffRepository.findById(staffId);
        Staff staffDtl = staffFrmDB.get();
        model.addAttribute("staffId", staffDtl.getStaff_id());

        model.addAttribute("headerName", pageName);
        return "report-list";
    }

    @GetMapping("/view-mdm-report-list")
    public String viewMDMReportList(Model model){
        return "mdm-report-list";
    }
}
