package test;

import battle.BattleSystem;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import pokemon.Pokemon;
import pokemon.PokemonFactory;
import trainer.Trainer;

import java.io.ByteArrayInputStream;
import java.util.Scanner;

import static org.junit.jupiter.api.Assertions.*;

public class BattleSystemTest {

    private BattleSystem battleSystem;
    private Pokemon pikachu;
    private Pokemon squirtle;
    private Pokemon mew1;
    private Pokemon mew2;

    @BeforeEach
    public void setUp() {
        // Inicialización del sistema de combate y de los Pokémon base para las pruebas
        battleSystem = new BattleSystem();
        pikachu = PokemonFactory.createPokemon(25);
        squirtle = PokemonFactory.createPokemon(7);
        mew1 = PokemonFactory.createPokemon(151);
        mew2 = PokemonFactory.createPokemon(151);
    }

    @Test
    public void fasterPokemonShouldAttackFirst() {
        // Valida que la prioridad de turno se otorgue correctamente al Pokémon con mayor estadística de velocidad
        boolean result = battleSystem.playerAttacksFirst(pikachu, squirtle);
        assertTrue(result);
    }

    @Test
    public void slowerPokemonShouldNotAttackFirst() {
        // Verifica que un Pokémon más lento pierda la prioridad de iniciativa frente a uno más rápido
        boolean result = battleSystem.playerAttacksFirst(squirtle, pikachu);
        assertFalse(result);
    }

    @Test
    public void sameSpeedShouldHaveConsistentOrder() {
        // Control de consistencia: si dos Pokémon tienen la misma velocidad, el orden de ataque debe ser determinante y no variar entre llamadas consecutivas
        boolean firstResult = battleSystem.playerAttacksFirst(mew1, mew2);
        boolean secondResult = battleSystem.playerAttacksFirst(mew1, mew2);
        assertEquals(firstResult, secondResult);
    }

    @Test
    public void testStartBattleFlujoVictoriaJugador() {
        Trainer playerTrainer = new Trainer("Ash");
        Trainer enemyTrainer = new Trainer("Gary");

        Pokemon fuerte = PokemonFactory.createPokemon(25); 
        Pokemon debil = PokemonFactory.createPokemon(7);   
        debil.receiveDamage(debil.getHp() - 5); // Lo dejamos a un golpe de debilitarse para forzar un fin de combate rápido

        playerTrainer.addPokemon(fuerte);
        enemyTrainer.addPokemon(debil);

        // Enviamos opciones fuera de rango (99) y letras ("invalido") para cubrir las ramas de error
        String inputsSimulados = "99\ninvalido\n1\n99\ntextoError\n1\n1\n1\n1\n";
        inyectarScannerSimulado(battleSystem, inputsSimulados);

        assertDoesNotThrow(() -> {
            battleSystem.startBattle(playerTrainer, enemyTrainer);
        });
    }

    @Test
    public void testStartBattleFlujoDerrotaYCambioDePokemon() {
        Trainer playerTrainer = new Trainer("Red");
        Trainer enemyTrainer = new Trainer("Blue");

        Pokemon p1 = PokemonFactory.createPokemon(7);   
        Pokemon p2 = PokemonFactory.createPokemon(4);   
        Pokemon e1 = PokemonFactory.createPokemon(25);  
        
        // Preparamos el escenario dejando a ambos Pokémon críticos para acelerar el flujo de cambios y derrotas
        p1.receiveDamage(p1.getHp() - 5); 
        e1.receiveDamage(e1.getHp() - 5); 

        playerTrainer.addPokemon(p1);
        playerTrainer.addPokemon(p2);
        enemyTrainer.addPokemon(e1);

        String inputsSimulados = "textoInvalido\n5\n2\n0\n1\n1\n1\n1\n1\n";
        inyectarScannerSimulado(battleSystem, inputsSimulados);

        assertDoesNotThrow(() -> {
            battleSystem.startBattle(playerTrainer, enemyTrainer);
        });
    }

    @Test
    public void testStartBattleCuandoEnemigoAtacaPrimero() {
        Trainer playerTrainer = new Trainer("Lucas");
        Trainer enemyTrainer = new Trainer("Cynthia");

        Pokemon lento = PokemonFactory.createPokemon(7);   
        Pokemon rapido = PokemonFactory.createPokemon(25); 

        playerTrainer.addPokemon(lento);
        enemyTrainer.addPokemon(rapido);

        String inputsSimulados = "1\n1\n1\n1\n1\n1\n";
        inyectarScannerSimulado(battleSystem, inputsSimulados);

        assertDoesNotThrow(() -> {
            battleSystem.startBattle(playerTrainer, enemyTrainer);
        });
    }

    private void inyectarScannerSimulado(BattleSystem sistema, String datos) {
        try {
            ByteArrayInputStream inputMock = new ByteArrayInputStream(datos.getBytes());
            Scanner scannerMock = new Scanner(inputMock);
            java.lang.reflect.Field field = BattleSystem.class.getDeclaredField("scanner");
            field.setAccessible(true);
            field.set(sistema, scannerMock);
        } catch (Exception e) {
            fail("Error" + e.getMessage());
        }
    }
}