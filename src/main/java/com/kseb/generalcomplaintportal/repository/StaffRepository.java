package com.kseb.generalcomplaintportal.repository;

import com.kseb.generalcomplaintportal.model.Staff;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

public interface StaffRepository extends JpaRepository<Staff, Integer> {
    Staff findByUsername(String username);

    @Query(value = "SELECT staff_firstname FROM kseb.staff_personal where staff_id = :staff_id", nativeQuery = true)
    String getStaffNameFrmId(@Param("staff_id") int staff_id);
}
