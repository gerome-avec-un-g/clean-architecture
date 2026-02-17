package fr.gerome.cleanarchitecture.domain.boundedcontexts.authors;

import java.util.UUID;

public record Author (UUID identifier, String firstName, String lastName) {

}