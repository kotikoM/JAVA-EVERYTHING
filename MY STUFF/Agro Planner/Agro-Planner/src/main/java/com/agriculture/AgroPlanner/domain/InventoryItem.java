package com.agriculture.AgroPlanner.domain;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;

import static com.agriculture.AgroPlanner.constants.ColumnNames.*;


@Entity
public class InventoryItem {
    @Id
    @Column(name = INVENTORY_ITEMID)
    private Long itemID;

    @Column(name = INVENTORY_NAME)
    private String name;

    @Column(name = INVENTORY_QUANTITY)
    private Integer quantity;

    @Column(name = INVENTORY_PRICE)
    private Double price;

    @Column(name = INVENTORY_EMPLOYEEID)
    private Long employeeID;

    public InventoryItem(Long itemID, String name, Integer quantity, Double price, Long employeeID) {
        this.itemID = itemID;
        this.name = name;
        this.quantity = quantity;
        this.price = price;
        this.employeeID = employeeID;
    }

    public InventoryItem() {
    }

    public Long getItemID() {
        return itemID;
    }

    public String getName() {
        return name;
    }

    public Integer getQuantity() {
        return quantity;
    }

    public Double getPrice() {
        return price;
    }

    public Long getEmployeeID() {
        return employeeID;
    }
}
