package br.com.reservix.core.domain.entities;



import br.com.reservix.core.domain.entities.company.Company;

import java.time.LocalDateTime;

public class User {
    private Long id;
    private String name;
    private String email;
    private String password;
    private UserRole role;
    private boolean active;
    private LocalDateTime createdAt;
    private LocalDateTime updatedAt;
    private Company company;

    public User() {
    }

    public User(Long id, String name, String email, String password, UserRole role, boolean active, LocalDateTime createdAt, LocalDateTime updatedAt, Company company) {
        this.id = id;
        this.name = name;
        this.email = email;
        this.password = password;
        this.role = role;
        this.active = active;
        this.createdAt = createdAt;
        this.updatedAt = updatedAt;
        this.company = company;
    }

    public static User createCompanyAdmin(
            Company company,
            String name,
            String email,
            String password
    ) {

        User user = new User();

        user.setCompany(company);
        user.setName(name);
        user.setEmail(email);
        user.setPassword(password);

        user.setRole(UserRole.ADMIN_COMPANY);
        user.setActive(true);

        user.setCreatedAt(LocalDateTime.now());
        user.setUpdatedAt(LocalDateTime.now());

        return user;
    }

    public static User createCompanyEmployee(
            Company company,
            String name,
            String email,
            String password
    ) {

        User user = new User();

        user.setCompany(company);
        user.setName(name);
        user.setEmail(email);
        user.setPassword(password);

        user.setRole(UserRole.EMPLOYEE);
        user.setActive(true);

        user.setCreatedAt(LocalDateTime.now());
        user.setUpdatedAt(LocalDateTime.now());

        return user;
    }

    public static User create(
            String name,
            String email,
            String password,
            UserRole role,
            Company company
    ) {

        User user = new User();

        user.setCompany(company);
        user.setName(name);
        user.setEmail(email);
        user.setPassword(password);

        user.setRole(role);
        user.setActive(true);

        user.setCreatedAt(LocalDateTime.now());
        user.setUpdatedAt(LocalDateTime.now());

        return user;
    }


    public Long getId() { return id; }
    public void setId(Long id) { this.id = id; }

    public String getName() { return name; }
    public void setName(String name) { this.name = name; }

    public String getEmail() { return email; }
    public void setEmail(String email) { this.email = email; }

    public String getPassword() { return password; }
    public void setPassword(String password) { this.password = password; }

    public UserRole getRole() { return role; }
    public void setRole(UserRole role) { this.role = role; }

    public boolean isActive() { return active; }
    public void setActive(boolean active) { this.active = active; }

    public LocalDateTime getCreatedAt() { return createdAt; }
    public void setCreatedAt(LocalDateTime createdAt) { this.createdAt = createdAt; }

    public LocalDateTime getUpdatedAt() { return updatedAt; }
    public void setUpdatedAt(LocalDateTime updatedAt) { this.updatedAt = updatedAt; }

    public Company getCompany() {
        return company;
    }

    public void setCompany(Company company) {
        this.company = company;
    }
}