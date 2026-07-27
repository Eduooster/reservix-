package br.com.reservix.core.domain.entities;

import br.com.reservix.core.domain.entities.company.Company;

import java.time.LocalDateTime;

public class Notification {
    private Long id;

    private User user;

    private String title;
    private String message;

    private NotificationType type;

    private NotificationStatus status;

    private LocalDateTime sentAt;
    private LocalDateTime createdAt;

    private Company company;

    public Notification(Long id, User user, String title, String message, NotificationType type, NotificationStatus status, LocalDateTime sentAt, LocalDateTime createdAt, Company company) {
        this.id = id;
        this.user = user;
        this.title = title;
        this.message = message;
        this.type = type;
        this.status = status;
        this.sentAt = sentAt;
        this.createdAt = createdAt;
        this.company = company;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public NotificationType getType() {
        return type;
    }

    public void setType(NotificationType type) {
        this.type = type;
    }

    public NotificationStatus getStatus() {
        return status;
    }

    public void setStatus(NotificationStatus status) {
        this.status = status;
    }

    public LocalDateTime getSentAt() {
        return sentAt;
    }

    public void setSentAt(LocalDateTime sentAt) {
        this.sentAt = sentAt;
    }

    public LocalDateTime getCreatedAt() {
        return createdAt;
    }

    public void setCreatedAt(LocalDateTime createdAt) {
        this.createdAt = createdAt;
    }

    public Company getCompany() {
        return company;
    }

    public void setCompany(Company company) {
        this.company = company;
    }
}
