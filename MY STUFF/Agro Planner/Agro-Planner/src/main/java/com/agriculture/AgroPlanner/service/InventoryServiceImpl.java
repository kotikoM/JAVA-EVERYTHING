package com.agriculture.AgroPlanner.service;

import com.agriculture.AgroPlanner.domain.InventoryItem;
import com.agriculture.AgroPlanner.repository.InventoryRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
@SuppressWarnings("unused")
public class InventoryServiceImpl implements InventoryService {
    @Autowired
    private InventoryRepository inventoryRepository;

    private static ResponseEntity<Page<InventoryItem>> getPageResponseEntity(Page<InventoryItem> entries) {
        return (entries.isEmpty()) ? ResponseEntity.noContent().build()
                : ResponseEntity.ok(entries);
    }

    public ResponseEntity<InventoryItem> createItem(InventoryItem inventoryItem) {
        InventoryItem savedItem = inventoryRepository.save(inventoryItem);
        return ResponseEntity.status(HttpStatus.CREATED).body(savedItem);
    }

    public ResponseEntity<Page<InventoryItem>> retrieveAllItems(Pageable pageable) {
        Page<InventoryItem> items = inventoryRepository.findAll(pageable);
        return getPageResponseEntity(items);
    }

    public ResponseEntity<InventoryItem> retrieveItem(Long itemID) {
        Optional<InventoryItem> itemOpt = inventoryRepository.findById(itemID);
        return itemOpt.map(ResponseEntity::ok)
                .orElseGet(() -> ResponseEntity.notFound().build());
    }

    public void deleteItem(Long itemID) {
        inventoryRepository.deleteById(itemID);
    }

    public ResponseEntity<Page<InventoryItem>> retrieveItemsByEmployeeID(Pageable pageable, Long employeeID) {
        Page<InventoryItem> items = inventoryRepository.findByEmployeeID(pageable, employeeID);
        return getPageResponseEntity(items);
    }
}
