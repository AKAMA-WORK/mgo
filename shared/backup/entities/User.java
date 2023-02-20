/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mgo.entities;

import io.quarkus.hibernate.orm.panache.PanacheEntityBase;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

import javax.persistence.*;
import java.io.Serializable;
import java.util.Date;
import java.util.HashSet;
import java.util.Set;

/**
 *
 * @author ginot.antsanirina
 */
@Entity
@Table(name = "user")
public class User extends PanacheEntityBase implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "id_user")
    private Integer idUser;

    @Column(name = "creation_date")
    @Temporal(TemporalType.TIMESTAMP)
    @CreationTimestamp
    private Date createdAt;

    @Column(name = "creation_id_user")
    private Integer createdBy;

    @Column(name = "update_date")
    @Temporal(TemporalType.TIMESTAMP)
    @UpdateTimestamp
    private Date updatedAt;

    @Column(name = "update_id_user")
    private Integer updatedBy;

    @Column(name = "civility")
    private String civility;

    @Column(name = "full_name")
    private String fullName;

    @Column(name = "phone")
    private String phone;

    @Column(name = "email")
    private String email;

    @Column(name = "id_number")
    private String idNumber;

    @Column(name = "id_issue_date")
    @Temporal(TemporalType.DATE)
    private Date idIssueDate;

    @Column(name = "id_issue_location")
    private String idIssueLocation;

    @Column(name = "id_duplicate_date")
    @Temporal(TemporalType.DATE)
    private Date idDuplicateDate;

    @Column(name = "id_duplicate_location")
    private String idDuplicateLocation;

    @Column(name = "id_type")
    private String idType;

    @Basic()
    @Column(name = "username")
    private String username;

    @Column(name = "password")
    private String password;


    @ManyToMany(cascade = { CascadeType.ALL })
    @JoinTable(
      name = "userpermission", 
      joinColumns = { @JoinColumn(name = "iduser") }, 
      inverseJoinColumns = { @JoinColumn(name = "idpermission") }
    )
    Set<Permission> permissions = new HashSet<Permission>();

    @JoinColumn(name = "role", referencedColumnName = "idrole")
    @ManyToOne
    private Role role;
    
    public User() {
    }
    public String getUsername() {
        return username;
    }
    public void setUsername(String username) {
        this.username = username;
    }
    public String getPassword() {
        return password;
    }
    public void setPassword(String password) {
        this.password = password;
    }
 
 
    public Role getRole() {
        return role;
    }
    public void setRole(Role role) {
        this.role = role;
    }
    public Set<Permission> getPermissions() {
        return permissions;
    }
    public void setPermissions(Set<Permission> permissions) {
        this.permissions = permissions;
    }


    
}
