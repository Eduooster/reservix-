package br.com.reservix.core.application.usecases.company.usecases;

import br.com.reservix.core.application.ports.out.CompanyRepository;
import br.com.reservix.core.application.usecases.company.CompanyDetailOutPut;
import br.com.reservix.core.application.usecases.company.UpdateCompanyCommand;

public class UpdateCompanyUseCase {

    private final CompanyRepository companyRepository;

    public UpdateCompanyUseCase(CompanyRepository companyRepository) {
        this.companyRepository = companyRepository;
    }

    public CompanyDetailOutPut execute(UpdateCompanyCommand command) {
        return null;
    }
}
