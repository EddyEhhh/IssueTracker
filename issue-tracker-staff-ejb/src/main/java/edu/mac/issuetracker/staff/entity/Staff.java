/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package edu.mac.issuetracker.staff.entity;

import edu.mac.issuetracker.staff.dto.StaffDTO;
import edu.mac.issuetracker.staff.validator.PermittedRole;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.NamedQueries;
import jakarta.persistence.NamedQuery;
import jakarta.persistence.Table;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Pattern;
import jakarta.validation.constraints.Size;

/**
 *
 * @author Jaibun Thana
 */
@Entity
@Table(name = "staff")
@NamedQueries({
    @NamedQuery(name = "Staff.all", query = "select s from Staff s order by s.id"),
    @NamedQuery(name = "Staff.byEmail", query = "select s from Staff s where s.email = :email")
})
public class Staff {

    @Id
    @Column(name = "id")
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    @Email
    @NotBlank
    @Column(name = "email",
            unique = true)
    private String email;

    @NotBlank
    @Column(name = "firstName")
    @Pattern(regexp = "^[A-Za-z]+[A-Za-z ,.'-/]*$", message = "Invalid name")
    @Size(min = 1, max = 64)
    private String firstName;

    @NotBlank
    @Column(name = "lastName")
    @Pattern(regexp = "^[A-Za-z]+[A-Za-z ,.'-/]*$", message = "Invalid name")
    @Size(min = 1, max = 32)
    private String lastName;

    //8 to 128 character password requiring at least 3 out 4 
    //(uppercase and lowercase letters, numbers and special characters)
    @Pattern(regexp = "^(?=.*[0-9])(?=.*[a-z])(?=.*[A-Z])(?=.*[~`!@#$%^&*()-_\\=+[{]}|;:\'\\\",<.>/?])(?=\\S+$).{8,}$",
             message = "Password must contain at least 3 out of 4 (uppercase, lowercase, number or symbol). Minimum 8 Characters")
    @NotBlank
    @Column(name = "password")
    private String password;

    @Column(name = "profilePicture")
    //Only accepts specific file type. Add avoid steg.
    //@Pattern(regexp = "^[\\w\\-. ]+(\\.jpg)$", message = "Invalid file name")
    private String profilePicture;

    @Column(name = "isFirstLogin")
    private boolean firstLogin;

    @Column(name = "isDelete")
    private boolean delete;

    @Column(name = "role")
    @NotBlank
    //Add list of accepted roles from db
    @PermittedRole
    private String role;

    @Column(name = "secretKey2FA")
    private String secretKey2FA;

    public Staff() {
    }

    public Staff(String email, String firstName, String lastName, String password, String profilePicture, boolean firstLogin, boolean delete, String role, String secretKey2FA) {
        this.email = email;
        this.firstName = firstName;
        this.lastName = lastName;
        this.password = password;
        this.profilePicture = profilePicture;
        this.firstLogin = firstLogin;
        this.delete = delete;
        this.role = role;
        this.secretKey2FA = secretKey2FA;
    }

    public Staff(String email, String firstName, String lastName, String role, String password, String profilePicture) {
        setEmail(email);
        setFirstName(firstName);
        setLastName(lastName);
        setRole(role);
        setPassword(password);
        setProfilePicture(profilePicture);
        setFirstLogin(true);
        setDelete(false);
    }

    public Staff(String email, String firstName, String lastName, String role) {
        setEmail(email);
        setFirstName(firstName);
        setLastName(lastName);
        setRole(role);
    }

    public Staff(String email, String password) {
        setEmail(email);
        setPassword(password);
    }

    public StaffDTO toDTO() {
        StaffDTO staffDTO = new StaffDTO(this.email, this.firstName, this.lastName, this.role, this.profilePicture, this.delete);
        return staffDTO;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email.toLowerCase();
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getProfilePicture() {
        return profilePicture;
    }

    public void setProfilePicture(String profilePicture) {
        this.profilePicture = profilePicture;
    }

    public boolean isFirstLogin() {
        return firstLogin;
    }

    public void setFirstLogin(boolean firstLogin) {
        this.firstLogin = firstLogin;
    }

    public String getRole() {
        return role;
    }

    public void setRole(String role) {
        this.role = role;
    }

    public boolean isDelete() {
        return delete;
    }

    public void setDelete(boolean delete) {
        this.delete = delete;
    }

    public String getSecretKey2FA() {
        return secretKey2FA;
    }

    public void setSecretKey2FA(String secretKey2FA) {
        this.secretKey2FA = secretKey2FA;
    }
}
