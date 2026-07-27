package br.com.reservix.core.domain.entities.invitation;

import br.com.reservix.core.domain.entities.User;
import br.com.reservix.core.domain.entities.UserRole;
import br.com.reservix.core.domain.entities.company.Company;

import java.time.LocalDateTime;
import java.util.UUID;

public class Invitation {
    private Long id;

    private User invitedBy;

    private Company company;

    private String email;

    private UUID token;

    private UserRole role;

    private InvitationStatus status;

    private LocalDateTime expiresAt;

    private LocalDateTime createdAt;

    private LocalDateTime acceptedAt;

    public Invitation() {
    }

    public Invitation(Long id, User invitedBy, Company company, String email, UUID token, UserRole role, InvitationStatus status, LocalDateTime expiresAt, LocalDateTime createdAt, LocalDateTime acceptedAt) {
        this.id = id;
        this.invitedBy = invitedBy;
        this.company = company;
        this.email = email;
        this.token = token;
        this.role = role;
        this.status = status;
        this.expiresAt = expiresAt;
        this.createdAt = createdAt;
        this.acceptedAt = acceptedAt;
    }

    public static Invitation create(Company company, String email, UserRole role,User inviter) {
        Invitation invitation = new Invitation();
        invitation.setCompany(company);
        invitation.setEmail(email);
        invitation.setRole(role);
        invitation.setStatus(InvitationStatus.PENDING);
        invitation.setExpiresAt(LocalDateTime.now().plusDays(7));
        invitation.setCreatedAt(LocalDateTime.now());
        invitation.setToken(UUID.randomUUID());
        invitation.setInvitedBy(inviter);



        return invitation;
    }

    public User getInvitedBy() {
        return invitedBy;
    }

    public void setInvitedBy(User invitedBy) {
        this.invitedBy = invitedBy;
    }

    public LocalDateTime getAcceptedAt() {
        return acceptedAt;
    }

    public void setAcceptedAt(LocalDateTime acceptedAt) {
        this.acceptedAt = acceptedAt;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Company getCompany() {
        return company;
    }

    public void setCompany(Company company) {
        this.company = company;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public UUID getToken() {
        return token;
    }

    public void setToken(UUID token) {
        this.token = token;
    }

    public UserRole getRole() {
        return role;
    }

    public void setRole(UserRole role) {
        this.role = role;
    }

    public InvitationStatus getStatus() {
        return status;
    }

    public void setStatus(InvitationStatus status) {
        this.status = status;
    }

    public LocalDateTime getExpiresAt() {
        return expiresAt;
    }

    public void setExpiresAt(LocalDateTime expiresAt) {
        this.expiresAt = expiresAt;
    }

    public LocalDateTime getCreatedAt() {
        return createdAt;
    }

    public void setCreatedAt(LocalDateTime createdAt) {
        this.createdAt = createdAt;
    }

    public void accept() {
        this.acceptedAt = LocalDateTime.now();
        this.status = InvitationStatus.ACCEPTED;
    }
}
