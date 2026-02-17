package fr.gerome.cleanarchitecture.infrastructure.restendpoints;

import fr.gerome.cleanarchitecture.domain.usecases.AnAdministratorCreatesAnAuthor;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.resttestclient.autoconfigure.AutoConfigureRestTestClient;
import org.springframework.boot.webmvc.test.autoconfigure.WebMvcTest;
import org.springframework.http.HttpStatus;
import org.springframework.http.ProblemDetail;
import org.springframework.test.context.bean.override.mockito.MockitoBean;
import org.springframework.test.web.servlet.client.RestTestClient;

import java.net.URI;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.Mockito.doThrow;

@WebMvcTest(AuthorController.class)
@AutoConfigureRestTestClient
class GlobalExceptionHandlerTest {

    @Autowired
    private RestTestClient restTestClient;

    @MockitoBean
    private AnAdministratorCreatesAnAuthor anAdministratorCreatesAnAuthor;

    @Test
    void greetingShouldReturnMessageFromService() {
        doThrow(new IllegalArgumentException("argument invalid")).when(anAdministratorCreatesAnAuthor).execute();
        ProblemDetail expected = ProblemDetail.forStatusAndDetail(HttpStatus.INTERNAL_SERVER_ERROR, "argument invalid");
        expected.setInstance(URI.create("/authors"));

        restTestClient.post().uri("/authors").exchange()
                .expectStatus().is5xxServerError()
                .expectBody(ProblemDetail.class)
                .consumeWith( response -> assertThat(response.getResponseBody()).isEqualTo(expected));
    }

}