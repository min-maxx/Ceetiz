package fr.ceetiz.impot;

public class Preconditions {
    public static void valideArgument(boolean doitEtreVrai, String message) {
        if(!doitEtreVrai) throw new IllegalArgumentException(message);
    }
}
