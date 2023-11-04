package com.agriculture.AgroPlanner.service;

import com.agriculture.AgroPlanner.domain.InventoryItem;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;

public interface InventoryService {
    ResponseEntity<InventoryItem> createItem(InventoryItem inventoryItem);

    ResponseEntity<Page<InventoryItem>> retrieveAllItems(Pageable pageable);

    ResponseEntity<InventoryItem> retrieveItem(Long itemID);

    void deleteItem(Long itemID);

    ResponseEntity<Page<InventoryItem>> retrieveItemsByEmployeeID(Pageable pageable, Long employeeID);
}
