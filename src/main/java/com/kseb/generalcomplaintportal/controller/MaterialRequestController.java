package com.kseb.generalcomplaintportal.controller;

import com.kseb.generalcomplaintportal.dto.MaterialRequestForm;
import com.kseb.generalcomplaintportal.model.MaterialIssue;
import com.kseb.generalcomplaintportal.model.MaterialRequest;
import com.kseb.generalcomplaintportal.model.MaterialRequestStatus;
import com.kseb.generalcomplaintportal.model.Staff;
import com.kseb.generalcomplaintportal.repository.*;
import jakarta.persistence.Tuple;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Controller
public class MaterialRequestController {

    @Autowired
    private MaterialRequestRepository materialRequestRepository;

    @Autowired
    private WorkAllocationRepository workAllocationRepository;

    @Autowired
    private MaterialRequestStatusRepository materialRequestStatusRepository;

    @Autowired
    private MaterialIssueRepository materialIssueRepository;

    @Autowired
    private StaffRepository staffRepository;

    @GetMapping("/material-request/{staffId}")
    public String showMaterialRequest(Model model, @PathVariable("staffId") Integer staffId) {
        List<MaterialRequest> materialRequests = materialRequestRepository.getMaterialRequestsByStaffId(staffId);
        List<Tuple> workAllocDetails = materialRequestRepository.getWorkAllocDetails(staffId);
        List<Tuple> materialItems = materialRequestRepository.getMaterialItems();

        for (MaterialRequest mr : materialRequests) {
            String status = materialRequestStatusRepository.getStatusFrmMaterialRequestId(mr.getMaterial_request_id());
            String material_type = materialRequestRepository.getDetailFrmMaterialItemId(mr.getMaterialItemId());

            mr.setMaterial_type(material_type);
            mr.setMaterial_request_status(status);
        }
        Optional<Staff> staffFrmDB = staffRepository.findById(staffId);
        Staff staffDtl = staffFrmDB.get();
        model.addAttribute("staffId", staffDtl.getStaff_id());
        model.addAttribute("staffName", staffDtl.getFirstName() + " " + staffDtl.getLastName());

        model.addAttribute("materialRequests" ,materialRequests);
        model.addAttribute("materialRequest",new MaterialRequest());
        model.addAttribute("workAllocDetails", workAllocDetails);
        model.addAttribute("materialItems", materialItems);

        return "material-request";
    }
    @PostMapping("/material-request-submit")
    public String submitMaterialRequest(MaterialRequest materialRequest, @RequestParam("staffId") String staffId, Model model, RedirectAttributes redirectAttributes){
        boolean isStatusUpdateRequired = (materialRequest.getMaterial_request_id() == 0) ? true : false;
        MaterialRequest savedMaterialRequest = materialRequestRepository.save(materialRequest);

        if (isStatusUpdateRequired) {
            MaterialRequestStatus materialRequestStatus = new MaterialRequestStatus();
            materialRequestStatus.setMaterialRequestId(savedMaterialRequest.getMaterial_request_id());
            materialRequestStatus.setMaterial_request_status("MDM Approval Waiting");
            materialRequestStatus.setMaterial_request_status_updated_date(savedMaterialRequest.getMaterial_request_date());
            materialRequestStatus.setMaterial_request_status_details("Waiting for approval from material depo manager");
            String staffName = workAllocationRepository.getStaffNameFrmWorkAllocationId(savedMaterialRequest.getWork_alloc_id());
            materialRequestStatus.setMaterial_request_status_updated_by(staffName);
            materialRequestStatusRepository.save(materialRequestStatus);
            redirectAttributes.addFlashAttribute("materialRequestSuccess", "Material Request submitted successfully");
        } else {
            redirectAttributes.addFlashAttribute("materialRequestSuccess", "Material Request updated successfully");
        }
        return "redirect:/material-request/" + staffId;
    }

    @GetMapping("/materialRequest/edit/{id}")
    public String editMaterialRequest(Model model, @PathVariable("id") Integer id ){
        MaterialRequest materialRequest = materialRequestRepository.findById(id)
                .orElseThrow(() -> new IllegalArgumentException("Invalid workAllocation Id:" + id));

        int staffId = materialRequestRepository.getStaffIdFrmMaterialRequest(id);
        List<MaterialRequest> materialRequests = materialRequestRepository.getMaterialRequestsByStaffId(staffId);
        List<Tuple> workAllocDetails = materialRequestRepository.getWorkAllocDetails(staffId);
        List<Tuple> materialItems = materialRequestRepository.getMaterialItems();

        for (MaterialRequest mr : materialRequests) {
            String status = materialRequestStatusRepository.getStatusFrmMaterialRequestId(mr.getMaterial_request_id());
            String material_type = materialRequestRepository.getDetailFrmMaterialItemId(mr.getMaterialItemId());

            mr.setMaterial_type(material_type);
            mr.setMaterial_request_status(status);
        }

        Optional<Staff> staffFrmDB = staffRepository.findById(staffId);
        Staff staffDtl = staffFrmDB.get();
        model.addAttribute("staffId", staffDtl.getStaff_id());
        model.addAttribute("staffName", staffDtl.getFirstName() + " " + staffDtl.getLastName());

        model.addAttribute("materialRequests" ,materialRequests);
        model.addAttribute("materialRequest", materialRequest);
        model.addAttribute("workAllocDetails", workAllocDetails);
        model.addAttribute("materialItems", materialItems);
        return "material-request";
    }
    @GetMapping("/material-issue")
    public String showMaterialIssue(Model model) {
        List<MaterialIssue> materialIssues = materialIssueRepository.findAll();
        model.addAttribute("materialIssues" ,materialIssues);
        return "material-issue-list";
    }

    @GetMapping("/material-issue/{id}")
    public String approvalMDMMaterialRequest(Model model, @PathVariable("id") Integer material_request_id) {
        MaterialIssue materialIssue = new MaterialIssue();
        materialIssue.setMaterial_request_id(material_request_id);

        List<MaterialIssue> materialIssues = materialIssueRepository.findAll();
        model.addAttribute("materialIssues" ,materialIssues);
        model.addAttribute("materialIssue", materialIssue);
        return "material-issue";
    }

    @PostMapping("/material/issue")
    public String issueMaterial(MaterialIssue materialIssue, Model model, RedirectAttributes redirectAttributes){
        boolean isMaterialNotIssued = (materialIssueRepository.getMaterialIssueCountOnMaterialReqId(materialIssue.getMaterial_request_id()) == 0) ? true : false;
        MaterialRequest materialRequest = materialRequestRepository.getMaterialRequestDetails(materialIssue.getMaterial_request_id());

        if (isMaterialNotIssued) {
            materialIssue.setMaterial_issued_status("Issued");
            materialIssue.setMaterial_issued_status_details("Material Issued");
            materialIssue.setMaterial_issued_qty(materialRequest.getMaterial_request_qty());
            materialIssueRepository.save(materialIssue);
        }
        redirectAttributes.addFlashAttribute("materialIssueSuccess", "Material Issued successfully");


        return "redirect:/material-issue";
    }

    @GetMapping("/material-issue-report")
    public String showMaterialIssueReport(Model model) {
        List<MaterialIssue> materialIssues = materialIssueRepository.findAll();
        model.addAttribute("materialIssues" ,materialIssues);
        return "material-issue-report";
    }
}
