package com.kseb.generalcomplaintportal.repository;

import com.kseb.generalcomplaintportal.model.MaterialRequest;
import com.kseb.generalcomplaintportal.model.MaterialRequestStatus;
import jakarta.persistence.Tuple;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

public interface MaterialRequestRepository extends JpaRepository<MaterialRequest, Integer> {
    @Query(value = "SELECT material_item_name FROM kseb.material_item where material_item_id = :material_item_id", nativeQuery = true)
    String getDetailFrmMaterialItemId(@Param("material_item_id") int material_item_id);

    @Query(value = "SELECT * FROM kseb.material_request WHERE material_request_id = ?1", nativeQuery = true)
    MaterialRequest getMaterialRequestDetails(@Param("material_request_id") int material_request_id);

    @Query(value = "SELECT work_alloc_id as work_alloc_id FROM kseb.work_allocation where ((work_alloc_id not in (select work_alloc_id from kseb.material_request)) or (work_alloc_id in (select work_alloc_id from kseb.work_allocation where staff_id=:staff_id))) and staff_id = :staff_id",
            nativeQuery = true)
    List<Tuple> getWorkAllocDetails(@Param("staff_id") int staff_id);

    @Query(value = "SELECT mr.material_request_id FROM kseb.material_request mr WHERE mr.work_alloc_id = :work_alloc_id", nativeQuery = true)
    Integer getMaterialRequestIdFrmWorkAllocation(@Param("work_alloc_id") int work_alloc_id);

    @Modifying
    @Transactional
    @Query(value = "DELETE FROM kseb.material_request mr WHERE mr.work_alloc_id = :work_alloc_id", nativeQuery = true)
    int deleteByMaterialRequestId(@Param("work_alloc_id") int work_alloc_id);

    @Query(value = "SELECT material_item_id, material_item_name FROM kseb.material_item",
            nativeQuery = true)
    List<Tuple> getMaterialItems();

    @Query(value = "SELECT COUNT(*) FROM kseb.material_request_status where material_request_id=:material_request_id and material_request_status='MDM Approval Waiting'", nativeQuery = true)
    Long showApproval(@Param("material_request_id") int material_request_id);

    @Query(value = "SELECT COUNT(*) FROM kseb.material_request_status where material_request_id=:material_request_id and material_request_status='EE Approved' and material_request_id not in (select material_request_id from kseb.material_issue)", nativeQuery = true)
    Long showIssue(@Param("material_request_id") int material_request_id);

    @Query(value = "SELECT COUNT(*) FROM kseb.material_request_status where material_request_id=:material_request_id and material_request_status='EE Approval Waiting'", nativeQuery = true)
    Long showEEApproval(@Param("material_request_id") int material_request_id);

    @Query(value = "SELECT count(*) FROM kseb.material_request mr, kseb.material_issue mi, kseb.work_allocation wa where mr.material_request_id = mi.material_request_id and wa.work_alloc_id=mr.work_alloc_id and mr.work_alloc_id = :work_alloc_id and wa.complaint_id not in (select complaint_id from kseb.complaint_status where complaint_status='RESOLVED');", nativeQuery = true)
    Long allowLineManStatusUpdate(@Param("work_alloc_id") int work_alloc_id);

    @Query(value = "SELECT * FROM kseb.material_request where work_alloc_id in (select work_alloc_id from kseb.work_allocation where staff_id = :staff_id)", nativeQuery = true)
    List<MaterialRequest> getMaterialRequestsByStaffId(@Param("staff_id") int staff_id);

    @Query(value = "SELECT staff_id FROM kseb.material_request mr, kseb.work_allocation wa where mr.work_alloc_id=wa.work_alloc_id and mr.material_request_id=:material_request_id", nativeQuery = true)
    Integer getStaffIdFrmMaterialRequest(@Param("material_request_id") int material_request_id);
}
