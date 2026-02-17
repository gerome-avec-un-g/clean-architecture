package fr.gerome.cleanarchitecture.infrastructure.restendpoints;

import fr.gerome.cleanarchitecture.domain.usecases.AnAdministratorCreatesAnAuthor;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.resttestclient.autoconfigure.AutoConfigureRestTestClient;
import org.springframework.boot.webmvc.test.autoconfigure.WebMvcTest;
import org.springframework.test.context.bean.override.mockito.MockitoBean;
import org.springframework.test.web.servlet.client.RestTestClient;

import static org.mockito.Mockito.verify;

@WebMvcTest(AuthorController.class)
@AutoConfigureRestTestClient
class AuthorControllerTest {

    @Autowired
    private RestTestClient restTestClient;

    @MockitoBean
    private AnAdministratorCreatesAnAuthor anAdministratorCreatesAnAuthor;

    @Test
    void greetingShouldReturnMessageFromService() {
        restTestClient.post().uri("/authors").exchange()
                .expectBody(String.class)
                .isEqualTo("ok");
        verify(anAdministratorCreatesAnAuthor).execute();
    }

}