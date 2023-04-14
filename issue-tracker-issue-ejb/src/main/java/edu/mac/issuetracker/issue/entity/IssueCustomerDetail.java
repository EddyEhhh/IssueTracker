/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package edu.mac.issuetracker.issue.entity;

import edu.mac.issuetracker.issue.dto.IssueCustomerDetailDTO;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.NamedQueries;
import jakarta.persistence.NamedQuery;
import jakarta.persistence.Table;

/**
 *
 * @author Sam
 */
@Entity
@Table(name = "issueCustomerDetail")
public class IssueCustomerDetail {

    @Id
    @Column(name = "id")
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    @Column(name = "hostName")
    private String hostName;

    @Column(name = "deviceModel")
    private String deviceModel;

    @Column(name = "deviceSerialNumber")
    private String deviceSerialNumber;

    @Column(name = "chargerSerialNumber")
    private String chargerSerialNumber;

    @Column(name = "operatingSystem")
    private String operatingSystem;

    @Column(name = "antivirus")
    private String antivirus;

    @Column(name = "name")
    private String name;

    @Column(name = "email")
    private String email;

    @Column(name = "contactNumber")
    private String contactNumber;

    @Column(name = "devicePassword")
    private String devicePassword;

    public IssueCustomerDetail() {
    }

    public IssueCustomerDetail(String hostName, String deviceModel, String deviceSerialNumber, String chargerSerialNumber, String operatingSystem, String antivirus, String name, String email, String contactNumber, String devicePassword) {
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

    public IssueCustomerDetailDTO toDTO() {
        IssueCustomerDetailDTO issueCustomerDetailDTO = new IssueCustomerDetailDTO(this.hostName, this.deviceModel, this.deviceSerialNumber, this.chargerSerialNumber, this.operatingSystem, this.antivirus, this.name, this.email, this.contactNumber, this.devicePassword);
        return issueCustomerDetailDTO;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
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
