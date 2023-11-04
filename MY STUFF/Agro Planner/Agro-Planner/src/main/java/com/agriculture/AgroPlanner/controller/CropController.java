package com.agriculture.AgroPlanner.controller;

import com.agriculture.AgroPlanner.domain.Crop;
import com.agriculture.AgroPlanner.service.CropServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import static com.agriculture.AgroPlanner.constants.Endpoints.CROPS_ENDPOINT;
import static com.agriculture.AgroPlanner.constants.Endpoints.CROP_ENDPOINT;

@RestController
@RequestMapping(CROPS_ENDPOINT)
@SuppressWarnings("unused")
public class CropController {
    @Autowired
    private CropServiceImpl cropService;

    @PostMapping
    public ResponseEntity<Crop> insertCrop(
            @RequestBody Crop crop) {
        return cropService.createCrop(crop);
    }

    @GetMapping
    public ResponseEntity<Page<Crop>> retrieveAllCrops(Pageable pageable) {
        return cropService.retrieveAllCrops(pageable);
    }

    @GetMapping(CROP_ENDPOINT)
    public ResponseEntity<Crop> retrieveCrop(
            @PathVariable Long cropID) {
        return cropService.retrieveCrop(cropID);
    }

    @DeleteMapping(CROP_ENDPOINT)
    public void deleteCrop(
            @PathVariable Long cropID) {
        cropService.deleteCrop(cropID);
    }
}
