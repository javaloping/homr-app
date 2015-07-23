package com.javaloping.homr.app.model;

import javax.persistence.Embeddable;

/**
 * @author victormiranda@gmail.com
 */
@Embeddable
public class Features {
    private Integer floor;

    private Float sqMeters;

    private Boolean lift;

    private int bedrooms;

    private int bathrooms;

    public void setBathrooms(int bathrooms) {
        this.bathrooms = bathrooms;
    }

    public Integer getFloor() {
        return floor;
    }

    public void setFloor(Integer floor) {
        this.floor = floor;
    }

    public Float getSqMeters() {
        return sqMeters;
    }

    public void setSqMeters(Float sqMeters) {
        this.sqMeters = sqMeters;
    }

    public Boolean getLift() {
        return lift;
    }

    public void setLift(Boolean lift) {
        this.lift = lift;
    }

    public int getBedrooms() {
        return bedrooms;
    }

    public void setBedrooms(int bedrooms) {
        this.bedrooms = bedrooms;
    }

    public int getBathrooms() {
        return bathrooms;
    }
}
