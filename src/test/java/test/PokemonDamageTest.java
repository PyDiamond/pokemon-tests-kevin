/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/UnitTests/JUnit5TestClass.java to edit this template
 */

package test;

import org.junit.jupiter.api.Test;
import pokemon.Pokemon;
import pokemon.PokemonFactory;

import static org.junit.jupiter.api.Assertions.*;

public class PokemonDamageTest {
    @Test
public void damageShouldNeverIncreaseHp() {
    
    //Primer caso unitario
    Pokemon onix = PokemonFactory.createPokemon(95);

    int initialHp = onix.getHp();

    onix.receiveDamage(10);

    assertEquals(initialHp, onix.getHp());
    }

    //Segundo caso unitario
    @Test
    public void hpShouldNeverBeNegative() {

    Pokemon pikachu = PokemonFactory.createPokemon(25);

    pikachu.receiveDamage(9999);

    assertEquals(0, pikachu.getHp());
    }

    //Tercer caso unitario
    @Test
    public void damageShouldBeAppliedOnlyOnce() {

    Pokemon pikachu = PokemonFactory.createPokemon(25);

    pikachu.receiveDamage(50);

    assertEquals(25, pikachu.getHp());
    }
}
