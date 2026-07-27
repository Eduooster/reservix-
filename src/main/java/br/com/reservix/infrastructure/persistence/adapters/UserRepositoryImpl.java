package br.com.reservix.infrastructure.persistence.adapters;

import br.com.reservix.core.application.ports.out.UserRepository;
import br.com.reservix.core.domain.entities.User;
import br.com.reservix.infrastructure.persistence.entities.UserEntity;
import br.com.reservix.infrastructure.persistence.mapper.UserEntityMapper;
import br.com.reservix.infrastructure.persistence.repositories.JpaUserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

import java.util.Optional;

@Component
@RequiredArgsConstructor
public class UserRepositoryImpl implements UserRepository {

    private final JpaUserRepository jpaUserRepository;
    private final UserEntityMapper userEntityMapper;



    @Override
    public Optional<User> findByEmail(String email) {
        return jpaUserRepository.findByEmail(email).map(
                user -> userEntityMapper.toDomain(user)
        );
    }

    @Override
    public User save(User user) {
        UserEntity userEntity = userEntityMapper.toEntity(user);
        UserEntity savedUser = jpaUserRepository.save(userEntity);
        return userEntityMapper.toDomain(savedUser);
    }

    @Override
    public boolean existsByEmail(String email) {
        return jpaUserRepository.existsByEmail(email);
    }

    @Override
    public Optional<User> findById(Long userId) {
        return jpaUserRepository.findById(userId).map(
                userEntity -> userEntityMapper.toDomain(userEntity)
        );
    }
}
