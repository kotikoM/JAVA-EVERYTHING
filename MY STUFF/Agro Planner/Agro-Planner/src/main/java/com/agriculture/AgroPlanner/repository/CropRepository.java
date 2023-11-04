package com.agriculture.AgroPlanner.repository;

import com.agriculture.AgroPlanner.domain.Crop;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface CropRepository extends JpaRepository<Crop, Long> {
}
