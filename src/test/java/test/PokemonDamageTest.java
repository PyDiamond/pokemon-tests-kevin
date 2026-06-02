package test;

import org.junit.jupiter.api.Test;
import pokemon.Pokemon;
import pokemon.PokemonFactory;

import static org.junit.jupiter.api.Assertions.*;

public class PokemonDamageTest {

    @Test
    public void damageShouldNeverIncreaseHp() {
        Pokemon onix = PokemonFactory.createPokemon(95);
        int initialHp = onix.getHp();

        onix.receiveDamage(10);

        assertTrue(onix.getHp() <= initialHp);
    }

    @Test
    public void damageShouldReduceHpCorrectly() {
        Pokemon pikachu = PokemonFactory.createPokemon(25);
        int initialHp = pikachu.getHp();
        int defense = pikachu.getDefense();
        int baseDamage = 50;

        pikachu.receiveDamage(baseDamage);

        int expectedDamage = baseDamage - defense;
        if (expectedDamage < 1) {
            expectedDamage = 0;
        }

        assertEquals(initialHp - expectedDamage, pikachu.getHp());
    }

    @Test
    public void specialDamageShouldReduceHpCorrectly() {
        Pokemon pikachu = PokemonFactory.createPokemon(25);
        int initialHp = pikachu.getHp();
        int spDefense = pikachu.getSpDefense();
        int baseDamage = 50;

        pikachu.receiveSpecialDamage(baseDamage);

        int expectedDamage = baseDamage - spDefense;
        if (expectedDamage < 1) {
            expectedDamage = 0;
        }

        assertEquals(initialHp - expectedDamage, pikachu.getHp());
    }

    @Test
    public void hpShouldNeverBeNegative() {
        Pokemon pikachu = PokemonFactory.createPokemon(25);

        pikachu.receiveDamage(9999);

        assertEquals(0, pikachu.getHp());
        assertTrue(pikachu.isFainted());
    }

    @Test
    public void damageShouldBeAppliedOnlyOnce() {
        Pokemon pikachu = PokemonFactory.createPokemon(25);
        int initialHp = pikachu.getHp();
        int defense = pikachu.getDefense();
        int baseDamage = 30;

        pikachu.receiveDamage(baseDamage);
        int hpAfterFirstHit = pikachu.getHp();

        int expectedDamage = baseDamage - defense;
        if (expectedDamage < 1) {
            expectedDamage = 0;
        }

        assertEquals(initialHp - expectedDamage, hpAfterFirstHit);
        assertEquals(hpAfterFirstHit, pikachu.getHp());
    }
}