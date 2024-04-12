package com.kseb.generalcomplaintportal.controller;

import com.kseb.generalcomplaintportal.dto.MaterialRequestForm;
import com.kseb.generalcomplaintportal.model.*;
import com.kseb.generalcomplaintportal.repository.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Controller
public class MaterialApprovalController {

    @Autowired
    private MaterialRequestRepository materialRequestRepository;

    @Autowired
    private MaterialRequestStatusRepository materialRequestStatusRepository;

    @Autowired
    private WorkAllocationRepository workAllocationRepository;

    @Autowired
    private ComplaintRepository complaintRepository;

    @Autowired
    private ComplaintStatusRepository complaintStatusRepository;

    @Autowired
    private MaterialIssueRepository materialIssueRepository;

    @Autowired
    private StaffRepository staffRepository;

    @GetMapping("/engineer-material-request-details")
    public String showEngineerMaterialRequest(Model model) {
        List<MaterialRequestForm> materialRequestForms = getMaterialRequestForms();
        model.addAttribute("materialRequests", materialRequestForms);
        return "engineer-material-request-details";
    }

    @GetMapping("/manager-material-request-details")
    public String showManagerMaterialRequest(Model model) {
        List<MaterialRequestForm> materialRequestForms = getMaterialRequestForms();
        model.addAttribute("materialRequests", materialRequestForms);
        return "manager-material-request-details";
    }

    private List<MaterialRequestForm> getMaterialRequestForms() {
        List<MaterialRequestForm> materialRequestForms = new ArrayList<>();
        List<MaterialRequest> materialRequests = materialRequestRepository.findAll();
        for (MaterialRequest materialRequest : materialRequests) {
            MaterialRequestForm materialRequestForm = new MaterialRequestForm();
            MaterialRequestStatus materialRequestStatus = materialRequestStatusRepository.getMaterialStatusDetails(materialRequest.getMaterial_request_id());

            WorkAllocation workAllocation = workAllocationRepository.getWorkAllocationDetails(materialRequest.getWork_alloc_id());
            Complaint complaint = complaintRepository.getComplaintDetails(workAllocation.getComplaint_id());
            ComplaintStatus complaintStatus = complaintStatusRepository.getComplaintStatusDetails(workAllocation.getComplaint_id());
            String consumer_name = complaintRepository.getConsumerNameByKSEBID(complaint.getConsumer_kseb_id());

            materialRequestForm.setComplaint_id(complaint.getComplaint_id());
            materialRequestForm.setComplaint_date(complaint.getComplaint_date());
            materialRequestForm.setConsumer_name(consumer_name);
            materialRequestForm.setConsumer_phone(complaint.getComplaint_phone_no());
            materialRequestForm.setComplaint_location(complaint.getComplaint_location());
            materialRequestForm.setComplaint_post_no(complaint.getComplaint_post_no());
            materialRequestForm.setComplaint_details(complaint.getComplaint_details());

            materialRequestForm.setComplaint_status_updated_date(complaintStatus.getComplaint_status_updated_date());
            materialRequestForm.setComplaint_status(complaintStatus.getComplaint_status());

            materialRequestForm.setWork_alloc_id(workAllocation.getWork_alloc_id());
            materialRequestForm.setWork_alloc_date(workAllocation.getWork_alloc_date());

            materialRequestForm.setStaff_name(materialRequestStatus.getMaterial_request_status_updated_by());
            materialRequestForm.setMaterial_request_id(materialRequest.getMaterial_request_id());
            materialRequestForm.setMaterial_request_date(materialRequest.getMaterial_request_date());

            String materialItemName = materialRequestRepository.getDetailFrmMaterialItemId(materialRequest.getMaterialItemId());
            materialRequestForm.setMaterial_details(materialItemName);
            materialRequestForm.setMaterial_request_status(materialRequestStatus.getMaterial_request_status());

            boolean showApproval = (materialRequestRepository.showApproval(materialRequest.getMaterial_request_id()) == 0) ? false : true;
            boolean showIssue = (materialRequestRepository.showIssue(materialRequest.getMaterial_request_id()) == 0) ? false : true;
            boolean showEEApproval = (materialRequestRepository.showEEApproval(materialRequest.getMaterial_request_id()) == 0) ? false : true;

            materialRequestForm.setShow_approval(showApproval);
            materialRequestForm.setShow_issue(showIssue);
            materialRequestForm.setShow_ee_approval(showEEApproval);

            materialRequestForms.add(materialRequestForm);
        }

        return materialRequestForms;
    }


    private List<MaterialRequestForm> getMaterialRequestFormsByStaffId(int staffId) {
        List<MaterialRequestForm> materialRequestForms = new ArrayList<>();
        List<MaterialRequest> materialRequests = materialRequestRepository.getMaterialRequestsByStaffId(staffId);
        for (MaterialRequest materialRequest : materialRequests) {
            MaterialRequestForm materialRequestForm = new MaterialRequestForm();
            MaterialRequestStatus materialRequestStatus = materialRequestStatusRepository.getMaterialStatusDetails(materialRequest.getMaterial_request_id());

            WorkAllocation workAllocation = workAllocationRepository.getWorkAllocationDetails(materialRequest.getWork_alloc_id());
            Complaint complaint = complaintRepository.getComplaintDetails(workAllocation.getComplaint_id());
            ComplaintStatus complaintStatus = complaintStatusRepository.getComplaintStatusDetails(workAllocation.getComplaint_id());
            String consumer_name = complaintRepository.getConsumerNameByKSEBID(complaint.getConsumer_kseb_id());

            materialRequestForm.setComplaint_id(complaint.getComplaint_id());
            materialRequestForm.setComplaint_date(complaint.getComplaint_date());
            materialRequestForm.setConsumer_name(consumer_name);
            materialRequestForm.setConsumer_phone(complaint.getComplaint_phone_no());
            materialRequestForm.setComplaint_location(complaint.getComplaint_location());
            materialRequestForm.setComplaint_post_no(complaint.getComplaint_post_no());
            materialRequestForm.setComplaint_details(complaint.getComplaint_details());

            materialRequestForm.setComplaint_status_updated_date(complaintStatus.getComplaint_status_updated_date());
            materialRequestForm.setComplaint_status(complaintStatus.getComplaint_status());

            materialRequestForm.setWork_alloc_id(workAllocation.getWork_alloc_id());
            materialRequestForm.setWork_alloc_date(workAllocation.getWork_alloc_date());

            materialRequestForm.setStaff_name(materialRequestStatus.getMaterial_request_status_updated_by());
            materialRequestForm.setMaterial_request_id(materialRequest.getMaterial_request_id());
            materialRequestForm.setMaterial_request_date(materialRequest.getMaterial_request_date());

            String materialItemName = materialRequestRepository.getDetailFrmMaterialItemId(materialRequest.getMaterialItemId());
            materialRequestForm.setMaterial_details(materialItemName);
            materialRequestForm.setMaterial_request_status(materialRequestStatus.getMaterial_request_status());

            boolean showApproval = (materialRequestRepository.showApproval(materialRequest.getMaterial_request_id()) == 0) ? false : true;
            boolean showIssue = (materialRequestRepository.showIssue(materialRequest.getMaterial_request_id()) == 0) ? false : true;
            boolean showEEApproval = (materialRequestRepository.showEEApproval(materialRequest.getMaterial_request_id()) == 0) ? false : true;

            materialRequestForm.setShow_approval(showApproval);
            materialRequestForm.setShow_issue(showIssue);
            materialRequestForm.setShow_ee_approval(showEEApproval);

            materialRequestForms.add(materialRequestForm);
        }

        return materialRequestForms;
    }

    @GetMapping("/materialRequest/mdm/approve/{id}")
    public String approvalMDMMaterialRequest(Model model, @PathVariable("id") Integer material_request_id) {
        List<MaterialRequestForm> materialRequestForms = getMaterialRequestForms();
        model.addAttribute("materialRequests", materialRequestForms);

        Optional<MaterialRequestStatus> materialRequestStatus = materialRequestStatusRepository.findByMaterialRequestId(material_request_id);
        MaterialRequestStatus materialRequestStatus1 = materialRequestStatus.get();
        model.addAttribute("materialRequestStatus", materialRequestStatus1);
        return "mdm-approval";
    }

    /*@PostMapping("/MDMMaterialRequestsubmit")
    public String submitMDMMaterialRequest(MaterialRequestStatus materialRequestStatus, Model model) {
        materialRequestStatusRepository.save(materialRequestStatus);
        return "redirect:/mdm-approval";
    }*/

    @PostMapping("/material-request/mdm-approval")
    public  String submitMDMApproval(@ModelAttribute MaterialRequestStatus materialRequestStatus, Model model, RedirectAttributes redirectAttributes) {
        MaterialRequestStatus ms1 = materialRequestStatusRepository.getMaterialStatusDetails(materialRequestStatus.getMaterialRequestId());
        materialRequestStatus.setMaterial_request_status_id(ms1.getMaterial_request_status_id());
        materialRequestStatus.setMaterial_request_status("EE Approval Waiting");
        materialRequestStatus.setMaterial_request_status_updated_by("Manager");
        materialRequestStatus.setMaterial_request_status_details("Waiting for approval from Executive Engineer");
        materialRequestStatusRepository.save(materialRequestStatus);
        redirectAttributes.addFlashAttribute("materialApproveSuccess", "Material Request Approved Successfully");
        return "redirect:/manager-material-request-details";
    }

    @GetMapping("/materialRequest/ee/approve/{id}")
    public String approvalEEMaterialRequest(Model model, @PathVariable("id") Integer material_request_id) {
        List<MaterialRequestForm> materialRequestForms = getMaterialRequestForms();
        model.addAttribute("materialRequests", materialRequestForms);

        Optional<MaterialRequestStatus> materialRequestStatus = materialRequestStatusRepository.findByMaterialRequestId(material_request_id);
        MaterialRequestStatus materialRequestStatus1 = materialRequestStatus.get();
        model.addAttribute("materialRequestStatus", materialRequestStatus1);
        return "ee-approval";
    }

    @PostMapping("/material-request/ee-approval")
    public  String submitEEApproval(@ModelAttribute MaterialRequestStatus materialRequestStatus, Model model, RedirectAttributes redirectAttributes) {
        MaterialRequestStatus ms1 = materialRequestStatusRepository.getMaterialStatusDetails(materialRequestStatus.getMaterialRequestId());
        materialRequestStatus.setMaterial_request_status_id(ms1.getMaterial_request_status_id());
        materialRequestStatus.setMaterial_request_status("EE Approved");
        materialRequestStatus.setMaterial_request_status_updated_by("Engineer");
        materialRequestStatus.setMaterial_request_status_details("Executive Engineer Approved");
        materialRequestStatusRepository.save(materialRequestStatus);
        redirectAttributes.addFlashAttribute("materialReqApproveSuccess", "Material Request Approved Successfully");
        return "redirect:/engineer-material-request-details";
    }

    @GetMapping("/view-material-report/{headerName}")
    public String viewMaterialReport(Model model, @PathVariable("headerName") String pageName) {
        List<MaterialRequestForm> materialRequestForms = getMaterialRequestForms();

        for (MaterialRequestForm mr : materialRequestForms) {
            String issueStatus = materialIssueRepository.getIssueStatusFrmMaterialRequestId(mr.getMaterial_request_id());
            mr.setMaterial_issue_status(issueStatus);
        }

        model.addAttribute("materialRequests", materialRequestForms);
        model.addAttribute("headerName", pageName);
        return "material-report";
    }

    @GetMapping("/view-material-report/{headerName}/{staffId}")
    public String viewMaterialReportByStaff(Model model, @PathVariable("headerName") String pageName, @PathVariable("staffId") int staffId) {
        List<MaterialRequestForm> materialRequestForms = getMaterialRequestFormsByStaffId(staffId);

        for (MaterialRequestForm mr : materialRequestForms) {
            String issueStatus = materialIssueRepository.getIssueStatusFrmMaterialRequestId(mr.getMaterial_request_id());
            mr.setMaterial_issue_status(issueStatus);
        }

        Optional<Staff> staffFrmDB = staffRepository.findById(staffId);
        Staff staffDtl = staffFrmDB.get();
        model.addAttribute("staffId", staffDtl.getStaff_id());

        model.addAttribute("materialRequests", materialRequestForms);
        model.addAttribute("headerName", pageName);
        return "material-report";
    }

    @GetMapping("/view-material-request-report")
    public String viewMaterialRequestReport(Model model) {
        List<MaterialRequestForm> materialRequestForms = getMaterialRequestForms();
        model.addAttribute("materialRequests", materialRequestForms);
        return "material-request-report";
    }

}
