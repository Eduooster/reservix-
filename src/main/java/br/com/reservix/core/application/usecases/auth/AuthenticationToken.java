package br.com.reservix.core.application.usecases.auth;

import java.time.Instant;

public record AuthenticationToken(String acessToken, Instant expiresIn) {
}
