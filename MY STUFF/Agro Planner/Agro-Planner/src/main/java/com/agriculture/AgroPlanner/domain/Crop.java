package com.agriculture.AgroPlanner.domain;

import jakarta.persistence.*;

import java.time.LocalDate;

import static com.agriculture.AgroPlanner.constants.ColumnNames.*;

@Entity
@SuppressWarnings("unused")
public class Crop {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = CROP_CROPID)
    private Long cropID;
    @Column(name = CROP_NAME)
    private String name;
    @Column(name = CROP_SPECIES)
    private String species;
    @Column(name = CROP_PLANTING_DATE)
    @Temporal(TemporalType.DATE)
    private LocalDate plantingDate;
    @Column(name = CROP_HARVEST_DATE)
    @Temporal(TemporalType.DATE)
    private LocalDate harvestDate;
    @Column(name = CROP_DESCRIPTION)
    private String description;
    @Column(name = CROP_REQUIREMENTS)
    private String requirements;

    public Crop(Long cropID, String name, String species, LocalDate plantingDate, LocalDate harvestDate, String description, String requirements) {
        this.cropID = cropID;
        this.name = name;
        this.species = species;
        this.plantingDate = plantingDate;
        this.harvestDate = harvestDate;
        this.description = description;
        this.requirements = requirements;
    }

    public Crop() {
    }

    public Long getCropID() {
        return cropID;
    }

    public String getName() {
        return name;
    }

    public String getSpecies() {
        return species;
    }

    public LocalDate getPlantingDate() {
        return plantingDate;
    }

    public LocalDate getHarvestDate() {
        return harvestDate;
    }

    public String getDescription() {
        return description;
    }

    public String getRequirements() {
        return requirements;
    }

    @Override
    public String toString() {
        return "Crop{" +
                "cropID=" + cropID +
                ", name='" + name + '\'' +
                ", species='" + species + '\'' +
                ", plantingDate=" + plantingDate +
                ", harvestDate=" + harvestDate +
                ", description='" + description + '\'' +
                ", requirements='" + requirements + '\'' +
                '}';
    }
}
