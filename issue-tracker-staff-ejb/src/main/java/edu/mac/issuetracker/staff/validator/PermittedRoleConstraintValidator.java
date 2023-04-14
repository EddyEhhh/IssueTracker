/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package edu.mac.issuetracker.staff.validator;

import jakarta.validation.ConstraintValidator;
import jakarta.validation.ConstraintValidatorContext;
import java.util.Arrays;

/**
 *
 * @author E
 */
public class PermittedRoleConstraintValidator implements ConstraintValidator<PermittedRole, String>{

    
    private static String[] permittedRole = {"admin", "user"};
    @Override
    public boolean isValid(String value, ConstraintValidatorContext context) {
        return Arrays.asList(permittedRole).contains(value); //To change body of generated methods, choose Tools | Templates.
    }
    
}
