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
        // Usamos IDs hipotéticos del factory para los tipos
        atacanteFuego = PokemonFactory.createPokemon(4);    // Charmander (Fuego)
        defensorAgua = PokemonFactory.createPokemon(7);     // Squirtle (Agua)
        defensorNormal = PokemonFactory.createPokemon(133); // Eevee (Normal)
        defensorPlanta = PokemonFactory.createPokemon(1);   // Bulbasaur (Planta)
    }

    @Test
    public void testAtaquePocoEfectivoCausaMenosDano() {
        int hpInicialAgua = defensorAgua.getHp();
        int hpInicialNormal = defensorNormal.getHp();
        
        // 1. Daño de referencia (Neutro: Fuego vs Normal)
        atacanteFuego.useMove(1, defensorNormal); 
        int danoNeutro = hpInicialNormal - defensorNormal.getHp();
        
        // 2. Daño reducido (Poco Efectivo: Fuego vs Agua)
        atacanteFuego.useMove(1, defensorAgua);
        int danoPocoEfectivo = hpInicialAgua - defensorAgua.getHp();
        
        // 3. Aserción
        assertTrue(danoPocoEfectivo < danoNeutro, 
            "El ataque de fuego contra agua debe causar menos daño que contra un tipo neutro."); [cite: 46]
    }

    @Test
    public void testAtaqueSuperEfectivoCausaMasDano() {
        int hpInicialNormal = defensorNormal.getHp();
        int hpInicialPlanta = defensorPlanta.getHp();
        
        // 1. Daño de referencia (Neutro: Fuego vs Normal)
        atacanteFuego.useMove(1, defensorNormal); 
        int danoNeutro = hpInicialNormal - defensorNormal.getHp();
        
        // 2. Daño amplificado (Super Efectivo: Fuego vs Planta)
        atacanteFuego.useMove(1, defensorPlanta);
        int danoSuperEfectivo = hpInicialPlanta - defensorPlanta.getHp();
        
        // 3. Aserción
        assertTrue(danoSuperEfectivo > danoNeutro, 
            "El ataque de fuego contra planta debe causar más daño que un ataque neutro."); [cite: 46]
    }
}