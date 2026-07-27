package br.com.reservix.infrastructure.web.mapper;

import br.com.reservix.core.application.usecases.company.CompanyDetailOutPut;
import br.com.reservix.core.application.usecases.company.CreateCompanyCommand;
import br.com.reservix.core.application.usecases.company.UpdateCompanyCommand;
import br.com.reservix.core.domain.entities.company.Company;
import br.com.reservix.infrastructure.persistence.entities.CompanyEntity;
import br.com.reservix.infrastructure.web.dtos.company.CompanyResponse;
import br.com.reservix.infrastructure.web.dtos.company.CreateCompanyRequest;
import br.com.reservix.infrastructure.web.dtos.company.UpdateCompanyRequest;
import org.mapstruct.Builder;
import org.mapstruct.Mapper;
import org.springframework.data.domain.Page;

@Mapper(componentModel = "spring",builder = @Builder(disableBuilder = true))
public interface CompanyMapper {
    UpdateCompanyCommand toUpdateCompanyCommand(Long id,  UpdateCompanyRequest request);

    CompanyResponse toCompanyResponse(CompanyDetailOutPut output);

    CreateCompanyCommand toCreateCompanyCommand(CreateCompanyRequest request);

    default Page<CompanyResponse> toCompanyResponse(Page<CompanyDetailOutPut> page) {
        return page.map(this::toCompanyResponse);
    }

    CompanyEntity toEntity(Company company);
}
