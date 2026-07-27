package br.com.reservix.core.application.usecases.company.usecases;

import br.com.reservix.core.application.ports.out.CompanyRepository;
import br.com.reservix.core.application.usecases.company.CompanyDetailOutPut;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

public class FindAllCompaniesUseCase {
    private final CompanyRepository companyRepository;

    public FindAllCompaniesUseCase(CompanyRepository companyRepository) {
        this.companyRepository = companyRepository;
    }

    public Page<CompanyDetailOutPut> execute(Pageable pageable) {
        return companyRepository.findAll(pageable)
                .map(company -> new CompanyDetailOutPut(
                        company.getId(),
                        company.getName(),
                        company.getCnpj(),
                        company.isActive(),
                        company.getCreatedAt(),
                        company.getUpdatedAt(),
                        company.getWorkdayStart(),
                        company.getWorkdayEnd()
                ));
    }
}
