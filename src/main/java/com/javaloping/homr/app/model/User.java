package com.javaloping.homr.app.model;

import javax.persistence.*;

/**
 * @author victormiranda@gmail.com
 */
@Entity
@Table(name = "users")
public class User implements AuditEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    private String username;

    private String password;

    private String email;

    @Embedded
    private EntityModification entityModification;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public EntityModification getEntityModification() {
        return entityModification;
    }

    public void setEntityModification(EntityModification entityModification) {
        this.entityModification = entityModification;
    }
}
