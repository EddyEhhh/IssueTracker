/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package edu.mac.issuetracker.staff.dto;

import edu.mac.issuetracker.staff.entity.Staff;
import edu.mac.issuetracker.staff.validator.PermittedRole;
import jakarta.security.enterprise.credential.Credential;
import jakarta.security.enterprise.credential.Password;
import jakarta.security.enterprise.credential.UsernamePasswordCredential;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Pattern;
import jakarta.validation.constraints.Size;
import java.awt.image.BufferedImage;

/**
 *
 * @author Jaibun Thana
 */
public class StaffDTO {

    @Email(message = "Invalid Email")
    @NotBlank(message = "Field cannot be empty")
    private String email;

    @NotBlank(message = "Field cannot be empty")
    @Pattern(regexp = "^[A-Za-z]+[A-Za-z ,.'-/]*$", message = "Invalid name")
    @Size(min = 1, max = 64)
    private String firstName;

    @NotBlank(message = "Field cannot be empty")
    @Pattern(regexp = "^[A-Za-z]+[A-Za-z ,.'-/]*$", message = "Invalid name")
    @Size(min = 1, max = 32)
    private String lastName;

    @NotBlank(message = "Field cannot be empty")
    @PermittedRole
    private String role;

    //Only accepts specific file type. Add avoid steg.
    //@Pattern(regexp = "^[\\w\\-. ]+(\\.png)$", message = "Invalid file name")
    private String profilePicture;

    //@Pattern(regexp = "^(?=.*[0-9])(?=.*[a-z])(?=.*[A-Z])(?=.*[~`!@#$%^&*()-_\\=+[{]}|;:\'\\\",<.>/?])(?=\\S+$).{8,}$"
    //      , message = "Password must contain at least 3 out of 4 (uppercase, lowercase, number or symbol). Minimum 8 Characters")
    @NotBlank(message = "Field cannot be empty")
    private String password;

    private boolean delete;

    private BufferedImage auth2FAQR;

    public StaffDTO() {
    }

    public StaffDTO(String email, String firstName, String lastName, String role, String profilePicture, boolean delete) {
        this.email = email;
        this.firstName = firstName;
        this.lastName = lastName;
        this.role = role;
        this.profilePicture = profilePicture;
        this.delete = delete;
    }

//    public StaffDTO(String email, String firstName, String lastName, String profilePicture,){
//        this.email = email;
//        this.firstName = firstName;
//        this.lastName = lastName;
//        this.profilePicture = profilePicture;
//        this.delete = delete;
//    }
    public Staff toStaff() {
        Staff staff = new Staff(this.email, this.firstName, this.lastName, this.role, this.password, this.profilePicture);
        return staff;
    }

    public Credential toCredential() {
        Credential credential = new UsernamePasswordCredential(this.email, new Password(this.password));
        return credential;
    }

    public String getInitial() {
        String firstNameInitial = Character.toString(this.firstName.charAt(0));
        String lastNameInitial = Character.toString(this.lastName.substring(this.lastName.lastIndexOf(" ") + 1).charAt(0));
        return firstNameInitial + lastNameInitial;
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

    public String getRole() {
        return role;
    }

    public void setRole(String role) {
        this.role = role;
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

    public boolean isDelete() {
        return delete;
    }

    public void setDelete(boolean delete) {
        this.delete = delete;
    }

    public BufferedImage getAuth2FAQR() {
        return auth2FAQR;
    }

    public void setAuth2FAQR(BufferedImage auth2FAQR) {
        this.auth2FAQR = auth2FAQR;
    }
}
