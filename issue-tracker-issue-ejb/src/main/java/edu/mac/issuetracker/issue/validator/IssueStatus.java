/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package edu.mac.issuetracker.issue.validator;

import jakarta.validation.Constraint;
import jakarta.validation.Payload;
import java.lang.annotation.Documented;
import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

/**
 *
 * @author E
 */

@Documented
@Constraint(validatedBy = {IssueStatusConstraintValidator.class})
@Retention(RetentionPolicy.RUNTIME)
@Target({ ElementType.TYPE, ElementType.FIELD, ElementType.PARAMETER, ElementType.CONSTRUCTOR, ElementType.ANNOTATION_TYPE})
public @interface IssueStatus {
    
    String message() default "Invalid status value";
    
    Class<?>[] groups() default {};
    
    Class<? extends Payload>[] payload() default {};
    
}
