package fr.gerome.cleanarchitecture.infrastructure.restendpoints;

import fr.gerome.cleanarchitecture.infrastructure.restendpoints.model.Name;
import fr.gerome.cleanarchitecture.infrastructure.restendpoints.model.NaturalPerson;
import fr.gerome.cleanarchitecture.infrastructure.restendpoints.model.PlaceOfBirth;
import fr.gerome.cleanarchitecture.infrastructure.restendpoints.model.Taxpayer2;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class TaxpayerController {

    @GetMapping(path="/taxpayer")
    public Taxpayer2 getAllAuthors() {
        return new Taxpayer2(new NaturalPerson(
                new Name("john", "JJ", null, "Doe", null),
                "2026-12-13",
                new PlaceOfBirth("Paris", "75001", "FR"),
                null, null
                ), null);
    }

}
