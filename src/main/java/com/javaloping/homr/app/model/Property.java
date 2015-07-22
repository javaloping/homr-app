package com.javaloping.homr.app.model;

import com.javaloping.homr.app.type.PropertyType;

import javax.persistence.*;
import java.math.BigDecimal;

/**
 * @author victormiranda@gmail.com
 */
@Entity
@Inheritance
@DiscriminatorColumn(name = "mode")
public abstract class Property {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @ManyToOne
    @JoinColumn(name="ownerId")
    private User owner;

    private String name;

    private String description;

    @ManyToOne
    @JoinColumn(name = "addressId")
    private Address address;

    @Column( precision = 10, scale = 2, columnDefinition="DECIMAL(10,2)")
    private BigDecimal price;

    @Embedded
    private Features features = new Features();

    private PropertyType type;

    @Embedded
    private EntityModification entityModification;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public User getOwner() {
        return owner;
    }

    public void setOwner(User owner) {
        this.owner = owner;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public Address getAddress() {
        return address;
    }

    public void setAddress(Address address) {
        this.address = address;
    }

    public BigDecimal getPrice() {
        return price;
    }

    public void setPrice(BigDecimal price) {
        this.price = price;
    }

    public Features getFeatures() {
        return features;
    }

    public void setFeatures(Features features) {
        this.features = features;
    }

    public PropertyType getType() {
        return type;
    }

    public void setType(PropertyType type) {
        this.type = type;
    }

    public EntityModification getEntityModification() {
        return entityModification;
    }

    public void setEntityModification(EntityModification entityModification) {
        this.entityModification = entityModification;
    }
}
