package com.agriculture.AgroPlanner.service;

import com.agriculture.AgroPlanner.domain.HarvestEntry;
import com.agriculture.AgroPlanner.domain.HarvestEntryKey;
import com.agriculture.AgroPlanner.repository.HarvestRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
@SuppressWarnings("unused")
public class HarvestServiceImpl implements HarvestService{
    @Autowired
    private HarvestRepository harvestRepository;

    private static ResponseEntity<Page<HarvestEntry>> getPageResponseEntity(Page<HarvestEntry> entries) {
        return (entries.isEmpty()) ? ResponseEntity.noContent().build()
                : ResponseEntity.ok(entries);
    }

    public ResponseEntity<HarvestEntry> createEntry(HarvestEntry harvestEntry) {
        HarvestEntry savedHarvestEntry = harvestRepository.save(harvestEntry);
        return ResponseEntity.status(HttpStatus.CREATED).body(savedHarvestEntry);
    }

    public ResponseEntity<Page<HarvestEntry>> retrieveAllEntries(Pageable pageable) {
        Page<HarvestEntry> allEntries = harvestRepository.findAll(pageable);
        return getPageResponseEntity(allEntries);
    }

    public ResponseEntity<HarvestEntry> retrieveEntry(Long employeeID, Long cropID) {
        Optional<HarvestEntry> entryOpt = harvestRepository.findById(HarvestEntryKey.create(employeeID, cropID));
        return entryOpt.map(ResponseEntity::ok)
                .orElseGet(() -> ResponseEntity.notFound().build());
    }

    public void deleteEntry(Long employeeID, Long cropID) {
        harvestRepository.deleteById(HarvestEntryKey.create(employeeID, cropID));
    }

    public ResponseEntity<Page<HarvestEntry>> retrieveByEmployeeID(Pageable pageable, Long employeeID) {
        Page<HarvestEntry> entries = harvestRepository.findByHarvestEntryKeyEmployeeID(pageable, employeeID);
        return getPageResponseEntity(entries);
    }

    public ResponseEntity<Page<HarvestEntry>> retrieveByCropID(Pageable pageable, Long cropID) {
        Page<HarvestEntry> entries = harvestRepository.findByHarvestEntryKeyCropID(pageable, cropID);
        return getPageResponseEntity(entries);
    }
}
