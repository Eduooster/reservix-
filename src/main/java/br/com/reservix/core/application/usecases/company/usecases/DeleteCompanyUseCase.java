package br.com.reservix.core.application.usecases.company.usecases;

import br.com.reservix.core.application.ports.out.CompanyRepository;
import br.com.reservix.core.domain.entities.company.Company;
import br.com.reservix.core.application.usecases.company.exceptions.CompanyNotFoundException;

public class DeleteCompanyUseCase {
    private final CompanyRepository companyRepository;


    public DeleteCompanyUseCase(CompanyRepository companyRepository) {
        this.companyRepository = companyRepository;
    }


    public void execute(Long id) {
        Company company = companyRepository.findById(id)
                .orElseThrow(()-> new CompanyNotFoundException("Company not found"));

        company.deactivate();

        companyRepository.save(company);

    }
}
