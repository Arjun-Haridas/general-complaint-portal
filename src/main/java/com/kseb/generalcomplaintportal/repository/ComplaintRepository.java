package com.kseb.generalcomplaintportal.repository;

import com.kseb.generalcomplaintportal.model.Complaint;
import com.kseb.generalcomplaintportal.model.WorkAllocation;
import jakarta.persistence.Tuple;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface ComplaintRepository extends JpaRepository<Complaint, Long> {

    @Query(value = "SELECT cp.consumer_firstname FROM kseb.consumer_kseb ck, kseb.consumer_personal cp " +
            "WHERE ck.consumer_id = cp.consumer_id AND ck.consumer_kseb_id = :ksebId",
            nativeQuery = true)
    String getConsumerNameByKSEBID(@Param("ksebId") int ksebId);

    @Query(value = "SELECT * FROM kseb.complaint where complaint_id = :complaint_id", nativeQuery = true)
    Complaint getComplaintDetails(@Param("complaint_id") int complaint_id);

    @Query(value = "SELECT ck.consumer_kseb_id as kseb_id, cp.consumer_firstname as first_name FROM kseb.consumer_kseb ck, kseb.consumer_personal cp\n" +
            "where ck.consumer_id = cp.consumer_id",
            nativeQuery = true)
    List<Tuple> getConsumerDetails();

    @Query(value = "SELECT complaint_details FROM kseb.complaint where complaint_id = :complaint_id", nativeQuery = true)
    String getComplaintFrmComplaintId(@Param("complaint_id") int complaint_id);
}
