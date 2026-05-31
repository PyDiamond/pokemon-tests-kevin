/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */

package test;

import battle.BattleSystem;
import org.junit.jupiter.api.Test;
import pokemon.Pokemon;
import pokemon.PokemonFactory;

import static org.junit.jupiter.api.Assertions.*;


public class BattleSystemTest {

//Regla de neg
//Primer caso unitario
    @Test
    public void fasterPokemonShouldAttackFirst() {

        BattleSystem battleSystem = new BattleSystem();

        Pokemon pikachu = PokemonFactory.createPokemon(25);

        Pokemon squirtle = PokemonFactory.createPokemon(7);

        boolean result =
                battleSystem.playerAttackFirst(pikachu, squirtle);

        assertTrue(result);
    }
    
//Segundo caso unitario
    @Test
public void sameSpeedShouldHaveConsistentOrder() {

    BattleSystem battleSystem = new BattleSystem();

    Pokemon mew1 = PokemonFactory.createPokemon(151);

    Pokemon mew2 = PokemonFactory.createPokemon(151);

    boolean firstResult =
            battleSystem.playerAttackFirst(mew1, mew2);

    boolean secondResult =
            battleSystem.playerAttackFirst(mew1, mew2);

    assertEquals(firstResult, secondResult);
    }
}

