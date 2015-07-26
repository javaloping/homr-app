package com.javaloping.homr.app.model;

import com.javaloping.homr.app.type.AgeType;
import com.javaloping.homr.app.type.EnergyCertificationType;
import com.javaloping.homr.app.type.PropertyType;

import javax.persistence.*;
import java.math.BigDecimal;
import java.util.Date;

/**
 * @author victormiranda@gmail.com
 */
@Entity
@Inheritance
@Table(name = "properties")
@DiscriminatorColumn(name = "mode")
public abstract class Property implements AuditEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @ManyToOne
    @JoinColumn(name="ownerId")
    private User owner;

    private String name;

    private String description;

    private Date publishedDate;

    @ManyToOne
    @JoinColumn(name = "addressId")
    private Address address;

    @Column( precision = 10, scale = 2, columnDefinition="DECIMAL(10,2)")
    private BigDecimal price;

    @Embedded
    private Features features = new Features();

    @Enumerated(EnumType.STRING)
    private PropertyType type;

    @Enumerated(EnumType.STRING)
    private AgeType age;

    @Enumerated(EnumType.STRING)
    private EnergyCertificationType energyCertification;

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

    public Date getPublishedDate() {
        return publishedDate;
    }

    public void setPublishedDate(Date publishedDate) {
        this.publishedDate = publishedDate;
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

    public AgeType getAge() {
        return age;
    }

    public void setAge(AgeType age) {
        this.age = age;
    }

    public EnergyCertificationType getEnergyCertification() {
        return energyCertification;
    }

    public void setEnergyCertification(EnergyCertificationType energyCertification) {
        this.energyCertification = energyCertification;
    }

    public EntityModification getEntityModification() {
        return entityModification;
    }

    public void setEntityModification(EntityModification entityModification) {
        this.entityModification = entityModification;
    }
}
