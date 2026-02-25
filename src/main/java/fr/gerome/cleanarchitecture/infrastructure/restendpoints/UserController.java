package fr.gerome.cleanarchitecture.infrastructure.restendpoints;

import fr.gerome.cleanarchitecture.infrastructure.restendpoints.api.UsersApi;
import fr.gerome.cleanarchitecture.infrastructure.restendpoints.model.Name;
import fr.gerome.cleanarchitecture.infrastructure.restendpoints.model.NaturalPerson;
import fr.gerome.cleanarchitecture.infrastructure.restendpoints.model.PlaceOfBirth;
import fr.gerome.cleanarchitecture.infrastructure.restendpoints.model.Taxpayer2;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
public class UserController implements UsersApi {

    @Override
    public ResponseEntity<List<String>> usersGet() throws Exception {
        return UsersApi.super.usersGet();
    }
}
