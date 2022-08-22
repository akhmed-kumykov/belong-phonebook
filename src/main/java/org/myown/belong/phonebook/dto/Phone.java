package org.myown.belong.phonebook.dto;

public class Phone {
    private Long phoneId;

    private String phoneNumber;

    private boolean activeFlag;

    public Phone() {
        super();
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

    public boolean isActiveFlag() {
        return activeFlag;
    }

    public void setActiveFlag(boolean activeFlag) {
        this.activeFlag = activeFlag;
    }

    @Override
    public String toString() {
        return "Phone{" +
                "phoneId=" + phoneId +
                ", phoneNumber='" + phoneNumber + '\'' +
                ", activeFlag=" + activeFlag +
                '}';
    }
}
