package com.mf.muslimFriendly.model;

import javax.persistence.*;
import java.util.HashSet;
import java.util.Set;

import com.fasterxml.jackson.annotation.JsonManagedReference;
import lombok.*;

@Getter
@Setter
@Entity
@Table(name = "school")
public class School {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String name;
    private String description;
    private boolean halal;
    private boolean prayerSpace;
    private boolean wuduArea;

    @OneToMany(cascade = CascadeType.ALL, mappedBy = "school")
    @JsonManagedReference
    private Set<Rating> ratings = new HashSet<>();

    public School() {
    }

    public School(String name, String description,
                  boolean halal, boolean prayerSpace, boolean wuduArea) {
        this.name = name;
        this.description = description;
        this.halal = halal;
        this.prayerSpace = prayerSpace;
        this.wuduArea = wuduArea;
    }

    @Override
    public String toString() {
        return "School{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", description='" + description + '\'' +
                ", halal=" + halal +
                ", prayerSpace=" + prayerSpace +
                ", wuduArea=" + wuduArea +
                ", ratings=" + ratings +
                '}';
    }
}
