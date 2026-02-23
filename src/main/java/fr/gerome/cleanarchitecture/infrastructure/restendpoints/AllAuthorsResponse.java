package fr.gerome.cleanarchitecture.infrastructure.restendpoints;

import java.util.List;

public record AllAuthorsResponse(List<AuthorResponse> authorResponses) {
}
