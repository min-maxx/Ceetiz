package fr.ceetiz.impot.imposition.model;

import java.math.BigDecimal;
import java.util.Objects;

import static fr.ceetiz.impot.Preconditions.valideArgument;

public class Euro {
    private BigDecimal montant;

    public Euro(BigDecimal montant) {
        valideArgument(montant.compareTo(BigDecimal.ZERO) >=0, "Un Euro doit être positif");
        this.montant = montant;
    }

    public Euro(double montant) {
        this(BigDecimal.valueOf(montant));
    }

    public BigDecimal getMontant() {
        return montant;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Euro that = (Euro) o;
        return montant.compareTo(that.montant) == 0;
    }

    @Override
    public int hashCode() {
        return Objects.hash(montant);
    }

    @Override
    public String toString() {
        return "fr.ceetiz.impot.entreprise.Euro{" +
                "montant=" + montant +
                '}';
    }

    public Euro calcule(TauxImposition pourcentage) {
        return new Euro(pourcentage.de(this.getMontant()));
    }
}
