package com.javaloping.homr.app.model;

/**
 * @author victormiranda@gmail.com
 */

import com.javaloping.homr.app.type.RentPeriodType;
import com.javaloping.homr.app.type.RentPriceType;

import javax.persistence.*;
import java.math.BigDecimal;

@DiscriminatorValue("rent")
public class RentProperty extends Property {

    @Embedded
    private RentFeatures rentFeatures;

    @Enumerated(EnumType.STRING)
    private RentPeriodType rentPeriod;

    @Enumerated(EnumType.STRING)
    private RentPriceType priceType;

    @Column( precision = 10, scale = 2, columnDefinition="DECIMAL(10,2)")
    private BigDecimal deposit;

    public RentFeatures getRentFeatures() {
        return rentFeatures;
    }

    public void setRentFeatures(RentFeatures rentFeatures) {
        this.rentFeatures = rentFeatures;
    }

    public RentPeriodType getRentPeriod() {
        return rentPeriod;
    }

    public void setRentPeriod(RentPeriodType rentPeriod) {
        this.rentPeriod = rentPeriod;
    }

    public RentPriceType getPriceType() {
        return priceType;
    }

    public void setPriceType(RentPriceType priceType) {
        this.priceType = priceType;
    }

    public BigDecimal getDeposit() {
        return deposit;
    }

    public void setDeposit(BigDecimal deposit) {
        this.deposit = deposit;
    }
}
