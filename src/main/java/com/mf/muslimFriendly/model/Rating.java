package com.mf.muslimFriendly.model;

import com.fasterxml.jackson.annotation.JsonBackReference;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;

@Getter
@Setter
@Entity
@Table(name = "rating")
public class Rating {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "school_id")
    @JsonBackReference
    private School school;

    private int dressing; // students comfort level in wearing traditional/cultural/islamic clothing
    private int muslimPresence; // muslim presence on the campus on the campus
    private int staffMuslimPresence; // muslim students presence on the campus
    private int leadership; // muslim leadership presence on the campus
    private int beardFriendliness;
    private int hijabFriendliness;
    private int halalFoodOptions; // on-campus halal food options
    private int prayerSpace;
    private int wuduArea;
    private int dormFriendliness; // student dorm friendliness

    public Rating() {
    }

    public Rating(int dressing, int muslimPresence, int staffMuslimPresence,
                  int leadership, int beardFriendliness, int hijabFriendliness,
                  int halalFoodOptions, int prayerSpace, int wuduArea, int dormFriendliness,
                  School school) {
        this.dressing = dressing;
        this.muslimPresence = muslimPresence;
        this.staffMuslimPresence = staffMuslimPresence;
        this.leadership = leadership;
        this.beardFriendliness = beardFriendliness;
        this.hijabFriendliness = hijabFriendliness;
        this.halalFoodOptions = halalFoodOptions;
        this.prayerSpace = prayerSpace;
        this.wuduArea = wuduArea;
        this.dormFriendliness = dormFriendliness;
        this.school = school;
    }

}
