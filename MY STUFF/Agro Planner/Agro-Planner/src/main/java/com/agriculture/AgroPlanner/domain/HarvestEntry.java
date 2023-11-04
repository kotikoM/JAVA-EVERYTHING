package com.agriculture.AgroPlanner.domain;

import jakarta.persistence.*;

import java.time.LocalDate;

import static com.agriculture.AgroPlanner.constants.ColumnNames.*;

@Entity
@SuppressWarnings("unused")
public class HarvestEntry {
    @EmbeddedId
    private HarvestEntryKey harvestEntryKey;
    @ManyToOne
    @MapsId(HARVEST_ENTRY_EMPLOYEEID)
    @JoinColumn(name = HARVEST_ENTRY_EMPLOYEEID)
    private Employee employee;
    @ManyToOne
    @MapsId(HARVEST_ENTRY_CROPID)
    @JoinColumn(name = HARVEST_ENTRY_CROPID)
    private Crop crop;
    @Column(name = HARVEST_ENTRY_QUANTITY)
    private Double quantityKg;
    @Column(name = HARVEST_ENTRY_DATE)
    @Temporal(TemporalType.DATE)
    private LocalDate date;
    @Column(name = HARVEST_ENTRY_NOTES)
    private String notes;

    public HarvestEntry(HarvestEntryKey harvestEntryKey, Employee employee, Crop crop, Double quantityKg, LocalDate date, String notes) {
        this.harvestEntryKey = harvestEntryKey;
        this.employee = employee;
        this.crop = crop;
        this.quantityKg = quantityKg;
        this.date = date;
        this.notes = notes;
    }

    public HarvestEntry() {

    }

    public HarvestEntryKey getHarvestEntryKey() {
        return harvestEntryKey;
    }

    public Employee getEmployee() {
        return employee;
    }

    public Crop getCrop() {
        return crop;
    }

    public Double getQuantityKg() {
        return quantityKg;
    }

    public LocalDate getDate() {
        return date;
    }

    public String getNotes() {
        return notes;
    }
}
