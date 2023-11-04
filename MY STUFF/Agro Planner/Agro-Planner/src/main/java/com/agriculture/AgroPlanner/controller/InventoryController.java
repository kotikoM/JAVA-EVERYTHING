package com.agriculture.AgroPlanner.controller;

import com.agriculture.AgroPlanner.domain.InventoryItem;
import com.agriculture.AgroPlanner.service.InventoryServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import static com.agriculture.AgroPlanner.constants.Endpoints.*;

@RestController
@RequestMapping(INVENTORY_ENDPOINT)
@SuppressWarnings("unused")
public class InventoryController {
    @Autowired
    private InventoryServiceImpl inventoryService;

    @PostMapping
    public ResponseEntity<InventoryItem> insertItem(
            @RequestBody InventoryItem item) {
        return inventoryService.createItem(item);
    }

    @GetMapping
    public ResponseEntity<Page<InventoryItem>> retrieveAllItems(Pageable pageable) {
        return inventoryService.retrieveAllItems(pageable);
    }

    @GetMapping(INVENTORY_ITEM_ENDPOINT)
    public ResponseEntity<InventoryItem> retrieveItem(
            @PathVariable Long itemID) {
        return inventoryService.retrieveItem(itemID);
    }

    @DeleteMapping(INVENTORY_ITEM_ENDPOINT)
    public void deleteItem(
            @PathVariable Long itemID) {
        inventoryService.deleteItem(itemID);
    }

    @GetMapping(INVENTORY_EMPLOYEE_ENDPOINT)
    public ResponseEntity<Page<InventoryItem>> retrieveItemsByEmployeeID(Pageable pageable,
                                                                         @PathVariable Long employeeID) {
        return inventoryService.retrieveItemsByEmployeeID(pageable, employeeID);
    }
}
