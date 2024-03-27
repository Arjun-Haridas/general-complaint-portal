package com.kseb.generalcomplaintportal.controller;

import com.kseb.generalcomplaintportal.model.MaterialRequest;
import com.kseb.generalcomplaintportal.model.Payment;
import com.kseb.generalcomplaintportal.repository.PaymentRepository;
import jakarta.persistence.Tuple;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;


import java.util.List;

@Controller
public class PaymentController {

    @Autowired
    private PaymentRepository paymentRepository;

    @GetMapping("/payment")
    public String showPayment(Model model) {
        List<Payment> payments = paymentRepository.findAll();
        List<Tuple> workAllocDetails = paymentRepository.getWorkAllocDetails();

        model.addAttribute("payments" ,payments);
        model.addAttribute("payment",new Payment());
        model.addAttribute("workAllocDetails", workAllocDetails);
        return "payment";
    }
    @PostMapping("/payment-submit")
    public String submitPayment(Payment payment, Model model){
        paymentRepository.save(payment);
        return "redirect:/payment";
    }
    @GetMapping("/payment/edit/{id}")
    public String editPayment(Model model, @PathVariable("id") Integer id){
        Payment payment = paymentRepository.findById(id)
                .orElseThrow(() -> new IllegalArgumentException("Invalid workAllocation Id:" + id));
        List<Payment> payments = paymentRepository.findAll();
        List<Tuple> workAllocDetails = paymentRepository.getWorkAllocDetails();
        model.addAttribute("payments" ,payments);
        model.addAttribute("payment",payment);
        model.addAttribute("workAllocDetails", workAllocDetails);
        return "payment";
    }
    @GetMapping("/view-payment-report")
    public String viewPaymentReport(Model model) {
        List<Payment> payments = paymentRepository.findAll();
        model.addAttribute("payments" ,payments);
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
