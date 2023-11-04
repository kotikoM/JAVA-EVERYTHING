package com.agriculture.AgroPlanner.service;

import com.agriculture.AgroPlanner.domain.FinancialRecord;
import com.agriculture.AgroPlanner.domain.HarvestEntry;
import com.agriculture.AgroPlanner.repository.FinanceRepository;
import com.agriculture.AgroPlanner.repository.HarvestRepository;
import com.agriculture.AgroPlanner.statistics.FinanceStatistics;
import com.agriculture.AgroPlanner.statistics.HarvestCropStatistics;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.Comparator;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

import static com.agriculture.AgroPlanner.constants.ColumnNames.FINANCIAL_RECORD_EXPENSE;
import static com.agriculture.AgroPlanner.constants.ColumnNames.FINANCIAL_RECORD_INCOME;

@Service
@SuppressWarnings("unused")
public class StatisticsServiceImpl implements StatisticsService{
    @Autowired
    private FinanceRepository financeRepository;
    @Autowired
    private HarvestRepository harvestRepository;

    public ResponseEntity<HarvestCropStatistics> retrieveHarvestStats() {
        List<HarvestEntry> entries = harvestRepository.findAll();

        if (entries.isEmpty()) {
            return ResponseEntity.noContent().build();
        }
        HarvestCropStatistics stats = calculateHarvestStatistics(entries);
        return ResponseEntity.ok(stats);
    }

    public ResponseEntity<FinanceStatistics> retrieveRecordStats() {
        List<FinancialRecord> records = financeRepository.findAll();

        if (records.isEmpty()) {
            return ResponseEntity.noContent().build();
        }
        FinanceStatistics stats = calculateFinanceStatistics(records);
        return ResponseEntity.ok(stats);
    }

    public FinanceStatistics calculateFinanceStatistics(List<FinancialRecord> records) {
        List<FinancialRecord> incomes = records.stream()
                .filter(rec -> rec.getTransactionType().equals(FINANCIAL_RECORD_INCOME))
                .toList();
        List<FinancialRecord> expenses = records.stream()
                .filter(rec -> rec.getTransactionType().equals(FINANCIAL_RECORD_EXPENSE))
                .toList();

        double totalIncome = incomes.stream()
                .mapToDouble(FinancialRecord::getAmount).sum();
        double totalExpense = expenses.stream()
                .mapToDouble(FinancialRecord::getAmount).sum();

        Double averageIncome = totalIncome / (double) incomes.size();
        Double averageExpense = totalExpense / (double) expenses.size();

        FinancialRecord maxIncome = incomes.stream()
                .max(Comparator.comparing(FinancialRecord::getAmount))
                .orElse(null);
        FinancialRecord maxExpense = expenses.stream()
                .max(Comparator.comparing(FinancialRecord::getAmount))
                .orElse(null);

        FinancialRecord minIncome = incomes.stream()
                .min(Comparator.comparing(FinancialRecord::getAmount))
                .orElse(null);
        FinancialRecord minExpense = expenses.stream()
                .min(Comparator.comparing(FinancialRecord::getAmount))
                .orElse(null);

        return new FinanceStatistics(totalIncome, totalExpense, averageIncome, averageExpense, maxIncome, maxExpense, minIncome, minExpense);
    }

    public HarvestCropStatistics calculateHarvestStatistics(List<HarvestEntry> entries) {
        Map<String, Double> cropMap = entries.stream().collect(Collectors.toConcurrentMap(
                entry -> entry.getCrop().getName(),
                HarvestEntry::getQuantityKg,
                Double::sum));

        double totalYield = cropMap.values().stream()
                .mapToDouble(Double::doubleValue)
                .sum();
        Double averageYield = totalYield / (double) entries.size();

        Map.Entry<String, Double> maxEntry = cropMap.entrySet()
                .stream()
                .max(Comparator.comparingDouble(Map.Entry::getValue))
                .orElse(null);

        Map.Entry<String, Double> minEntry = cropMap.entrySet()
                .stream()
                .min(Comparator.comparingDouble(Map.Entry::getValue))
                .orElse(null);

        return new HarvestCropStatistics(cropMap, totalYield, averageYield, maxEntry, minEntry);
    }
}
