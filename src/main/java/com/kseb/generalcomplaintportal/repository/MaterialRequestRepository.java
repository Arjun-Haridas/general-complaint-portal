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

    @Query(value = "SELECT work_alloc_id as work_alloc_id FROM kseb.work_allocation where work_alloc_id not in (select work_alloc_id from kseb.material_request)",
            nativeQuery = true)
    List<Tuple> getWorkAllocDetails();

    @Query(value = "SELECT mr.material_request_id FROM kseb.material_request mr WHERE mr.work_alloc_id = :work_alloc_id", nativeQuery = true)
    Integer getMaterialRequestIdFrmWorkAllocation(@Param("work_alloc_id") int work_alloc_id);

    @Modifying
    @Transactional
    @Query(value = "DELETE FROM kseb.material_request mr WHERE mr.work_alloc_id = :work_alloc_id", nativeQuery = true)
    int deleteByMaterialRequestId(@Param("work_alloc_id") int work_alloc_id);

    @Query(value = "SELECT material_item_id, material_item_name FROM kseb.material_item",
            nativeQuery = true)
    List<Tuple> getMaterialItems();
}
