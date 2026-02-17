package fr.gerome.cleanarchitecture.infrastructure.restendpoints;

import fr.gerome.cleanarchitecture.domain.usecases.CreateAnAuthor;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/authors")
public class AuthorController {

    private final CreateAnAuthor createAnAuthor;

    public AuthorController(CreateAnAuthor createAnAuthor) {
        this.createAnAuthor = createAnAuthor;
    }

    @PostMapping
    public String createAnAuthor() {
        createAnAuthor.execute();
        return "ok";
    }

}
