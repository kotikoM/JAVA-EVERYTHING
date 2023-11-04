package com.agriculture.AgroPlanner.service;

import com.agriculture.AgroPlanner.domain.Crop;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;

public interface CropService {
    ResponseEntity<Crop> createCrop(Crop crop);

    ResponseEntity<Page<Crop>> retrieveAllCrops(Pageable pageable);

    ResponseEntity<Crop> retrieveCrop(Long cropID);

    void deleteCrop(Long cropID);
}