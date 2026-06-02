package test;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import pokemon.Pokemon;
import pokemon.PokemonFactory;

import static org.junit.jupiter.api.Assertions.*;

public class Regla3Test {

    private Pokemon atacanteFuego;
    private Pokemon defensorAgua;
    private Pokemon defensorNormal;
    private Pokemon defensorPlanta;

    @BeforeEach
    public void setUp() {
        // ID 4: Charmander (Fuego)
        atacanteFuego = PokemonFactory.createPokemon(4);
        // ID 7: Squirtle (Agua)
        defensorAgua = PokemonFactory.createPokemon(7);
        // ID 133: Eevee (Normal)
        defensorNormal = PokemonFactory.createPokemon(133);
        // ID 1: Bulbasaur (Planta)
        defensorPlanta = PokemonFactory.createPokemon(1);
    }

    @Test
    public void ataqueNeutroDebeCausarDano() {
        int hpInicial = defensorNormal.getHp();

        // Se usa el índice 1 porque el 0 es "Defend"
        atacanteFuego.useMove(1, defensorNormal);

        assertTrue(
                defensorNormal.getHp() < hpInicial,
                "El HP del defensor neutral debería disminuir tras recibir un ataque."
        );
    }

    @Test
    public void ataquePocoEfectivoDebeCausarMenosDano() {
        int hpInicialAgua = defensorAgua.getHp();
        int hpInicialNormal = defensorNormal.getHp();

        // Ataque neutro (Fuego contra Normal)
        atacanteFuego.useMove(1, defensorNormal);
        int danoNeutro = hpInicialNormal - defensorNormal.getHp();

        // Ataque poco efectivo (Fuego contra Agua)
        atacanteFuego.useMove(1, defensorAgua);
        int danoPocoEfectivo = hpInicialAgua - defensorAgua.getHp();

        assertTrue(
                danoPocoEfectivo <= danoNeutro,
                "El daño poco efectivo (" + danoPocoEfectivo + ") debería ser menor o igual al daño neutro (" + danoNeutro + ")."
        );
    }

    @Test
    public void ataqueSuperEfectivoDebeCausarMasDano() {
        int hpInicialNormal = defensorNormal.getHp();
        int hpInicialPlanta = defensorPlanta.getHp();

        // Ataque neutro (Fuego contra Normal)
        atacanteFuego.useMove(1, defensorNormal);
        int danoNeutro = hpInicialNormal - defensorNormal.getHp();

        // Ataque súper efectivo (Fuego contra Planta)
        atacanteFuego.useMove(1, defensorPlanta);
        int danoSuperEfectivo = hpInicialPlanta - defensorPlanta.getHp();

        assertTrue(
                danoSuperEfectivo >= danoNeutro,
                "El daño súper efectivo (" + danoSuperEfectivo + ") debería ser mayor o igual al daño neutro (" + danoNeutro + ")."
        );
    }
}