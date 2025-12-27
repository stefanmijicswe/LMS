package org.singidunum.backend.dto;

public class UpdateUniversityDTO {
    private String name;
    private String description;
    private String phoneNumber;
    private String officialEmail;
    private String website;
    private Long addressId;
    private Long rectorId;

    public UpdateUniversityDTO() {
    }

    public UpdateUniversityDTO(String name, String description, String phoneNumber, String officialEmail, String website, Long addressId, Long rectorId) {
        this.name = name;
        this.description = description;
        this.phoneNumber = phoneNumber;
        this.officialEmail = officialEmail;
        this.website = website;
        this.addressId = addressId;
        this.rectorId = rectorId;
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

    public String getPhoneNumber() {
        return phoneNumber;
    }

    public void setPhoneNumber(String phoneNumber) {
        this.phoneNumber = phoneNumber;
    }

    public String getOfficialEmail() {
        return officialEmail;
    }

    public void setOfficialEmail(String officialEmail) {
        this.officialEmail = officialEmail;
    }

    public String getWebsite() {
        return website;
    }

    public void setWebsite(String website) {
        this.website = website;
    }

    public Long getAddressId() {
        return addressId;
    }

    public void setAddressId(Long addressId) {
        this.addressId = addressId;
    }
    public Long getRectorId() {
        return rectorId;
    }
    public void setRectorId(Long rectorId) {
        this.rectorId = rectorId;
    }
}