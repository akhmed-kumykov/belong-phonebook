package org.myown.belong.phonebook.dao.vo;

import org.hibernate.annotations.DynamicUpdate;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "Phone")
@DynamicUpdate
public class PhoneEntity {
    @Id
    @Column(name = "ID")
    private Long phoneId;

    @Column(name = "PHONE_NO")
    private String phoneNumber;

    @Column(name = "ACTIVE")
    private String activeFlag;

    public PhoneEntity() {
        super();
    }

    public PhoneEntity(Long phoneId, String phoneNumber, String activeFlag) {
        super();
        this.phoneId = phoneId;
        this.phoneNumber = phoneNumber;
        this.activeFlag = activeFlag;
    }

    public Long getPhoneId() {
        return phoneId;
    }

    public void setPhoneId(Long phoneId) {
        this.phoneId = phoneId;
    }

    public String getPhoneNumber() {
        return phoneNumber;
    }

    public void setPhoneNumber(String phoneNumber) {
        this.phoneNumber = phoneNumber;
    }

    public String getActiveFlag() {
        return activeFlag;
    }

    public void setActiveFlag(String activeFlag) {
        this.activeFlag = activeFlag;
    }

    @Override
    public String toString() {
        return "PhoneEntity{" +
                "phoneId=" + phoneId +
                ", phoneNumber='" + phoneNumber + '\'' +
                ", activeFlag='" + activeFlag + '\'' +
                '}';
    }
}
