package br.com.reservix.infrastructure.security;

import br.com.reservix.infrastructure.persistence.repositories.JpaUserRepositoryAdapter;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class UserDetailsServiceImpl implements UserDetailsService {
    private final JpaUserRepositoryAdapter jpaUserRepositoryAdapter;



    public UserDetails loadUserByUsername(String email) throws UsernameNotFoundException {
        return jpaUserRepositoryAdapter.findByEmail(email)
                .orElseThrow(() -> new UsernameNotFoundException("Usuário não encontrado"));
    }
}