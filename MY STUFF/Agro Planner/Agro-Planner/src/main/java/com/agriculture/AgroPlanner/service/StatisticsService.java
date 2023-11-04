package com.agriculture.AgroPlanner.service;

import com.agriculture.AgroPlanner.domain.FinancialRecord;
import com.agriculture.AgroPlanner.domain.HarvestEntry;
import com.agriculture.AgroPlanner.statistics.FinanceStatistics;
import com.agriculture.AgroPlanner.statistics.HarvestCropStatistics;
import org.springframework.http.ResponseEntity;

import java.util.List;

public interface StatisticsService {
    ResponseEntity<HarvestCropStatistics> retrieveHarvestStats();

    ResponseEntity<FinanceStatistics> retrieveRecordStats();

    FinanceStatistics calculateFinanceStatistics(List<FinancialRecord> records);

    HarvestCropStatistics calculateHarvestStatistics(List<HarvestEntry> entries);
}
