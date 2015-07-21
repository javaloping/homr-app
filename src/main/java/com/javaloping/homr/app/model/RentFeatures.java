package com.javaloping.homr.app.model;

import javax.persistence.Embeddable;

/**
 * @author victormiranda@gmail.com
 */

@Embeddable
public class RentFeatures {
    private Boolean petsAllowed;
    private Boolean furnished;
    private Boolean dishwasher;

    public Boolean isPetsAllowed() {
        return petsAllowed;
    }

    public void setPetsAllowed(Boolean petsAllowed) {
        this.petsAllowed = petsAllowed;
    }

    public Boolean isFurnished() {
        return furnished;
    }

    public void setFurnished(Boolean furnished) {
        this.furnished = furnished;
    }

    public Boolean isDishwasher() {
        return dishwasher;
    }

    public void setDishwasher(Boolean dishwasher) {
        this.dishwasher = dishwasher;
    }
}
