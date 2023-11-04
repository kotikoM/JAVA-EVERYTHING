package com.agriculture.AgroPlanner.controller;

import com.agriculture.AgroPlanner.domain.HarvestEntry;
import com.agriculture.AgroPlanner.service.HarvestServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import static com.agriculture.AgroPlanner.constants.Endpoints.*;

@RestController
@RequestMapping(HARVEST_ENDPOINT)
@SuppressWarnings("unused")
public class HarvestController {
    @Autowired
    private HarvestServiceImpl harvestService;

    @PostMapping
    public ResponseEntity<HarvestEntry> insertEntry(
            @RequestBody HarvestEntry harvestEntry) {
        return harvestService.createEntry(harvestEntry);
    }

    @GetMapping
    public ResponseEntity<Page<HarvestEntry>> retrieveAllEntries(Pageable pageable) {
        return harvestService.retrieveAllEntries(pageable);
    }

    @GetMapping(HARVEST_ENTRY_ENDPOINT)
    public ResponseEntity<HarvestEntry> retrieveEntry(
            @PathVariable Long employeeID,
            @PathVariable Long cropID) {
        return harvestService.retrieveEntry(employeeID, cropID);
    }

    @DeleteMapping(HARVEST_ENTRY_ENDPOINT)
    public void deleteEntry(
            @PathVariable Long employeeID,
            @PathVariable Long cropID) {
        harvestService.deleteEntry(employeeID, cropID);
    }

    @GetMapping(HARVEST_EMPLOYEE_ENDPOINT)
    public ResponseEntity<Page<HarvestEntry>> retrieveByEmployeeID(Pageable pageable, @PathVariable Long employeeID) {
        return harvestService.retrieveByEmployeeID(pageable, employeeID);
    }

    @GetMapping(HARVEST_CROP_ENDPOINT)
    public ResponseEntity<Page<HarvestEntry>> retrieveByCropID(Pageable pageable, @PathVariable Long cropID) {
        return harvestService.retrieveByCropID(pageable, cropID);
    }
}
