/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package edu.mac.issuetracker.staff.dto;

import edu.mac.issuetracker.staff.entity.Staff;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Pattern;
import jakarta.validation.constraints.Size;

/**
 *
 * @author Sam
 */
public class StaffUpdateDTO {

    //@Pattern(regexp = "^(?=.*[0-9])(?=.*[a-z])(?=.*[A-Z])(?=.*[~`!@#$%^&*()-_\\=+[{]}|;:\'\\\",<.>/?])(?=\\S+$).{8,}$"
    //      , message = "Password must contain at least 3 out of 4 (uppercase, lowercase, number or symbol). Minimum 8 Characters")
    @NotBlank(message = "Field cannot be empty")
    private String currentPassword;

    //@Pattern(regexp = "^(?=.*[0-9])(?=.*[a-z])(?=.*[A-Z])(?=.*[~`!@#$%^&*()-_\\=+[{]}|;:\'\\\",<.>/?])(?=\\S+$).{8,}$"
    //      , message = "Password must contain at least 3 out of 4 (uppercase, lowercase, number or symbol). Minimum 8 Characters")
    @NotBlank(message = "Field cannot be empty")
    private String newPassword;

    //@Pattern(regexp = "^(?=.*[0-9])(?=.*[a-z])(?=.*[A-Z])(?=.*[~`!@#$%^&*()-_\\=+[{]}|;:\'\\\",<.>/?])(?=\\S+$).{8,}$"
    //      , message = "Password must contain at least 3 out of 4 (uppercase, lowercase, number or symbol). Minimum 8 Characters")
    @NotBlank(message = "Field cannot be empty")
    private String confirmNewPassword;

    @Email(message = "Invalid Email")
    @NotBlank(message = "Field cannot be empty")
    private String currentEmail;

    @Email(message = "Invalid Email")
    private String newEmail;

    @Pattern(regexp = "^[A-Za-z]+[A-Za-z ,.'-/]*$", message = "Invalid name")
    @Size(min = 1, max = 64)
    private String firstName;

    @Pattern(regexp = "^[A-Za-z]+[A-Za-z ,.'-/]*$", message = "Invalid name")
    @Size(min = 1, max = 32)
    private String lastName;

    private String role;

    public StaffUpdateDTO() {
    }

    public StaffUpdateDTO(String currentPassword, String newPassword, String confirmNewPassword, String currentEmail, String newEmail, String firstName, String lastName, String role) {
        this.currentPassword = currentPassword;
        this.newPassword = newPassword;
        this.confirmNewPassword = confirmNewPassword;
        this.currentEmail = currentEmail;
        this.newEmail = newEmail;
        this.firstName = firstName;
        this.lastName = lastName;
        this.role = role;
    }

    public Staff toStaff() {
        Staff staff = new Staff(this.newEmail, this.firstName, this.lastName, this.role);
        return staff;
    }

    public String getCurrentPassword() {
        return currentPassword;
    }

    public void setCurrentPassword(String currentPassword) {
        this.currentPassword = currentPassword;
    }

    public String getNewPassword() {
        return newPassword;
    }

    public void setNewPassword(String newPassword) {
        this.newPassword = newPassword;
    }

    public String getConfirmNewPassword() {
        return confirmNewPassword;
    }

    public void setConfirmNewPassword(String confirmNewPassword) {
        this.confirmNewPassword = confirmNewPassword;
    }

    public String getCurrentEmail() {
        return currentEmail;
    }

    public void setCurrentEmail(String currentEmail) {
        this.currentEmail = currentEmail;
    }

    public String getNewEmail() {
        return newEmail;
    }

    public void setNewEmail(String newEmail) {
        this.newEmail = newEmail;
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
}
