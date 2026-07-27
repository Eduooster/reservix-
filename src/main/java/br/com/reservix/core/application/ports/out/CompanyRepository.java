package br.com.reservix.core.application.ports.out;

import br.com.reservix.core.domain.entities.company.Company;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.Optional;


public interface CompanyRepository {
    Company save(Company company);

    boolean existsByCnpj(String cnpj);

    Optional<Company> findById(Long id);

    Page<Company> findAll(Pageable pageable);
}
