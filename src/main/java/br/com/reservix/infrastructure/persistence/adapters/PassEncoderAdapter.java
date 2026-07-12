package br.com.reservix.infrastructure.persistence.adapters;

import br.com.reservix.core.application.ports.out.PassEncoderService;
import org.jspecify.annotations.Nullable;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;

@Component
public class PassEncoderAdapter implements PassEncoderService {

    private final PasswordEncoder passwordEncoder;
    public PassEncoderAdapter(PasswordEncoder passwordEncoder) {
        this.passwordEncoder = passwordEncoder;
    }
    @Override
    public String hash(String senhaPura) {
        return passwordEncoder.encode(senhaPura) ;
    }

    @Override
    public boolean matches(String senhaPura, String senhaHash) {
        return passwordEncoder.matches(senhaPura, senhaHash);
    }
}
