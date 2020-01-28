package fr.ceetiz.impot.creation_entreprise.model;

import java.util.Objects;

import static fr.ceetiz.impot.Preconditions.valideArgument;

public class Siret {
    public static final String SIRET_PATTERN = "[0-9]{14}|MONACOCONF[0-9]{4}";
    private String numero;

    public Siret(String numero) {
        valideArgument(numero !=null , "Le type est obligatoire.");
        valideArgument(numero.matches(SIRET_PATTERN), "Un Siret doit être de 14 chiffres sauf pour ...");

        this.numero = numero;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Siret siret = (Siret) o;
        return numero.equals(siret.numero);
    }

    @Override
    public int hashCode() {
        return Objects.hash(numero);
    }

    @Override
    public String toString() {
        return "Siret{" +
                "numero='" + numero + '\'' +
                '}';
    }
}
