package fr.gerome.cleanarchitecture.infrastructure.restendpoints;

import java.util.List;

public record AllAuthorsResponseV2(List<AuthorResponseV2> authorResponses) {
}
