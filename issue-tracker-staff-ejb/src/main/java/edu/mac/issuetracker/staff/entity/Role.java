/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package edu.mac.issuetracker.staff.entity;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;

/**
 *
 * @author E
 */

@Entity
@Table(name="role")
public class Role {
    
    @Id
    @Column(name="id")
    @GeneratedValue(strategy=GenerationType.AUTO)
    private Long id;
    
    @Column(name="displayName")
    private String displayName;
    
    @Column(name="internalName")
    private String internalName;
    
    @Column(name="description")
    private String description;
    
    @Column(name="permission")
    private String permission;

    public Role(){}
    
    public Role(String displayName, String internalName, String description, String permission){
        this.displayName = displayName;
        this.internalName = internalName;
        this.description = description;
        this.permission = permission;
    }
    
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getDisplayName() {
        return displayName;
    }

    public void setDisplayName(String displayName) {
        this.displayName = displayName;
    }

    public String getInternalName() {
        return internalName;
    }

    public void setInternalName(String internalName) {
        this.internalName = internalName;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getPermission() {
        return permission;
    }

    public void setPermission(String permission) {
        this.permission = permission;
    }
}
