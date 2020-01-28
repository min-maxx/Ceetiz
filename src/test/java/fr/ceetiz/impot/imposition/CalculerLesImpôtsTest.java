package fr.ceetiz.impot.imposition;

import fr.ceetiz.impot.creation_entreprise.model.Entreprise;
import fr.ceetiz.impot.creation_entreprise.model.Siret;
import fr.ceetiz.impot.creation_entreprise.model.Type;
import fr.ceetiz.impot.imposition.model.Euro;
import fr.ceetiz.impot.imposition.model.ImpôtsDus;
import fr.ceetiz.impot.imposition.model.NotFoundException;
import org.assertj.core.api.ThrowableAssert;
import org.junit.jupiter.api.*;

import java.util.Arrays;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;

@DisplayNameGeneration(DisplayNameGenerator.ReplaceUnderscores.class)
@DisplayName("Calculer Les Impôts")
class CalculerLesImpôtsTest {

    public static final Siret UN_SIRET = new Siret("43210987654321");
    public static final Siret AUTRE_SIRET = new Siret("09876543211234");
    private final CalculerLesImpôts calculerLesImpôts = new CalculerLesImpôts(() -> Arrays.asList(
            new Entreprise(Type.SAS, UN_SIRET, "_", "_"),
            new Entreprise(Type.AUTO, AUTRE_SIRET, "_", "_")));

    @Test
    void doit_prendre_33_pourcents_du_CA_pour_une_entreprise_SAS() {
        ImpôtsDus impôtsDus = calculerLesImpôts.calcule(UN_SIRET, new Euro(30));

        assertThat(impôtsDus).isEqualTo(new ImpôtsDus(UN_SIRET, new Euro(9.9)));
    }


    @Test
    void doit_prendre_25_pourcents_du_CA_pour_une_auto_entreprise() {
        ImpôtsDus impôtsDus = calculerLesImpôts.calcule(AUTRE_SIRET, new Euro(20));

        assertThat(impôtsDus).isEqualTo(new ImpôtsDus(AUTRE_SIRET, new Euro(5)));
    }

    @Test
    void ne_doit_pas_rien_calculer_quand_le_siret_ne_correspond_à_aucune_entreprise() {
        ThrowableAssert.ThrowingCallable impôtsDus = () -> calculerLesImpôts.calcule(new Siret("34567890987654"), new Euro(20));

        assertThatThrownBy(impôtsDus).isInstanceOf(NotFoundException.class);
    }

}