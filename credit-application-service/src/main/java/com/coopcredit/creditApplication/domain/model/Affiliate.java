package com.coopcredit.creditApplication.domain.model;

public class Affiliate {
    private Long id;
    private String name;
    private String email;
    private String document;
    private Double salary;
    private String address;
    private java.time.LocalDate startDate;
    private boolean active;

    public Affiliate() {
    }

    public Affiliate(Long id, String name, String email, String document, Double salary, String address, java.time.LocalDate startDate, boolean active) {
        this.id = id;
        this.name = name;
        this.email = email;
        this.document = document;
        this.salary = salary;
        this.address = address;
        this.startDate = startDate;
        this.active = active;
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

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getDocument() {
        return document;
    }

    public void setDocument(String document) {
        this.document = document;
    }

    public Double getSalary() {
        return salary;
    }

    public void setSalary(Double salary) {
        this.salary = salary;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public java.time.LocalDate getStartDate() {
        return startDate;
    }

    public void setStartDate(java.time.LocalDate startDate) {
        this.startDate = startDate;
    }

    public boolean isActive() {
        return active;
    }

    public void setActive(boolean active) {
        this.active = active;
    }
}
