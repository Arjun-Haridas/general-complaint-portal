package com.kseb.generalcomplaintportal.repository;

import com.kseb.generalcomplaintportal.model.MaterialRequestStatus;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;

public interface MaterialRequestStatusRepository extends JpaRepository<MaterialRequestStatus, Integer> {
    @Query(value = "SELECT material_request_status FROM kseb.material_request_status where material_request_id = :material_request_id", nativeQuery = true)
    String getStatusFrmMaterialRequestId(@Param("material_request_id") int material_request_id);

    @Query(value = "SELECT * FROM kseb.material_request_status WHERE material_request_id = ?1", nativeQuery = true)
    MaterialRequestStatus getMaterialStatusDetails(@Param("material_request_id") int material_request_id);

    Optional<MaterialRequestStatus> findByMaterialRequestId(Integer integer);

    @Modifying
    @Transactional
    @Query(value = "DELETE FROM kseb.material_request_status mrs WHERE mrs.material_request_id = :material_request_id", nativeQuery = true)
    int deleteByMaterialRequestStatusId(@Param("material_request_id") int material_request_id);
}
