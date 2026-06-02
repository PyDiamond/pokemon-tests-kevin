package test;

import org.junit.jupiter.api.Test;
import pokemon.Pokemon;
import pokemon.PokemonFactory;
import trainer.Trainer;

import java.io.ByteArrayInputStream;
import java.util.Scanner;

import static org.junit.jupiter.api.Assertions.*;

public class TrainerTest {

    @Test
    public void testFlujoCompletoEntrenadorYGestionDeEquipo() {
        Trainer trainer = new Trainer("Ash");
        
        assertEquals("Ash", trainer.getName());
        assertTrue(trainer.getTeam().isEmpty());
        assertFalse(trainer.hasAlivePokemons());
        assertNull(trainer.getFirstAlivePokemon());

        trainer.showTeam();

        Pokemon p1 = PokemonFactory.createPokemon(25);
        Pokemon p2 = PokemonFactory.createPokemon(7);
        Pokemon p3 = PokemonFactory.createPokemon(4);
        Pokemon p4 = PokemonFactory.createPokemon(1);
        Pokemon p5 = PokemonFactory.createPokemon(133);
        Pokemon p6 = PokemonFactory.createPokemon(95);
        Pokemon p7 = PokemonFactory.createPokemon(151);

        trainer.addPokemon(p1);
        trainer.addPokemon(p1); 
        
        trainer.addPokemon(p2);
        trainer.addPokemon(p3);
        trainer.addPokemon(p4);
        trainer.addPokemon(p5);
        trainer.addPokemon(p6);
        trainer.addPokemon(p7); 

        assertEquals(6, trainer.getTeam().size());
        assertTrue(trainer.hasAlivePokemons());
        assertEquals(p1, trainer.getFirstAlivePokemon());

        trainer.showTeam();

        trainer.removePokemon(-1);
        trainer.removePokemon(10);
        trainer.removePokemon(5); 
        assertEquals(5, trainer.getTeam().size());

        p1.receiveDamage(9999); 
        assertTrue(p1.isFainted());
        trainer.showTeam();
        assertEquals(p2, trainer.getFirstAlivePokemon());
    }

    @Test
    public void testChoosePokemonConEntradasSimuladas() {
        Trainer trainer = new Trainer("Red");
        Pokemon pikachu = PokemonFactory.createPokemon(25);
        Pokemon squirtle = PokemonFactory.createPokemon(7);
        
        squirtle.receiveDamage(9999); 

        trainer.addPokemon(pikachu);
        trainer.addPokemon(squirtle);

        String entradaSimulada = "textoInvalido\n9\n1\n0\n";
        ByteArrayInputStream in = new ByteArrayInputStream(entradaSimulada.getBytes());
        Scanner scanner = new Scanner(in);

        Pokemon elegido = trainer.choosePokemon(scanner);
        assertEquals(pikachu, elegido);
    }

    @Test
    public void testMetodoNoSoportadoLanzaExcepcion() {
        Trainer trainer = new Trainer("Gary");
        assertThrows(UnsupportedOperationException.class, () -> {
            trainer.getPokemonTeam();
        });
    }
}