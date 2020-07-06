package com.example.propertyrealtors.model;

import java.util.List;

public class Agent {
    String id, agentId, locality, address, contactPerson, agencyName, description, operatingSince;
    List<String> transactionType, propertyType;

    public Agent(String id, String agentId, String locality, String address, String contactPerson, String agencyName,
                 String description, String operatingSince, List<String> transactionType, List<String> propertyType) {
        this.id = id;
        this.agentId= agentId;
        this.locality = locality;
        this.address = address;
        this.contactPerson = contactPerson;
        this.agencyName = agencyName;
        this.description = description;
        this.operatingSince = operatingSince;
        this.transactionType = transactionType;
        this.propertyType = propertyType;
    }

    public Agent() {
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getAgentId() {
        return agentId;
    }

    public void setAgentId(String agentId) {
        this.agentId = agentId;
    }

    public String getLocality() {
        return locality;
    }

    public void setLocality(String locality) {
        this.locality = locality;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getContactPerson() {
        return contactPerson;
    }

    public void setContactPerson(String contactPerson) {
        this.contactPerson = contactPerson;
    }

    public String getAgencyName() {
        return agencyName;
    }

    public void setAgencyName(String agencyName) {
        this.agencyName = agencyName;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getOperatingSince() {
        return operatingSince;
    }

    public void setOperatingSince(String operatingSince) {
        this.operatingSince = operatingSince;
    }

    public List<String> getTransactionType() {
        return transactionType;
    }

    public void setTransactionType(List<String> transactionType) {
        this.transactionType = transactionType;
    }

    public List<String> getPropertyType() {
        return propertyType;
    }

    public void setPropertyType(List<String> propertyType) {
        this.propertyType = propertyType;
    }
}