package fr.ceetiz.impot.imposition;

import fr.ceetiz.impot.creation_entreprise.model.Entreprise;
import fr.ceetiz.impot.creation_entreprise.model.Siret;
import fr.ceetiz.impot.imposition.model.Euro;
import fr.ceetiz.impot.imposition.model.ImpôtsDus;
import fr.ceetiz.impot.imposition.model.NotFoundException;
import fr.ceetiz.impot.imposition.model.TauxImposition;

import java.util.List;
import java.util.function.Supplier;

public class CalculerLesImpôts {
    private Supplier<List<Entreprise>> entreprises;

    public CalculerLesImpôts(Supplier<List<Entreprise>> entreprises) {
        this.entreprises = entreprises;
    }

    public ImpôtsDus calcule(Siret siret, Euro chiffreAffaireAnnuel) {
        Entreprise entreprise = entrepriseBySiret(siret);
        TauxImposition tauxImposition = TauxImposition.àPartirDe(entreprise.getType());
        Euro montantImpôt = chiffreAffaireAnnuel.calcule(tauxImposition);
        return new ImpôtsDus(siret, montantImpôt);
    }

    private Entreprise entrepriseBySiret(Siret siret) {
        return entreprises.get().stream()
                .filter(e -> e.getSiret().equals(siret))
                .findFirst()
                .orElseThrow(NotFoundException::new);
    }
}
