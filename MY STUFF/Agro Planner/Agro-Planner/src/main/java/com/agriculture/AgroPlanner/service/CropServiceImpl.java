package com.agriculture.AgroPlanner.service;

import com.agriculture.AgroPlanner.domain.Crop;
import com.agriculture.AgroPlanner.repository.CropRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
@SuppressWarnings("unused")
public class CropServiceImpl implements CropService{
    @Autowired
    private CropRepository cropRepository;

    private static ResponseEntity<Page<Crop>> getPageResponseEntity(Page<Crop> crops) {
        return (crops.isEmpty()) ? ResponseEntity.noContent().build()
                : ResponseEntity.ok(crops);
    }

    public ResponseEntity<Crop> createCrop(Crop crop) {
        Crop savedCrop = cropRepository.save(crop);
        return ResponseEntity.status(HttpStatus.CREATED).body(savedCrop);
    }

    public ResponseEntity<Page<Crop>> retrieveAllCrops(Pageable pageable) {
        Page<Crop> crops = cropRepository.findAll(pageable);
        return getPageResponseEntity(crops);
    }

    public ResponseEntity<Crop> retrieveCrop(Long cropID) {
        Optional<Crop> cropOpt = cropRepository.findById(cropID);
        return cropOpt.map(ResponseEntity::ok)
                .orElseGet(() -> ResponseEntity.notFound().build());
    }

    public void deleteCrop(Long cropID) {
        cropRepository.deleteById(cropID);
    }
}
