package com.agriculture.AgroPlanner.domain;

import jakarta.persistence.Column;
import jakarta.persistence.Embeddable;

import java.io.Serializable;

import static com.agriculture.AgroPlanner.constants.ColumnNames.HARVEST_ENTRY_CROPID;
import static com.agriculture.AgroPlanner.constants.ColumnNames.HARVEST_ENTRY_EMPLOYEEID;

@Embeddable
@SuppressWarnings("unused")
public class HarvestEntryKey implements Serializable {
    @Column(name = HARVEST_ENTRY_EMPLOYEEID)
    private Long employeeID;
    @Column(name = HARVEST_ENTRY_CROPID)
    private Long cropID;

    public HarvestEntryKey(Long employeeID, Long cropID) {
        this.employeeID = employeeID;
        this.cropID = cropID;
    }

    public HarvestEntryKey() {
    }

    public static HarvestEntryKey create(Long employeeID, Long cropID) {
        return new HarvestEntryKey(employeeID, cropID);
    }

    public Long getEmployeeID() {
        return employeeID;
    }

    public void setEmployeeID(Long employeeID) {
        this.employeeID = employeeID;
    }

    public Long getCropID() {
        return cropID;
    }

    public void setCropID(Long cropID) {
        this.cropID = cropID;
    }
}
