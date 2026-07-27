package br.com.reservix.core.domain.entities;



import java.time.LocalDateTime;

public class EmailNotification {

    private Long id;

    private String recipient;

    private String template;

    private EmailNotificationStatus status;

    private String providerMessageId;

    private String errorMessage;

    private Integer attempts;

    private LocalDateTime createdAt;

    private LocalDateTime sentAt;

    public EmailNotification(
            Long id,
            String recipient,
            String template,
            EmailNotificationStatus status,
            String providerMessageId,
            String errorMessage,
            Integer attempts,
            LocalDateTime createdAt,
            LocalDateTime sentAt
    ) {
        this.id = id;
        this.recipient = recipient;
        this.template = template;
        this.status = status;
        this.providerMessageId = providerMessageId;
        this.errorMessage = errorMessage;
        this.attempts = attempts;
        this.createdAt = createdAt;
        this.sentAt = sentAt;
    }

    public static EmailNotification create(String recipient, String template) {
        return new EmailNotification(
                null,
                recipient,
                template,
                EmailNotificationStatus.PENDING,
                null,
                null,
                0,
                LocalDateTime.now(),
                null
        );
    }

    public void markAsSent(String providerMessageId) {
        this.status = EmailNotificationStatus.SENT;
        this.providerMessageId = providerMessageId;
        this.sentAt = LocalDateTime.now();
        this.errorMessage = null;
        this.attempts++;
    }

    public void markAsFailed(String errorMessage) {
        this.status = br.com.reservix.core.domain.entities.EmailNotificationStatus.FAILED;
        this.errorMessage = errorMessage;
        this.attempts++;
    }

    public Long getId() {
        return id;
    }

    public String getRecipient() {
        return recipient;
    }

    public String getTemplate() {
        return template;
    }

    public EmailNotificationStatus getStatus() {
        return status;
    }

    public String getProviderMessageId() {
        return providerMessageId;
    }

    public String getErrorMessage() {
        return errorMessage;
    }

    public Integer getAttempts() {
        return attempts;
    }

    public LocalDateTime getCreatedAt() {
        return createdAt;
    }

    public LocalDateTime getSentAt() {
        return sentAt;
    }
}
