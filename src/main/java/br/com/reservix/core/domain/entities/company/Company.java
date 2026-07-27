package br.com.reservix.core.domain.entities.company;

import java.time.LocalDateTime;
import java.time.LocalTime;

public class Company {

    private Long id;
    private String name;
    private String cnpj;
    private boolean active;

    private LocalDateTime createdAt;
    private LocalDateTime updatedAt;
    private LocalTime workdayStart;
    private LocalTime workdayEnd;


    public Company(Long id, String name, String cnpj, boolean active, LocalDateTime createdAt, LocalDateTime updatedAt, LocalTime workdayStart, LocalTime workdayEnd) {
        this.id = id;
        this.name = name;
        this.cnpj = cnpj;
        this.active = active;
        this.createdAt = createdAt;
        this.updatedAt = updatedAt;
        this.workdayStart = workdayStart;
        this.workdayEnd = workdayEnd;
    }

    public Company() {
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getCnpj() {
        return cnpj;
    }

    public void setCnpj(String cnpj) {
        this.cnpj = cnpj;
    }

    public boolean isActive() {
        return active;
    }

    public void setActive(boolean active) {
        this.active = active;
    }

    public LocalDateTime getCreatedAt() {
        return createdAt;
    }

    public void setCreatedAt(LocalDateTime createdAt) {
        this.createdAt = createdAt;
    }

    public LocalDateTime getUpdatedAt() {
        return updatedAt;
    }

    public void setUpdatedAt(LocalDateTime updatedAt) {
        this.updatedAt = updatedAt;
    }

    public LocalTime getWorkdayStart() {
        return workdayStart;
    }

    public void setWorkdayStart(LocalTime workdayStart) {
        this.workdayStart = workdayStart;
    }

    public LocalTime getWorkdayEnd() {
        return workdayEnd;
    }

    public void setWorkdayEnd(LocalTime workdayEnd) {
        this.workdayEnd = workdayEnd;
    }

    public static Company create(String name, String cnpj, LocalTime workdayStart, LocalTime workdayEnd) {

        Company company = new Company();

        company.setName(name);
        company.setCnpj(cnpj);
        company.setActive(true);

        company.setCreatedAt(LocalDateTime.now());
        company.setUpdatedAt(LocalDateTime.now());
        company.setWorkdayStart(workdayStart);
        company.setWorkdayEnd(workdayEnd);

        return company;
    }

    public void deactivate() {
        if (!this.active) {
            throw new CompanyAlreadyInactiveException("Cnpj already in use");
        }

        this.active = false;
        this.updatedAt = LocalDateTime.now();
    }

    @Override
    public String toString() {
        return "Company{" +
                "id=" + id +
                ", name='" + name + '\'' +
                '}';
    }
}
