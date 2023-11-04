package com.agriculture.AgroPlanner.repository;

import com.agriculture.AgroPlanner.domain.HarvestEntry;
import com.agriculture.AgroPlanner.domain.HarvestEntryKey;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface HarvestRepository extends JpaRepository<HarvestEntry, HarvestEntryKey> {
    Page<HarvestEntry> findByHarvestEntryKeyEmployeeID(Pageable pageable, Long employeeID);

    Page<HarvestEntry> findByHarvestEntryKeyCropID(Pageable pageable, Long cropID);
}
