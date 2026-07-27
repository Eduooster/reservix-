package br.com.reservix.core.domain.entities;

import java.time.LocalDateTime;

public class AcessToken {

    private Long id;

    private String token;

    private LocalDateTime expiresAt;

    private LocalDateTime usedAt;

    private User user;
}
