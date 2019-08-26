package com.example.application.model;

import javax.persistence.*;
import javax.validation.constraints.NotEmpty;
import java.io.Serializable;

@Entity
@Table(name = "admin")
public class Admin implements Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Integer id;
    @NotEmpty
    @Column(nullable = false, unique = true)
    private String adminName;

    @NotEmpty
    @Column(nullable = false,unique = true)
    private String adminEmail;
    @NotEmpty
    private String password;
    @Transient
    private String passwordConf;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getAdminName() {
        return adminName;
    }

    public void setAdminName(String adminName) {
        this.adminName = adminName;
    }

    public String getAdminEmail() {
        return adminEmail;
    }

    public void setAdminEmail(String adminEmail) {
        this.adminEmail = adminEmail;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getPasswordConf() {
        return passwordConf;
    }

    public void setPasswordConf(String passwordConf) {
        this.passwordConf = passwordConf;
    }
}
