package br.com.reservix.core.domain.entities.company;

public class CompanyAlreadyInactiveException extends RuntimeException {
    public CompanyAlreadyInactiveException(String message) {
        super(message);
    }
}
