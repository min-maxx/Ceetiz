package fr.ceetiz.impot.creation_entreprise;

import fr.ceetiz.impot.creation_entreprise.model.Entreprise;
import fr.ceetiz.impot.creation_entreprise.model.Siret;
import fr.ceetiz.impot.creation_entreprise.model.Type;

import static fr.ceetiz.impot.Preconditions.valideArgument;

public class CréerEntreprise {
    public Entreprise créé(Type type, String numeroSiret, String nom, String adresse) {

        return new Entreprise(type, new Siret(numeroSiret), nom, adresse);
    }
}
