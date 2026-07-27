package br.com.reservix.infrastructure.persistence.mapper;

import br.com.reservix.core.domain.entities.company.Company;
import br.com.reservix.infrastructure.persistence.entities.CompanyEntity;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface CompanyEntityMapper {
    CompanyEntity toEntity(Company company);

    Company toDomain(CompanyEntity savedCompany);
}
