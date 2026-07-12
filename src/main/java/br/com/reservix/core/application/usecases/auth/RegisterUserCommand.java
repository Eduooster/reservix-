package br.com.reservix.core.application.usecases.auth;

public record RegisterUserCommand(String email, String password, String name){
}
