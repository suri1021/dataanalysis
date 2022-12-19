package com.myproject.dataanalysys.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.Data;

import javax.persistence.*;

@Entity
@Table(name = "statistics")
@Data
public class StatisticsData {

    @JsonIgnore
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(unique = true, nullable = false, insertable = false, updatable = false)
    @Id
    private Long id;

    @Column(name = "year")
    private int year;

    @Column(name = "cityid")
    private String cityId;

    @Column(name = "avgMaxTemparature")
    private int avgMaxTemparature;

    @Column(name = "avgMinTemparature")
    private int avgMinTemparature;

    @Column(name = "totalPrecipitation")
    private int totalPrecipitation;
}