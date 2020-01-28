package fr.ceetiz.impot.imposition.model;

import fr.ceetiz.impot.creation_entreprise.model.Type;

import java.math.BigDecimal;
import java.util.Objects;

import static fr.ceetiz.impot.Preconditions.valideArgument;

public class TauxImposition {
    private static final BigDecimal CENT = BigDecimal.valueOf(100);
    private int valeur;

    public TauxImposition(int valeur) {
        valideArgument(valeur >= 0 && valeur <= 100, "Un taux doit être en 0 et 100 inclus");

        this.valeur = valeur;
    }

    public static TauxImposition àPartirDe(Type type) {
        switch (type) {
            case AUTO:  return new TauxImposition(25);
            case SAS:   return new TauxImposition(33);
            default:    throw new IllegalStateException("Unexpected value: " + type);
        }
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        TauxImposition tauxImposition = (TauxImposition) o;
        return valeur == tauxImposition.valeur;
    }

    @Override
    public int hashCode() {
        return Objects.hash(valeur);
    }

    @Override
    public String toString() {
        return "TauxImposition{" +
                "valeur=" + valeur +
                '}';
    }

    public BigDecimal de(BigDecimal valeur) {
       return valeur.multiply(BigDecimal.valueOf(this.valeur).divide(CENT));
    }
}
