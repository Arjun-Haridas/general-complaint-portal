package com.kseb.generalcomplaintportal.repository;

import com.kseb.generalcomplaintportal.model.MaterialIssue;
import com.kseb.generalcomplaintportal.model.MaterialRequestStatus;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.transaction.annotation.Transactional;

public interface MaterialIssueRepository extends JpaRepository<MaterialIssue, Integer> {
    @Query(value = "SELECT count(*) FROM kseb.material_issue WHERE material_request_id = ?1", nativeQuery = true)
    int getMaterialIssueCountOnMaterialReqId(@Param("material_request_id") int material_request_id);

    @Query(value = "SELECT material_issued_status FROM kseb.material_issue where material_request_id = :material_request_id", nativeQuery = true)
    String getIssueStatusFrmMaterialRequestId(@Param("material_request_id") int material_request_id);

    @Modifying
    @Transactional
    @Query(value = "DELETE FROM kseb.material_issue mi WHERE mi.material_request_id = :material_request_id", nativeQuery = true)
    int deleteByMaterialIssueId(@Param("material_request_id") int material_request_id);

}
