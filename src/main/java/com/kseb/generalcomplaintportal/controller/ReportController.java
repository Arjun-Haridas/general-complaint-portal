package com.kseb.generalcomplaintportal.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

@Controller
public class ReportController {

    @GetMapping("/view-report-list/{headerName}")
    public String viewReportList(Model model, @PathVariable("headerName") String pageName){
        model.addAttribute("headerName", pageName);
        return "report-list";
    }

    @GetMapping("/view-mdm-report-list")
    public String viewMDMReportList(Model model){
        return "mdm-report-list";
    }
}
