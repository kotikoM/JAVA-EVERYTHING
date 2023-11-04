package com.agriculture.AgroPlanner.service;

import com.agriculture.AgroPlanner.domain.HarvestEntry;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;

public interface HarvestService {
    ResponseEntity<HarvestEntry> createEntry(HarvestEntry harvestEntry);

    ResponseEntity<Page<HarvestEntry>> retrieveAllEntries(Pageable pageable);

    ResponseEntity<HarvestEntry> retrieveEntry(Long employeeID, Long cropID);

    void deleteEntry(Long employeeID, Long cropID);

    ResponseEntity<Page<HarvestEntry>> retrieveByEmployeeID(Pageable pageable, Long employeeID);

    ResponseEntity<Page<HarvestEntry>> retrieveByCropID(Pageable pageable, Long cropID);
}
