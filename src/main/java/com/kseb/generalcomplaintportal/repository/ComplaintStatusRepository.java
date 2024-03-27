package com.kseb.generalcomplaintportal.repository;

import com.kseb.generalcomplaintportal.model.ComplaintStatus;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.transaction.annotation.Transactional;

public interface ComplaintStatusRepository extends JpaRepository<ComplaintStatus, Integer> {
    @Query(value = "SELECT * FROM kseb.complaint_status WHERE complaint_id = ?1", nativeQuery = true)
    ComplaintStatus getComplaintStatusDetails(@Param("id") int complaint_id);

    @Modifying
    @Transactional
    @Query(value = "DELETE FROM kseb.complaint_status cs WHERE cs.complaint_id = :complaint_id", nativeQuery = true)
    void deleteByComplaintId(@Param("complaint_id") int complaint_id);

    @Query(value = "SELECT cs.complaint_status_updated_by FROM kseb.complaint_status cs WHERE cs.complaint_id = :complaint_id", nativeQuery = true)
    int getDesignationIdFrmComplaint(@Param("complaint_id") int complaint_id);

}
