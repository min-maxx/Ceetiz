package fr.ceetiz.impot.creation_entreprise;

import fr.ceetiz.impot.creation_entreprise.model.Entreprise;
import fr.ceetiz.impot.creation_entreprise.model.Siret;
import fr.ceetiz.impot.creation_entreprise.model.Type;
import org.assertj.core.api.ThrowableAssert;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.EnumSource;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;


class CréerEntrepriseTest {

    private final CréerEntreprise créerEntreprise = new CréerEntreprise();

    @Nested
    class Quand_on_créé_une_entreprise_SAS {
        Entreprise entreprise;

        @BeforeEach
        void setUp() {
            entreprise = créerEntreprise.créé(Type.SAS, "12345678901234", "un nom d'entreprise", "une adresse d'entreprise");
        }

        @Test
        void doit_avoir_un_siret() {
            assertThat(entreprise.getSiret()).isEqualTo(new Siret("12345678901234"));
        }

        @Test
        void doit_avoir_un_type() {
            assertThat(entreprise.getType()).isEqualTo(Type.SAS);
        }

        @Test
        void doit_avoir_un_nom() {
            assertThat(entreprise.getDenomination()).isEqualTo("un nom d'entreprise");
        }

        @Test
        void doit_avoir_une_adresse() {
            assertThat(entreprise.getAdresse()).isEqualTo("une adresse d'entreprise");
        }

    }

    @Nested
    class Quand_on_créé_une_auto_entreprise {
        Entreprise entreprise;

        @BeforeEach
        void setUp() {
            entreprise = créerEntreprise.créé(Type.AUTO, "12345678901234", "un nom d'entreprise", null);
        }

        @Test
        void doit_avoir_un_type() {
            assertThat(entreprise.getType()).isEqualTo(Type.AUTO);
        }

        @Test
        void doit_avoir_un_siret() {
            assertThat(entreprise.getSiret()).isEqualTo(new Siret("12345678901234"));
        }

        @Test
        void doit_avoir_un_nom() {
            assertThat(entreprise.getDenomination()).isEqualTo("un nom d'entreprise");
        }

        @Test
        void ne_doit_pas_avoir_une_adresse() {
            assertThat(entreprise.getAdresse()).isEmpty();
        }

    }

    @Nested
    class Quand_on_essaie_de_créer_une_entreprise_incomplète {
        @Test
        void ne_doit_pas_créer_sans_type() {
            ThrowableAssert.ThrowingCallable créé = () -> créerEntreprise.créé(null, "12345678901234", "un nom d'entreprise", "une adresse d'entreprise");

            assertThatThrownBy(créé).isInstanceOf(IllegalArgumentException.class);
        }

        @ParameterizedTest
        @EnumSource(Type.class)
        void ne_doit_pas_créer_sans_siret(Type type) {

            ThrowableAssert.ThrowingCallable créé = () -> créerEntreprise.créé(type, null, "un nom d'entreprise", "une adresse d'entreprise");

            assertThatThrownBy(créé).isInstanceOf(IllegalArgumentException.class);
        }

        @ParameterizedTest
        @EnumSource(Type.class)
        void ne_doit_pas_créer_avec_un_siret_vide(Type type) {

            ThrowableAssert.ThrowingCallable créé = () -> créerEntreprise.créé(type, "", "un nom d'entreprise", "une adresse d'entreprise");

            assertThatThrownBy(créé).isInstanceOf(IllegalArgumentException.class);
        }

        @ParameterizedTest
        @EnumSource(Type.class)
        void ne_doit_pas_créer_sans_nom(Type type) {
            ThrowableAssert.ThrowingCallable créé = () -> créerEntreprise.créé(type, "12345678901234", null, "une adresse d'entreprise");

            assertThatThrownBy(créé).isInstanceOf(IllegalArgumentException.class);
        }

        @ParameterizedTest
        @EnumSource(Type.class)
        void ne_doit_pas_créer_avec_un_nom_vide(Type type) {
            ThrowableAssert.ThrowingCallable créé = () -> créerEntreprise.créé(type, "12345678901234", "", "une adresse d'entreprise");

            assertThatThrownBy(créé).isInstanceOf(IllegalArgumentException.class);
        }
    }

    @Nested
    class Quand_on_essaie_de_créer_une_entreprise_SAS_incomplète {

        @Test
        void ne_doit_pas_créer_sans_adresse() {
            ThrowableAssert.ThrowingCallable créé = () -> créerEntreprise.créé(Type.SAS, "12345678901234", "un nom d'entreprise", null);

            assertThatThrownBy(créé).isInstanceOf(IllegalArgumentException.class);
        }

        @Test
        void ne_doit_pas_créer_avec_adresse_vide() {
            ThrowableAssert.ThrowingCallable créé = () -> créerEntreprise.créé(Type.SAS, "12345678901234", "un nom d'entreprise", "");

            assertThatThrownBy(créé).isInstanceOf(IllegalArgumentException.class);
        }

    }

}