package com.agriculture.AgroPlanner.controller;

import com.agriculture.AgroPlanner.domain.FinancialRecord;
import com.agriculture.AgroPlanner.service.FinanceServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import static com.agriculture.AgroPlanner.constants.ColumnNames.FINANCIAL_RECORD_EXPENSE;
import static com.agriculture.AgroPlanner.constants.ColumnNames.FINANCIAL_RECORD_INCOME;
import static com.agriculture.AgroPlanner.constants.Endpoints.*;


@RestController
@RequestMapping(FINANCES_ENDPOINT)
@SuppressWarnings("unused")
public class FinancialRecordController {
    @Autowired
    private FinanceServiceImpl financialRecordService;

    @PutMapping
    public ResponseEntity<FinancialRecord> insertRecord(
            @RequestBody FinancialRecord record) {
        return financialRecordService.createRecord(record);
    }

    @GetMapping
    public ResponseEntity<Page<FinancialRecord>> retrieveAllRecords(Pageable pageable) {
        return financialRecordService.retrieveAllRecords(pageable);
    }

    @GetMapping(FINANCE_ENDPOINT)
    public ResponseEntity<FinancialRecord> retrieveRecord(
            @PathVariable Long recordID) {
        return financialRecordService.retrieveRecord(recordID);
    }

    @DeleteMapping(FINANCE_ENDPOINT)
    public void deleteRecord(
            @PathVariable Long recordID) {
        financialRecordService.deleteRecord(recordID);
    }

    @GetMapping(FINANCES_INCOMES_ENDPOINT)
    public ResponseEntity<Page<FinancialRecord>> retrieveIncomeRecords(Pageable pageable) {
        return financialRecordService.retrieveRecordsByType(pageable, FINANCIAL_RECORD_INCOME);
    }

    @GetMapping(FINANCES_EXPENSES_ENDPOINT)
    public ResponseEntity<Page<FinancialRecord>> retrieveExpenseRecords(Pageable pageable) {
        return financialRecordService.retrieveRecordsByType(pageable, FINANCIAL_RECORD_EXPENSE);
    }

    @GetMapping(USER_FINANCIAL_RECORDS_ENDPOINT)
    public ResponseEntity<Page<FinancialRecord>> retrieveAllUserRecords(Pageable pageable,
                                                                        @PathVariable Long userID) {
        return financialRecordService.retrieveAllUserRecords(pageable, userID);
    }
}
