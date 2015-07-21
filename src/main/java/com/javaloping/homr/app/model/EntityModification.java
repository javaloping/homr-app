package com.javaloping.homr.app.model;

import javax.persistence.Embeddable;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import java.util.Date;

/**
 * @author victormiranda@gmail.com
 */
@Embeddable
public class EntityModification {
    private Date createDate;

    private Date modifyDate;

    @ManyToOne
    @JoinColumn(name="modificationUserId")
    private User createUser;

    @ManyToOne
    @JoinColumn(name="creationUserId")
    private User modifyUser;


    public Date getCreateDate() {
        return createDate;
    }

    public void setCreateDate(Date createDate) {
        this.createDate = createDate;
    }

    public Date getModifyDate() {
        return modifyDate;
    }

    public void setModifyDate(Date modifyDate) {
        this.modifyDate = modifyDate;
    }

    public User getCreateUser() {
        return createUser;
    }

    public void setCreateUser(User createUser) {
        this.createUser = createUser;
    }

    public User getModifyUser() {
        return modifyUser;
    }

    public void setModifyUser(User modifyUser) {
        this.modifyUser = modifyUser;
    }
}
