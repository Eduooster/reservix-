package br.com.reservix.core.application.ports.out;

public interface PassEncoderService {
    String hash(String senhaPura);
    boolean matches(String senhaPura, String senhaHash);
}
