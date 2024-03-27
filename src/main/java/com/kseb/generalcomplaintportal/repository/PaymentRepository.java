package com.kseb.generalcomplaintportal.repository;

import com.kseb.generalcomplaintportal.model.Payment;
import jakarta.persistence.Tuple;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

public interface PaymentRepository extends JpaRepository<Payment, Integer> {

    @Query(value = "SELECT work_alloc_id as work_alloc_id FROM kseb.work_allocation",
            nativeQuery = true)
    List<Tuple> getWorkAllocDetails();

    @Modifying
    @Transactional
    @Query(value = "DELETE FROM kseb.payment_bill pb WHERE pb.work_alloc_id = :work_alloc_id", nativeQuery = true)
    int deleteByPaymentBillId(@Param("work_alloc_id") int work_alloc_id);
}
