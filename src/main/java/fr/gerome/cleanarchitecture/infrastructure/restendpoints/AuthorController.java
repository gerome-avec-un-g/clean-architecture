package fr.gerome.cleanarchitecture.infrastructure.restendpoints;

import fr.gerome.cleanarchitecture.domain.usecases.AnAdministratorCreatesAnAuthor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
//@RequestMapping("/authors")
public class AuthorController {

    private final AnAdministratorCreatesAnAuthor anAdministratorCreatesAnAuthor;

    public AuthorController(AnAdministratorCreatesAnAuthor anAdministratorCreatesAnAuthor) {
        this.anAdministratorCreatesAnAuthor = anAdministratorCreatesAnAuthor;
    }

    @GetMapping(path="/authors"/*, version = "1"*/)
    public AllAuthorsResponse getAllAuthors() {
        return new AllAuthorsResponse(List.of(new AuthorResponse("John", "Doe")));
    }

    @PostMapping(path="/authors"/*, version = "1"*/)
    public String createAnAuthor(@RequestBody CreateAnAuthorRequest request) {
        anAdministratorCreatesAnAuthor.execute();
        return "ok";
    }

//    @PostMapping(path="/authors"/*, version = "2"*/)
//    public String createAnAuthorV2(@RequestBody CreateAnAuthorRequestV2 request) {
//        anAdministratorCreatesAnAuthor.execute();
//        return "ok";
//    }

}
