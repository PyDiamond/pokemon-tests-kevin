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
        // Usamos el Factory que tus compañeros ya implementan
        pikachu = PokemonFactory.createPokemon(25); 
        maxHpOriginal = pikachu.getHp();
    }

    @Test
    public void testHealFullRestauraHpAlMaximo() {
        pikachu.receiveDamage(30);
        assertTrue(pikachu.getHp() < maxHpOriginal, "El HP debe haber bajado antes de curar.");

        pikachu.healFull();

        assertEquals(maxHpOriginal, pikachu.getHp(), "healFull() debe restaurar el HP exactamente a su valor máximo original.");
    }

    @Test
    public void testHealFullRevivePokemonDerrotado() {
        // Forzamos la derrota del Pokémon
        pikachu.receiveDamage(9999);
        assertTrue(pikachu.isFainted(), "El Pokémon debe estar derrotado (Fainted).");

        pikachu.healFull();

        // Validamos la transición completa de estados
        assertFalse(pikachu.isFainted(), "El Pokémon ya no debe estar 'fainted' tras curarse.");
        assertTrue(pikachu.isAlive(), "El Pokémon debe volver a estar vivo.");
        assertEquals(maxHpOriginal, pikachu.getHp(), "El HP debe ser máximo al revivir.");
    }
}
