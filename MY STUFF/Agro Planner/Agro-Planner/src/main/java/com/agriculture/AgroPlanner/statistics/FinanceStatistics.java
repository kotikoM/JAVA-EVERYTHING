package com.agriculture.AgroPlanner.statistics;

import com.agriculture.AgroPlanner.domain.FinancialRecord;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonRootName;

import java.util.Comparator;
import java.util.List;

import static com.agriculture.AgroPlanner.constants.ColumnNames.*;


@JsonRootName(FINANCE_STATS)
@SuppressWarnings("unused")
public class FinanceStatistics {
    @JsonProperty(FINANCE_STATS_TOTAL_INCOME)
    private Double totalIncome;
    @JsonProperty(FINANCE_STATS_TOTAL_EXPENSES)
    private Double totalExpense;
    @JsonProperty(FINANCE_STATS_AVERAGE_INCOME)
    private Double averageIncome;
    @JsonProperty(FINANCE_STATS_AVERAGE_EXPENSE)
    private Double averageExpense;
    @JsonProperty(FINANCE_STATS_MAX_INCOME)
    private FinancialRecord maxIncome;
    @JsonProperty(FINANCE_STATS_MAX_EXPENSE)
    private FinancialRecord maxExpense;
    @JsonProperty(FINANCE_STATS_MIN_INCOME)
    private FinancialRecord minIncome;
    @JsonProperty(FINANCE_STATS_MIN_EXPENSE)
    private FinancialRecord minExpense;

    public FinanceStatistics(
            Double totalIncomes, Double totalExpenses, Double averageIncome, Double averageExpense, FinancialRecord maxIncome, FinancialRecord maxExpense, FinancialRecord minIncome, FinancialRecord minExpense) {
        this.totalIncome = totalIncomes;
        this.totalExpense = totalExpenses;
        this.averageIncome = averageIncome;
        this.averageExpense = averageExpense;
        this.maxIncome = maxIncome;
        this.maxExpense = maxExpense;
        this.minIncome = minIncome;
        this.minExpense = minExpense;
    }

    public FinanceStatistics() {
    }
}
