package br.com.reservix.infrastructure.persistence.adapters;

import br.com.reservix.core.application.ports.out.CompanyRepository;
import br.com.reservix.core.domain.entities.company.Company;
import br.com.reservix.infrastructure.persistence.entities.CompanyEntity;
import br.com.reservix.infrastructure.persistence.mapper.CompanyEntityMapper;
import br.com.reservix.infrastructure.persistence.repositories.JpaCompanyRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Repository;

import java.util.Optional;


@Repository
@RequiredArgsConstructor
public class ComapanyRepositoryImpl implements CompanyRepository {

    private final JpaCompanyRepository jpaCompanyRepository;
    private final CompanyEntityMapper companyEntityMapper;



    @Override
    public Company save(Company company) {
        CompanyEntity companyEntity = companyEntityMapper.toEntity(company);
        CompanyEntity savedCompany = jpaCompanyRepository.save(companyEntity);

        return companyEntityMapper.toDomain(savedCompany);
    }

    @Override
    public boolean existsByCnpj(String cnpj) {
        return jpaCompanyRepository.existsByCnpj(cnpj);
    }

    @Override
    public Optional<Company> findById(Long id) {

       return jpaCompanyRepository.findById(id).map(companyEntityMapper::toDomain);


    }

    @Override
    public Page<Company> findAll(Pageable pageable) {
       return jpaCompanyRepository.findAll(pageable).map(companyEntityMapper::toDomain);
    }
}
