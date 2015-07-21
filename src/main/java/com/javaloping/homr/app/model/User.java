package com.javaloping.homr.app.model;

import javax.persistence.*;

/**
 * @author victormiranda@gmail.com
 */
@Entity
public class User {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @Embedded
    private EntityModification entityModification;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public EntityModification getEntityModification() {
        return entityModification;
    }

    public void setEntityModification(EntityModification entityModification) {
        this.entityModification = entityModification;
    }
}
