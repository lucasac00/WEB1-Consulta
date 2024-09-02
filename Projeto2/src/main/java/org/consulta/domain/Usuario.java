package org.consulta.domain;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;

@SuppressWarnings("serial")
@Entity
@Table(name = "Usuario")
public class Usuario extends AbstractEntity<Long> {
    @NotBlank
    @Column(nullable = false, length = 65, unique = true)
    private String username;

    @NotBlank
    @Column(nullable = false, length = 64)
    private String password;

    @NotBlank
    @Size(max = 60)
    @Column(nullable = false, length = 60)
    private String email;

    @NotBlank
    @Column(nullable = false, length = 60)
    private String name;

    @NotBlank
    @Column(nullable = false, length = 14)
    private String cpf;

    @NotBlank
    @Column(nullable = false, length = 50)
    private String role;

    @Column(nullable = false)
    private boolean enabled;

    public String getUsername() {
        return username;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getEmail() {
        return email;
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

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getCpf() {
        return cpf;
    }

    public void setCpf(String cPF) {
        cpf = cPF;
    }

    public String getRole() {
        return role;
    }

    public void setRole(String role) {
        this.role = role;
    }

    public boolean isEnabled() {
        return enabled;
    }

    public void setEnabled(boolean enabled) {
        this.enabled = enabled;
    }
}