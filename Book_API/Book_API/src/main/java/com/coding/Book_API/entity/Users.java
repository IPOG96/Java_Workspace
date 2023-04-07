package com.coding.Book_API.entity;


import com.coding.Book_API.common.Constant;
import org.joda.time.DateTime;

import javax.persistence.*;
import java.time.LocalDateTime;

@Entity
public class Users {

    @Id
    @GeneratedValue(generator = "book", strategy = GenerationType.SEQUENCE)
    @SequenceGenerator(name = "book", sequenceName = "book_seq1", initialValue = 1, allocationSize = 1)
    private Long id;
    private String name;
    private String gender;

    private String password;

    private String ssoType;
    private DateTime loginAt;
    private Integer loginCount = 0;
    private LocalDateTime createdAt;
    private LocalDateTime updatedAt;
    private String emailId;
    private Integer phoneNumber;
    private String userType = Constant.USER_TYPE.NORMAL;
    private Boolean isActive = true;

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }


    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getGender() {
        return gender;
    }

    public void setGender(String gender) {
        this.gender = gender;
    }

    public String getEmailId() {
        return emailId;
    }

    public void setEmailId(String emailId) {
        this.emailId = emailId;
    }

    public Integer getPhoneNumber() {
        return phoneNumber;
    }

    public void setPhoneNumber(Integer phoneNumber) {
        this.phoneNumber = phoneNumber;
    }

    public String getUserType() {
        return userType;
    }

    public void setUserType(String userType) {
        this.userType = userType;
    }

    public Boolean getActive() {
        return isActive;
    }

    public void setActive(Boolean active) {
        isActive = active;
    }

    public String getSsoType() {
        return ssoType;
    }

    public void setSsoType(String ssoType) {
        this.ssoType = ssoType;
    }

    public DateTime getLoginAt() {
        return loginAt;
    }

    public void setLoginAt(DateTime loginAt) {
        this.loginAt = loginAt;
    }

    public Integer getLoginCount() {
        return loginCount;
    }

    public void setLoginCount(Integer loginCount) {
        this.loginCount = loginCount;
    }

    public LocalDateTime getCreatedAt() {
        return createdAt;
    }

    public void setCreatedAt(LocalDateTime createdAt) {
        this.createdAt = createdAt;
    }

    public LocalDateTime getUpdatedAt() {
        return updatedAt;
    }

    public void setUpdatedAt(LocalDateTime updatedAt) {
        this.updatedAt = updatedAt;
    }


    @PrePersist
    public void onSave() {

        LocalDateTime currentDateTime = LocalDateTime.now();


        this.createdAt = currentDateTime;


        this.updatedAt = currentDateTime;
    }


    @PostPersist
    public void onUpdate() {

        LocalDateTime currentDateTime = LocalDateTime.now();

        this.updatedAt = currentDateTime;

    }


}
