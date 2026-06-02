package test;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import pokemon.Pokemon;
import pokemon.PokemonFactory;

import static org.junit.jupiter.api.Assertions.*;

public class Regla6_CuracionTest {

    private Pokemon pikachu;
    private int maxHpOriginal;

    @BeforeEach
    public void setUp() {
        pikachu = PokemonFactory.createPokemon(25);
        maxHpOriginal = pikachu.getHp();
    }

    @Test
    public void testHealFullRestauraHpAlMaximo() {
        pikachu.receiveDamage(200);

        assertTrue(pikachu.getHp() < maxHpOriginal);

        pikachu.healFull();

        assertEquals(maxHpOriginal, pikachu.getHp());
    }

    @Test
    public void testHealFullRevivePokemonDerrotado() {
        pikachu.receiveDamage(9999);

        assertTrue(pikachu.isFainted());

        pikachu.healFull();

        assertFalse(pikachu.isFainted());
        assertTrue(pikachu.isAlive());
        assertEquals(maxHpOriginal, pikachu.getHp());
    }

    @Test
    public void testMetodosDeMuestraEInformacion() {
        assertDoesNotThrow(() -> {
            pikachu.showStatus();
            pikachu.showMoves();
            pikachu.showSummary();
        });
    }
}