package com.kseb.generalcomplaintportal.controller;

import com.kseb.generalcomplaintportal.model.MaterialRequest;
import com.kseb.generalcomplaintportal.model.Payment;
import com.kseb.generalcomplaintportal.model.Staff;
import com.kseb.generalcomplaintportal.repository.PaymentRepository;
import com.kseb.generalcomplaintportal.repository.StaffRepository;
import jakarta.persistence.Tuple;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;


import java.util.List;
import java.util.Optional;

@Controller
public class PaymentController {

    @Autowired
    private PaymentRepository paymentRepository;

    @Autowired
    private StaffRepository staffRepository;

    @GetMapping("/payment/{staffId}")
    public String showPayment(Model model, @PathVariable("staffId") Integer staffId) {
        List<Payment> payments = paymentRepository.getPaymentsByStaffId(staffId);
        List<Tuple> workAllocDetails = paymentRepository.getWorkAllocDetails(staffId);

        Optional<Staff> staffFrmDB = staffRepository.findById(staffId);
        Staff staffDtl = staffFrmDB.get();
        model.addAttribute("staffId", staffDtl.getStaff_id());
        model.addAttribute("staffName", staffDtl.getFirstName() + " " + staffDtl.getLastName());

        model.addAttribute("payments" ,payments);
        model.addAttribute("payment",new Payment());
        model.addAttribute("workAllocDetails", workAllocDetails);
        return "payment";
    }
    @PostMapping("/payment-submit")
    public String submitPayment(Payment payment, @RequestParam("staffId") String staffId, Model model){
        paymentRepository.save(payment);
        return "redirect:/payment/" + staffId;
    }
    @GetMapping("/payment/edit/{id}")
    public String editPayment(Model model, @PathVariable("id") Integer id){
        Payment payment = paymentRepository.findById(id)
                .orElseThrow(() -> new IllegalArgumentException("Invalid workAllocation Id:" + id));
        int staffId = paymentRepository.getStaffIdFrmPayment(id);

        List<Payment> payments = paymentRepository.getPaymentsByStaffId(staffId);
        List<Tuple> workAllocDetails = paymentRepository.getWorkAllocDetails(staffId);
        model.addAttribute("payments" ,payments);
        model.addAttribute("payment",payment);
        model.addAttribute("workAllocDetails", workAllocDetails);
        return "payment";
    }
    @GetMapping("/view-payment-report/{headerName}")
    public String viewPaymentReport(Model model, @PathVariable("headerName") String pageName) {
        List<Payment> payments = paymentRepository.findAll();
        model.addAttribute("payments" ,payments);
        model.addAttribute("headerName", pageName);
        return "payment-report";
    }

    @GetMapping("/view-payment-report/{headerName}/{staffId}")
    public String viewPaymentReportByStaff(Model model, @PathVariable("headerName") String pageName, @PathVariable("staffId") int staffId) {
        List<Payment> payments = paymentRepository.getPaymentsByStaffId(staffId);

        Optional<Staff> staffFrmDB = staffRepository.findById(staffId);
        Staff staffDtl = staffFrmDB.get();
        model.addAttribute("staffId", staffDtl.getStaff_id());

        model.addAttribute("payments" ,payments);
        model.addAttribute("headerName", pageName);
        return "payment-report";
    }

    @GetMapping("/payment-manager")
    public String showManagerPayment(Model model) {
        List<Payment> payments = paymentRepository.findAll();
        List<Tuple> workAllocDetails = paymentRepository.getWorkAllocDetails();

        model.addAttribute("payments" ,payments);
        model.addAttribute("payment",new Payment());
        model.addAttribute("workAllocDetails", workAllocDetails);
        return "payment-manager";
    }
    @PostMapping("/payment-manager-submit")
    public String submitMangerPayment(Payment payment, Model model){
        paymentRepository.save(payment);
        return "redirect:/payment-manager";
    }
    @GetMapping("/payment/manager/edit/{id}")
    public String editManagerPayment(Model model, @PathVariable("id") Integer id){
        Payment payment = paymentRepository.findById(id)
                .orElseThrow(() -> new IllegalArgumentException("Invalid workAllocation Id:" + id));
        List<Payment> payments = paymentRepository.findAll();
        List<Tuple> workAllocDetails = paymentRepository.getWorkAllocDetails();
        model.addAttribute("payments" ,payments);
        model.addAttribute("payment",payment);
        model.addAttribute("workAllocDetails", workAllocDetails);
        return "payment-manager";
    }
}
