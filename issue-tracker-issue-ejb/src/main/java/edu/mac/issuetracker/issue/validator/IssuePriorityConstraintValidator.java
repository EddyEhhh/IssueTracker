/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package edu.mac.issuetracker.issue.validator;

import jakarta.validation.ConstraintValidator;
import jakarta.validation.ConstraintValidatorContext;
import java.util.Arrays;

/**
 *
 * @author E
 */
public class IssuePriorityConstraintValidator implements ConstraintValidator<IssuePriority, String>{

    
    private static String[] permittedPriority = {"Low", "Medium", "High"};
    @Override
    public boolean isValid(String value, ConstraintValidatorContext context) {
        return Arrays.asList(permittedPriority).contains(value); //To change body of generated methods, choose Tools | Templates.
    }
    
}
