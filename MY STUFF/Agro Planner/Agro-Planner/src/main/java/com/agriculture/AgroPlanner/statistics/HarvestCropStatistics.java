package com.agriculture.AgroPlanner.statistics;

import com.agriculture.AgroPlanner.domain.HarvestEntry;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonRootName;

import java.util.Comparator;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

import static com.agriculture.AgroPlanner.constants.ColumnNames.*;

@JsonRootName(CROP_STATS)
@SuppressWarnings("unused")
public class HarvestCropStatistics {
    @JsonProperty(CROP_STATS_QUANTITY)
    private Map<String, Double> cropQuantityMap;
    @JsonProperty(CROP_STATS_TOTAL)
    private Double totalYield;
    @JsonProperty(CROP_STATS_AVERAGE)
    private Double averageYield;
    @JsonProperty(CROP_STATS_MAX_CROP)
    private Map.Entry<String, Double> maxCrop;
    @JsonProperty(CROP_STATS_MIN_CROP)
    private Map.Entry<String, Double> crop;

    public HarvestCropStatistics(Map<String, Double> cropQuantityMap, Double totalYield, Double averageYield, Map.Entry<String, Double> maxCrop, Map.Entry<String, Double> crop) {
        this.cropQuantityMap = cropQuantityMap;
        this.totalYield = totalYield;
        this.averageYield = averageYield;
        this.maxCrop = maxCrop;
        this.crop = crop;
    }

    public HarvestCropStatistics() {
    }
}
