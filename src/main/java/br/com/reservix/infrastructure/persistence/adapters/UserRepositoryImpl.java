package br.com.reservix.infrastructure.persistence.adapters;

import br.com.reservix.core.application.ports.out.UserRepository;
import br.com.reservix.core.domain.entities.User;
import br.com.reservix.infrastructure.persistence.entities.UserEntity;
import br.com.reservix.infrastructure.persistence.mapper.UserMapper.UserEntityMapper;
import br.com.reservix.infrastructure.persistence.repositories.JpaUserRepositoryAdapter;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Component
@RequiredArgsConstructor
public class UserRepositoryImpl implements UserRepository {

    private final JpaUserRepositoryAdapter jpaUserRepositoryAdapter;
    private final UserEntityMapper userEntityMapper;


    @Override
    public Optional<User> findByEmail(String email) {
        return jpaUserRepositoryAdapter.findByEmail(email).map(
                user -> userEntityMapper.toDomain(user)
        );
    }

    @Override
    public User save(User user) {
        UserEntity userEntity = userEntityMapper.toEntity(user);
        UserEntity savedUser = jpaUserRepositoryAdapter.save(userEntity);
        return userEntityMapper.toDomain(savedUser);
    }
}
