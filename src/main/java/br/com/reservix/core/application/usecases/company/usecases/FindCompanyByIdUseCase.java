package br.com.reservix.core.application.usecases.company.usecases;

import br.com.reservix.core.application.ports.out.CompanyRepository;
import br.com.reservix.core.application.usecases.company.CompanyDetailOutPut;

public class FindCompanyByIdUseCase {
    private final CompanyRepository companyRepository;

    public FindCompanyByIdUseCase(CompanyRepository companyRepository) {
        this.companyRepository = companyRepository;
    }

    public CompanyDetailOutPut execute(Long id) {
        return null;
    }
}
