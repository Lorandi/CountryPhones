package com.rodrigolorandi.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.With;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.criteria.CriteriaBuilder;

import java.util.ArrayList;
import java.util.List;

import static javax.persistence.GenerationType.IDENTITY;

@Entity
@Data
@With
@AllArgsConstructor
@NoArgsConstructor
public class Country implements Comparable<Country>{

    private String country;
    private String ddi;
    private Integer count;

    private List<String> added = new ArrayList<>();

    public Country(String country, String numbers, Integer count) {
        this.country = country;
        this.ddi = numbers;
        this.count = count;
    }

    @Override
    public int compareTo(Country o) {
        if (this.count > o.getCount()) {
            return -1;
        } if (this.count < o.getCount()) {
            return 1;
        }
        return 0;
    }
}
