package br.com.reservix.core.application.usecases.auth;

public record LoginUserCommand(String email, String password) {
}
