package com.kseb.generalcomplaintportal.repository;

import com.kseb.generalcomplaintportal.model.WorkAllocation;
import jakarta.persistence.Tuple;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

public interface WorkAllocationRepository extends JpaRepository<WorkAllocation, Integer> {
    @Query(value = "SELECT sp.staff_firstname FROM kseb.work_allocation wa, kseb.staff_personal sp where wa.staff_id = sp.staff_id and wa.work_alloc_id = :work_alloc_id", nativeQuery = true)
    String getStaffNameFrmWorkAllocationId(@Param("work_alloc_id") int work_alloc_id);

    @Query(value = "SELECT * FROM kseb.work_allocation where work_alloc_id = :work_alloc_id", nativeQuery = true)
    WorkAllocation getWorkAllocationDetails(@Param("work_alloc_id") int work_alloc_id);

    @Query(value = "SELECT work_alloc_id FROM kseb.work_allocation where complaint_id = :complaint_id", nativeQuery = true)
    Integer getWorkAllocIdFrmComplaintId(@Param("complaint_id") int complaint_id);

    @Query(value = "SELECT c.complaint_id as complaint_id, c.complaint_details as complaint, cs.complaint_status as status FROM kseb.complaint c, kseb.complaint_status cs where cs.complaint_status = 'PENDING' and c.complaint_id = cs.complaint_id and (c.complaint_id not in (select complaint_id from kseb.work_allocation))",
            nativeQuery = true)
    List<Tuple> getComplaintDetails();

    @Query(value = "SELECT c.complaint_id as complaint_id, c.complaint_details as complaint, cs.complaint_status as status FROM kseb.complaint c, kseb.complaint_status cs where cs.complaint_status = 'PENDING' and c.complaint_id = cs.complaint_id and ((c.complaint_id not in (select complaint_id from kseb.work_allocation)) or (c.complaint_id in (select complaint_id from kseb.work_allocation where work_alloc_id=:work_alloc_id)))",
            nativeQuery = true)
    List<Tuple> getComplaintDetailsOnEdit(@Param("work_alloc_id") int work_alloc_id);

    @Query(value = "SELECT sp.staff_firstname as firstname, sp.staff_id as staff_id, sp.staff_desig_id as desig_id, d.designation  FROM kseb.staff_personal sp,kseb.designation d where sp.staff_desig_id= d.design_id and d.designation = 'Line Man'",
            nativeQuery = true)
    List<Tuple> getLinemanDetails();

    @Query(value = "SELECT wa.work_alloc_id FROM kseb.work_allocation wa WHERE wa.complaint_id = :complaint_id", nativeQuery = true)
    Integer getWorkAllocationIdFrmComplaint(@Param("complaint_id") int complaint_id);

    @Modifying
    @Transactional
    @Query(value = "DELETE FROM kseb.work_allocation wa WHERE wa.complaint_id = :complaint_id", nativeQuery = true)
    int deleteByWorkAllocationsId(@Param("complaint_id") int complaint_id);

    @Query(value = "SELECT staff_id FROM kseb.work_allocation where work_alloc_id = :work_alloc_id", nativeQuery = true)
    int getStaffIdFrmWorkAllocationId(@Param("work_alloc_id") int work_alloc_id);

    @Query(value = "SELECT * FROM kseb.work_allocation where staff_id = :staff_id", nativeQuery = true)
    List<WorkAllocation> getWorkAllocDtlsOnStaffId(@Param("staff_id") int staff_id);

    @Query(value = "SELECT count(*) FROM kseb.complaint_status where complaint_id=:complaint_id and complaint_status = 'PENDING'", nativeQuery = true)
    Long showWorkAllocEdit(@Param("complaint_id") int complaint_id);
}
