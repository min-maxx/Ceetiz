package fr.ceetiz.impot.creation_entreprise.model;

import static fr.ceetiz.impot.Preconditions.valideArgument;

public class Entreprise{
    public static final String PAS_D_ADRESSE = "";
    private final Siret siret;
    private final Type type;
    private String denomination;
    private String adresse;

    public Entreprise(Type type, Siret siret, String denomination, String adresse) {
        valideArgument(type !=null , "Le type est obligatoire.");
        valideArgument(denomination !=null && !denomination.isEmpty(), "Le nom est obligatoire.");
        valideArgument(type == Type.AUTO || adresse !=null && !adresse.isEmpty(), "L'adresse est obligatoire pour une entreprise SAS.");

        this.siret = siret;
        this.denomination = denomination;
        this.adresse = adresse==null ? PAS_D_ADRESSE : adresse;
        this.type = type;
    }

    public Siret getSiret() {
        return siret;
    }

    public String getDenomination() {
        return denomination;
    }

    public String getAdresse() {
        return adresse;
    }

    public Type getType() {
        return type;
    }
}
