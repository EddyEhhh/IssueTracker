/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package edu.mac.issuetracker.issue.dto;

import edu.mac.issuetracker.issue.entity.IssueCustomerDetail;
import jakarta.validation.constraints.NotBlank;

/**
 *
 * @author Sam
 */
public class IssueCustomerDetailDTO {

    @NotBlank(message = "Field cannot be empty")
    private String hostName;

    @NotBlank(message = "Field cannot be empty")
    private String deviceModel;

    @NotBlank(message = "Field cannot be empty")
    private String deviceSerialNumber;

    @NotBlank(message = "Field cannot be empty")
    private String chargerSerialNumber;

    @NotBlank(message = "Field cannot be empty")
    private String operatingSystem;

    @NotBlank(message = "Field cannot be empty")
    private String antivirus;

    @NotBlank(message = "Field cannot be empty")
    private String name;

    @NotBlank(message = "Field cannot be empty")
    private String email;

    @NotBlank(message = "Field cannot be empty")
    private String contactNumber;

    private String devicePassword;

    public IssueCustomerDetailDTO() {
    }

    public IssueCustomerDetailDTO(String hostName, String deviceModel, String deviceSerialNumber, String chargerSerialNumber, String operatingSystem, String antivirus, String name, String email, String contactNumber, String devicePassword) {
        this.hostName = hostName;
        this.deviceModel = deviceModel;
        this.deviceSerialNumber = deviceSerialNumber;
        this.chargerSerialNumber = chargerSerialNumber;
        this.operatingSystem = operatingSystem;
        this.antivirus = antivirus;
        this.name = name;
        this.email = email;
        this.contactNumber = contactNumber;
        this.devicePassword = devicePassword;
    }

    public IssueCustomerDetail toIssueCustomerDetail() {
        IssueCustomerDetail issueCustomerDetail = new IssueCustomerDetail(this.hostName, this.deviceModel, this.deviceSerialNumber, this.chargerSerialNumber, this.operatingSystem, this.antivirus, this.name, this.email, this.contactNumber, this.devicePassword);
        return issueCustomerDetail;
    }

    public String getHostName() {
        return hostName;
    }

    public void setHostName(String hostName) {
        this.hostName = hostName;
    }

    public String getDeviceModel() {
        return deviceModel;
    }

    public void setDeviceModel(String deviceModel) {
        this.deviceModel = deviceModel;
    }

    public String getDeviceSerialNumber() {
        return deviceSerialNumber;
    }

    public void setDeviceSerialNumber(String deviceSerialNumber) {
        this.deviceSerialNumber = deviceSerialNumber;
    }

    public String getChargerSerialNumber() {
        return chargerSerialNumber;
    }

    public void setChargerSerialNumber(String chargerSerialNumber) {
        this.chargerSerialNumber = chargerSerialNumber;
    }

    public String getOperatingSystem() {
        return operatingSystem;
    }

    public void setOperatingSystem(String operatingSystem) {
        this.operatingSystem = operatingSystem;
    }

    public String getAntivirus() {
        return antivirus;
    }

    public void setAntivirus(String antivirus) {
        this.antivirus = antivirus;
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

    public String getContactNumber() {
        return contactNumber;
    }

    public void setContactNumber(String contactNumber) {
        this.contactNumber = contactNumber;
    }

    public String getDevicePassword() {
        return devicePassword;
    }

    public void setDevicePassword(String devicePassword) {
        this.devicePassword = devicePassword;
    }
}
