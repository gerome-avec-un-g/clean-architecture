package fr.gerome.cleanarchitecture.infrastructure.restendpoints;

import java.time.LocalDate;

public record CreateAnAuthorRequestV2(String firstName, String lastName, LocalDate dateOfBirth) {
}
