package com.agriculture.AgroPlanner.domain;

import jakarta.persistence.*;

import java.time.LocalDate;

import static com.agriculture.AgroPlanner.constants.ColumnNames.*;

@Entity
@SuppressWarnings("unused")
public class FinancialRecord {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = FINANCIAL_RECORD_RECORDID)
    private Long recordID;
    @Column(name = FINANCIAL_RECORD_DATE)
    @Temporal(TemporalType.DATE)
    private LocalDate date;
    @Column(name = FINANCIAL_RECORD_TYPE)
    private String transactionType;
    @Column(name = FINANCIAL_RECORD_AMOUNT)
    private Double amount;
    @Column(name = FINANCIAL_RECORD_DESC)
    private String description;
    @Column(name = FINANCIAL_RECORD_USERID)
    private Long userID;

    public FinancialRecord(Long recordID, LocalDate date, String transactionType, Double amount, String description, Long userID) {
        this.recordID = recordID;
        this.date = date;
        this.transactionType = transactionType;
        this.amount = amount;
        this.description = description;
        this.userID = userID;
    }

    public FinancialRecord() {
    }

    public Long getRecordID() {
        return recordID;
    }

    public LocalDate getDate() {
        return date;
    }

    public String getTransactionType() {
        return transactionType;
    }

    public double getAmount() {
        return amount;
    }

    public String getDescription() {
        return description;
    }

    public Long getUserID() {
        return userID;
    }
}
