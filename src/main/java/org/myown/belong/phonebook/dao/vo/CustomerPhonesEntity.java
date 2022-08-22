package org.myown.belong.phonebook.dao.vo;


import javax.persistence.*;

@Entity
@Table(name = "Customer_phone")
public class CustomerPhonesEntity {
    @Id
    @Column(name = "ID")
    private Long customerPhoneId;

    @Column(name = "CUSTOMER_ID")
    private Long customerId;

    @OneToOne
    @JoinColumn(name = "PHONE_ID")
    private PhoneEntity phone;

    public CustomerPhonesEntity() {
        super();
    }

    public CustomerPhonesEntity(Long customerPhoneId, Long customerId, PhoneEntity phone) {
        this.customerPhoneId = customerPhoneId;
        this.customerId = customerId;
        this.phone = phone;
    }

    public Long getCustomerPhoneId() {
        return customerPhoneId;
    }

    public void setCustomerPhoneId(Long customerPhoneId) {
        this.customerPhoneId = customerPhoneId;
    }

    public Long getCustomerId() {
        return customerId;
    }

    public void setCustomerId(Long customerId) {
        this.customerId = customerId;
    }

    public PhoneEntity getPhone() {
        return phone;
    }

    public void setPhone(PhoneEntity phone) {
        this.phone = phone;
    }

    @Override
    public String toString() {
        return "CustomerPhonesEntity{" +
                "customerPhoneId=" + customerPhoneId +
                ", customerId=" + customerId +
                ", phone=" + phone +
                '}';
    }
}
