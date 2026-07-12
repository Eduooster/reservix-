package br.com.reservix.core.application.ports.out;

import br.com.reservix.core.domain.entities.User;

import java.util.Optional;

public interface UserRepository {

    Optional<User> findByEmail(String email);
    User save(User user);
}
