package br.com.reservix.core.application.usecases.company.exceptions;

public class CompanyNotFoundException extends RuntimeException {
  public CompanyNotFoundException(String message) {
    super(message);
  }
}
