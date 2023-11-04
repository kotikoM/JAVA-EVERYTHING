package com.agriculture.AgroPlanner.service;

import com.agriculture.AgroPlanner.domain.FinancialRecord;
import com.agriculture.AgroPlanner.repository.FinanceRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
@SuppressWarnings("unused")
public class FinanceServiceImpl implements FinanceService {
    @Autowired
    private FinanceRepository financeRepository;

    private static ResponseEntity<Page<FinancialRecord>> getPageResponseEntity(Page<FinancialRecord> recordsPage) {
        return (recordsPage.isEmpty()) ? ResponseEntity.noContent().build()
                : ResponseEntity.ok(recordsPage);
    }

    public ResponseEntity<FinancialRecord> createRecord(FinancialRecord record) {
        FinancialRecord savedRecord = financeRepository.save(record);
        return ResponseEntity.status(HttpStatus.CREATED).body(savedRecord);
    }

    public ResponseEntity<Page<FinancialRecord>> retrieveAllRecords(Pageable pageable) {
        Page<FinancialRecord> financialRecords = financeRepository.findAll(pageable);
        return getPageResponseEntity(financialRecords);
    }

    public ResponseEntity<FinancialRecord> retrieveRecord(Long recordID) {
        Optional<FinancialRecord> optionalRecord = financeRepository.findById(recordID);
        return optionalRecord.map(ResponseEntity::ok)
                .orElseGet(() ->
                        ResponseEntity.notFound().build());
    }

    public void deleteRecord(Long recordID) {
        financeRepository.deleteById(recordID);
    }

    public ResponseEntity<Page<FinancialRecord>> retrieveAllUserRecords(Pageable pageable, Long userID) {
        Page<FinancialRecord> records = financeRepository.findAllUserRecords(pageable, userID);
        return getPageResponseEntity(records);
    }

    public ResponseEntity<Page<FinancialRecord>> retrieveRecordsByType(Pageable pageable, String type) {
        List<FinancialRecord> records = financeRepository.findAll().stream()
                .filter(rec ->
                        rec.getTransactionType().equals(type))
                .collect(Collectors.toList());

        Page<FinancialRecord> recordsPage = new PageImpl<>(records, pageable, records.size());
        return getPageResponseEntity(recordsPage);
    }
}
