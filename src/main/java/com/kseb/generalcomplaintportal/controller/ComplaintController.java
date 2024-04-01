package com.kseb.generalcomplaintportal.controller;

import com.kseb.generalcomplaintportal.dto.ComplaintRegistrationForm;
import com.kseb.generalcomplaintportal.model.Complaint;
import com.kseb.generalcomplaintportal.model.ComplaintStatus;
import com.kseb.generalcomplaintportal.model.Staff;
import com.kseb.generalcomplaintportal.repository.*;
import com.kseb.generalcomplaintportal.service.StatusService;
import com.kseb.generalcomplaintportal.service.enums.Status;
import jakarta.persistence.Tuple;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Controller
@RequestMapping("/complaint")
public class ComplaintController {

    @Autowired
    private ComplaintRepository complaintRepository;

    @Autowired
    private ComplaintStatusRepository complaintStatusRepository;

    @Autowired
    private StatusService statusService;

    @Autowired
    private WorkAllocationRepository workAllocationRepository;

    @Autowired
    private MaterialRequestRepository materialRequestRepository;

    @Autowired
    private MaterialIssueRepository materialIssueRepository;

    @Autowired
    private MaterialRequestStatusRepository materialRequestStatusRepository;

    @Autowired
    private PaymentRepository paymentRepository;

    @Autowired
    private StaffRepository staffRepository;

    @GetMapping("/registration/{desigFrmDB}")
    public String showComplaintRegistration(@PathVariable("desigFrmDB") int designId, Model model) {
        ComplaintRegistrationForm complaintRegistrationForm = new ComplaintRegistrationForm();

        List<ComplaintRegistrationForm> complaintRegistrations = getComplaintRegistrationForms();
        List<Tuple> consumerDetails = complaintRepository.getConsumerDetails();

        complaintRegistrationForm.setComplaint_status_updated_by(designId);
        model.addAttribute("complaint", complaintRegistrationForm);
        model.addAttribute("complaints", complaintRegistrations);
        model.addAttribute("consumerDetails", consumerDetails);
        return "complaint-registration";
    }

    private List<ComplaintRegistrationForm> getComplaintRegistrationForms() {
        List<Complaint> complaints = complaintRepository.findAll();
        List<ComplaintRegistrationForm> complaintRegistrations = new ArrayList<>();
        for (Complaint complaint: complaints) {
            ComplaintRegistrationForm derivedRegistrationForm = new ComplaintRegistrationForm();

            String consumer_name = complaintRepository.getConsumerNameByKSEBID(complaint.getConsumer_kseb_id());
            ComplaintStatus complaintStatus = complaintStatusRepository.getComplaintStatusDetails(complaint.getComplaint_id());

            derivedRegistrationForm.setComplaint_id(complaint.getComplaint_id());
            derivedRegistrationForm.setComplaint_date(complaint.getComplaint_date());
            derivedRegistrationForm.setConsumer_name(consumer_name);
            derivedRegistrationForm.setConsumer_kseb_id(complaint.getConsumer_kseb_id());
            derivedRegistrationForm.setComplaint_phone_no(complaint.getComplaint_phone_no());
            derivedRegistrationForm.setComplaint_location(complaint.getComplaint_location());
            derivedRegistrationForm.setComplaint_post_no(complaint.getComplaint_post_no());
            derivedRegistrationForm.setComplaint_details(complaint.getComplaint_details());
            derivedRegistrationForm.setComplaint_status_updated_date(complaintStatus.getComplaint_status_updated_date());
            derivedRegistrationForm.setComplaint_status(complaintStatus.getComplaint_status());
            derivedRegistrationForm.setComplaint_status_details(complaintStatus.getComplaint_status_details());

            complaintRegistrations.add(derivedRegistrationForm);
        }
        return complaintRegistrations;
    }

    @PostMapping("/submit")
    public  String submitComplaint(@ModelAttribute ComplaintRegistrationForm form, Model model) {
        Complaint complaint;
        ComplaintStatus complaintStatus;

        if (form.getComplaint_id() != 0) {
            complaint = complaintRepository.findById((long) form.getComplaint_id()).orElse(new Complaint());
            complaintStatus = complaintStatusRepository.getComplaintStatusDetails(form.getComplaint_id());
        } else {
            complaint = new Complaint();
            complaintStatus = new ComplaintStatus();
        }

        complaint.setConsumer_kseb_id(form.getConsumer_kseb_id());
        complaint.setComplaint_phone_no(form.getComplaint_phone_no());
        complaint.setComplaint_location(form.getComplaint_location());
        complaint.setComplaint_post_no(form.getComplaint_post_no());
        complaint.setComplaint_details(form.getComplaint_details());
        complaint.setComplaint_date(form.getComplaint_date());
        Complaint savedComplaint = complaintRepository.save(complaint);

        complaintStatus.setComplaint_id(savedComplaint.getComplaint_id());
        complaintStatus.setComplaint_status(statusService.getStatusName(Status.PENDING));
        complaintStatus.setComplaint_status_details(statusService.getStatusDescription(Status.PENDING));
        complaintStatus.setComplaint_status_updated_date(form.getComplaint_date());
        int designationId = form.getComplaint_status_updated_by();
        complaintStatus.setComplaint_status_updated_by(designationId);

        complaintStatusRepository.save(complaintStatus);
        return "redirect:/complaint/registration/" + designationId;
    }

    @GetMapping("/delete/{id}")
    public String deleteComplaints(@PathVariable Long id) {
        int desigId = complaintStatusRepository.getDesignationIdFrmComplaint(Math.toIntExact(id));
        Integer workAllocationId = workAllocationRepository.getWorkAllocationIdFrmComplaint(Math.toIntExact(id));

        if (workAllocationId != null) {
            Integer materialReqId = materialRequestRepository.getMaterialRequestIdFrmWorkAllocation(workAllocationId);
            if (materialReqId != null) {
                materialIssueRepository.deleteByMaterialIssueId(materialReqId);
                materialRequestStatusRepository.deleteByMaterialRequestStatusId(materialReqId);
            }
            materialRequestRepository.deleteByMaterialRequestId(workAllocationId);
            paymentRepository.deleteByPaymentBillId(workAllocationId);
        }
        workAllocationRepository.deleteByWorkAllocationsId(Math.toIntExact(id));

        complaintStatusRepository.deleteByComplaintId(Math.toIntExact(id));
        complaintRepository.deleteById(id);

        return "redirect:/complaint/registration/" + desigId;
    }

    @GetMapping("/edit/{id}")
    public String editForm(Model model, @PathVariable("id") Long id) {
        ComplaintRegistrationForm complaintRegistrationForm = new ComplaintRegistrationForm();
        int desigId = complaintStatusRepository.getDesignationIdFrmComplaint(Math.toIntExact(id));
        Complaint complaint = complaintRepository.findById(id)
                .orElseThrow(() -> new IllegalArgumentException("Invalid complaint Id:" + id));
        List<ComplaintRegistrationForm> complaintRegistrations = getComplaintRegistrationForms();

        complaintRegistrationForm.setComplaint_id(complaint.getComplaint_id());
        complaintRegistrationForm.setComplaint_date(complaint.getComplaint_date());
        complaintRegistrationForm.setConsumer_kseb_id(complaint.getConsumer_kseb_id());
        complaintRegistrationForm.setComplaint_phone_no(complaint.getComplaint_phone_no());
        complaintRegistrationForm.setComplaint_location(complaint.getComplaint_location());
        complaintRegistrationForm.setComplaint_post_no(complaint.getComplaint_post_no());
        complaintRegistrationForm.setComplaint_details(complaint.getComplaint_details());

        complaintRegistrationForm.setComplaint_status_updated_by(desigId);
        model.addAttribute("complaint", complaintRegistrationForm);
        model.addAttribute("complaints", complaintRegistrations);

        List<Tuple> consumerDetails = complaintRepository.getConsumerDetails();
        model.addAttribute("consumerDetails", consumerDetails);

        return "complaint-registration";
    }

    @GetMapping("/edit/status/{id}")
    public String editComplaintStatus(Model model, @PathVariable("id") Integer complaintId) {
        ComplaintStatus complaintStatus = complaintStatusRepository.getComplaintStatusDetails(complaintId);
        int allocationId = workAllocationRepository.getWorkAllocIdFrmComplaintId(complaintId);
        int staffId = workAllocationRepository.getStaffIdFrmWorkAllocationId(allocationId);

        model.addAttribute("complaintStatus", complaintStatus);
        model.addAttribute("allocationId", allocationId);
        model.addAttribute("statuses", Status.values());
        model.addAttribute("staffId", staffId);
        return "status-updation";
    }

    @PostMapping("/complaintStatus/submit")
    public  String submitComplaintStatus(@ModelAttribute ComplaintStatus complaintStatus, @RequestParam("staffId") String staffId, Model model) {
        ComplaintStatus complaintStatusDtls = complaintStatusRepository.getComplaintStatusDetails(complaintStatus.getComplaint_id());
        complaintStatusDtls.setComplaint_status(complaintStatus.getSelectedStatus());
        complaintStatusDtls.setComplaint_status_details(complaintStatus.getSelectedStatus());
        complaintStatusRepository.save(complaintStatusDtls);
        return "redirect:/lineman-work-allocation/" + staffId;
    }

    @GetMapping("/complaint-report-view/{headerName}")
    public String showComplaintReport(Model model, @PathVariable("headerName") String pageName) {
        List<ComplaintRegistrationForm> complaintRegistrations = getComplaintRegistrationForms();

        model.addAttribute("complaints", complaintRegistrations);
        model.addAttribute("headerName", pageName);
        return "complaint-report";
    }

    @GetMapping("/complaint-report-view/{headerName}/{staffId}")
    public String showComplaintReportByStaff(Model model, @PathVariable("headerName") String pageName, @PathVariable("staffId") int staffId) {
        List<ComplaintRegistrationForm> complaintRegistrations = getComplaintRegistrationForms();

        Optional<Staff> staffFrmDB = staffRepository.findById(staffId);
        Staff staffDtl = staffFrmDB.get();
        model.addAttribute("staffId", staffDtl.getStaff_id());

        model.addAttribute("complaints", complaintRegistrations);
        model.addAttribute("headerName", pageName);
        return "complaint-report";
    }
}
