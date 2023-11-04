package com.agriculture.AgroPlanner.repository;

import com.agriculture.AgroPlanner.domain.InventoryItem;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.domain.Page;
import org.springframework.stereotype.Repository;

@Repository
public interface InventoryRepository extends JpaRepository<InventoryItem, Long> {
    Page<InventoryItem> findByEmployeeID(Pageable pageable, Long employeeID);
}
