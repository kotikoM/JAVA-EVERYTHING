package com.agriculture.AgroPlanner.service;

import com.agriculture.AgroPlanner.domain.FinancialRecord;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;

public interface FinanceService {
    ResponseEntity<FinancialRecord> createRecord(FinancialRecord record);

    ResponseEntity<Page<FinancialRecord>> retrieveAllRecords(Pageable pageable);

    ResponseEntity<FinancialRecord> retrieveRecord(Long recordID);

    void deleteRecord(Long recordID);

    ResponseEntity<Page<FinancialRecord>> retrieveAllUserRecords(Pageable pageable, Long userID);

    ResponseEntity<Page<FinancialRecord>> retrieveRecordsByType(Pageable pageable, String type);
}
