package br.com.reservix.core.application.usecases.company.usecases;

import br.com.reservix.core.application.ports.out.CompanyRepository;
import br.com.reservix.core.application.ports.out.UserRepository;
import br.com.reservix.core.application.usecases.company.CompanyDetailOutPut;
import br.com.reservix.core.application.usecases.company.CreateCompanyCommand;
import br.com.reservix.core.domain.entities.company.Company;
import br.com.reservix.core.domain.entities.User;
import br.com.reservix.core.application.usecases.company.exceptions.CompanyAlreadyExistsException;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.crypto.password.PasswordEncoder;

@Slf4j
public class CreateCompanyUseCase {

    private final CompanyRepository companyRepository;
    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;

    public CreateCompanyUseCase(CompanyRepository companyRepository, UserRepository userRepository, PasswordEncoder passwordEncoder) {
        this.companyRepository = companyRepository;
        this.userRepository = userRepository;

        this.passwordEncoder = passwordEncoder;
    }


    public CompanyDetailOutPut execute(CreateCompanyCommand command) {
        if (companyRepository.existsByCnpj(command.cnpj())) {
            throw new CompanyAlreadyExistsException("Company already exists");
        }

        Company company = Company.create(
                command.companyName(),
                command.cnpj(),
                command.workDayStart(),command.workDayEnd()
        );

        Company savedCompany = companyRepository.save(company);


        User companyAdmin = User.createCompanyAdmin(
                savedCompany,
                command.adminName(),
                command.adminEmail(),
                passwordEncoder.encode(command.adminPassword())
        );

        userRepository.save(companyAdmin);

        return new CompanyDetailOutPut(
                savedCompany.getId(),
                savedCompany.getName(),
                savedCompany.getCnpj(),
                savedCompany.isActive(),
                savedCompany.getCreatedAt(),
                savedCompany.getUpdatedAt(),
                savedCompany.getWorkdayStart(),
                savedCompany.getWorkdayEnd()
        );
    }
}
