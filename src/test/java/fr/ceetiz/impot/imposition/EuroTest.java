package fr.ceetiz.impot.imposition;

import fr.ceetiz.impot.imposition.model.Euro;
import org.assertj.core.api.ThrowableAssert.ThrowingCallable;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.DisplayNameGeneration;
import org.junit.jupiter.api.DisplayNameGenerator;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.*;

@DisplayNameGeneration(DisplayNameGenerator.ReplaceUnderscores.class)
@DisplayName("Euro")
class EuroTest {
    @Test
    void ne_doit_pas_être_négatif() {
        ThrowingCallable mauvaisEuro = () -> new Euro(-12);

        assertThatThrownBy(mauvaisEuro).isInstanceOf(IllegalArgumentException.class);
    }

    @Test
    void doit_être_positif() {
        assertThat(new Euro(12.4)).isEqualTo(new Euro(12.4));
    }

    @Test
    void peut_être_zéro() {
        assertThat(new Euro(0)).isEqualTo(new Euro(0));

    }
}