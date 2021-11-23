package com.cjss.ecommerce.entity;

import javax.persistence.*;
import java.time.LocalDateTime;

@Entity
@Table(name="login_entity")
public class LoginEntity {
    @Id
    private String email;
    @Column
    private String password;
    @Column
    private LocalDateTime tokenExpiryDateTime;

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public LocalDateTime getTokenExpiryDateTime() {
        return tokenExpiryDateTime;
    }

    public void setTokenExpiryDateTime(LocalDateTime tokenExpiryDateTime) {
        this.tokenExpiryDateTime = tokenExpiryDateTime;
    }
}
