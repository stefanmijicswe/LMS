package org.singidunum.backend.dto;

public class UpdateFacultyDTO {
    private String name;
    private String description;
    private String phoneNumber;
    private String officialEmail;
    private String website;
    private Long addressId;
    private Long deanId;
    private Long universityId;

    public UpdateFacultyDTO() {
    }

    public String getName() { return name; }
    public void setName(String name) { this.name = name; }

    public String getDescription() { return description; }
    public void setDescription(String description) { this.description = description; }

    public String getPhoneNumber() { return phoneNumber; }
    public void setPhoneNumber(String phoneNumber) { this.phoneNumber = phoneNumber; }

    public String getOfficialEmail() { return officialEmail; }
    public void setOfficialEmail(String officialEmail) { this.officialEmail = officialEmail; }

    public String getWebsite() { return website; }
    public void setWebsite(String website) { this.website = website; }

    public Long getAddressId() { return addressId; }
    public void setAddressId(Long addressId) { this.addressId = addressId; }

    public Long getDeanId() { return deanId; }
    public void setDeanId(Long deanId) { this.deanId = deanId; }

    public Long getUniversityId() { return universityId; }
    public void setUniversityId(Long universityId) { this.universityId = universityId; }
}