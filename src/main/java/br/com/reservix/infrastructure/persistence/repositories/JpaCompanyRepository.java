package br.com.reservix.infrastructure.persistence.repositories;

import br.com.reservix.infrastructure.persistence.entities.CompanyEntity;
import org.springframework.data.jpa.repository.JpaRepository;

public interface JpaCompanyRepository extends JpaRepository<CompanyEntity, Long> {
    boolean existsByCnpj(String cnpj);
}
