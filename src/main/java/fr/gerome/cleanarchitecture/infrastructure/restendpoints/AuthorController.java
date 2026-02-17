package fr.gerome.cleanarchitecture.infrastructure.restendpoints;

import fr.gerome.cleanarchitecture.domain.usecases.AnAdministratorCreatesAnAuthor;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/authors")
public class AuthorController {

    private final AnAdministratorCreatesAnAuthor anAdministratorCreatesAnAuthor;

    public AuthorController(AnAdministratorCreatesAnAuthor anAdministratorCreatesAnAuthor) {
        this.anAdministratorCreatesAnAuthor = anAdministratorCreatesAnAuthor;
    }

    @PostMapping
    public String createAnAuthor() {
        anAdministratorCreatesAnAuthor.execute();
        return "ok";
    }

}
