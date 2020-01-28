package fr.ceetiz.impot.creation_entreprise;

import fr.ceetiz.impot.creation_entreprise.model.Siret;
import org.assertj.core.api.ThrowableAssert.ThrowingCallable;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.DisplayNameGeneration;
import org.junit.jupiter.api.DisplayNameGenerator;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.*;

@DisplayName("Siret")
@DisplayNameGeneration(DisplayNameGenerator.ReplaceUnderscores.class)
class SiretTest {

    @Test
    void doit_être_de_14_chiffres() {
        assertThat(new Siret("12345678901234")).isEqualTo(new Siret("12345678901234"));
    }

    @Test
    void ne_doit_pas_être_de_13_chiffres() {
        ThrowingCallable mauvaisSiret = () -> new Siret("1234567890123");

        assertThatThrownBy(mauvaisSiret).isInstanceOf(IllegalArgumentException.class);
    }

    @Test
    void ne_doit_pas_être_de_15_chiffres() {
        ThrowingCallable mauvaisSiret = () -> new Siret("123456789012345");

        assertThatThrownBy(mauvaisSiret).isInstanceOf(IllegalArgumentException.class);
    }


    @Test
    void ne_doit_pas_être_composer_de_lettres() {
        ThrowingCallable mauvaisSiret = () -> new Siret("ABCDEFGHIJKLMN");

        assertThatThrownBy(mauvaisSiret).isInstanceOf(IllegalArgumentException.class);
    }

    @Test
    void doit_être_composer_de_10_lettres_et_4_chiffres_pour_Monaco() {
        assertThat(new Siret("MONACOCONF0001")).isEqualTo(new Siret("MONACOCONF0001"));
    }
}