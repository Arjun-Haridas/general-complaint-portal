package com.kseb.generalcomplaintportal.controller;

import com.kseb.generalcomplaintportal.dto.MaterialRequestForm;
import com.kseb.generalcomplaintportal.dto.WorkAllocationForm;
import com.kseb.generalcomplaintportal.model.*;
import com.kseb.generalcomplaintportal.repository.*;
import jakarta.persistence.Tuple;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

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

    @Autowired
    private MaterialRequestRepository materialRequestRepository;

    @GetMapping("/allocation")
    public String showWorkAllocation(Model model) {
        List<WorkAllocation> workAllocations = workAllocationRepository.findAll();
        for (WorkAllocation wa : workAllocations) {
            wa.setComplaint(complaintRepository.getComplaintFrmComplaintId(wa.getComplaint_id()));
            wa.setStaffName(staffRepository.getStaffNameFrmId(wa.getStaff_id()));
            boolean showEdit = (workAllocationRepository.showWorkAllocEdit(wa.getComplaint_id()) == 0) ? false : true;
            wa.setAllowEdit(showEdit);
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
    public String submitWorkAllocation(WorkAllocation workAllocation, Model model, RedirectAttributes redirectAttributes){
        workAllocationRepository.save(workAllocation);
        redirectAttributes.addFlashAttribute("workAlocSuccess", "Work allocated successfully");
        return "redirect:/allocation";
    }

    @GetMapping("/workAllocation/edit/{id}")
    public String editAllocationForm(Model model, @PathVariable("id") Integer id){
        WorkAllocation workAllocation = workAllocationRepository.findById(id)
                .orElseThrow(() -> new IllegalArgumentException("Invalid workAllocation Id:" + id));
        List<WorkAllocation> workAllocations = workAllocationRepository.findAll();
        for (WorkAllocation wa : workAllocations) {
            wa.setComplaint(complaintRepository.getComplaintFrmComplaintId(wa.getComplaint_id()));
            wa.setStaffName(staffRepository.getStaffNameFrmId(wa.getStaff_id()));
            boolean showEdit = (workAllocationRepository.showWorkAllocEdit(wa.getComplaint_id()) == 0) ? false : true;
            wa.setAllowEdit(showEdit);
        }

        List<Tuple> complaintDetails = workAllocationRepository.getComplaintDetailsOnEdit(id);
        List<Tuple> linemanDetails = workAllocationRepository.getLinemanDetails();
        model.addAttribute("workAllocations", workAllocations);
        model.addAttribute("workAllocation", workAllocation);
        model.addAttribute("complaintDetails", complaintDetails);
        model.addAttribute("linemanDetails", linemanDetails);
        return "work-allocation";
    }

    @GetMapping("/lineman-work-allocation/{staffId}")
    public String showLinemanWorkAllocation(Model model, @PathVariable("staffId") Integer staffId) {
            List<WorkAllocationForm> workAllocationForms = getWorkAllocationFormsByStaff(staffId);

        Optional<Staff> staffFrmDB = staffRepository.findById(staffId);
        Staff staffDtl = staffFrmDB.get();
        model.addAttribute("staffId", staffDtl.getStaff_id());
        model.addAttribute("staffName", staffDtl.getFirstName() + " " + staffDtl.getLastName());

        model.addAttribute("workAllocationForms", workAllocationForms);

        return "lineman-work-allocation";
    }

    /*@GetMapping("/lineman-work-allocation")
    public String showLinemanWorkAllocation(Model model) {
        List<WorkAllocationForm> workAllocationForms = getWorkAllocationForms();
        model.addAttribute("workAllocationForms", workAllocationForms);
        return "lineman-work-allocation";
    }*/

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

            boolean allowStatusUpdate = (materialRequestRepository.allowLineManStatusUpdate(workAllocation.getWork_alloc_id()) == 0) ? false : true;
            workAllocationForm.setMaterialIssued(allowStatusUpdate);

            workAllocationForms.add(workAllocationForm);
        }
        return workAllocationForms;
    }

    private List<WorkAllocationForm> getWorkAllocationFormsByStaff(int staffId) {
        List<WorkAllocationForm> workAllocationForms = new ArrayList<>();
        List<WorkAllocation> workAllocations = workAllocationRepository.getWorkAllocDtlsOnStaffId(staffId);
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

            boolean allowStatusUpdate = (materialRequestRepository.allowLineManStatusUpdate(workAllocation.getWork_alloc_id()) == 0) ? false : true;
            workAllocationForm.setMaterialIssued(allowStatusUpdate);

            workAllocationForms.add(workAllocationForm);
        }
        return workAllocationForms;
    }

    @GetMapping("/view-work-report/{headerName}")
    public String viewWorkReport(Model model, @PathVariable("headerName") String pageName) {
        List<WorkAllocationForm> workAllocationForms = getWorkAllocationForms();
        model.addAttribute("workAllocationForms", workAllocationForms);
        model.addAttribute("headerName", pageName);
        return "work-report";
    }

    @GetMapping("/view-work-report/{headerName}/{staffId}")
    public String viewWorkReportByStaff(Model model, @PathVariable("headerName") String pageName, @PathVariable("staffId") int staffId) {
        List<WorkAllocationForm> workAllocationForms = getWorkAllocationFormsByStaff(staffId);

        Optional<Staff> staffFrmDB = staffRepository.findById(staffId);
        Staff staffDtl = staffFrmDB.get();
        model.addAttribute("staffId", staffDtl.getStaff_id());

        model.addAttribute("workAllocationForms", workAllocationForms);
        model.addAttribute("headerName", pageName);
        return "work-report";
    }

}

