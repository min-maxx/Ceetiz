package fr.ceetiz.impot.imposition;

import fr.ceetiz.impot.imposition.model.TauxImposition;
import org.assertj.core.api.ThrowableAssert;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.DisplayNameGeneration;
import org.junit.jupiter.api.DisplayNameGenerator;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.*;

@DisplayName("Un Pourcent")
@DisplayNameGeneration(DisplayNameGenerator.ReplaceUnderscores.class)
class TauxImpositionTest {

    @Test
    void ne_doit_pas_être_negatif() {
        ThrowableAssert.ThrowingCallable mauvaisPourcent = () -> new TauxImposition(-25);

        assertThatThrownBy(mauvaisPourcent).isInstanceOf(IllegalArgumentException.class);
    }

    @Test
    void doit_être_positif() {
        assertThat(new TauxImposition(12)).isEqualTo(new TauxImposition(12));
    }

    @Test
    void peut_être_zéro() {
        assertThat(new TauxImposition(0)).isEqualTo(new TauxImposition(0));
    }

    @Test
    void doit_être_inférieur_à_100() {
        ThrowableAssert.ThrowingCallable mauvaisPourcent = () -> new TauxImposition(101);

        assertThatThrownBy(mauvaisPourcent).isInstanceOf(IllegalArgumentException.class);
    }

    @Test
    void peut_être_100() {
        assertThat(new TauxImposition(100)).isEqualTo(new TauxImposition(100));

    }
}