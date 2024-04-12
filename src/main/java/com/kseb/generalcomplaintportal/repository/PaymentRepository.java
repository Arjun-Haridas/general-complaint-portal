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

    @Query(value = "SELECT work_alloc_id as work_alloc_id FROM kseb.work_allocation where staff_id = :staff_id and work_alloc_id not in (select work_alloc_id FROM kseb.payment_bill)",
            nativeQuery = true)
    List<Tuple> getWorkAllocDetails(@Param("staff_id") int staff_id);

    @Query(value = "SELECT work_alloc_id as work_alloc_id FROM kseb.work_allocation where staff_id = :staff_id and ((work_alloc_id not in (select work_alloc_id FROM kseb.payment_bill)) or work_alloc_id in (select work_alloc_id from kseb.payment_bill where payment_bill_id = :payment_bill_id))",
            nativeQuery = true)
    List<Tuple> getWorkAllocDetailsOnEdit(@Param("staff_id") int staff_id, @Param("payment_bill_id") int payment_bill_id);

    @Query(value = "SELECT work_alloc_id as work_alloc_id FROM kseb.work_allocation",
            nativeQuery = true)
    List<Tuple> getWorkAllocDetails();

    @Modifying
    @Transactional
    @Query(value = "DELETE FROM kseb.payment_bill pb WHERE pb.work_alloc_id = :work_alloc_id", nativeQuery = true)
    int deleteByPaymentBillId(@Param("work_alloc_id") int work_alloc_id);

    @Query(value = "SELECT * FROM kseb.payment_bill where work_alloc_id in (select work_alloc_id from kseb.work_allocation where staff_id = :staff_id)",
            nativeQuery = true)
    List<Payment> getPaymentsByStaffId(@Param("staff_id") int staff_id);

    @Query(value = "SELECT staff_id FROM kseb.payment_bill py, kseb.work_allocation wa where py.work_alloc_id=wa.work_alloc_id and payment_bill_id=:payment_bill_id", nativeQuery = true)
    Integer getStaffIdFrmPayment(@Param("payment_bill_id") int payment_bill_id);
}
