package br.com.reservix.infrastructure.web.controllers;

import br.com.reservix.core.application.usecases.company.CompanyDetailOutPut;
import br.com.reservix.core.application.usecases.company.CreateCompanyCommand;
import br.com.reservix.core.application.usecases.company.UpdateCompanyCommand;
import br.com.reservix.core.application.usecases.company.usecases.*;
import br.com.reservix.infrastructure.web.dtos.company.CompanyResponse;
import br.com.reservix.infrastructure.web.dtos.company.CreateCompanyRequest;
import br.com.reservix.infrastructure.web.dtos.company.UpdateCompanyRequest;
import br.com.reservix.infrastructure.web.mapper.CompanyMapper;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import java.net.URI;

@RestController
@RequestMapping("/companies")
@RequiredArgsConstructor
public class CompanyController {

    private final CreateCompanyUseCase createCompanyUseCase;
    private final UpdateCompanyUseCase updateCompanyUseCase;
    private final FindCompanyByIdUseCase findCompanyByIdUseCase;
    private final FindAllCompaniesUseCase findAllCompaniesUseCase;
    private final DeleteCompanyUseCase deleteCompanyUseCase;
    private final CompanyMapper companyMapper;

    @PostMapping
    public ResponseEntity<CompanyResponse> createCompany(
            @Valid @RequestBody CreateCompanyRequest request) {

        CreateCompanyCommand command =
                companyMapper.toCreateCompanyCommand(request);

        CompanyDetailOutPut output =
                createCompanyUseCase.execute(command);

        CompanyResponse response =
                companyMapper.toCompanyResponse(output);

        URI location = ServletUriComponentsBuilder
                .fromCurrentRequest()
                .path("/{id}")
                .buildAndExpand(output.id())
                .toUri();

        return ResponseEntity.created(location).body(response);
    }

    @PutMapping("/{id}")
    public ResponseEntity<CompanyResponse> updateCompany(
            @PathVariable Long id,
            @Valid @RequestBody UpdateCompanyRequest request) {

        UpdateCompanyCommand command =
                companyMapper.toUpdateCompanyCommand(id, request);

        CompanyDetailOutPut output =
                updateCompanyUseCase.execute(command);

        CompanyResponse response =
                companyMapper.toCompanyResponse(output);

        return ResponseEntity.ok(response);
    }

    @GetMapping("/{id}")
    public ResponseEntity<CompanyResponse> findById(
            @PathVariable Long id) {

        CompanyDetailOutPut output =
                findCompanyByIdUseCase.execute(id);

        CompanyResponse response =
                companyMapper.toCompanyResponse(output);

        return ResponseEntity.ok(response);
    }

    @GetMapping
    public ResponseEntity<Page<CompanyResponse>> findAll(
            Pageable pageable) {

        Page<CompanyDetailOutPut> output =
                findAllCompaniesUseCase.execute(pageable);

        Page<CompanyResponse> response =
                companyMapper.toCompanyResponse(output);

        return ResponseEntity.ok(response);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteCompany(
            @PathVariable Long id) {

        deleteCompanyUseCase.execute(id);

        return ResponseEntity.noContent().build();
    }

}
