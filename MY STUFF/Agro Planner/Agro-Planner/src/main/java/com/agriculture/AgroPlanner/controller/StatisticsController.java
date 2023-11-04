package com.agriculture.AgroPlanner.controller;

import com.agriculture.AgroPlanner.service.StatisticsServiceImpl;
import com.agriculture.AgroPlanner.statistics.FinanceStatistics;
import com.agriculture.AgroPlanner.statistics.HarvestCropStatistics;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import static com.agriculture.AgroPlanner.constants.Endpoints.*;

@RestController
@SuppressWarnings("unused")
public class StatisticsController {
    @Autowired
    private StatisticsServiceImpl statsService;

    @GetMapping(FINANCES_ENDPOINT + FINANCE_STATS_ENDPOINT)
    public ResponseEntity<FinanceStatistics> retrieveRecordsStats() {
        return statsService.retrieveRecordStats();
    }
    @GetMapping(HARVEST_ENDPOINT + HARVEST_CROP_STATS_ENDPOINT)
    public ResponseEntity<HarvestCropStatistics> retrieveStatistics() {
        return statsService.retrieveHarvestStats();
    }

}
