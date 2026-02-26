package fr.gerome.cleanarchitecture.infrastructure.restendpoints;

import fr.gerome.cleanarchitecture.infrastructure.restendpoints.api.DossierApi;
import fr.gerome.cleanarchitecture.infrastructure.restendpoints.model.RPonseUneDemandeDeGarantieCrDitLogement;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class DossierController implements fr.gerome.cleanarchitecture.infrastructure.restendpoints.api.DossierApi {

    @Override
    public ResponseEntity<RPonseUneDemandeDeGarantieCrDitLogement> dossierGet() throws Exception {
        return DossierApi.super.dossierGet();
    }

}
