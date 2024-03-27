package com.kseb.generalcomplaintportal.controller;

import com.kseb.generalcomplaintportal.dto.MaterialRequestForm;
import com.kseb.generalcomplaintportal.dto.WorkAllocationForm;
import com.kseb.generalcomplaintportal.model.Complaint;
import com.kseb.generalcomplaintportal.model.ComplaintStatus;
import com.kseb.generalcomplaintportal.model.MaterialRequest;
import com.kseb.generalcomplaintportal.model.WorkAllocation;
import com.kseb.generalcomplaintportal.repository.ComplaintRepository;
import com.kseb.generalcomplaintportal.repository.ComplaintStatusRepository;
import com.kseb.generalcomplaintportal.repository.StaffRepository;
import com.kseb.generalcomplaintportal.repository.WorkAllocationRepository;
import jakarta.persistence.Tuple;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;

import java.util.ArrayList;
import java.util.List;

@Controller
public class WorkAllocationController {

    @Autowired
    private WorkAllocationRepository workAllocationRepository;

    @Autowired
    private ComplaintRepository complaintRepository;

    @Autowired
    private ComplaintStatusRepository complaintStatusRepository;

    @Autowired
    private StaffRepository staffRepository;

    @GetMapping("/allocation")
    public String showWorkAllocation(Model model) {
        List<WorkAllocation> workAllocations = workAllocationRepository.findAll();
        for (WorkAllocation wa : workAllocations) {
            wa.setComplaint(complaintRepository.getComplaintFrmComplaintId(wa.getComplaint_id()));
            wa.setStaffName(staffRepository.getStaffNameFrmId(wa.getStaff_id()));
        }
        List<Tuple> complaintDetails = workAllocationRepository.getComplaintDetails();
        List<Tuple> linemanDetails = workAllocationRepository.getLinemanDetails();

        model.addAttribute("workAllocations", workAllocations);
        model.addAttribute("workAllocation", new WorkAllocation());
        model.addAttribute("complaintDetails", complaintDetails);
        model.addAttribute("linemanDetails", linemanDetails);
        return "work-allocation";
    }

    @PostMapping("/workallocationsubmit")
    public String submitWorkAllocation(WorkAllocation workAllocation, Model model){
        workAllocationRepository.save(workAllocation);
        return "redirect:/allocation";
    }

    @GetMapping("/workAllocation/edit/{id}")
    public String editAllocationForm(Model model, @PathVariable("id") Integer id){
        WorkAllocation workAllocation = workAllocationRepository.findById(id)
                .orElseThrow(() -> new IllegalArgumentException("Invalid workAllocation Id:" + id));
        List<WorkAllocation> workAllocations = workAllocationRepository.findAll();
        List<Tuple> complaintDetails = workAllocationRepository.getComplaintDetails();
        List<Tuple> linemanDetails = workAllocationRepository.getLinemanDetails();
        model.addAttribute("workAllocations", workAllocations);
        model.addAttribute("workAllocation", workAllocation);
        model.addAttribute("complaintDetails", complaintDetails);
        model.addAttribute("linemanDetails", linemanDetails);
        return "work-allocation";
    }

    @GetMapping("/lineman-work-allocation")
    public String showLinemanWorkAllocation(Model model) {
        List<WorkAllocationForm> workAllocationForms = getWorkAllocationForms();
        model.addAttribute("workAllocationForms", workAllocationForms);
        return "lineman-work-allocation";
    }

    private List<WorkAllocationForm> getWorkAllocationForms() {
        List<WorkAllocationForm> workAllocationForms = new ArrayList<>();
        List<WorkAllocation> workAllocations = workAllocationRepository.findAll();
        for (WorkAllocation workAllocation:workAllocations) {
            WorkAllocationForm workAllocationForm = new WorkAllocationForm();
            workAllocationForm.setWork_alloc_id(workAllocation.getWork_alloc_id());
            workAllocationForm.setComplaint_id(workAllocation.getComplaint_id());

            Complaint complaint = complaintRepository.getComplaintDetails(workAllocation.getComplaint_id());
            ComplaintStatus complaintStatus = complaintStatusRepository.getComplaintStatusDetails(workAllocation.getComplaint_id());
            String consumer_name = complaintRepository.getConsumerNameByKSEBID(complaint.getConsumer_kseb_id());

            workAllocationForm.setComplaint_date(complaint.getComplaint_date());
            workAllocationForm.setConsumer_name(consumer_name);
            workAllocationForm.setConsumer_phone(complaint.getComplaint_phone_no());
            workAllocationForm.setComplaint_location(complaint.getComplaint_location());
            workAllocationForm.setComplaint_post_no(complaint.getComplaint_post_no());
            workAllocationForm.setComplaint_details(complaint.getComplaint_details());
            workAllocationForm.setComplaint_status_updated_date(complaintStatus.getComplaint_status_updated_date());
            workAllocationForm.setComplaint_status(complaintStatus.getComplaint_status());

            workAllocationForms.add(workAllocationForm);
        }
        return workAllocationForms;
    }

    @GetMapping("/view-work-report")
    public String viewWorkReport(Model model) {
        List<WorkAllocationForm> workAllocationForms = getWorkAllocationForms();
        model.addAttribute("workAllocationForms", workAllocationForms);
        return "work-report";
    }

}

