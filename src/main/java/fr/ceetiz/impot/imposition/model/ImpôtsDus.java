package fr.ceetiz.impot.imposition.model;

import fr.ceetiz.impot.creation_entreprise.model.Siret;

import java.util.Objects;

public class ImpôtsDus {
    private final Siret siret;
    private final Euro euro;

    public ImpôtsDus(Siret siret, Euro euro) {
        this.siret = siret;
        this.euro = euro;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        ImpôtsDus impôtsDus = (ImpôtsDus) o;
        return siret.equals(impôtsDus.siret) &&
                euro.equals(impôtsDus.euro);
    }

    @Override
    public int hashCode() {
        return Objects.hash(siret, euro);
    }

    @Override
    public String toString() {
        return "fr.ceetiz.impot.imposition.model.ImpôtsDus{" +
                "siren=" + siret +
                ", euro=" + euro +
                '}';
    }
}
